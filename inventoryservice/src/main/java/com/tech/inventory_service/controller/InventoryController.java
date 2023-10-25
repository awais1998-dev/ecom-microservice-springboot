package com.tech.inventory_service.controller;

import com.tech.inventory_service.dto.InventoryResponse;
import com.tech.inventory_service.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    @GetMapping
    public ResponseEntity<List<InventoryResponse>> isInStock(@RequestParam List<String> skuCode){
       return new ResponseEntity<List<InventoryResponse>>(inventoryService.isInStock(skuCode), HttpStatus.OK);
    }
}
