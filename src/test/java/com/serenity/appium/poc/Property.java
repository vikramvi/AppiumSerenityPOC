package com.serenity.appium.poc;

public final class Property {

    public static final String APPIUM_HOST = System.getProperty("appium.host");
    public static final String APPIUM_PORT = System.getProperty("appium.port");
    public static final String APPIUM_PLATFORM = System.getProperty("platform.name");
    public static final String DEVICE_NAME = System.getProperty("device.name");
    public static final String UUID = System.getProperty("device.name");
    public static final String APPIUM_LOG_LEVEL = System.getProperty("appium.log");
    public static final String NO_RESET = System.getProperty("no.reset");
    public static final String APP_FILE = System.getProperty("file");
    public static final String IMPLICIT_WAIT_TIME = System.getProperty("implicit.wait");
    public static String RESET_FLAG = System.getProperty("appium.noReset");

}
