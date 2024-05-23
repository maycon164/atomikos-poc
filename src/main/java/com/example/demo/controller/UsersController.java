package com.example.demo.controller;

import com.example.demo.model.Employee;
import com.example.demo.model.Worker;
import com.example.demo.repository.postgres.UserEntity;
import com.example.demo.service.MagicService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/teste")
@RequiredArgsConstructor
public class UsersController {

    private final MagicService twoCommitService;

    private final Employee employee = new Employee(null, "DEVELOPER", 0, 1L);
    private final UserEntity user = new UserEntity(null, "Fulano Silva", "fulano@gmail.com");


    @GetMapping
    private String getUsers(){
        return "USUARIOS";
    }

    @GetMapping("saveOracle")
    private Employee getEmployees(){
        return twoCommitService.saveEmployeeInOracleDatabase(employee);
    }

    @GetMapping("savePostgres")
    private UserEntity saveUser(){
        return twoCommitService.saveUserInPostgresDatabase(user);
    }

    @GetMapping("saveBoth")
    private Worker saveInBothDatabases() {
        return twoCommitService.createUserAndCreateEmployeeAssociated(user.getName(), user.getEmail(), employee.job(), employee.salary());
    }
}
