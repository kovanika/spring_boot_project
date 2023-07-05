package com.example.spring_boot_project;

import java.util.List;

public interface DocumentRepository {
    void add(FileEntity file);
    void update(FileEntity file);
    void remove(FileEntity file);
    List<FileEntity> query(FileSpecification fileSpecification);
}
