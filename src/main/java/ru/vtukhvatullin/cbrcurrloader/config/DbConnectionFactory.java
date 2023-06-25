package ru.vtukhvatullin.cbrcurrloader.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnectionFactory {
    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(AppConfig.dbHostUrl, AppConfig.dbUser, AppConfig.dbPassword);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
