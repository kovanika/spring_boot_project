package com.example.spring_boot_project;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.naming.OperationNotSupportedException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.Callable;

@Component
public class DBDocumentRepository implements DocumentRepository{
    private String PATH;

    @Autowired DBManager dbManager;

    public DBDocumentRepository(DirectoryProperties directoryProperties){
        PATH = directoryProperties.path();
    }
    @Override
    public String add(FileEntity file) throws OperationNotSupportedException, SQLException, IOException {
        if(Files.exists(Paths.get(PATH, file.getName()))){
            throw new FileIsAlreadyExistException("Файл с таким именем уже существует");
        }

        Files.write(Paths.get(PATH, file.getName()), file.getFile());

        dbManager.update("INSERT INTO file (name_file) VALUES (?);", new Object[]{file.getName()});

        return file.getName();
    }

    @Override
    public void update(FileEntity file) throws SQLException, IOException {
//        boolean exist = false;
//        if(!dbManager.query(this::map, "SELECT * FROM  file WHERE name_file = ?;", new Object[]{file.getName()}).isEmpty()){
//            exist = true;
//        }
//        if(!exist){
//            throw  new FileNotFoundException("Файл не найден");
//        }

        Path path = Paths.get(PATH, file.getName());

        if(!Files.exists(path)){
            throw  new FileNotFoundException("Файл не найден");
        }
        Files.write(path, file.getFile());
    }

    @Override
    public void remove(FileEntity file) throws SQLException, IOException {
        dbManager.update("DELETE FROM file WHERE name_file = ?;", new Object[]{file.getName()});

        Files.deleteIfExists(Paths.get(PATH, file.getName()));
    }

    @Override
    public List<FileEntity> queryAll() throws IOException, SQLException {
        List<FileEntity> result = dbManager.query(this::map, "SELECT * FROM file;", new Object[]{});

        return result;
    }

    @Override
    public FileEntity query(FileEntity fileEntity) {
        try{
            List<FileEntity> result = dbManager.query(this::map,
                    "SELECT * FROM file WHERE name_file = ?;",
                    new Object[]{fileEntity.getName()});

            if(result == null || result.isEmpty()){
                throw new FileNotFoundException("Файл не найден");
            }

            return result.get(0);
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public List<FileEntity> map(ResultSet resultSet) {
        List<FileEntity> result = new ArrayList<>();

        try{
            while(resultSet.next()){
                FileEntity fileEntity = new FileEntity();
                fileEntity.setName(resultSet.getString("name_file"));
                Path path = Paths.get(PATH, fileEntity.getName());
                if(!Files.exists(path)){
                    throw  new FileNotFoundException("Файл не найден");
                }
                fileEntity.setFile(Files.readAllBytes(path));
                result.add(fileEntity);
            }
        }
        catch (IOException | SQLException e){
            throw new RuntimeException(e);
        }
        return result;
    }
}
