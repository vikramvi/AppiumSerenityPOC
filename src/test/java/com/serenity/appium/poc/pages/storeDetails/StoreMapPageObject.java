package com.serenity.appium.poc.pages.storeDetails;

import com.serenity.appium.poc.pages.MobilePageObject;
import com.serenity.appium.poc.utils.Utils;
import io.appium.java_client.MobileBy;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSFindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class StoreMapPageObject extends MobilePageObject {

    @AndroidFindBy(accessibility = "touchableImage-store-map")
    @iOSFindBy(accessibility = "touchableImage-store-map")
    private WebElement TOUCHABLE_IMAGE_storeMap;

    @AndroidFindBy(accessibility = "Tap to speak")
    @iOSFindBy(accessibility = "Tracking")
    private WebElement ICON_mapIdentifier;

    public boolean isStoreMapThumbnailPresent() {
        try {
            return TOUCHABLE_IMAGE_storeMap.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    public boolean clickStoreMapThumbnail() {
        boolean successLevel = Utils.tryClicking(TOUCHABLE_IMAGE_storeMap);
        Utils.tryClickingAllow();
        return successLevel;
    }

    public boolean isActualStoreMapLoaded() {
        try {
            return ICON_mapIdentifier.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    
    public StoreMapPageObject(WebDriver driver) {
        super(driver);
    }
}
