package com.filmbooking.utils;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesUtils {
    private static final String APPLICATION_PROPERTIES_PATH = "/properties/application.properties";
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
        System.out.println();
        try {
            InputStream inputStream = this.getClass().getResourceAsStream(APPLICATION_PROPERTIES_PATH);
            properties.load(inputStream);

        } catch (Exception e) {
            throw new RuntimeException("Cannot load properties file: " + APPLICATION_PROPERTIES_PATH);
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
