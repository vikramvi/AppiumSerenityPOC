package com.serenity.appium.poc.pages.home;

import com.serenity.appium.poc.pages.MobilePageObject;
import com.serenity.appium.poc.utils.StoreDataParser;
import com.serenity.appium.poc.utils.Utils;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSFindBy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class MyStoreHeaderPageObject extends MobilePageObject {

    @iOSFindBy(accessibility = "touchable-store-data")
    private WebElement TOUCHABLE_TEXT_storeData;

    @AndroidFindBy(accessibility = "text-store-name")
    private WebElement TEXT_storeTitle;

    @AndroidFindBy(accessibility = "text-hours")
    private WebElement TEXT_openUntilAtHour;

    @iOSFindBy(accessibility = "\uE832 SIGN IN")
    @AndroidFindBy(accessibility = "SIGN IN")
    private WebElement BUTTON_signIn;

    @iOSFindBy(accessibility = "\uE81E CREATE ACCOUNT")
    @AndroidFindBy(accessibility = "CREATE ACCOUNT")
    private WebElement BUTTON_createAccount;

    @iOSFindBy(accessibility = "\uE82F CHANGE STORE")
    @AndroidFindBy(accessibility = "CHANGE STORE")
    private WebElement BUTTON_changeStore;

    public MyStoreHeaderPageObject(WebDriver driver) {
        super(driver);
    }


    public boolean clickTouchableStoreData() {
        boolean result = false;
        if (isAndroid()) {
            if (Utils.isVisible(getDriver(), TEXT_storeTitle, 5)) {
                result = Utils.tryClicking(TEXT_storeTitle);
            } else {
                throw new IllegalStateException("Touchable store data not present to click!");
            }
        } else {
            if (Utils.isVisible(getDriver(), TOUCHABLE_TEXT_storeData, 5)) {
                result = Utils.tryClicking(TOUCHABLE_TEXT_storeData);
            } else {
                throw new IllegalStateException("Touchable store data not present to click!");
            }
        }
        return result;
    }


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

    public boolean isChangeStoreOptionPresent() {
        return Utils.isVisible(getDriver(), BUTTON_changeStore, 5);
    }

    public boolean clickChangeStore() {
        return Utils.tryClicking(BUTTON_changeStore);
    }
}
