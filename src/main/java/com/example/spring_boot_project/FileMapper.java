package com.example.spring_boot_project;

import com.example.spring_boot_project.FileEntity;
import javax.swing.tree.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FileMapper implements RowMapper<FileEntity> {

    @Override
    public FileEntity mapRow(ResultSet resultSet, int i) throws SQLException{
        FileEntity fileEntity = new FileEntity();

        fileEntity.setId(resultSet.getInt("id"));
        fileEntity.setOriginalName(resultSet.getString("name"));
        fileEntity.setFile(resultSet.getBytes("data"));

        return fileEntity;
    }
}
