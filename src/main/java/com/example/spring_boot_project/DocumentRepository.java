package com.example.spring_boot_project;

import java.io.IOException;
import java.util.List;

public interface DocumentRepository {
    void add(FileEntity file) throws IOException;
    void update(FileEntity file);
    void remove(FileEntity file);
    List<FileEntity> query() throws IOException;
}
