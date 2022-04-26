package com.microservice.orderservices.Services.Impl;

import com.microservice.orderservices.Entities.Order;
import com.microservice.orderservices.Repositories.OrderReposity;
import com.microservice.orderservices.Services.OrderService;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderReposity orderReposity;

    public OrderServiceImpl(OrderReposity orderReposity) {
        this.orderReposity = orderReposity;
    }

    @Override
    public void createOrder(Order order) {
        orderReposity.save(order);
    }
}
