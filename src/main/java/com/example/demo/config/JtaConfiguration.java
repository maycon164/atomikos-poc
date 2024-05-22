package com.example.demo.config;

import com.atomikos.icatch.jta.UserTransactionImp;
import com.atomikos.icatch.jta.UserTransactionManager;

import jakarta.transaction.SystemException;
import jakarta.transaction.UserTransaction;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.jta.JtaTransactionManager;


@Configuration
@EnableTransactionManagement
public class JtaConfiguration {

    @Bean(initMethod = "init", destroyMethod = "close")
    public UserTransactionManager userTransactionManager() throws SystemException {

        UserTransactionManager userTransactionManager = new UserTransactionManager();
        userTransactionManager.setTransactionTimeout(300000);
        userTransactionManager.setForceShutdown(true);
        return userTransactionManager;
    }

    @Bean
    @Primary
    public JtaTransactionManager transactionManager() throws SystemException {
        JtaTransactionManager jtaTransactionManager = new JtaTransactionManager();
        jtaTransactionManager.setTransactionManager(userTransactionManager());
        jtaTransactionManager.setUserTransaction(userTransactionManager());
        return jtaTransactionManager;
    }

    @Bean
    public UserTransaction userTransaction() throws SystemException {
        UserTransaction userTransaction = new UserTransactionImp();
        userTransaction.setTransactionTimeout(300000);
        return userTransaction;
    }
}
