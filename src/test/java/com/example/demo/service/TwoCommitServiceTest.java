package com.example.demo.service;

import com.example.demo.model.Employee;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TwoCommitServiceTest {

    @Autowired
    private MagicService magicService;

    @Test
    void shouldInsertEmployee_inOracleDatabase(){
        Employee employee = Employee.builder().job("ASPIRING DEVELOPER").userId(1L).salary(100).build();
        magicService.saveEmployeeInOracleDatabase(employee);
    }
}
