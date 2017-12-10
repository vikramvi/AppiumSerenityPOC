package com.serenity.appium.poc.pages.home;

import com.serenity.appium.poc.pages.MobilePageObject;
import com.serenity.appium.poc.utils.Scrolling;
import com.serenity.appium.poc.utils.StoreDataParser;
import com.serenity.appium.poc.utils.Utils;
import io.appium.java_client.MobileBy;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSFindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class MyStoreHeaderPageObject extends MobilePageObject {

    @iOSFindBy(accessibility = "touchable-store-data")
    private WebElement TOUCHABLE_TEXT_storeData;

//    @iOSFindBy(accessibility = "text-address1")
    @AndroidFindBy(accessibility = "text-address1")
    private WebElement TEXT_storeTitle;

//    @iOSFindBy(accessibility = "text-hours")
    @AndroidFindBy(accessibility = "text-hours")
    private WebElement TEXT_openUntilAtHour;

//    @iOSFindBy(accessibility = "button-change-store")
    @iOSFindBy(xpath = "(//XCUIElementTypeButton[@name=\"touchable-home-tile\"])[3]")
//    @AndroidFindBy(accessibility = "button-change-store")
    @AndroidFindBy(xpath = "(//android.widget.Button[@content-desc='touchable-home-tile'])[3]/android.widget.TextView[1]")
    private WebElement BUTTON_changeStore;

    public MyStoreHeaderPageObject(WebDriver driver) {
        super(driver);
    }

    private static final String noResultsFound = "NOT_FOUND!";

    public String getStoreDataForIos() {
        String result = noResultsFound;
        try {
            result = TOUCHABLE_TEXT_storeData.getText();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public String getTitleFromStoreDataForIos() {
        String result = noResultsFound;
        try {
            String stream = getStoreDataForIos();
            result = StoreDataParser.getTitle(stream).trim();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public String getOpenCloseHourFromStoreDataForIos() {
        String result = noResultsFound;
        try {
            String stream = getStoreDataForIos();
            result = StoreDataParser.getOpenCloseHour(stream);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public String getTitleFromStoreData() {
        String result = isAndroid()
                ? TEXT_storeTitle.getText()
                : getTitleFromStoreDataForIos();
        return result;
    }

    public String getOpenInfoFromStoreData() {
        String result = isAndroid()
                ? TEXT_openUntilAtHour.getText()
                : getOpenCloseHourFromStoreDataForIos();
        return result;
    }

//    public boolean scrollToStore() {
//        try {
//            By BY_changeStore = MobileBy.AccessibilityId("button-change-store");
//            if (!Utils.isVisible(getDriver(), BY_changeStore, 2)) {
//                if (isIOS()) {
//                    Scrolling.iosScroll(Scrolling.IosDirection.DOWN, BUTTON_changeStore);
//                } else {
//                    //TODO: neither of these is working for iOS or Android!
//                    Scrolling.androidSwipe(Scrolling.AndroidDirection.DOWN);
//                    Scrolling.androidSwipe(Scrolling.AndroidDirection.UP);
//                }
//            }
//            return true;
//        } catch (Exception e) {
//            e.printStackTrace();
//            return false;
//        }
//    }

    public boolean isChangeStoreOptionPresent() {
        try {
            return BUTTON_changeStore.isDisplayed();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean clickChangeStore() {
        try {
            BUTTON_changeStore.click();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
