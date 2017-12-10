package com.serenity.appium.poc.utils;

import io.appium.java_client.MobileDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import net.thucydides.core.webdriver.WebDriverFacade;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;

import java.time.Duration;
import java.util.HashMap;

import static net.thucydides.core.webdriver.ThucydidesWebDriverSupport.getDriver;


public class Scrolling {

    public enum Axis {X, Y};
    public static int getScreenDimension(Axis axis) {
        Dimension dim = getDriver().manage().window().getSize();
        int result = 0;
        if (axis.equals(Axis.X)) {
            result = dim.getWidth();
        } else if (axis.equals(Axis.Y)) {
            result = dim.getHeight();
        }
        return result;
    }

    public enum AndroidDirection {
        DOWN(0.80, 0.20),
        UP(0.20, 0.80);
        double yStartPercentage;
        double yEndPercentage;

        AndroidDirection(double start, double end) {
            this.yStartPercentage = start;
            this.yEndPercentage = end;
        }
    }

    public static void androidSwipe(AndroidDirection direction) {
        int height = getScreenDimension(Axis.Y);
        int width = getScreenDimension(Axis.X);
        int x = width / 2;
        int startY = (int) (height * direction.yStartPercentage);
        int endY = (int) (height * direction.yEndPercentage);

        //TouchAction touchAction = new TouchAction((AppiumDriver)getDriver());
        WebDriver facade = getDriver();
        WebDriver driver = ((WebDriverFacade) facade).getProxiedDriver();
        TouchAction touchAction = new TouchAction((AndroidDriver) driver);

        Duration duration = Duration.ofSeconds(2);
        touchAction.press(x, startY).waitAction(duration).moveTo(x, endY).release().perform();
        //touchAction.press(x, startY).moveTo(element).release();
        //((MobileDriver) driver).performTouchAction(touchAction);
    }

    public enum IosDirection {DOWN, UP, RIGHT, LEFT};

    public static void iosScroll(IosDirection direction) { // , WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor)(IOSDriver)((WebDriverFacade) getDriver()).getProxiedDriver();
        HashMap<String, String> scrollObject = new HashMap<String, String>();
        scrollObject.put("direction", direction.toString().toLowerCase());
//        scrollObject.put("element", ((RemoteWebElement) element).getId());
        js.executeScript("mobile: scroll", scrollObject);
    }

//    public static void iosScroll(IosDirection direction, IOSElement element) {
//        JavascriptExecutor js = (JavascriptExecutor) getDriver();
//        HashMap<String, String> scrollObject = new HashMap<String, String>();
//        scrollObject.put("direction", direction.toString().toLowerCase());
//        scrollObject.put("element", ((RemoteWebElement) element).getId());
//        js.executeScript("mobile: scroll", scrollObject);
//    }
}

