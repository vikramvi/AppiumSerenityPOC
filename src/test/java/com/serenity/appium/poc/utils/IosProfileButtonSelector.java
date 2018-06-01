package com.serenity.appium.poc.utils;

import io.appium.java_client.TouchAction;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.touch.offset.PointOption;
import net.thucydides.core.webdriver.WebDriverFacade;

import static net.thucydides.core.webdriver.ThucydidesWebDriverSupport.getDriver;

public class IosProfileButtonSelector {

    public enum ButtonPosition {
        ABOVE_KEYBOARD_UPDATE(0.58, 0.5887),
        ABOVE_KEYBOARD_RETURN(0.1, 0.5887),
        NORMAL_UPDATE(0.58, 0.9274),
        NORMAL_RETURN(0.1, 0.9274);
        double xMultiplier;
        double yMultiplier;

        ButtonPosition(double xMultiplier, double yMultiplier) {
            this.xMultiplier = xMultiplier;
            this.yMultiplier = yMultiplier;
        }
        public int getX() {
            int coordinate = (int) Math.round(xMultiplier * Utils.getScreenDimension(Utils.Axis.X));
            return coordinate;
        }
        public int getY() {
            int coordinate = (int) Math.round(yMultiplier * Utils.getScreenDimension(Utils.Axis.Y));
            return coordinate;
        }
    }

    public static void selectButton(ButtonPosition position) {
        TouchAction tapCoordinates = new TouchAction(((IOSDriver)((WebDriverFacade) getDriver()).getProxiedDriver()));
        tapCoordinates.tap(new PointOption().withCoordinates(position.getX(), position.getY())).perform();
    }
}

