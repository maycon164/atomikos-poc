package com.example.demo;

import com.example.demo.config.OracleDataSourceDTO;
import com.example.demo.config.PostgresDataSourceDTO;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({
		PostgresDataSourceDTO.class,
		OracleDataSourceDTO.class
})
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}
