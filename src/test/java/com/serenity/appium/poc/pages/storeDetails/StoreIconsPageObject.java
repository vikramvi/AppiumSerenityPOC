package com.serenity.appium.poc.pages.storeDetails;

import com.serenity.appium.poc.pages.MobilePageObject;
import com.serenity.appium.poc.utils.Utils;
import io.appium.java_client.MobileBy;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSFindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class StoreIconsPageObject extends MobilePageObject {

    @AndroidFindBy(accessibility = "touchableIcon-call-store")
    @iOSFindBy(accessibility = "touchableIcon-call-store")
    private WebElement TOUCHABLE_ICON_callStore;

    @AndroidFindBy(accessibility = "touchableIcon-get-directions")
    @iOSFindBy(accessibility = "touchableIcon-get-directions")
    private WebElement TOUCHABLE_ICON_getDirections;

    @AndroidFindBy(accessibility = "touchableIcon-bookmark-myStore")
    @iOSFindBy(accessibility = "touchableIcon-bookmark-myStore")
    private WebElement TOUCHABLE_ICON_myStore;


    //    private By TOUCHABLE_ICON_callStore = MobileBy.AccessibilityId("touchableIcon-call-store");
    public boolean isCallStoreIconPresent() {
        //return Utils.isVisible(getDriver(), TOUCHABLE_ICON_callStore, 5);
        return TOUCHABLE_ICON_callStore.isDisplayed();
    }

    public boolean clickCallStore() {
        //getDriver().findElement(TOUCHABLE_ICON_callStore).click();
        return Utils.tryClicking(TOUCHABLE_ICON_callStore);
    }

    //    private By TOUCHABLE_ICON_getDirections = MobileBy.AccessibilityId("touchableIcon-get-directions");
    public boolean isGetDirectionsIconPresent() {
        //return Utils.isVisible(getDriver(), TOUCHABLE_ICON_getDirections, 10);
        return TOUCHABLE_ICON_getDirections.isDisplayed();
    }

    public boolean clickGetDirections() {
        //getDriver().findElement(TOUCHABLE_ICON_getDirections).click();
        TOUCHABLE_ICON_getDirections.click();
        return Utils.tryClicking(TOUCHABLE_ICON_getDirections);
    }

    //    private By TOUCHABLE_ICON_myStore = MobileBy.AccessibilityId("touchableIcon-bookmark-myStore");
    public boolean isMyStoreIconPresent() {
        //return Utils.isVisible(getDriver(), TOUCHABLE_ICON_myStore, 10);
        return TOUCHABLE_ICON_myStore.isDisplayed();
    }

    public boolean clickMyStore() {
        try {
            //getDriver().findElement(TOUCHABLE_ICON_myStore).click();
            TOUCHABLE_ICON_myStore.click();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public StoreIconsPageObject(WebDriver driver) {
        super(driver);
    }
}
