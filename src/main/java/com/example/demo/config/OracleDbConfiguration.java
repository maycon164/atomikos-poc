package com.example.demo.config;

import com.atomikos.jdbc.nonxa.AtomikosNonXADataSourceBean;
import com.example.demo.config.datasources.OracleDataSourceDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.jta.JtaTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

@Configuration
@RequiredArgsConstructor
public class OracleDbConfiguration {

    private final OracleDataSourceDTO oracleDataSourceDTO;

    @Bean
    public AtomikosNonXADataSourceBean oracleDataSource(){
        AtomikosNonXADataSourceBean atomikosNonXADataSourceBean = new AtomikosNonXADataSourceBean();

        atomikosNonXADataSourceBean.setUser(oracleDataSourceDTO.username());
        atomikosNonXADataSourceBean.setPassword(oracleDataSourceDTO.password());
        atomikosNonXADataSourceBean.setDriverClassName(oracleDataSourceDTO.driverClassName());
        atomikosNonXADataSourceBean.setUrl(oracleDataSourceDTO.url());
        atomikosNonXADataSourceBean.setUniqueResourceName("oracle");

        // enable transaction to jdbc template
        atomikosNonXADataSourceBean.setLocalTransactionMode(true);

        return atomikosNonXADataSourceBean;
    }

    @Bean
    public JdbcTemplate oracleJdbcTemplate(@Qualifier("oracleDataSource") AtomikosNonXADataSourceBean oracleDataSource){
        return new JdbcTemplate(oracleDataSource);
    }
}
