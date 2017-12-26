package com.serenity.appium.poc.pages.storeDetails;

import com.serenity.appium.poc.pages.MobilePageObject;
import com.serenity.appium.poc.utils.Utils;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSFindBy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class StoreDataHeaderPageObject extends StoreDetailsCommonPageObject {

    @AndroidFindBy(accessibility = "text-store-title")
    @iOSFindBy(accessibility = "text-store-title")
    private WebElement TEXT_storeAddress1;

    @AndroidFindBy(accessibility = "text-store-address")
    @iOSFindBy(accessibility = "text-store-address")
    private WebElement TEXT_storeAddress2;

    @AndroidFindBy(accessibility = "text-store-cityStateZip")
    @iOSFindBy(accessibility = "text-store-cityStateZip")
    private WebElement TEXT_cityStateZip;

    @AndroidFindBy(accessibility = "text-store-openCloseHour")
    @iOSFindBy(accessibility = "text-store-openCloseHour")
    private WebElement TEXT_openCloseHour;

    public boolean isAddress1Displayed() {
        return Utils.isVisible(getDriver(), TEXT_storeAddress1, 5);
    }
    public String getAddress1() {
        String result = "NOT FOUND!";
        try {
            result = TEXT_storeAddress1.getText();
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalStateException("Can't retrieve Address1 text!");
        }
        return result;
    }

    public boolean isAddress2Displayed() {
        return Utils.isVisible(getDriver(), TEXT_storeAddress2, 5);
    }
    public String getAddress2() {
        String result = "NOT FOUND!";
        try {
            result = TEXT_storeAddress2.getText();
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalStateException("Can't retrieve Address2 text!");
        }
        return result;
    }

    public boolean isCityStateZipDisplayed() {
        return Utils.isVisible(getDriver(), TEXT_cityStateZip, 5);
    }
    public String getCityStateZip() {
        String result = "NOT FOUND!";
        try {
            result = TEXT_cityStateZip.getText();
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalStateException("Can't retrieve City, State, Zip text!");
        }
        return result;
    }

    public boolean isOpenCloseHourDisplayed() {
        return Utils.isVisible(getDriver(), TEXT_openCloseHour, 5);
    }
    public String getOpenCloseHour() {
        String result = "NOT FOUND!";
        try {
            result = TEXT_openCloseHour.getText();
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalStateException("Can't retrieve Open/Close hour text!");
        }
        return result;
    }

    public StoreDataHeaderPageObject(WebDriver driver) {
        super(driver);
    }
}
