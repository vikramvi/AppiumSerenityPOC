package com.serenity.appium.poc.pages.storeDetails;

import com.serenity.appium.poc.pages.MobilePageObject;
import com.serenity.appium.poc.utils.Utils;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSFindBy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class StoreIconsPageObject extends MobilePageObject {

    @AndroidFindBy(accessibility = "touchableIcon-get-directions")
    @iOSFindBy(accessibility = "touchableIcon-get-directions")
    private WebElement TOUCHABLE_ICON_getDirections;

    @AndroidFindBy(accessibility = "touchableIcon-bookmark-myStore")
    @iOSFindBy(accessibility = "touchableIcon-bookmark-myStore")
    private WebElement TOUCHABLE_ICON_shopThisStore;

    @AndroidFindBy(accessibility = "touchableIcon-change-store")
    @iOSFindBy(accessibility = "touchableIcon-change-store")
    private WebElement TOUCHABLE_ICON_changeStore;


    public boolean isChangeStoreIconPresent() {
        return TOUCHABLE_ICON_changeStore.isDisplayed();
    }

    public boolean clickChangeStore() {
        return Utils.tryClicking(TOUCHABLE_ICON_changeStore);
    }

    public boolean isGetDirectionsIconPresent() {
        return TOUCHABLE_ICON_getDirections.isDisplayed();
    }

    public boolean clickGetDirections() {
        return Utils.tryClicking(TOUCHABLE_ICON_getDirections);
    }

    public boolean isMyStoreIconPresent() {
        return TOUCHABLE_ICON_shopThisStore.isDisplayed();
    }

    public boolean clickShopThisStoreIcon() {
        try {
            TOUCHABLE_ICON_shopThisStore.click();
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
