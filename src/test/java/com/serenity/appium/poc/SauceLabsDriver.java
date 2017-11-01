package com.serenity.appium.poc;


import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Driver;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import net.thucydides.core.webdriver.DriverSource;

public class SauceLabsDriver implements DriverSource {

    public static final String USERNAME = "vikramvi";
    public static final String ACCESS_KEY = "90cda43b-ab66-4d8b-ac12-88ddd4dc51e1";
    public static final String URL = "https://" + USERNAME + ":" + ACCESS_KEY + "@ondemand.saucelabs.com:443/wd/hub";

    @Override
    public WebDriver newDriver() {
        try {
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability("platformName", "iOS");
            capabilities.setCapability("deviceName", "iPhone 7");
            capabilities.setCapability("platformVersion", "11.0");
            capabilities.setCapability("app", "sauce-storage:tw_mobile.zip");
            capabilities.setCapability("deviceOrientation", "portrait");
            //capabilities.setCapability("appiumVersion", "1.6.5");
            WebDriver driver= new AppiumDriver<MobileElement>(new URL(URL), capabilities);
            return driver;
        } catch (MalformedURLException e) {
            throw new Error(e);
        }

    }

    @Override
    public boolean takesScreenshots() {
        // TODO Auto-generated method stub
        return true;
    }
}
