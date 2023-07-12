package com.example.spring_boot_project;

import javax.naming.OperationNotSupportedException;
import java.io.IOException;
import java.util.List;


public interface DocumentRepository {
    String add(FileEntity file) throws IOException, OperationNotSupportedException;
    void update(FileEntity file);
    void remove(FileEntity file);
    List<FileEntity> queryAll() throws IOException;
    FileEntity query(String path) throws IOException;

    FileEntity get(String url) throws IOException;
}