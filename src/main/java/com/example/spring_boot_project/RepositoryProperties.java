package com.example.spring_boot_project;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "repository")
public record RepositoryProperties(String type) {
}
