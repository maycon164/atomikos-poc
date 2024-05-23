package com.example.demo.service;

import com.example.demo.model.Employee;
import com.example.demo.model.Worker;
import com.example.demo.repository.oracle.JdbcEmployeeRepository;
import com.example.demo.repository.postgres.UserEntity;
import com.example.demo.repository.postgres.UserRepository;
import com.example.demo.validator.MagicValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MagicService {

    private final UserRepository userRepository;
    private final JdbcEmployeeRepository jdbcEmployeeRepository;
    private final MagicValidator validator;

    public List<Employee> getAllEmployee(){
        userRepository.findAll();
        return jdbcEmployeeRepository.getAllEmployees();
    }

    /*
        it can use import org.springframework.transaction.annotation.Transactional;
        @org.springframework.transaction.annotation.Transactional(transactionManager = "transactionManager", rollbackFor = Exception.class);
           - for some reason I need to specify transactionManager;

        or can use Transaction from jakarta package, I don't need to specify transactionManager
        @jakarta.transaction.Transactional;
     */
    @Transactional(transactionManager = "transactionManager", rollbackFor = Exception.class)
    public Worker createUserAndCreateEmployeeAssociated(String name, String email, String job, Integer salary){
        UserEntity savedUser = saveUserInPostgresDatabase(UserEntity.builder().name(name).email(email).build());
        Employee savedEmployee = saveEmployeeInOracleDatabase(Employee.builder().job(job).salary(salary).build());
        Worker worker = new Worker(savedUser.getName(), savedUser.getEmail(), savedEmployee.job(), savedEmployee.salary());

        validator.validateWorker(worker);

        return worker;
    }


    public Employee saveEmployeeInOracleDatabase(Employee employee){
        return jdbcEmployeeRepository.save(employee);
    }

    public UserEntity saveUserInPostgresDatabase(UserEntity user){
        return userRepository.save(user);
    }

}
