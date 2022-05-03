package com.example.springproject.controller.api;

import java.util.UUID;

public record CreateOrderItemRequest(UUID productId, long productCount) {
}
