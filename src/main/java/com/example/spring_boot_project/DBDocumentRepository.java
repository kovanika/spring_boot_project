package com.example.spring_boot_project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Component;

import javax.naming.OperationNotSupportedException;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.DriverManager;
import java.util.List;

@Component
public class DBDocumentRepository implements DocumentRepository{

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public DBDocumentRepository(NamedParameterJdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public String add(FileEntity file) throws OperationNotSupportedException {
        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("name", file.getName())
                .addValue("data", file.getFile());
       return jdbcTemplate.queryForObject("INSERT INTO FILE VALUES (:name, :data)", namedParameters, String.class);
    }

    @Override
    public void update(FileEntity updatedFile ) {
        return jdbcTemplate.update("UPDATE file SET name=?, data=? WHERE id=?", updatedFile.getOriginalName(), updatedFile.getFile(), id );
    }

    @Override
    public void remove(FileEntity file) {
        jdbcTemplate.update("DELETE FROM file WHERE id=?", id);
    }

    @Override
    public List<FileEntity> queryAll() throws IOException {
        return jdbcTemplate.query("SELECT * FROM file", new BeanPropertyRowMapper<>(File.class));
    }

    @Override
    public FileEntity query(String path) throws IOException {
        return jdbcTemplate.query("SELECT * FROM file WHERE id=?", id);
    }


}
