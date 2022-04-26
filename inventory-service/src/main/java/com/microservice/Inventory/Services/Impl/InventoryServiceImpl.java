package com.microservice.Inventory.Services.Impl;

import com.microservice.Inventory.Entities.Inventory;
import com.microservice.Inventory.Repositories.InventoryRepository;
import com.microservice.Inventory.Services.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class InventoryServiceImpl implements InventoryService {

    private final InventoryRepository inventoryRepository;

    @Override
    public Long isInStock(String sku) {
        Inventory supply = inventoryRepository.findBySku(sku)
                .orElseThrow(()-> new RuntimeException(String.format("%s is not found",sku)));
        return supply.getStock();
    }

    @Override
    public Inventory importWareHouse(String sku, Long stock) {
        Inventory result = null;
        if(this.isAvaiable(sku)){
            result = inventoryRepository.findBySku(sku).get();
            result.setStock(stock);
            inventoryRepository.save(result);
        }else{
            result = new Inventory();
            result.setSku(sku);
            result.setStock(stock);
            inventoryRepository.save(result);
        }
        return result;
    }

    @Override
    public boolean isAvaiable(String sku) {
        return inventoryRepository.findBySku(sku).isPresent();
    }
}
