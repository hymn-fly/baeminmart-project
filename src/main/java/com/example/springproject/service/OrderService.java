package com.example.springproject.service;

import com.example.springproject.controller.api.CreateOrderItemRequest;
import com.example.springproject.domain.Email;
import com.example.springproject.domain.Order;
import com.example.springproject.domain.OrderItem;
import com.example.springproject.repository.OrderItemRepository;
import com.example.springproject.repository.OrderRepository;
import lombok.val;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;

    public OrderService(OrderRepository orderRepository, OrderItemRepository orderItemRepository) {
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
    }

    public List<Order> getOrders(){
        return orderRepository.findAll();
    }

    public List<OrderItem> getOrderItems(UUID orderId) {
        return orderItemRepository.findByOrderId(orderId);
    }

    public Order createOrder(String customerName, String email, String address){
        val order = new Order(customerName, new Email(email), address);

        return orderRepository.save(order);
    }

    public void createOrderItems(List<CreateOrderItemRequest> items, UUID orderId) {
        items.forEach(item -> {
            val orderItem = new OrderItem(item.productId(), item.productCount());
            orderItem.setOrderId(orderId);
            orderItemRepository.save(orderItem);
        });
    }

    public Optional<Order> deleteOrder(UUID id){
        return orderRepository.deleteById(id);
    }
}
