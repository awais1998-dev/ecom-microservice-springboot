package com.tech.orderservice.controller;

import com.tech.orderservice.model.Orders;
import com.tech.orderservice.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping
    public ResponseEntity<List<Orders>> getAllOrders(){
        return new ResponseEntity<List<Orders>>(orderService.getAllOrders(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> placeOrder(@RequestBody Orders orders){
        System.out.println("orders "+ orders);
        return new ResponseEntity<String>(orderService.placeOrder(orders), HttpStatus.CREATED);
    }
}
