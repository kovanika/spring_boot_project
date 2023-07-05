package com.example.spring_boot_project;

import org.springframework.web.multipart.MultipartFile;

public class FileEntity {
    public FileEntity(){

    }
    private String name;
    private String originalName;
    private String email;

    public String getOriginalName() {
        return originalName;
    }

    public void setOriginalName(String originalName) {
        this.originalName = originalName;
    }

    private byte[] file;

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

    public byte[] getFile() {
        return file;
    }

    public void setFile(byte[] file) {
        this.file = file;
    }
}
