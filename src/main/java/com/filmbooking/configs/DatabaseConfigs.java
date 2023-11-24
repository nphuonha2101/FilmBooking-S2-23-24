package com.filmbooking.configs;

public class DatabaseConfigs {
    public static final String DB_CLASS_NAME = "org.postgresql.Driver";
    public static final String DB_HOST = "localhost";
    public static final String DB_PORT = "5432";
    public static final String DB_NAME = "FilmBooking";

    public static final String DB_USERNAME = "postgres";
    public static final String DB_PASSWORD = "nphuonha";

    public static String getDatabaseURL() {
        return "jdbc:postgresql://" + DB_HOST + ":" + DB_PORT + "/" + DB_NAME;
    }
}
