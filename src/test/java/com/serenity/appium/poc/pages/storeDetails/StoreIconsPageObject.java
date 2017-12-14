package com.serenity.appium.poc.pages.storeDetails;

import com.serenity.appium.poc.pages.MobilePageObject;
import com.serenity.appium.poc.utils.Utils;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSFindBy;
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


    public boolean isCallStoreIconPresent() {
        return TOUCHABLE_ICON_callStore.isDisplayed();
    }

    public boolean clickCallStore() {
        return Utils.tryClicking(TOUCHABLE_ICON_callStore);
    }

    public boolean isGetDirectionsIconPresent() {
        return TOUCHABLE_ICON_getDirections.isDisplayed();
    }

    public boolean clickGetDirections() {
        TOUCHABLE_ICON_getDirections.click();
        return Utils.tryClicking(TOUCHABLE_ICON_getDirections);
    }

    public boolean isMyStoreIconPresent() {
        return TOUCHABLE_ICON_myStore.isDisplayed();
    }

    public boolean clickMyStore() {
        try {
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
