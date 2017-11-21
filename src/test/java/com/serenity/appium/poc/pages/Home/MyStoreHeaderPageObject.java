package com.serenity.appium.poc.pages.Home;


import com.serenity.appium.poc.pages.MobilePageObject;
import com.serenity.appium.poc.utils.StoreDataParser;
import io.appium.java_client.MobileBy;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSFindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class MyStoreHeaderPageObject extends MobilePageObject {

    @iOSFindBy(accessibility = "touchable-store-data")
    private WebElement TOUCHABLE_TEXT_storeData;

    @iOSFindBy(accessibility = "button-change-store")
    @AndroidFindBy(accessibility = "button-change-store")
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

    public String getLocationFromStoreDataForIos() {
        String result = noResultsFound;
        try {
            String stream = getStoreDataForIos();
            result = StoreDataParser.getLocation(stream);
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

    private String XPATH_storeDataPattern = "//android.widget.Button[@content-desc=\"touchable-store-data\"]/android.widget.TextView[%s]";
    private String XPATH_storeTitle = String.format(XPATH_storeDataPattern, "1");
    private By TEXT_storeTitle = MobileBy.xpath(XPATH_storeTitle);

    public String getTitleFromStoreData() {
        LOGGER.info("Getting Title from store data...");
        String result = isAndroid()
                ? getDriver().findElement(TEXT_storeTitle).getText()
                : getTitleFromStoreDataForIos();
        return result;
    }

    private String XPATH_storeLocation = String.format(XPATH_storeDataPattern, "2");
    private By TEXT_storeLocation = MobileBy.xpath(XPATH_storeLocation);

    public String getLocationFromStoreData() {
        String result = isAndroid()
                ? getDriver().findElement(TEXT_storeLocation).getText()
                : getLocationFromStoreDataForIos();
        return result;
    }

    private String XPATH_storeOpenData = String.format(XPATH_storeDataPattern, "3");
    private By TEXT_storeOpenData = MobileBy.xpath(XPATH_storeOpenData);

    public String getOpenInfoFromStoreData() {
        String result = isAndroid()
                ? getDriver().findElement(TEXT_storeOpenData).getText()
                : getOpenCloseHourFromStoreDataForIos();
        return result;
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
