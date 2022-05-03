package com.example.springproject.controller.api;

import com.example.springproject.domain.OrderItem;

import java.util.List;

public record CreateOrderRequest(String name, String email, String address, List<CreateOrderItemRequest> items) {
}
