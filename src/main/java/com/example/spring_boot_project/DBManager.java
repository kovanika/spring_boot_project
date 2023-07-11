package com.example.spring_boot_project;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.function.Function;

@Component
public class DBManager {

    private final HikariDataSource dataSource;

    public DBManager(AppProperties appProperties) {
        //    @Value("${database.url}")
        //    private String url;
        //    @Value("${database.userName}")
        //    private String userName;
        //    @Value("${database.password}")
        //    private String password;

        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(appProperties.url());
        config.setUsername(appProperties.username());
        config.setPassword(appProperties.password());
        config.setDriverClassName("org.postgresql.Driver");

        dataSource = new HikariDataSource(config);
    }

    private PreparedStatement prepare(PreparedStatement statement, Object[] params) throws SQLException {
        for(int i = 0; i < params.length; i++){
            statement.setObject(i + 1, params[i]);
        }
        return statement;
    }

    public <T> T query(Function<ResultSet, T> mapper, String sql, Object[] params) throws SQLException {
        try(Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);){
            prepare(preparedStatement, params);
            var exec = preparedStatement.executeQuery();
            return mapper.apply(exec);
        }
    }

    public int insert(String sql, Object[] params) throws SQLException {
        try(Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);){
            prepare(preparedStatement, params);
            preparedStatement.executeUpdate();
            var exec = preparedStatement.getGeneratedKeys();
            if(exec.next()){
                return exec.getInt("id");
            }
            return -1;
        }
    }

    public void update(String sql, Object[] params) throws SQLException {
        try(Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);){
            prepare(preparedStatement, params);
            preparedStatement.executeUpdate();
        }
    }



    @PreDestroy
    public void destroy(){
        dataSource.close();
    }
}
