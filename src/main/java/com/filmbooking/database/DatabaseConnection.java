package com.filmbooking.database;

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
            Class.forName("org.postgresql.Driver");
            String DB_URL = "jdbc:postgresql://localhost:5432/FilmBooking";
            String USER_NAME = "postgres";
            String PASSWORD = "nphuonha210103";
            connection = DriverManager.getConnection(DB_URL, USER_NAME, PASSWORD);

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
