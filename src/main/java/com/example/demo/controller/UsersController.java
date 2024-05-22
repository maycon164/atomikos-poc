package com.example.demo.controller;

import com.example.demo.model.Employee;
import com.example.demo.service.TwoCommitService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UsersController {

    private final TwoCommitService twoCommitService;

    @GetMapping
    private String getUsers(){
        return "USUARIOS";
    }

    @GetMapping("teste")
    private String getEmployees(){
        return twoCommitService.createUserAndCreateEmployeeAssociated();
    }

    @GetMapping("teste2")
    private String saveUser(){
        return twoCommitService.saveUser();
    }
}
