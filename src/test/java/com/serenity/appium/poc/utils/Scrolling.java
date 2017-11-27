package com.serenity.appium.poc.utils;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import org.openqa.selenium.Dimension;

import java.time.Duration;

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

    public enum Direction {
        UP(0.80, 0.20),
        DOWN(0.20, 0.80);
        double yStartPercentage;
        double yEndPercentage;
        Direction(double start, double end) {
            this.yStartPercentage = start;
            this.yEndPercentage = end;
        }
    };
    public static void verticalSwipe(Direction direction) {
        int height = getScreenDimension(Axis.Y);
        int width = getScreenDimension(Axis.X);
        int x = width/2;
        int startY = (int) (height*direction.yStartPercentage);
        int endY = (int) (height*direction.yEndPercentage);
        TouchAction touchAction = new TouchAction((AppiumDriver)getDriver());
        Duration duration = Duration.ofSeconds(1);
        touchAction.press(x, startY).waitAction(duration).moveTo(x, endY).release().perform();
    }
}

