package com.serenity.appium.poc.pages.orderingFlow;

import com.serenity.appium.poc.pages.MobilePageObject;
import com.serenity.appium.poc.utils.Utils;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class OrderDetailsPageObject extends MobilePageObject {

    @AndroidFindBy(xpath = "//android.widget.TextView[@content-desc='header-title' and @text='ORDER DETAILS']")
    private WebElement TEXT_pageTitle;
    private static final String expectedTitle = "ORDER DETAILS";

    @AndroidFindBy(xpath = "android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[3]/android.widget.TextView[2]")
    private WebElement OrderId;

    @AndroidFindBy( accessibility = "continue-shopping")
    private WebElement continueShoppingButton;

    public OrderDetailsPageObject(WebDriver driver){
        super(driver);
    }

    public boolean isPageTitleCorrect() {
        if(Utils.isVisible(getDriver(), TEXT_pageTitle, 20)) {
            return Utils.isPageTitleCorrectAfterPolling(TEXT_pageTitle, expectedTitle);
        }
        return false;
    }

    public int getOrderId(){
        return Integer.parseInt( OrderId.getText() );
    }

    public void clickContinueShoppingButton(){
        continueShoppingButton.click();
    }

}
