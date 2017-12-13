package com.serenity.appium.poc.pages;

import com.openhtmltopdf.util.Util;
import com.serenity.appium.poc.utils.Scrolling;
import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSFindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class StoreSearchPageObject extends MobilePageObject {


    @AndroidFindBy(accessibility = "field-search-stores")
    @iOSFindBy(accessibility = "\uE820 CITY, STATE OR ZIP SEARCH")
//    @iOSFindBy(accessibility = "CITY, STATE OR ZIP")
//    @iOSFindBy(xpath = "//XCUIElementTypeOther[@name=\"\uE820 CITY, STATE OR ZIP SEARCH\"]")
//    @iOSFindBy(xpath = "(//XCUIElementTypeOther[@name=\"CITY, STATE OR ZIP\"])[4]")
//    @iOSFindBy(xpath = "//XCUIElementTypeOther[@name=\"\uE820 CITY, STATE OR ZIP SEARCH\"]")
    private WebElement FIELD_geoSearch;

    @AndroidFindBy(accessibility = "button-search-stores")
    @iOSFindBy(accessibility = "SEARCH") // not needed if \n is added on search term
    private WebElement FIELD_searchButton;

    public StoreSearchPageObject(WebDriver driver) {
        super(driver);
    }

    public boolean isSearchFieldPresent() {
        boolean result = FIELD_geoSearch.isDisplayed();
        return result;
    }

    public boolean enterSearchToken(String token) {
        try {
            if (isIOS()) {
                token = token + "\n";
            }
            FIELD_geoSearch.sendKeys(token);
            if (isAndroid()) {
                clickSearchButtonOnly();
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public void clickSearchButtonOnly() {
        FIELD_searchButton.click();
    }

    private String XPATH_PATTERN_iosStoreName = "(//XCUIElementTypeOther[contains(@name,'%s')])";
    private final String XPATH_androidStoreName = "//android.widget.TextView[@content-desc='store-address1']";
    public boolean selectStore(String storeFragment) {
        try {
            if (isIOS()) {
                //Scrolling.iosScroll(Scrolling.IosDirection.DOWN);
                String xpath = String.format(XPATH_PATTERN_iosStoreName, storeFragment);
                new WebDriverWait(getDriver(), 10).until(ExpectedConditions.visibilityOfElementLocated(MobileBy.xpath(xpath)));
                int index = getDriver().findElements(By.xpath(xpath)).size();
                System.out.println(">>> Select Store index = " + index);
                String suffix = String.format("[%s]", index);
                getDriver().findElement(By.xpath(xpath + suffix)).click();
            } else {
//                Util.sleep(10000);
                new WebDriverWait(getDriver(), 10).until(ExpectedConditions.visibilityOfElementLocated(MobileBy.xpath(XPATH_androidStoreName)));
                Scrolling.androidSwipe(Scrolling.AndroidDirection.DOWN);
                List<WebElement> elements = getDriver().findElements(By.xpath(XPATH_androidStoreName));
                for (WebElement element:elements) {
                    if (element.getText().equalsIgnoreCase(storeFragment)) {
                        element.click();
                        break;
                    }
                }
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
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

