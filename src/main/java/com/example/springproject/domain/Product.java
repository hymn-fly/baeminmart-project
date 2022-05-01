package com.example.springproject.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.UUID;

import static com.google.common.base.Preconditions.checkArgument;

@Getter
@EqualsAndHashCode(of = "id")
public class Product {
    /* 상품 아이디 */
    private final UUID id;

    /* 상품 이름 */
    private final String name;

    /* 상품 가격 */
    private final long price;

    public Product(UUID id, String name, long price) {
        checkArgument(id != null, "id는 null 일 수 없습니다.");
        checkArgument(name != null, "name 은 null 일 수 없습니다.");
        checkArgument(price > 0 , "가격은 0보다 커야 합니다.");

        this.id = id;
        this.name = name;
        this.price = price;
    }


}
