package com.serenity.appium.poc.utils;

import com.serenity.appium.poc.pages.MobilePageObject;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static net.thucydides.core.webdriver.ThucydidesWebDriverSupport.getDriver;

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
        stream = stream.trim();
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(stream);
        String result = "NOT IN POSITION 0 !";
        if (matcher.find()) {
            if (matcher.start() == 0) {
                String matched = matcher.group();
                result = stream.replaceFirst(matched, "");
            } else {
                throw new IllegalStateException("Cannot strip expression when it is not in position 0!");
            }
        }
        return result;
    }

    public static String stripAnyRegex(String stream, String regex) {
        stream = stream.trim();
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
//            e.printStackTrace();
            return false;
        }
    }

    public static boolean isVisible(WebDriver driver, WebElement element, int seconds) {
        try {
            new WebDriverWait(driver, seconds).until(ExpectedConditions.visibilityOf(element));
            return true;
        } catch (Exception e) {
//            e.printStackTrace();
            return false;
        }
    }

    public static boolean isLastInstanceVisible(WebDriver driver, String xpath) {
        int lastInstance = driver.findElements(By.xpath(xpath)).size();
        String suffix = String.format("[%d]", lastInstance);
        boolean result = ((lastInstance > 0) &&
                (driver.findElement(By.xpath(xpath + suffix)).isDisplayed()));
        return result;
    }

    public static boolean tryClicking(String xpath) {
        WebElement element = getDriver().findElement(By.xpath(xpath));
        return tryClicking(element);
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

    public static boolean tryClicking(WebDriver driver, By byReference) {
        try {
            driver.findElement(byReference).click();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean tryClickingAllow() {
        try {
            Thread.sleep(3000);
            JavascriptExecutor js = (JavascriptExecutor) getDriver();

            HashMap<String, String> tapObject = new HashMap<String, String>();
            tapObject.put("action", "accept");
            tapObject.put("label", "Allow");

            js.executeScript("mobile:alert", tapObject);
            return true;
        } catch (Exception e) {
            System.out.println("Caught exception trying to click Allow!");
            return false;
        }
    }

    public static String getAllAndroidGridData(String gridElementXpath) {
        String result = "";
        String gridElementXpathPattern = gridElementXpath + "[%d]";
        int totalGridItems = getDriver().findElements(MobileBy.xpath(gridElementXpath)).size();
        for (int i = 1; i <= (totalGridItems); i++) {
            String xpath = String.format(gridElementXpathPattern, i);
            boolean itemExists = (getDriver().findElements(MobileBy.xpath(xpath)).size() > 0);
            if (itemExists) {
                String text = getDriver().findElement(MobileBy.xpath(xpath)).getText() + " ";
                result = result + text;
            }
        }
        return result;
    }

    public static void setPlatform() {
        String platform = System.getProperty("testEnvironment");
        switch (platform) {
            case ("Android"):
                MobilePageObject.setAndroid(true);
                MobilePageObject.setIOS(false);
                break;
            case ("iOS"):
                MobilePageObject.setIOS(true);
                MobilePageObject.setAndroid(false);
                break;
            default:
                try {
                    throw new IllegalAccessException("No match for plaform!");
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
        }
    }

}
