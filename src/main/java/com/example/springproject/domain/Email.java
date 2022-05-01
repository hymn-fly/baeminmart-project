package com.example.springproject.domain;

import lombok.Getter;

import static com.google.common.base.Preconditions.checkArgument;

@Getter
public class Email {
    private final String email;

    public Email(String email) {
        checkArgument(email != null, "이메일은 null 일 수 없습니다.");
        checkArgument(!email.isBlank(), "이메일은 공백일 수 없습니다.");
        this.email = email;
    }
}
