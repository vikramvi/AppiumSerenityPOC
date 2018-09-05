package com.serenity.appium.poc.pages;


import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebDriver;
import io.appium.java_client.pagefactory.*;

public class WordPressSignUpPage extends MobilePageObject {

    public WordPressSignUpPage(WebDriver driver){
        super(driver);
    }

    @AndroidFindBy(id="org.wordpress.android:id/create_site_button")
    private WebElement createaWordPressSiteButton;

    @AndroidFindBy(id="org.wordpress.android:id/email_address")
    private WebElement emailAddressInputField;

    @AndroidFindBy(id="org.wordpress.android:id/username")
    private WebElement userNameInputField;

    @AndroidFindBy(id="org.wordpress.android:id/password")
    private WebElement passwordInputField;

    @AndroidFindBy(id="org.wordpress.android:id/site_url")
    private WebElement blogAddressInputField;

    @AndroidFindBy(id="org.wordpress.android:id/signup_button")
    private WebElement createAccountButton;

    public void gotoWPSignUpPage(){
        createaWordPressSiteButton.click();
    }

    public void enterSignUpScreenDetails(){
        emailAddressInputField.sendKeys("test");
        userNameInputField.sendKeys("test");
        passwordInputField.sendKeys("test");
        blogAddressInputField.sendKeys("test");
    }

    public void clickSignUpButton(){
        createAccountButton.click();
    }
}
