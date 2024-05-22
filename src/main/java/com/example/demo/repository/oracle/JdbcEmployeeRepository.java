package com.example.demo.repository.oracle;

import com.example.demo.model.Employee;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;
import java.util.OptionalInt;

@RequiredArgsConstructor
@Repository
public class JdbcEmployeeRepository {
    private final String SCHEMA = "XA_ORA";
    private final JdbcTemplate oracleJdbcTemplate;

    public List<Employee> getAllEmployees(){
        String query = """
                SELECT * FROM ${schema}.employee
                """.replace("${schema}", SCHEMA);
        return oracleJdbcTemplate.query(query, (rs, rowNum) -> new Employee(rs.getLong("id"), rs.getString("job"), rs.getInt("salary"), rs.getLong("user_id")));
    }

    public Employee save(Employee employee) {
        String query = "INSERT INTO ${schema}.employee (job, salary, user_id) VALUES (?, ?, ?)"
                .replace("${schema}", SCHEMA);

        return oracleJdbcTemplate.query(query, List.of(employee.job(), employee.salary(), employee.userId()).toArray(), rs -> {
           Long id = rs.getLong("ID");
           return employee.toBuilder().id(id).build();
        });
    }

}
