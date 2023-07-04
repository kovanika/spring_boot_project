package com.example.spring_boot_project;

import org.springframework.web.multipart.MultipartFile;

public class File {
    private String name;
    private String email;
    private MultipartFile file;

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }
}
