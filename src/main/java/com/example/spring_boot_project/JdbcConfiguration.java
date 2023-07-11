package com.example.spring_boot_project;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
public class JdbcConfiguration {
    private final AppProperties properties;

    public JdbcConfiguration(AppProperties properties) {
        this.properties = properties;
    }

    @Bean
    public DataSource dataSource()
    {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();

        dataSource.setDriverClassName(properties.driverClassName());
        dataSource.setUrl(properties.url());
        dataSource.setUsername(properties.userName());
        dataSource.setPassword(properties.password());

        return dataSource;
    }
}