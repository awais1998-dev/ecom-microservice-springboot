package com.tech.orderservice.service;

import com.tech.orderservice.model.Orders;

import java.util.List;

public interface OrderService {

    public List<Orders> getAllOrders();
    public String placeOrder(Orders orders);
}
