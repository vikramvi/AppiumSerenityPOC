package com.serenity.appium.poc.pages;

import io.appium.java_client.MobileBy;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSFindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class StoreSearchPage extends MobilePageObject {

//    private By BY_FIELD_geoSearch = isAndroid()
//                                        ? MobileBy.AccessibilityId("field-search-stores")
//                                        : MobileBy.AccessibilityId("\uE820 CITY, STATE OR ZIP SEARCH");

    @AndroidFindBy(accessibility = "field-search-stores")
    @iOSFindBy(accessibility = "\\uE820 CITY, STATE OR ZIP SEARCH")
    private WebElement FIELD_geoSearch;

    @AndroidFindBy(accessibility = "button-search-stores")
    private WebElement FIELD_searchButton;

    public StoreSearchPage(WebDriver driver) {
        super(driver);
    }

    public boolean isSearchFieldPresent() {
        return FIELD_geoSearch.isDisplayed();
    }

    public void enterSearchToken(String token) {
        if (isIOS()) {
            token = token + "\n";
        }
        FIELD_geoSearch.clear();
        FIELD_geoSearch.sendKeys(token);
        if (isAndroid()) {
            clickSearchButtonOnly();
        }
    }

    public void clickSearchButtonOnly() {
        FIELD_searchButton.click();
    }

    //@TODO
//    public void selectStore(String storeFragment) {
//        String xpath = String.format("(//XCUIElementTypeOther[contains(@name,'%s')])", storeFragment);
//        new WebDriverWait(getDriver(), 10).until(ExpectedConditions.visibilityOfElementLocated(MobileBy.xpath(xpath)));
//        int index = getDriver().findElements(By.xpath(xpath)).size();
//        System.out.println(">>> Select Store index = " + index);
//        String suffix = String.format("[%s]", index);
//        getDriver().findElement(By.xpath(xpath + suffix)).click();
//    }
//
//    private String XPATH_storeNumberPattern = "(//android.widget.Button[@content-desc=\"touchable-store-detail\"])[%d]/android.widget.TextView[1]";
//    public void selectStore(int storeNumber) {
//        String xpath = String.format(XPATH_storeNumberPattern, storeNumber);
//        new WebDriverWait(getDriver(), 25)
//                .until(ExpectedConditions
//                        .visibilityOfElementLocated(MobileBy.xpath(xpath)))
//                .click();
//    }
}

