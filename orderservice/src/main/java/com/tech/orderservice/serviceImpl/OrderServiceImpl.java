package com.tech.orderservice.serviceImpl;

import com.tech.orderservice.dto.InventoryResponse;
import com.tech.orderservice.model.OrderItems;
import com.tech.orderservice.model.Orders;
import com.tech.orderservice.repository.OrderRepository;
import com.tech.orderservice.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private WebClient.Builder webClient;

    @Override
    public List<Orders> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public String placeOrder(Orders order) {
        order.setOrderNumber(UUID.randomUUID().toString());
        System.out.println(order);

        List<String> skuCodes = order.getOrderItemsList().stream()
                .map(OrderItems::getSkuCode).toList();

        InventoryResponse[] inventoryResponseArray = webClient.build().get()
                .uri("http://inventoryservice/api/inventory",
                        uriBuilder -> uriBuilder.queryParam("skuCode", skuCodes).build())
                .retrieve()
                .bodyToMono(InventoryResponse[].class)
                .block();

        boolean inStock = Arrays.stream(inventoryResponseArray).allMatch(InventoryResponse::getIsInStock);

        if(inStock){
            orderRepository.save(order);
        }
        else {
            throw new RuntimeException("Product is not in stock, please try again later.");
        }
        return "Orders Placed Successfully";
    }
}
