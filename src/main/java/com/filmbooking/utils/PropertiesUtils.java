package com.filmbooking.utils;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesUtils {
    private static final String APPLICATION_PROPERTIES_FILE_PATH_TEST = "src/main/resources/properties/application.properties";
    private static final String APPLICATION_PROPERTIES_FILE_PATH = "C:\\Users\\nphuonha\\Desktop\\FilmBooking-WebProgramming\\target\\classes\\properties\\application.properties";
    private final Properties properties;
    private static PropertiesUtils instance = null;

    private PropertiesUtils() {
        properties = new Properties();
        loadPropertiesFile();
    }

    public static PropertiesUtils getInstance() {
        if (instance == null) {
            instance = new PropertiesUtils();
        }
        return instance;
    }

    private void loadPropertiesFile() {
        try {
            InputStream inputStream = new FileInputStream(APPLICATION_PROPERTIES_FILE_PATH);
            properties.load(inputStream);

        } catch (Exception e) {
            throw new RuntimeException("Cannot load properties file: " + APPLICATION_PROPERTIES_FILE_PATH);
        }
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }

    public Properties getProperties() {
        return this.properties;
    }
    public static void main(String[] args) {
        PropertiesUtils propertiesUtils = PropertiesUtils.getInstance();
        System.out.println(propertiesUtils.getProperty("db.userPassword"));
    }
}
