package com.example.demo.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Configuration
@RequiredArgsConstructor
public class DbConfiguration {

    private final PostgresDataSourceDTO postgresDataSourceDTO;
    private final OracleDataSourceDTO oracleDataSourceDTO;

    @Bean
    @Primary
    public DataSource postgresDataSource(){
        return DataSourceBuilder.create()
                .url(postgresDataSourceDTO.url())
                .username(postgresDataSourceDTO.username())
                .password(postgresDataSourceDTO.password())
                .driverClassName(postgresDataSourceDTO.driverClassName())
                .build();
    }

    @Bean
    public DataSource oracleDataSource(){
        return DataSourceBuilder.create()
                .url(oracleDataSourceDTO.url())
                .username(oracleDataSourceDTO.username())
                .password(oracleDataSourceDTO.password())
                .driverClassName(oracleDataSourceDTO.driverClassName())
                .build();
    }

    @Bean
    public JdbcTemplate oracleJdbcTemplate(@Qualifier("oracleDataSource") DataSource oracleDataSource){
        return new JdbcTemplate(oracleDataSource);
    }
}
