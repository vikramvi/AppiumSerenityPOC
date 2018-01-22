package com.serenity.appium.poc.pages;

import com.serenity.appium.poc.utils.Scrolling;
import com.serenity.appium.poc.utils.StoreDataParser;
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
import java.util.StringJoiner;

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
        boolean result = Utils.isVisible(getDriver(), FIELD_geoSearch, 2);
        return result;
    }

    By BY_searchField = By.xpath("(//XCUIElementTypeOther[starts-with(@name, '\uE820')])[4]");
    public boolean enterSearchToken(String token) {
        try {
            if (isIOS()) {
                token = token + "\n";
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

    private String XPATH_PATTERN_iosStoreTitle = "(//XCUIElementTypeOther[contains(@name,'%s')])";
    private final String XPATH_androidStoreTitle = "//android.widget.TextView[@content-desc='store-title']";
    public boolean selectStore(String storeFragment) {
        try {
            if (isIOS()) {
                //Scrolling.iosScroll(Scrolling.IosDirection.DOWN);
                String xpath = String.format(XPATH_PATTERN_iosStoreTitle, storeFragment.toUpperCase());
                new WebDriverWait(getDriver(), 10).until(ExpectedConditions.visibilityOfElementLocated(MobileBy.xpath(xpath)));
                int index = getDriver().findElements(By.xpath(xpath)).size();
                System.out.println(">>> Select Store index = " + index);
                String suffix = String.format("[%s]", index);
                getDriver().findElement(By.xpath(xpath + suffix)).click();
            } else {
                new WebDriverWait(getDriver(), 10).until(ExpectedConditions.visibilityOfElementLocated(MobileBy.xpath(XPATH_androidStoreTitle)));
                int i=0;
                boolean found = false;
                while ((!found) && (i<3)) {
                    List<WebElement> elements = getDriver().findElements(By.xpath(XPATH_androidStoreTitle));
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

    private String XPATH_PATTERN_iosStoreTitleInList = "(//XCUIElementTypeOther[starts-with(@name,'%s')])[2]";
    public String getStoreDataForIos(String storeTitle) {
        String result = noResultsFound;
        try {
            String xpath = String.format(XPATH_PATTERN_iosStoreTitleInList, storeTitle.toUpperCase());
            result = getDriver().findElement(By.xpath(xpath)).getText();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public String getAddress1FromStoreDataForIos(String storeTitle) {
        String result = noResultsFound;
        try {
            String stream = getStoreDataForIos(storeTitle);
            result = StoreDataParser.getAddress(stream).trim();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public String getOpenCloseHourFromStoreDataForIos(String storeTitle) {
        String result = noResultsFound;
        try {
            String stream = getStoreDataForIos(storeTitle);
            result = StoreDataParser.getOpenCloseHour(stream);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    private int getStoreIndexForAndroid(String expectedTitle) {
        int result = -1;
        for (int i=1; i<=2; i++) {
            String xpath = StoreListData.TITLE.getXpath(getDriver(), i);
            if (Utils.isVisible(getDriver(), By.xpath(xpath), 2)) {
                String title = StoreListData.TITLE.getValue(getDriver(), i);
                if (title.equalsIgnoreCase(expectedTitle)) {
                    result = i;
                }
            }
        }
        return result;
    }

    public enum StoreListData {
        TITLE("(//android.widget.TextView[@content-desc=\"store-title\"])[%d]"),
        ADDRESS1("(//android.widget.TextView[@content-desc=\"store-address1\"])[%d]"),
        ADDRESS2("(//android.widget.TextView[@content-desc=\"store-address2\"])[%d]"),
        CITY_STATE_ZIP("(//android.widget.TextView[@content-desc=\"store-city-state-zip\"])[%d]"),
        PHONE_NUMBER("(//android.widget.TextView[@content-desc=\"touchable-call-phoneNumber\"])[%d]"),
        OPEN_CLOSE_HOUR("(//android.widget.TextView[@content-desc=\"store-hours\"])[%d]");
        String pattern;
        StoreListData(String pattern) {
            this.pattern = pattern;
        }
        String getValue(WebDriver driver, int index) {
            String result = getElement(driver, index).getText();
            return result;
        }
        WebElement getElement(WebDriver driver, int index) {
            String xpath = String.format(pattern, index);
            WebElement result = driver.findElement(By.xpath(xpath));
            return result;
        }
        String getXpath(WebDriver driver, int index) {
            String result = String.format(pattern, index);
            return result;
        }
    }

    public boolean verifyStoreInList(String title, String address1, String address2, String cityStateZip, String phoneNumber, String openCloseHour) {
        boolean result = false;
        String xpath = "";
        try {
            if (isIOS()) {
                String stream = getStoreDataForIos(title);
                StringJoiner join = new StringJoiner(" ");
                join.add(title.toUpperCase()).add(address1).add(address2).add(cityStateZip).add(phoneNumber).add(openCloseHour);
                String expected = join.toString();
                result = stream.contains(expected);
            } else {
                int index = getStoreIndexForAndroid(title);
                if (index > 0) {
                    String actualAddress1 = StoreListData.ADDRESS1.getValue(getDriver(), index);
                    if (address1.equalsIgnoreCase(actualAddress1)) {
                        String actualAddress2 = StoreListData.ADDRESS2.getValue(getDriver(), index);
                        if (address2.equalsIgnoreCase(actualAddress2)) {
                            String actualCityStateZip = StoreListData.CITY_STATE_ZIP.getValue(getDriver(), index);
                            if (cityStateZip.equalsIgnoreCase(actualCityStateZip)) {
                                String actualPhoneNumber = StoreListData.PHONE_NUMBER.getValue(getDriver(), index);
                                if (phoneNumber.equalsIgnoreCase(actualPhoneNumber)) {
                                    String actualOpenCloseHour = StoreListData.OPEN_CLOSE_HOUR.getValue(getDriver(), index);
                                    if (openCloseHour.equalsIgnoreCase(actualOpenCloseHour)) {
                                        result = true;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return result;
    }
}

