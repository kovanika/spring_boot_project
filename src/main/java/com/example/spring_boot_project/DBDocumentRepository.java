package com.example.spring_boot_project;

import javax.naming.OperationNotSupportedException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class DBDocumentRepository implements DocumentRepository{
    private String url = "jdbc:postgresql://10.3.0.70:5432/practice_2023_dropmefiles";
    private Properties properties;

    private String PATH = "/tmp";

    public DBDocumentRepository(){
        properties = new Properties();
        properties.setProperty("user", "students");
        properties.setProperty("password", "9V1$2x37c9V*b0");
    }
    @Override
    public String add(FileEntity file) throws OperationNotSupportedException, SQLException, IOException {
        if(Files.exists(Paths.get(PATH, file.getName()))){
            throw new FileIsAlreadyExistException("Файл с таким именем уже существует");
        }
        Connection conn = DriverManager.getConnection(url, properties);
        PreparedStatement statement = conn.prepareStatement("INSERT INTO file (name_file) VALUES (?);");
        statement.setString(1, file.getName());
        statement.executeUpdate();

        Files.write(Paths.get(PATH, file.getName()), file.getFile());

        statement.close();
        conn.close();

        return file.getName();
    }

    @Override
    public void update(FileEntity file) throws SQLException, IOException {
        Connection conn = DriverManager.getConnection(url, properties);
        PreparedStatement statement = conn.prepareStatement("SELECT * FROM  file WHERE name_file = ?;");
        statement.setString(1, file.getName());
        ResultSet resultSet = statement.executeQuery();
        boolean exist = false;
        if(resultSet.next()){
            exist = true;
        }
        conn.close();
        statement.close();
        resultSet.close();
        if(!exist){
            throw  new FileNotFoundException("Файл не найден");
        }

        Files.write(Paths.get(PATH, file.getName()), file.getFile());
    }

    @Override
    public void remove(FileEntity file) throws SQLException, IOException {
        Connection conn = DriverManager.getConnection(url, properties);
        PreparedStatement statement = conn.prepareStatement("DELETE FROM file WHERE name_file = ?;");
        statement.setString(1, file.getName());
        statement.executeUpdate();

        Files.deleteIfExists(Paths.get(PATH, file.getName()));

        statement.close();
        conn.close();
    }

    @Override
    public List<FileEntity> queryAll() throws IOException, SQLException {
        Connection conn = DriverManager.getConnection(url, properties);
        PreparedStatement statement = conn.prepareStatement("SELECT * FROM file;");
        ResultSet resultSet = statement.executeQuery();

        List<FileEntity> result = new ArrayList<FileEntity>();
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

        resultSet.close();
        statement.close();
        conn.close();

        return result;
    }

    @Override
    public FileEntity query(FileEntity fileEntity) throws IOException, SQLException {
        Connection conn = DriverManager.getConnection(url, properties);
        PreparedStatement statement = conn.prepareStatement("SELECT * FROM file WHERE name_file = ?;");
        statement.setString(1, fileEntity.getName());
        ResultSet resultSet = statement.executeQuery();

        FileEntity result = null;
        if(resultSet.next()){
            result = new FileEntity();
            result.setName(resultSet.getString("name_file"));
            Path path = Paths.get(PATH, fileEntity.getName());
            if(!Files.exists(path)){
                throw  new FileNotFoundException("Файл не найден");
            }
            result.setFile(Files.readAllBytes(path));
        }

        resultSet.close();
        statement.close();
        conn.close();

        if(result == null){
            throw new FileNotFoundException("Файл не найден");
        }

        return result;
    }
}
