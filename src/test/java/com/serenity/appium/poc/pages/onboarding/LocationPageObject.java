package com.serenity.appium.poc.pages.onboarding;


import com.serenity.appium.poc.pages.MobilePageObject;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSFindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.HashMap;

public class LocationPageObject extends MobilePageObject {

    @AndroidFindBy(xpath = "//android.widget.Button[@content-desc='FIND MY STORE']")
    @iOSFindBy(xpath = "//XCUIElementTypeButton[@name='FIND MY STORE']")
    private WebElement BUTTON_allowLocation;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='turn on later']")
    @iOSFindBy(xpath = "//XCUIElementTypeOther[@name='turn on later']")
    private WebElement LINK_turnOnLater;

    public LocationPageObject(WebDriver driver) {
        super(driver);
    }

    public boolean allowLocationTracking() {
        try {
            BUTTON_allowLocation.click();
            if (isAndroid()) {
                By BUTTON_androidConfirmAllowLocation = By.id("com.android.packageinstaller:id/permission_allow_button");
                if (getDriver().findElements(BUTTON_androidConfirmAllowLocation).size() > 0) {
                    getDriver().findElement(BUTTON_androidConfirmAllowLocation).click();
                }
                // if needed for iOS too:
                //By BUTTON_iosConfirmAllowLocation = By.xpath("//XCUIElementTypeButton[@name='Allow']");
            } else {
                try {
                    Thread.sleep(3000);
                    JavascriptExecutor js = (JavascriptExecutor) getDriver();

                    HashMap<String, String> tapObject = new HashMap<String, String>();
                    tapObject.put("action", "accept");
                    tapObject.put("label", "Allow");

                    js.executeScript("mobile:alert", tapObject);
                } catch (Exception e) {
                    System.out.println("Caught exception trying to click Allow on Location!");
                }
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean declineLocationTracking() {
        try {
            LINK_turnOnLater.click();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
