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

    @iOSFindBy(accessibility = "button-change-store")
    //@AndroidFindBy(accessibility = "button-change-store")
    @AndroidFindBy(accessibility = "button-change-store-title")
    private WebElement BUTTON_changeStore;

    @iOSFindBy(accessibility = "text-address1")
    @AndroidFindBy(accessibility = "text-address1")
    private WebElement TEXT_address1;

    @iOSFindBy(accessibility = "text-address2")
    @AndroidFindBy(accessibility = "text-address2")
    private WebElement TEXT_address2;

    @iOSFindBy(accessibility = "text-city-state")
    @AndroidFindBy(accessibility = "text-city-state")
    private WebElement TEXT_cityState;

    @iOSFindBy(accessibility = "text-hours")
    @AndroidFindBy(accessibility = "text-hours")
    private WebElement TEXT_openUntilAtHour;

    public MyStoreHeaderPageObject(WebDriver driver) {
        super(driver);
    }

    private static final String noResultsFound = "NOT_FOUND!";

    private String XPATH_storeDataPattern = "//android.widget.Button[@content-desc=\"button-change-store\"]/android.widget.TextView[%s]";
    private String XPATH_storeTitle = String.format(XPATH_storeDataPattern, "1");
    public String getTitleFromStoreData() {
        LOGGER.info("Getting Title from store data...");
        String result = isAndroid()
                ? BUTTON_changeStore.getText()
                : BUTTON_changeStore.getText().replace(" \uE827", "");
        return result;
    }

    private String XPATH_storeLocation = String.format(XPATH_storeDataPattern, "2");
    private By TEXT_storeLocation = MobileBy.xpath(XPATH_storeLocation);
    public String getLocationFromStoreData() {
        String result = TEXT_address1.getText();
        return result;
    }

    private String XPATH_storeOpenData = String.format(XPATH_storeDataPattern, "3");
    private By TEXT_storeOpenData = MobileBy.xpath(XPATH_storeOpenData);
    public String getOpenInfoFromStoreData() {
        String result = TEXT_openUntilAtHour.getText();
        return result;
    }

    public boolean scrollToStore() {
        try {
            By BY_changeStore = MobileBy.AccessibilityId("button-change-store");
            if (!Utils.isVisible(getDriver(), BY_changeStore, 2)) {
                if (isIOS()) {
                    Scrolling.iosScroll(Scrolling.IosDirection.DOWN, BUTTON_changeStore);
                } else {
                    //TODO: neither of these is working for iOS or Android!
                    Scrolling.androidSwipe(Scrolling.AndroidDirection.DOWN);
                    Scrolling.androidSwipe(Scrolling.AndroidDirection.UP);
                }
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

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
