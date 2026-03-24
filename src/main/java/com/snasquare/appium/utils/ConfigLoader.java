package com.snasquare.appium.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigLoader {

    private static Properties properties = null;

    public static Properties getProperties() {
        if (properties == null) {
            loadProperties();
        }
        return properties;
    }

    private static void loadProperties() {
        properties = new Properties();

        // Build path: projectRoot/config/config.properties
        String configPath = System.getProperty("user.dir")
                + File.separator + "config"
                + File.separator + "config.properties";

        try (InputStream input = new FileInputStream(configPath)) {
            properties.load(input);
            System.out.println("=========================================");
            System.out.println("✅ Configuration loaded successfully");
            System.out.println("📂 Config Path: " + configPath);
            System.out.println("=========================================");
        } catch (IOException e) {
            System.err.println("❌ Failed to load configuration from: " + configPath);
            e.printStackTrace();
            throw new RuntimeException("Could not load config.properties file", e);
        }
    }

    public static String getProperty(String key) {
        return getProperties().getProperty(key);
    }

    public static String getProperty(String key, String defaultValue) {
        return getProperties().getProperty(key, defaultValue);
    }
}