package com.example.spring_boot_project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.naming.OperationNotSupportedException;
import java.io.IOException;
import java.util.List;

@Repository
@ConditionalOnProperty(prefix = "repository", name = "type", havingValue = "db", matchIfMissing = true)
public class DBDocumentRepository implements DocumentRepository {

    @Autowired
    EmailController service;
    private final JdbcTemplate jdbcTemplate;

    public DBDocumentRepository(JdbcTemplate jdbcTemplate){

        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public String add(FileEntity file) throws OperationNotSupportedException {
        var fe = jdbcTemplate.queryForObject("INSERT INTO FILE (name_file, data, email) VALUES (?, ?, ?) returning *", new Object[]{file.getOriginalName(), file.getFile(), file.getEmail()}, new FileMapper());
        String url = UrlService.encode(fe.getId());
        var fs = jdbcTemplate.update("UPDATE file SET short_url = ? WHERE id= ?", url, fe.getId());
        String email = file.getEmail();
        service.sendMessage(email, url);
        return url;
    }

    @Override
    public void update(FileEntity updatedFile) {
        jdbcTemplate.update("UPDATE file SET name= ?, data= ? WHERE id= ?", updatedFile.getOriginalName(), updatedFile.getFile(), updatedFile.getId() );
    }

    @Override
    public void remove(FileEntity file) {
        jdbcTemplate.update("DELETE FROM file WHERE id=?", file.getId());
    }

    @Override
    public List<FileEntity> queryAll() throws IOException {
        var s = jdbcTemplate.query("SELECT * FROM file", new FileMapper());
        if(s.isEmpty()){

            throw new RuntimeException("Files Not Found");

        }

        return s;
    }

    @Override
    public FileEntity query(String path) {

        var q = jdbcTemplate.queryForObject("SELECT * FROM file WHERE name= ?", new Object[]{path}, new FileMapper());

        if (q == null)
        {
            throw new RuntimeException( "File Not Found");
        }

        return q;
    }


}