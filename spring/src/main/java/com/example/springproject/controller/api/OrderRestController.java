package com.example.springproject.controller.api;

import com.example.springproject.domain.Email;
import com.example.springproject.domain.Order;
import com.example.springproject.domain.OrderItem;
import com.example.springproject.service.OrderService;
import lombok.val;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class OrderRestController {
    private final OrderService orderService;

    public OrderRestController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/orders")
    public Order createNewOrder(@RequestBody CreateOrderRequest createOrderRequest){

        val order = orderService.createOrder(createOrderRequest.name(), createOrderRequest.email(), createOrderRequest.address());

        orderService.createOrderItems(createOrderRequest.items(), order.getId());

        return order;
    }

}
