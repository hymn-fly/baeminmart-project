package com.example.springproject.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.UUID;

import static com.google.common.base.Preconditions.checkArgument;

@Getter
@EqualsAndHashCode(of = "id")
public class OrderItem {
    /* 아이템 아이디 */
    private final UUID id;
    /* 주문 아이디 */
    private final UUID orderId;

    /* 상품 아이디 */
    private final UUID productId;

    /* 상품 갯수 */
    private final long productCount;

    public OrderItem(UUID id, UUID orderId, UUID productId, long productCount) {
        checkArgument(id != null, "id 는 null 일 수 없습니다.");
        checkArgument(orderId != null, "주문 id 는 null 일 수 없습니다.");
        checkArgument(productId!= null, "상품 id 는 null 일 수 없습니다.");
        checkArgument(productCount > 0, "상품 갯수는 0 개보다 커야 합니다.");

        this.id = id;
        this.orderId = orderId;
        this.productId = productId;
        this.productCount = productCount;
    }
}
