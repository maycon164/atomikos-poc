package com.example.demo.repository.oracle;

import com.example.demo.model.Employee;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


import java.util.List;

@RequiredArgsConstructor
@Repository
public class JdbcEmployeeRepository {
    private final static String SCHEMA = "XA_ORA";
    private final JdbcTemplate oracleJdbcTemplate;

    public List<Employee> getAllEmployees(){
        String query = """
                SELECT * FROM ${schema}.employee
                """.replace("${schema}", SCHEMA);
        return oracleJdbcTemplate.query(query, (rs, rowNum) -> new Employee(rs.getLong("id"), rs.getString("job"), rs.getInt("salary"), rs.getLong("user_id")));
    }

    public Employee save(Employee employee) {
        String query = "INSERT INTO ${schema}.employee (id, job, salary, user_id) VALUES (?, ?, ?, ?)"
                .replace("${schema}", SCHEMA);

        Long primaryKey = getPrimaryKey();

        oracleJdbcTemplate.update(query, List.of(primaryKey, employee.job(), employee.salary(), employee.userId()).toArray());

        return employee.toBuilder().id(primaryKey).build();
    }

    public Employee save(Employee employee, Boolean flag) {
        String query = "INSERT INTO ${schema}.employee (job, salary, user_id) VALUES (?, ?, ?)"
                .replace("${schema}", SCHEMA);


        oracleJdbcTemplate.update(query, List.of(employee.job(), employee.salary(), employee.userId()).toArray());

        return employee.toBuilder().id(1l).build();
    }

    private Long getPrimaryKey() {
        String sql = "SELECT ${schema}.SEQ_ID_EMPLOYEE.NEXTVAL FROM DUAL".replace("${schema}", SCHEMA);
        return oracleJdbcTemplate.queryForObject(sql, Long.class);
    }
}
