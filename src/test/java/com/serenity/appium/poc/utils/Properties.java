package com.serenity.appium.poc.utils;

import java.io.FileInputStream;
import java.io.IOException;

public class Properties {
    private static final String propertiesFile = "serenity.properties";
    private java.util.Properties properties = new java.util.Properties();

    public String getValue(String targetName) {
        String result = "NOT FOUND!";
        try {
            properties.load(new FileInputStream(propertiesFile));
            result = properties.getProperty(targetName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public boolean isSauceLabsRun() {
        String appLocation = getValue("appium.app");
        return appLocation.contains("sauce-storage");
    }
}
