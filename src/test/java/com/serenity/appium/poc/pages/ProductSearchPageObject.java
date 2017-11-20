package com.serenity.appium.poc.pages;

import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSFindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ProductSearchPageObject extends MobilePageObject {

    //    @AndroidFindBy(xpath="//android.widget.EditText[@content-desc='active-search-field']") // pending tw_mobile update
    @AndroidFindBy(xpath="//android.widget.EditText[@text='WHAT CAN WE HELP YOU FIND?']")
    @iOSFindBy(xpath="//XCUIElementTypeOther[contains(@name,'WHAT CAN WE HELP YOU FIND?')]")
    private WebElement FIELD_searchProduct;

    public ProductSearchPageObject(WebDriver driver) { super(driver); }

    public boolean typeSearchTerm(String productName){
        try {
            FIELD_searchProduct.sendKeys(productName);
            return true;
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

}
