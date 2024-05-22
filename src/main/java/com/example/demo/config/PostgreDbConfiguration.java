package com.example.demo.config;

import com.atomikos.jdbc.nonxa.AtomikosNonXADataSourceBean;
import com.example.demo.config.datasources.PostgresDataSourceDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import java.util.HashMap;

@Configuration
@EnableJpaRepositories(basePackages = "com.example.demo.repository.postgres",
        entityManagerFactoryRef = "postgresEntityManager",
        transactionManagerRef = "transactionManager"
)
@RequiredArgsConstructor
public class PostgreDbConfiguration {

    private final PostgresDataSourceDTO postgresDataSourceDTO;

    @Bean(initMethod = "init", destroyMethod = "close")
    public AtomikosNonXADataSourceBean postgresDataSource(){
        AtomikosNonXADataSourceBean atomikosNonXADataSourceBean = new AtomikosNonXADataSourceBean();

        atomikosNonXADataSourceBean.setUser(postgresDataSourceDTO.username());
        atomikosNonXADataSourceBean.setPassword(postgresDataSourceDTO.password());
        atomikosNonXADataSourceBean.setDriverClassName(postgresDataSourceDTO.driverClassName());
        atomikosNonXADataSourceBean.setUrl(postgresDataSourceDTO.url());
        atomikosNonXADataSourceBean.setUniqueResourceName("postgres");

        return atomikosNonXADataSourceBean;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean postgresEntityManager() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();

        em.setDataSource(postgresDataSource());
        em.setPackagesToScan(new String[] {"com.example.demo.repository.postgres"});
        em.setJtaDataSource(postgresDataSource());

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);

        HashMap<String, Object> properties = new HashMap<>();
        properties.put("hibernate.hbm2ddl.auto", "none");
        properties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        em.setJpaPropertyMap(properties);

        return em;
    }
}
