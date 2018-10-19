package com.serenity.appium.poc.pages;

import com.serenity.appium.poc.utils.Utils;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidKeyCode;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSFindBy;
import net.thucydides.core.webdriver.WebDriverFacade;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static net.thucydides.core.webdriver.ThucydidesWebDriverSupport.getDriver;

public class ProductSearchPageObject extends MobilePageObject {

    @AndroidFindBy(accessibility = "search field")
    @iOSFindBy(accessibility="search view")
    private WebElement FIELD_searchProduct;

    @AndroidFindBy(xpath = "//android.widget.ScrollView/android.view.ViewGroup//android.widget.TextView[@text='POPULAR SEARCH TERMS']")
    private WebElement popularSearchTerms;

    public ProductSearchPageObject(WebDriver driver) { super(driver); }

    public boolean typeSearchTerm(String productName){
        try {
            if(Utils.isVisible(getDriver(), FIELD_searchProduct, 10)) {
                FIELD_searchProduct.sendKeys(productName);
                return true;
            }
                return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean enterSearchTerm(String productName){
        boolean result = false;
        String token = productName;
        try {
            token += isIOS() ? "\n" : "";
            result = typeSearchTerm(token);
            if (result && isAndroid()) {
                WebDriver facade = getDriver();
                WebDriver driver = ((WebDriverFacade) facade).getProxiedDriver();
                ((AndroidDriver)driver).pressKeyCode(66);
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private String XPATH_PATTERN_suggestedSearchTerm = "//*[@*='%s']";
    public boolean selectSearchSuggestion(String productName) {
        try {
            String xpath = String.format(XPATH_PATTERN_suggestedSearchTerm, productName);
            getDriver().findElement(By.xpath(xpath)).click();
            // TODO: the following was inserted just to see if it worked.  It does not because the element refers to the whole row
            //         even if there is only a single search result.
            //getDriver().findElement(By.xpath("(//XCUIElementTypeOther[@name=\"$215.99 KRUG VINTAGE 750ML\"])[3]")).click();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean isSearchFieldPresent() {
        if (!Utils.isVisible(getDriver(), popularSearchTerms, 20)) {
            if (isAndroid()) {
                //sometimes keyboard overlaps item search field which results in failure
                if(!Utils.isVisible(getDriver(), FIELD_searchProduct, 3)) {
                    LOGGER.info("Android Keyboard overlapping item search input field");
                    ((AndroidDriver) ((WebDriverFacade) getDriver()).getProxiedDriver()).hideKeyboard();
                    Utils.waitFor(500);
                    return Utils.isVisible(getDriver(), FIELD_searchProduct, 3);
                }
            } else {
                //iOS TDB
            }
        }
        return true;
    }
}
