package org.kuswanid.propertease.utils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseUtil {
    private static final String URL;
    private static final String USER;
    private static final String PASSWORD;

    static {
        try (InputStream input = DatabaseUtil.class.getClassLoader().getResourceAsStream("database.properties");) {
            Properties properties = new Properties();
            properties.load(input);

            URL = properties.getProperty("db.url");
            USER = properties.getProperty("db.user");
            PASSWORD = properties.getProperty("db.password");
        } catch (IOException e) {
            System.out.println("Failed to load database configuration!");
            throw new RuntimeException(e);
        }
    }

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            System.out.println("Database connection failed!");
            e.printStackTrace();
            return null;
        }
    }
}
