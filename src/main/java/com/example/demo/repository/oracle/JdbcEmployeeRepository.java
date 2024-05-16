package com.example.demo.repository.oracle;

import com.example.demo.model.Employee;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class JdbcEmployeeRepository {
    private final String SCHEMA = "XA_ORA";
    private final JdbcTemplate oracleJdbcTemplate;

    public List<Employee> getAllEmployees(){
        String query = """
                SELECT * FROM ${schema}.employee
                """.replace("${schema}", SCHEMA);
        return oracleJdbcTemplate.query(query, (rs, rowNum) -> new Employee(rs.getLong("id"), rs.getString("job"), rs.getDouble("salary"), rs.getLong("user_id")));
    }
}
