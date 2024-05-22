package com.example.demo.service;

import com.example.demo.model.Employee;
import com.example.demo.model.Worker;
import com.example.demo.repository.oracle.JdbcEmployeeRepository;
import com.example.demo.repository.postgres.UserEntity;
import com.example.demo.repository.postgres.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
    public Worker createUserAndCreateEmployeeAssociated(){
        UserEntity user = saveUserInPostgresDatabase();
        Employee employee = saveEmployeeInOracleDatabase(user.getId());
        Worker worker = new Worker(user.getName(), user.getEmail(), employee.job(), employee.salary());
        if (user.getEmail().contains("@gmail.com")) throw new RuntimeException("Algo deu errado");
        return worker;
    }


    public Employee saveEmployeeInOracleDatabase(Long userId){

        Employee employee = Employee.builder().salary(100).job("TRAINEE DEVELOPER") .userId(userId) .build();
        var savedEmployee = jdbcEmployeeRepository.save(employee);
        return savedEmployee;
    }

    public UserEntity saveUserInPostgresDatabase(){
        UserEntity user = UserEntity.builder().name("Maycon").email("maycon@gmail.com").build();
        var savedUser = userRepository.save(user);
        return savedUser;
    }

}
