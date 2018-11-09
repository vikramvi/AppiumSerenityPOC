package com.serenity.appium.poc.pages;

import com.serenity.appium.poc.utils.Utils;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AndMoreRewardsPageObject extends MobilePageObject{

    @AndroidFindBy(accessibility = "header-title")
    private WebElement TEXT_pageTitle;
    private static final String expectedTitle = "&MORE REWARDS";

    @AndroidFindBy(accessibility = "button-signUp-rewardsLanding")
    private WebElement BUTTON_JoinNow;

    public AndMoreRewardsPageObject(WebDriver driver) {
        super(driver);
    }

    public boolean isPageTitleCorrect() {
        if(Utils.isVisible(getDriver(), TEXT_pageTitle, 5)) {
            return Utils.isPageTitleCorrectAfterPolling(TEXT_pageTitle, expectedTitle);
        }
        return false;
    }

    public void clickJoinNow(){
        BUTTON_JoinNow.click();
    }

}
