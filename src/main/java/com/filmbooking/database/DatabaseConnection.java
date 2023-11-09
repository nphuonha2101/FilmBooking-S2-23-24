package com.filmbooking.database;

import com.filmbooking.configs.DatabaseConfigs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private Connection connection = null;
    private static DatabaseConnection instance = null;

    private DatabaseConnection() {
    }

    public static DatabaseConnection getInstance() {
        if (instance == null) {
            instance = new DatabaseConnection();
        }
        return instance;
    }

    public boolean connect() {
        try {
            Class.forName(DatabaseConfigs.DB_CLASS_NAME);
            connection = DriverManager.getConnection(DatabaseConfigs.getDatabaseURL(), DatabaseConfigs.DB_USERNAME, DatabaseConfigs.DB_PASSWORD);


//            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//            String DB_URL = "jdbc:sqlserver://localhost;databaseName=FilmBooking;user=sqlserver;password=nphuonha2101" +
//                    ";encrypt=true;trustServerCertificate=true;loginTimeout=30";
//
//            connection = DriverManager.getConnection(DB_URL);

            if (connection != null) {
                System.out.println("Connect successfully!");
                return true;
            }

        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    public void close() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Connection getConnection() {
        return connection;
    }
}
