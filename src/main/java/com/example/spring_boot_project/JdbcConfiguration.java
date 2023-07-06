package com.example.spring_boot_project;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
public class JdbcConfiguration {
    @Bean
    public DataSource dataSource()
    {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();

        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl("jdbc:postgresql://10.3.0.70:5432/practice_2023_dropmefiles");
        dataSource.setUsername("students");
        dataSource.setPassword("9V1$2x37c9V*b0");

        return dataSource;
    }
}
