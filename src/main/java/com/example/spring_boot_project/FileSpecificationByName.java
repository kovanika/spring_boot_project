package com.example.spring_boot_project;

public class FileSpecificationByName implements FileSpecification{
    private String name;
    public FileSpecificationByName(String name){
        this.name = name;
    }

    @Override
    public String toSqlCauses() {
        return String.format("file_name = '%s'", name);
    }
}