package com.serenity.appium.poc.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.WebElement;

import io.appium.java_client.pagefactory.*;


import org.openqa.selenium.WebDriver;

public class WordPressLoginPage extends MobilePageObject {

    public WordPressLoginPage(WebDriver driver) {
        super(driver);
    }


    //https://github.com/appium/java-client/blob/master/docs/Page-objects.md
    //Appium Java client has facilities which components to Page Object design pattern and Selenium PageFactory.

     @AndroidFindBy(xpath="//android.widget.Button[@text='Log In']")
     @iOSFindBy(xpath="//XCUIElementTypeButton[@label='Log In']")
     private WebElement WPLogInButton;

     @AndroidFindBy(xpath="//android.widget.EditText")
     @iOSFindBy(xpath="//XCUIElementTypeTextField[@name=\"Email address\"]")
     private WebElement WPEmailAddressField;

     @AndroidFindBy(id="org.wordpress.android:id/primary_button")
     @iOSFindBy(xpath="//XCUIElementTypeButton[@name=\"Next Button\"]")
     private WebElement WPLogInPageNextButton;

     @AndroidFindBy(id="org.wordpress.android:id/textinput_error")
     @iOSFindBy(xpath="//XCUIElementTypeStaticText[contains(@name,'not registered')]")
     private WebElement WPLogInPageInvalidEmailErrorMessage;

    public void gotoWPLoginPage(){
        //WebDriverWait wait = new WebDriverWait(getDriver(), 60);
        //wait.until(ExpectedConditions.visibilityOfElementLocated(WPLogInButton) );
        //http://discuss.appium.io/t/how-can-i-give-webdriverwait-and-expectedconditions-for-appium-driver-type-as-mobile-element/6115
        element(WPLogInButton).click();
    }

    public void enterInvalidEmailIdWPLoginPage(){
        WPEmailAddressField.sendKeys("vikram@invalid.com");
    }

    public boolean isErrorMessageShownWPLoginPage(){
        WPLogInPageNextButton.click();
        return WPLogInPageInvalidEmailErrorMessage.getText().contains("is not registered on WordPress.com");
    }

}
