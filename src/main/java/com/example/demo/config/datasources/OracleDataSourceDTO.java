package com.example.demo.config.datasources;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "spring.datasource.oracle")
public record OracleDataSourceDTO(String url, String username, String password, String driverClassName) { }
