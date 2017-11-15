package com.serenity.appium.poc.pages.Onboarding;


import com.serenity.appium.poc.pages.MobilePageObject;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSFindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SplashPageObject extends MobilePageObject {

    @AndroidFindBy(xpath="//android.widget.Button[@content-desc='button-lets-begin']")
    @iOSFindBy(xpath="//XCUIElementTypeButton[@name='button-lets-begin']")
    private WebElement LetsBeginButton;

    public SplashPageObject(WebDriver driver) {
        super(driver);
    }

    public boolean startOnboarding(){
        try{
            LetsBeginButton.click();
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
