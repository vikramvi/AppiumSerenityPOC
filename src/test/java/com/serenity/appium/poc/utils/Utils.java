package com.serenity.appium.poc.utils;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {
    public static String getPlatform(AppiumDriver driver) {
        return String.valueOf(driver.getCapabilities().getCapability("platformName"));
    }
    public static boolean isIosPlatform(AppiumDriver driver) {
        String platform = getPlatform(driver);
        return platform.equalsIgnoreCase("ios");
    }
    public static boolean isAndroidPlatform(AppiumDriver driver) {
        return getPlatform(driver).equalsIgnoreCase("android");
    }

    public static String acceptAlert(AppiumDriver driver) {
        String message = null;
        if (isIosPlatform(driver)) {
            Alert popup = driver.switchTo().alert();
            message = popup.getText();
            popup.accept();
        } else if (Utils.isAndroidPlatform(driver)) {
            message = driver.findElement(By.id("android:id/message")).getText();
            //TODO: need to access OK button on Android
        } else throw new IllegalStateException("Platform not iOS or Android!");
        return message;
    }
    public static String stripFrontRegex(String stream, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(stream);
        String result = "NOT IN POSITION 0 !";
        if (matcher.find()) {
            if (matcher.start() == 0) {
                String matched = matcher.group();
                result = stream.replace(matched, "");
            } else {
                throw new IllegalStateException("Cannot strip expression when it is not in position 0!");
            }
        }
        return result;
    }
    public static String stripAnyRegex(String stream, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(stream);
        String result = "NOT FOUND!";
        if (matcher.find()) {
            if (matcher.start() >= 0) {
                String matched = matcher.group();
                result = stream.replace(matched, "");
            } else {
                throw new IllegalStateException("Cannot strip expression when it is not found!");
            }
        }
        return result;
    }
    public static String trimFinalSpace(String string) {
        if (string != null && string.length() > 0 && string.charAt(string.length() - 1) == ' ') {
            string = string.substring(0, string.length() - 1);
        }
        return string;
    }
    public static boolean isVisible(WebDriver driver, By reference, int seconds) {
        try {
            new WebDriverWait(driver, seconds).until(ExpectedConditions.visibilityOfElementLocated(reference));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    public static boolean tryClicking(WebElement element) {
        try {
            element.click();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
