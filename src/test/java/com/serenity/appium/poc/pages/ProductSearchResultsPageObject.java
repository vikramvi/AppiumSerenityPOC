package com.serenity.appium.poc.pages;

import io.appium.java_client.MobileBy;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSFindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ProductSearchResultsPageObject extends MobilePageObject {

    @AndroidFindBy(accessibility="search results count")
    @iOSFindBy(accessibility="search results count")
    private WebElement TEXT_searchResultsCount;

    public String getResultsCount() {
        String itemCount = TEXT_searchResultsCount.getText();
        return itemCount.replace(" ITEM", "");
    }

    public ProductSearchResultsPageObject(WebDriver driver) {
        super(driver);
    }
}
