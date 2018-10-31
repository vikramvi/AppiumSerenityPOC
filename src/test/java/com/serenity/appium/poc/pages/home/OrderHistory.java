package com.serenity.appium.poc.pages.home;

import com.serenity.appium.poc.pages.MobilePageObject;
import com.serenity.appium.poc.utils.Utils;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class OrderHistory extends MobilePageObject {


    @AndroidFindBy(xpath="//android.widget.TextView[@content-desc='header-title' and @text='ORDER HISTORY']")
    private WebElement TEXT_pageTitle;
    private static final String expectedTitle = "ORDER HISTORY";

    public OrderHistory(WebDriver driver) {
        super(driver);
    }

    public boolean isPageTitleCorrect() {
        if(Utils.isVisible(getDriver(), TEXT_pageTitle, 20)) {
            return Utils.isPageTitleCorrectAfterPolling(TEXT_pageTitle, expectedTitle);
        }
        return false;
    }

    String XPATH_PATTERN_ORDER = "//android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[2]/android.view.ViewGroup/android.view.ViewGroup/android.widget.TextView[2][@text='%d']";
    public void tapOrderId(int orderId){
        String XPATH_OrderRow = String.format(XPATH_PATTERN_ORDER, orderId);
        getDriver().findElement(By.xpath(XPATH_OrderRow)).click();
    }
}
