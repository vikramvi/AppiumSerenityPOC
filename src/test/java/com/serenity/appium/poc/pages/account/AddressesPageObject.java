package com.serenity.appium.poc.pages.account;

import com.serenity.appium.poc.pages.MobilePageObject;
import com.serenity.appium.poc.utils.Utils;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSFindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AddressesPageObject extends MobilePageObject {

    @iOSFindBy(accessibility = "header-title")
    @AndroidFindBy(accessibility = "header-title")
    private WebElement TEXT_pageTitle;
    private static final String expectedTitle = "ADDRESSES";

    @AndroidFindBy(accessibility = "button-add-address")
    private WebElement BUTTON_update;

    @AndroidFindBy(accessibility =  "button-floating-return")
    private WebElement BUTTON_return;

    public enum AddressField {
        NICKNAME("(//android.widget.TextView[@content-desc='text-address-nickname'])[%d]"),
        PERSON_NAME("(//android.widget.TextView[@content-desc='text-address-person-name'])[%d]"),
        ADDRESS1("(//android.widget.TextView[@content-desc='text-address-address1'])[%d]"),
        ADDRESS2("(//android.widget.TextView[@content-desc='text-address-address2'])[%d]"),
        CITY_STATE_ZIP("(//android.widget.TextView[@content-desc='text-address-city-state-zip'])[%d]");
        String xpathPattern;
        AddressField(String xpathPattern) {
            this.xpathPattern = xpathPattern;
        }
        public String getText(WebDriver driver, int addressNumber) {
            String xpath = String.format(xpathPattern, addressNumber);
            String text = driver.findElement(By.xpath(xpath)).getText();
            return text;
        }
    }

    public enum AddressButton {
        DELETE("(//android.widget.Button[@content-desc='button-delete-address'])[%d]"),
        EDIT("(//android.view.ViewGroup[@content-desc=\"button-address-edit\"])[%d]");
        String xpathPattern;
        AddressButton(String xpathPattern) {
            this.xpathPattern = xpathPattern;
        }
        public void click(WebDriver driver, int addressNumber) {
            String xpath = String.format(xpathPattern, addressNumber);
            driver.findElement(By.xpath(xpath)).click();
        }
    }

    public AddressesPageObject(WebDriver driver) {
        super(driver);
    }

    public boolean isPageTitleCorrect() {
        return Utils.isPageTitleCorrectAfterPolling(TEXT_pageTitle, expectedTitle);
    }


    public boolean clickAndroidAddNewAddressButton() {
        return Utils.tryClicking(getDriver(), BUTTON_update);
    }

    public boolean clickAddNewAddressButton() {
        if (isIOS()) {
            return Utils.clickIosUpdateButton();
        } else {
            return clickAndroidAddNewAddressButton();
        }
    }

    public boolean clickAndroidReturn() {
        return Utils.tryClicking(BUTTON_return);
    }

    public boolean clickReturnButton() {
        if (isIOS()) {
            return Utils.clickIosReturnButton();
        } else {
            return clickAndroidReturn();
        }
    }
}
