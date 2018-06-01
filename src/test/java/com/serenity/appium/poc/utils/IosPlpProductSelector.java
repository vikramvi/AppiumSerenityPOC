package com.serenity.appium.poc.utils;

import io.appium.java_client.TouchAction;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.touch.offset.PointOption;
import net.thucydides.core.webdriver.WebDriverFacade;
import org.openqa.selenium.Dimension;

import static net.thucydides.core.webdriver.ThucydidesWebDriverSupport.getDriver;

public class IosPlpProductSelector {

    public enum ProductPosition {
        FIRST_ITEM_1ST_ROW(0.25, 0.3333),
        SECOND_ITEM_1ST_ROW(0.75, 0.3333),
        FIRST_ITEM_2ND_ROW(0.25, 0.6666),
        SECOND_ITEM_2ND_ROW(0.75, 0.6666);
        double xMultiplier;
        double yMultiplier;

        ProductPosition(double xMultiplier, double yMultiplier) {
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

    public static void selectProduct(ProductPosition position) {
        TouchAction tapCoordinates = new TouchAction(((IOSDriver)((WebDriverFacade) getDriver()).getProxiedDriver()));
        tapCoordinates.tap(new PointOption().withCoordinates(position.getX(), position.getY())).perform();
    }
}

