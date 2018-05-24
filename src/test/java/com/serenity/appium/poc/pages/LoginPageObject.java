package com.serenity.appium.poc.pages;


import com.serenity.appium.poc.utils.Utils;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSFindBy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPageObject extends MobilePageObject {

    @AndroidFindBy(accessibility = "header-title")
    @iOSFindBy(accessibility = "header-title")
    private WebElement TEXT_header;
    private String expectedHeader = "SIGN IN";

//    @AndroidFindBy(xpath = "//android.widget.EditText[@content-desc=\"input-email\"]")
    @AndroidFindBy(accessibility = "android-input-Email")
    @iOSFindBy(accessibility = "ios-input-Email")
    private WebElement INPUT_email;

//    @AndroidFindBy(xpath = "//android.widget.EditText[@content-desc=\"input-password\"]")
    @AndroidFindBy(accessibility = "android-input-Password")
    @iOSFindBy(accessibility = "ios-input-Password")
    private WebElement INPUT_password;

    @AndroidFindBy(accessibility = "touchable-forgot-password")
    @iOSFindBy(accessibility = "touchable-forgot-password")
    private WebElement LINK_forgotPassword;

    @AndroidFindBy(accessibility = "button-login")
    private WebElement BUTTON_androidLogin;

    public LoginPageObject(WebDriver driver) {
        super(driver);
    }

    public void enterEmailAddress(String email){
        INPUT_email.sendKeys(email);
    }

    public void enterPassword(String password){
        if (isIOS()) {
            INPUT_password.sendKeys(password+"\n");
        } else {
            INPUT_password.sendKeys(password);
            BUTTON_androidLogin.click();
        }
    }

    public void clickForgotPassword() {
        LINK_forgotPassword.click();
    }

    public boolean performDefaultLogin() {
        try {
            enterEmailAddress("jphtest@yopmail.com");
            enterPassword("test123");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean confirmHeader() {
        String actual = TEXT_header.getText();
        boolean result = actual.equals(expectedHeader);
        return result;
    }
}
