package com.example.spring_boot_project;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "database")
public record AppProperties(String url, String userName, String password) {
}
