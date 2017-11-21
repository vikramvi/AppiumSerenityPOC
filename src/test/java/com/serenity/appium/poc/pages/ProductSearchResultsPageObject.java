package com.serenity.appium.poc.pages;

import io.appium.java_client.MobileBy;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSFindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ProductSearchResultsPageObject extends MobilePageObject {


    public ProductSearchResultsPageObject(WebDriver driver) {
        super(driver);
    }

    public String getResultsCount() {
        String itemCount = getDriver().findElement(MobileBy.AccessibilityId("search results count")).getText();
        return itemCount.replace(" ITEM", "");

    }

}
