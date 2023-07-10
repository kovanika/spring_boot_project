package com.example.spring_boot_project;

import org.springframework.stereotype.Component;

import javax.naming.OperationNotSupportedException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface DocumentRepository {
    String add(FileEntity file) throws IOException, OperationNotSupportedException, SQLException;
    void update(FileEntity file) throws SQLException, IOException;
    void remove(FileEntity file) throws SQLException, IOException;
    List<FileEntity> queryAll() throws IOException, SQLException;
    FileEntity query(FileEntity fileEntity) throws IOException, SQLException;
}
