package com.example.spring_boot_project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Component;

import javax.naming.OperationNotSupportedException;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.DriverManager;
import java.util.List;

@Component
public class DBDocumentRepository implements DocumentRepository{


    public DBDocumentRepository(JdbcTemplate jdbcTemplate){
    }

    @Override
    public String add(FileEntity file) throws OperationNotSupportedException {
        throw new OperationNotSupportedException();
    }

    @Override
    public void update(FileEntity file) {

    }

    @Override
    public void remove(FileEntity file) {

    }

    @Override
    public List<FileEntity> queryAll() throws IOException {
        return null;
    }

    @Override
    public FileEntity query(String path) throws IOException {
        return null;
    }


}
