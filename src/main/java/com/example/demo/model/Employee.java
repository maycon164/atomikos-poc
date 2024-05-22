package com.example.demo.model;

import lombok.Builder;

@Builder(toBuilder = true)
public record Employee(
        Long id,
        String job,
        Integer salary,
        Long userId
) {
}
