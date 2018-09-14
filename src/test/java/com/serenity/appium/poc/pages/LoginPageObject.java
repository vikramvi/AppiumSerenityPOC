package com.serenity.appium.poc.pages;


import com.serenity.appium.poc.utils.Utils;
import io.appium.java_client.MobileBy;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSFindBy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPageObject extends MobilePageObject {

    @AndroidFindBy(accessibility = "header-title")
    @iOSFindBy(accessibility = "header-title")
    private WebElement TEXT_header;
    private String expectedHeader = "SIGN IN";

    @AndroidFindBy(accessibility = "android-input-Email")
    @iOSFindBy(accessibility = "ios-input-Email")
    private WebElement INPUT_email;

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

            WebElement x = getDriver().findElement(MobileBy.AccessibilityId("button-login"));

            if (Utils.isVisible(getDriver(), x, 5)) {
                Utils.tryClicking(x);
            } else {
                throw new IllegalStateException("Login button not visible after wait!");
            }
        }
    }

    public void clickForgotPassword() {
        LINK_forgotPassword.click();
    }

    public boolean performDefaultLogin() {
        return performLogin("jphtest9@yopmail.com");
    }

    public boolean performLogin(String email) {
        try {
            enterEmailAddress(email);
            enterPassword("test123");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean confirmHeader(){

        if(Utils.isVisible(getDriver(), TEXT_header, 8)) {
            String actual = TEXT_header.getText();
            boolean result = actual.equals(expectedHeader);
            return result;
        }
        return false;
    }
}
