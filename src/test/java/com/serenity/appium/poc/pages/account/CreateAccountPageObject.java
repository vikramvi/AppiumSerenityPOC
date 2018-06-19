package com.serenity.appium.poc.pages.account;

import com.serenity.appium.poc.pages.MobilePageObject;
import com.serenity.appium.poc.utils.Utils;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSFindBy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CreateAccountPageObject extends MobilePageObject {

    @iOSFindBy(accessibility = "header-title")
    @AndroidFindBy(accessibility = "header-title")
    private WebElement TEXT_pageTitle;
    private static final String expectedTitle = "CREATE ACCOUNT";



    @iOSFindBy(accessibility = "ios-input-First-Name")
    @AndroidFindBy(accessibility = "android-input-First Name")
    private WebElement TEXT_firstName;

    @iOSFindBy(accessibility = "ios-input-Last-Name")
    @AndroidFindBy(accessibility = "android-input-Last Name")
    private WebElement TEXT_lastName;

    @iOSFindBy(accessibility = "ios-input-Email")
    @AndroidFindBy(accessibility = "android-input-Email")
    private WebElement TEXT_email;

    @iOSFindBy(accessibility = "ios-input-Password")
    @AndroidFindBy(accessibility = "android-input-Password")
    private WebElement TEXT_password;

    @iOSFindBy(accessibility = "ios-input-Confirm-Password")
    @AndroidFindBy(accessibility = "android-input-Confirm Password")
    private WebElement TEXT_confirmPassword;

    @iOSFindBy(accessibility = "ios-input-Phone-Number")
    @AndroidFindBy(accessibility = "android-input-Phone Number")
    private WebElement TEXT_phoneNumber;


    @AndroidFindBy(accessibility = "button-create-account")
    private WebElement BUTTON_createAccount;

    @AndroidFindBy(accessibility =  "button-floating-return")
    private WebElement BUTTON_return;

    public CreateAccountPageObject(WebDriver driver) {
        super(driver);
    }

    public boolean isPageTitleCorrect() {
        return Utils.isPageTitleCorrectAfterPolling(TEXT_pageTitle, expectedTitle);
    }



    public boolean isAndroidCreateAccountVisible() {
        return Utils.isVisible(getDriver(), BUTTON_createAccount, 3);
    }

    public boolean clickAndroidCreateAccountButton() {
        return Utils.tryClicking(getDriver(), BUTTON_createAccount);
    }

    public boolean clickCreateAccountButton() {
        if (isIOS()) {
            return Utils.clickIosUpdateButton();
        } else {
            return clickAndroidCreateAccountButton();
        }
    }

    public boolean isAndroidReturnButtonVisible() {
        return Utils.isVisible(getDriver(), BUTTON_return, 3);
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
