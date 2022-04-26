package com.microservice.Inventory.Services;

import com.microservice.Inventory.Entities.Inventory;

import java.util.Optional;

public interface InventoryService {
    Long isInStock(String sku);
    Inventory importWareHouse(String sku, Long stock);
    boolean isAvaiable(String sku);
}
