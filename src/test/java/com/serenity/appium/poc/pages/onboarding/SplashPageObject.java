package com.serenity.appium.poc.pages.onboarding;


import com.serenity.appium.poc.pages.MobilePageObject;
import com.serenity.appium.poc.utils.Utils;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSFindBy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SplashPageObject extends MobilePageObject {

    @AndroidFindBy(accessibility = "button-lets-begin")
    @iOSFindBy(accessibility = "button-lets-begin")
    private WebElement LetsBeginButton;

    public SplashPageObject(WebDriver driver) {
        super(driver);
    }

    public boolean startOnboarding(){
        return Utils.tryClicking(LetsBeginButton);
    }
}
