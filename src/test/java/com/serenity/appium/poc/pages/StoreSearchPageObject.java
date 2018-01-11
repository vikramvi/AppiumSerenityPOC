package com.serenity.appium.poc.pages;

import com.serenity.appium.poc.utils.Scrolling;
import com.serenity.appium.poc.utils.Utils;
import io.appium.java_client.MobileBy;
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
//    @iOSFindBy(xpath = "(//XCUIElementTypeOther[starts-with(@name, '\uE820')])[4]")
//    @iOSFindBy(xpath = "//XCUIElementTypeOther[starts-with(@name,'\uE820') and ends-with(@name,'SEARCH')]")
//    @iOSFindBy(accessibility = "CITY, STATE OR ZIP")
    private WebElement FIELD_geoSearch;

    @AndroidFindBy(accessibility = "button-search-stores")
    @iOSFindBy(accessibility = "SEARCH")
    private WebElement FIELD_searchButton;

    public StoreSearchPageObject(WebDriver driver) {
        super(driver);
    }

    private final String defaultSearchBoxText = "CITY, STATE OR ZIP";
    private String searchBoxAccessibilityPattern = "\uE820 %s SEARCH";
    private WebElement getIosSearchBoxElement(String oldSearchText) {
        WebElement result;
        String text = null;
        if (oldSearchText.isEmpty()) {
            text = String.format(searchBoxAccessibilityPattern, defaultSearchBoxText);
        } else {
            text = String.format(searchBoxAccessibilityPattern, oldSearchText);
        }
        return getDriver().findElement(MobileBy.AccessibilityId(text));
    }

    public boolean isSearchFieldPresent() {
//        boolean result = FIELD_geoSearch.isDisplayed();
        boolean result = Utils.isVisible(getDriver(), FIELD_geoSearch, 2);
        return result;
    }

    By BY_searchField = By.xpath("(//XCUIElementTypeOther[starts-with(@name, '\uE820')])[4]");
    public boolean enterSearchToken(String token){ //}, String oldToken) {
        try {
            if (isIOS()) {
                token = token + "\n";
//                WebElement searchBox = getIosSearchBoxElement(oldToken);
//                searchBox.sendKeys(token);
//                FIELD_geoSearch.sendKeys(token);
                //getDriver().findElement(By.xpath("(//XCUIElementTypeOther[starts-with(@name, '\uE820')])[4]")).sendKeys(token);
                new WebDriverWait(getDriver(), 10).until(ExpectedConditions.visibilityOfElementLocated(BY_searchField));
                getDriver().findElement(BY_searchField).sendKeys(token);
            } else {
                FIELD_geoSearch.sendKeys(token);
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
                String xpath = String.format(XPATH_PATTERN_iosStoreName, storeFragment.toUpperCase());
                new WebDriverWait(getDriver(), 10).until(ExpectedConditions.visibilityOfElementLocated(MobileBy.xpath(xpath)));
                int index = getDriver().findElements(By.xpath(xpath)).size();
                System.out.println(">>> Select Store index = " + index);
                String suffix = String.format("[%s]", index);
                getDriver().findElement(By.xpath(xpath + suffix)).click();
            } else {
                new WebDriverWait(getDriver(), 10).until(ExpectedConditions.visibilityOfElementLocated(MobileBy.xpath(XPATH_androidStoreName)));
                int i=0;
                boolean found = false;
                while ((!found) && (i<3)) {
                    List<WebElement> elements = getDriver().findElements(By.xpath(XPATH_androidStoreName));
                    for (WebElement element:elements) {
                        String storeName = element.getText();
                        System.out.println("StoreName = " + storeName);
                        if (storeName.equalsIgnoreCase(storeFragment)) {
                            element.click();
                            found = true;
                        }
                    }
                    if (!found) {
                        Scrolling.androidSwipe(Scrolling.AndroidDirection.DOWN);
                    }
                    i++;
                }
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}

