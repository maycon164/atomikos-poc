package com.example.demo.service;

import com.example.demo.model.Employee;
import com.example.demo.repository.oracle.JdbcEmployeeRepository;
import com.example.demo.repository.postgres.UserEntity;
import com.example.demo.repository.postgres.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TwoCommitService {

    private final UserRepository userRepository;
    private final JdbcEmployeeRepository jdbcEmployeeRepository;

    public List<Employee> getAllEmployee(){
        userRepository.findAll();
        return jdbcEmployeeRepository.getAllEmployees();
    }

    @Transactional
    public String createUserAndCreateEmployeeAssociated(){
        UserEntity user = UserEntity.builder().name("Maycon").email("maycon@gmail.com").build();
        var savedUser = userRepository.save(user);

        Employee employee = Employee.builder().salary(100).job("TRAINEE DEVELOPER") .userId(savedUser.getId()) .build();
        jdbcEmployeeRepository.save(employee);

        return "Saved Succesfully";
    }


    public String saveUser(){

        Employee employee = Employee.builder().salary(300).job("TECH LEAD") .userId(10L).build();
        jdbcEmployeeRepository.save(employee);

        return "User Created";
    }

}
