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
//    @iOSFindBy(accessibility = "\uE820 CITY, STATE OR ZIP SEARCH")
    @iOSFindBy(accessibility = "\uE835 CITY, STATE OR ZIP SEARCH")
    private WebElement FIELD_geoSearch;

    @AndroidFindBy(accessibility = "button-search-stores")
    //@iOSFindBy(accessibility = "SEARCH")
    private WebElement FIELD_searchButton;

    @AndroidFindBy(accessibility =  "button-floating-return")
    @iOSFindBy(accessibility = "button-floating-return")
    private WebElement BUTTON_return;

    public StoreSearchPageObject(WebDriver driver) {
        super(driver);
    }

    public boolean clickReturn() {
        return Utils.tryClicking(BUTTON_return);
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
        boolean result = Utils.isVisible(getDriver(), FIELD_geoSearch, 25);
        if(!result){
            LOGGER.error("Change location call failed after 25 seconds");
        }
        return result;
    }

//    public boolean isAndroidSearchButtonPresent() {
//        boolean result = Utils.isVisible(getDriver(), FIELD_searchButton, 2);
//        return result;
//    }
//
//    By BY_searchField = By.xpath("(//XCUIElementTypeOther[starts-with(@name, '\uE820')])[4]");
    By BY_searchField = By.xpath("(//XCUIElementTypeOther[starts-with(@name, '\uE835')])[4]");
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

    private String XPATH_PATTERN_iosStoreTitle = "(//XCUIElementTypeOther[starts-with(@name,'%s')])";
    private String XPATH_iosStoreListTag = "//XCUIElementTypeOther[ends-with(@name,'SHOP \uE81B')]";
    private final String XPATH_androidStoreTitle = "//android.widget.TextView[@content-desc='store-title']";
    public boolean selectStore(String completeStoreName) {
        try {
            boolean found = false;
            int i=0;
            if (isIOS()) {

                new WebDriverWait(getDriver(), 10)
                    .until(ExpectedConditions.visibilityOf(BUTTON_return));

                String xpath = String.format(XPATH_PATTERN_iosStoreTitle, completeStoreName.toUpperCase());
                found = Utils.isLastInstanceVisible(getDriver(), xpath);
                while ((!found) && (i<3)) {
                    Scrolling.iosScroll(Scrolling.IosDirection.DOWN);
                    found = Utils.isLastInstanceVisible(getDriver(), xpath);
                    i++;
                }
                if (found) {
                    int lastInstance = getDriver().findElements(By.xpath(xpath)).size();
                    System.out.println(">>> Select Store index = " + lastInstance);
                    String suffix = String.format("[%s]", lastInstance);
                    getDriver().findElement(By.xpath(xpath + suffix)).click();
                } else {
                    System.out.println(">>>>> store not found!");
                }
            } else {
                    String xPathFirstStore = "//android.widget.Button[@content-desc='touchable-store-detail'][1]";
                    if( !Utils.isVisible(getDriver(), getDriver().findElement(By.xpath(xPathFirstStore)), 25) &&
                        !Utils.isClickable(getDriver(), BUTTON_return, 25)){
                        LOGGER.error("search page did not load completely");
                        return false;
                    }

                    while ((!found) && (i<3)) {
                        List<WebElement> elements = getDriver().findElements(By.xpath(XPATH_androidStoreTitle));
                        for (WebElement element:elements) {
                            String storeName = element.getText();
                            System.out.println("StoreName = " + storeName);
                            if (storeName.equalsIgnoreCase(completeStoreName)) {
                                element.click();
                                found = true;
                                break;
                            }
                        }
                        if (!found) {
                            Scrolling.androidSwipe(Scrolling.AndroidDirection.DOWN);
                        }
                        i++;
                    }
            }
            return found;
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
            if (Utils.isVisible(getDriver(), By.xpath(xpath), 10)) {
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
                join.add(title.toUpperCase()).add(address1).add(address2).add(cityStateZip).add(openCloseHour).add(phoneNumber);
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

    String XPathForSelectStoreButton = "//android.widget.Button[@content-desc='button-select-preferred-store'][1]";

    public void clickFirstRowSelectStoreButton(){
        getDriver().findElements(By.xpath(XPathForSelectStoreButton)).get(0).click();
    }

}

