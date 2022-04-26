package com.microservice.productsservice.Clients;


import com.microservice.productsservice.DTOs.InventoryDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name="inventory-service")
public interface InventoryClient {
    @PostMapping("api/v1/inventory")
    Boolean importWareHouse(@RequestBody InventoryDTO dto);
}
