package com.example.demo.service;

import com.example.demo.model.Employee;
import com.example.demo.repository.oracle.JdbcEmployeeRepository;
import com.example.demo.repository.postgres.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TwoCommitService {

    private final UserRepository userRepository;
    private final JdbcEmployeeRepository jdbcEmployeeRepository;

    public List<Employee> getAllEmployee(){
        return jdbcEmployeeRepository.getAllEmployees();
    }

}
