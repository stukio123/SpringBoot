package com.microservice.orderservices.Controllers;

import com.microservice.orderservices.Clients.InventoryClient;
import com.microservice.orderservices.DTOs.OrderDTO;
import com.microservice.orderservices.Entities.Item;
import com.microservice.orderservices.Entities.Order;
import com.microservice.orderservices.Services.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JCircuitBreaker;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JCircuitBreakerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.function.Supplier;
import java.util.stream.LongStream;
import java.util.stream.Stream;

@RestController
@Slf4j
@RequestMapping("api/v1/order")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    private final InventoryClient inventoryClient;
    private final Resilience4JCircuitBreakerFactory circuitBreakerFactory;
    private final ExecutorService traceableExecutorService;

    @PostMapping
    public String placeOrder(@RequestBody OrderDTO dto){
        circuitBreakerFactory.configureExecutorService(traceableExecutorService);
        Resilience4JCircuitBreaker breaker = circuitBreakerFactory.create("inventory");
        Supplier<Map<String,Long>> supplier = () -> {
            Map<String,Long> result = new HashMap<>();
            dto.getItems().forEach(product ->
                    result.put(product.getSku(), inventoryClient.checkStock(product.getSku()))
            );
            return result;
        };
        Map<String,Long> inStock = breaker.run(supplier, this::hasError);

        for(Item product : dto.getItems()){
            if(inStock != null && inStock.get(product.getSku()) > product.getQuantity()){
                Order order = new Order();
                order.setOrderNumber(UUID.randomUUID().toString());
                order.setItems(dto.getItems());
                orderService.createOrder(order);
                log.info("Order Created !!");
                return "Order Created";
            }else{
                return String.format("%s is out of Stock",product.getSku());
            }
        }
        return "Something Wrong !!!";
    }

    public Map<String,Long> hasError(Throwable t){
        log.error(t.getLocalizedMessage());
        return null;
    }

}
