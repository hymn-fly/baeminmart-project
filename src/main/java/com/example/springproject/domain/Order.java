package com.example.springproject.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

import static com.google.common.base.Preconditions.checkArgument;

@Getter
@EqualsAndHashCode(of="id")
public class Order {
    private final UUID id;
    private OrderStatus orderStatus;
    private final LocalDateTime createdAt;
    private final String customerName;
    private final Email email;
    private final String address;

    public Order(UUID id, OrderStatus orderStatus, LocalDateTime createdAt, String customerName, Email email, String address) {
        checkArgument(id != null, "id 는 null 일 수 없습니다.");
        checkArgument(customerName != null, "고객 이름은 null 일 수 없습니다.");
        checkArgument(!customerName.isBlank(), "고객 이름은 공백일 수 없습니다.");
        checkArgument(email != null, "email 은 null 일 수 없습니다.");
        checkArgument(address != null, "주소는 null 일 수 없습니다.");
        checkArgument(!address.isBlank(), "주소는 공백일 수 없습니다.");

        this.id = id;
        this.orderStatus = orderStatus;
        this.createdAt = createdAt;
        this.customerName = customerName;
        this.email = email;
        this.address = address;
    }
}
