package com.serenity.appium.poc.pages.storeDetails;

import com.serenity.appium.poc.pages.MobilePageObject;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSFindBy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class StoreDetailsCommonPageObject extends MobilePageObject {

    @iOSFindBy(accessibility = "button-floating-return")
    @AndroidFindBy(accessibility = "button-floating-return")
    private WebElement BUTTON_return;

    public StoreDetailsCommonPageObject(WebDriver driver) {
        super(driver);
    }

    public boolean clickReturnButton() {
        try {
            BUTTON_return.click();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
