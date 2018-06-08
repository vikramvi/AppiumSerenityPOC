package com.serenity.appium.poc.pages.account;

import com.serenity.appium.poc.pages.MobilePageObject;
import com.serenity.appium.poc.utils.Utils;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSFindBy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PaymentsPageObject extends MobilePageObject {

    @iOSFindBy(accessibility = "header-title")
    @AndroidFindBy(accessibility = "header-title")
    private WebElement TEXT_pageTitle;
    private static final String expectedTitle = "PAYMENTS";




    @AndroidFindBy(accessibility = "button-add-payment")
    private WebElement BUTTON_addNewPayment;

    @AndroidFindBy(accessibility =  "button-floating-return")
    private WebElement BUTTON_return;

    public PaymentsPageObject(WebDriver driver) {
        super(driver);
    }

    public boolean isPageTitleCorrect() {
        return Utils.isPageTitleCorrectAfterPolling(TEXT_pageTitle, expectedTitle);
    }


    public boolean clickAndroidAddNewPaymentButton() {
        return Utils.tryClicking(getDriver(), BUTTON_addNewPayment);
    }

    public boolean clickAddNewAddressButton() {
        if (isIOS()) {
            return Utils.clickIosUpdateButton();
        } else {
            return clickAndroidAddNewPaymentButton();
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
