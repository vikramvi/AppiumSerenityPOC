package com.serenity.appium.poc.pages.storeDetails;

import com.serenity.appium.poc.pages.MobilePageObject;
import com.serenity.appium.poc.utils.Utils;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ClassesAndEventsPageObject extends MobilePageObject {

    @AndroidFindBy(accessibility = "header-title")
    private WebElement TEXT_pageTitle;
    private static final String expectedTitle = "CLASSES & EVENTS";

    @AndroidFindBy(xpath="//android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[3]/android.widget.TextView")
    private WebElement storeAndStateName;

    @AndroidFindBy(xpath = "//android.widget.ScrollView//android.view.ViewGroup[3]/android.widget.Button")
    private WebElement changeButton;

    public ClassesAndEventsPageObject(WebDriver driver) {
        super(driver);
    }

    public boolean isPageTitleCorrect() {
        if(Utils.isVisible(getDriver(), TEXT_pageTitle, 20)) {
            return Utils.isPageTitleCorrectAfterPolling(TEXT_pageTitle, expectedTitle);
        }
        return false;
    }

    public void clickChangeButton(){
        changeButton.click();
    }

    public String getStoreName(){
        return storeAndStateName.getText();
    }

}
