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

    @AndroidFindBy(accessibility = "text-checkbox-confirm-age")
    private WebElement CHECKBOX_ageConfirmation;

    @AndroidFindBy(accessibility = "text-checkbox-terms")
    private WebElement CHECKBOX_termsConfirmation;

    @AndroidFindBy(accessibility = "button-create-account")
    private WebElement BUTTON_createAccount;

    @AndroidFindBy(accessibility =  "button-floating-return")
    private WebElement BUTTON_return;

    public CreateAccountPageObject(WebDriver driver) {
        super(driver);
    }

    public boolean isPageTitleCorrect() {
        if(Utils.isVisible(getDriver(), TEXT_pageTitle, 15)) {
            return Utils.isPageTitleCorrectAfterPolling(TEXT_pageTitle, expectedTitle);
        }
        return false;
    }

    public void enterFirstName(String name){
        TEXT_firstName.sendKeys(name);
    }
    public String enterRandomFirstName() {
        String name = Utils.getRandomFirstName();
        enterFirstName(name);
        return name;
    }

    public void enterLastName(String name){ TEXT_lastName.sendKeys(name); }
    public String enterRandomLastName() {
        String name = Utils.getRandomLastName();
        enterLastName(name);
        return name;
    }

    public void enterEmail(String email){ TEXT_email.sendKeys(email); }
    public String enterRandomEmail() {
        String email = Utils.getRandomEmailAddress();
        enterEmail(email);
        return email;
    }

    private static final String defaultPassword = "test123";
    public void enterPassword(String password){ TEXT_password.sendKeys(password); }
    public void enterDefaultPassword() {
        enterPassword(defaultPassword);
    }

    public void enterPasswordConfirmation(String password){ TEXT_confirmPassword.sendKeys(password); }
    public void enterDefaultPasswordConfirmation() {
        enterPasswordConfirmation(defaultPassword);
    }

    private static final String fakePhoneNumber = "(800)555-1212";
    public void enterPhoneNumber(String phone){ TEXT_phoneNumber.sendKeys(phone); }
    public void enterFakePhoneNumber() {
        enterPhoneNumber(fakePhoneNumber);
    }

    public void clickAgeConfirmation() {
        CHECKBOX_ageConfirmation.click();
    }

    public void clickTermsConfirmation() {
        CHECKBOX_termsConfirmation.click();
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
