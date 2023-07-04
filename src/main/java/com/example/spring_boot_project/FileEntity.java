package com.example.spring_boot_project;

import org.springframework.web.multipart.MultipartFile;

public class FileEntity {
    public FileEntity(){

    }
    private String name;
    private String email;
    private MultipartFile file;

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }
}
