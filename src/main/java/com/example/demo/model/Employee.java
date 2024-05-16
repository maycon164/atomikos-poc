package com.example.demo.model;

public record Employee(
        Long id,
        String job,
        Double salary,
        Long userId
) {
}
