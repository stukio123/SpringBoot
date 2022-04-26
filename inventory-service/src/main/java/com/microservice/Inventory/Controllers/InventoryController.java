package com.microservice.Inventory.Controllers;

import com.microservice.Inventory.DTOs.InventoryDTO;
import com.microservice.Inventory.Entities.Inventory;
import com.microservice.Inventory.Services.InventoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("api/v1/inventory")
@RequiredArgsConstructor
public class InventoryController {
    private final InventoryService inventoryService;

    @GetMapping("/{sku}")
    public Long isInStock(@PathVariable String sku){
        return inventoryService.isInStock(sku);
    }

    @PostMapping()
    public ResponseEntity importWareHouse(@RequestBody InventoryDTO dto){
        StringBuilder result = new StringBuilder();
        Inventory supply = inventoryService.importWareHouse(dto.getSku(),dto.getStock());
        if(null != supply){
            result.append(String.format("%s is imported successful",dto.getSku()));
            return ResponseEntity.ok(result);
        }else{
            result.append(String.format("%s could not found !",dto.getSku()));
            return new ResponseEntity(result, HttpStatus.NOT_FOUND);
        }
    }
}
