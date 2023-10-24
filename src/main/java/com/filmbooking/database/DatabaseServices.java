package com.filmbooking.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseServices {
    private Connection connection = null;

    public boolean connectDatabase() {
        try {
            Class.forName("org.postgresql.Driver");
            String DB_URL = "jdbc:postgresql://localhost:5432/FilmBooking";
            String USER_NAME = "postgres";
            String PASSWORD = "quocdang";
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

    public void disconnectDatabase() {
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
