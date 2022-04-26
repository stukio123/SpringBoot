package com.microservice.orderservices.Clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Map;

@FeignClient(name="inventory-service")
public interface InventoryClient {
    @GetMapping("api/v1/inventory/{sku}")
    Long checkStock(@PathVariable String sku);
}
