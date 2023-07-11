package com.example.spring_boot_project;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FileMapper implements RowMapper<FileEntity> {

    @Override
    public FileEntity mapRow(ResultSet resultSet, int i) throws SQLException{
        FileEntity fileEntity = new FileEntity();

        fileEntity.setId(resultSet.getInt("id"));
        fileEntity.setOriginalName(resultSet.getString("name_file"));
        fileEntity.setFile(resultSet.getBytes("data"));
        fileEntity.setEmail(resultSet.getString("email"));
        return fileEntity;
    }

}