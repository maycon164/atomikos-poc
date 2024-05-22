package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class Worker {
    private String name;
    private String email;
    private String jobPosition;
    private Integer salary;
}
