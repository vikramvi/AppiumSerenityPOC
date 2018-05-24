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

    @iOSFindBy(accessibility = "button-header-sign in")
    @AndroidFindBy(accessibility = "button-header-sign in")
    private WebElement BUTTON_signIn;

    @iOSFindBy(accessibility = "button-header-create account")
    @AndroidFindBy(accessibility = "button-header-create account")
    private WebElement BUTTON_createAccount;

    @iOSFindBy(accessibility = "button-header-change store")
    @AndroidFindBy(accessibility = "button-header-change store")
    private WebElement BUTTON_changeStore;

    @iOSFindBy(accessibility = "button-header-my orders")
    @AndroidFindBy(accessibility = "button-header-my orders")
    private WebElement BUTTON_myOrders;

    public MyStoreHeaderPageObject(WebDriver driver) {
        super(driver);
    }

    public boolean isMyOrdersPresent() {
        boolean result = Utils.isVisible(getDriver(), BUTTON_myOrders, 10);
        return result;
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

    public boolean clickSignIn() { return Utils.tryClicking(BUTTON_signIn); }


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
