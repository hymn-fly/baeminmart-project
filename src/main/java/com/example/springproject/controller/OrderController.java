package com.example.springproject.controller;

import com.example.springproject.service.OrderService;
import lombok.val;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@Controller
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/orders")
    public String orders(Model model){
        val orders = orderService.getOrders();

        model.addAttribute("orders", orders);

        return "order-list";
    }

    @GetMapping("/orders/{orderId}")
    public String orderDetail(@PathVariable UUID orderId, Model model){
        val orderItems = orderService.getOrderItems(orderId);

        model.addAttribute("orderItems", orderItems);

        return "order-detail";
    }
}
