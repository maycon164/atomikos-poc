package com.example.demo.controller;

import com.example.demo.model.Employee;
import com.example.demo.model.Worker;
import com.example.demo.repository.postgres.UserEntity;
import com.example.demo.service.TwoCommitService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/teste")
@RequiredArgsConstructor
public class UsersController {

    private final TwoCommitService twoCommitService;

    @GetMapping
    private String getUsers(){
        return "USUARIOS";
    }

    @GetMapping("saveOracle")
    private Employee getEmployees(){
        return twoCommitService.saveEmployeeInOracleDatabase(1L);
    }

    @GetMapping("savePostgres")
    private UserEntity saveUser(){
        return twoCommitService.saveUserInPostgresDatabase();
    }

    @GetMapping("saveBoth")
    private Worker saveInBothDatabases() {
        return twoCommitService.createUserAndCreateEmployeeAssociated();
    }
}
