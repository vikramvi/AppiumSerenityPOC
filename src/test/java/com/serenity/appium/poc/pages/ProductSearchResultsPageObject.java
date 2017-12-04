package com.serenity.appium.poc.pages;

import com.serenity.appium.poc.utils.Utils;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSFindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ProductSearchResultsPageObject extends MobilePageObject {

    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc=\"search results count\"]/android.widget.TextView")
    @iOSFindBy(accessibility = "search results count")
    private WebElement TEXT_searchResultsCount;

    public String getResultsCount() {
        String itemCount = TEXT_searchResultsCount.getText();
        return itemCount.replace(" ITEM", "");
    }

    private String XPATH_productName = "//android.widget.TextView[3]";
    private String XPATH_PATTERN_productNameOfMany = "//android.view.ViewGroup[%d]/android.widget.TextView[3]";

    public String getProductNameForAndroid(int productNumber) {
        String result = "NOT FOUND!";
        if (isAndroid()) {
            int index = productNumber - 1;
            String xpath = String.format(XPATH_PATTERN_productNameOfMany, index);
            result = getDriver().findElement(By.xpath(xpath)).getText();
        } else {
            throw new IllegalStateException("getProductNameForAndroid not supported for iOS!");
        }
        return result;
    }

    public boolean selectProductForAndroid(int productNumber) {
        if ((productNumber == 1) && (getResultsCount().equals("1"))) {
            return Utils.tryClicking(XPATH_productName);
        } else {
            int index = productNumber - 1;
            String xpath = String.format(XPATH_PATTERN_productNameOfMany, index);
            return Utils.tryClicking(xpath);
        }
    }

    public boolean selectProductForAndroid(String productName) {
        boolean found = false;
        try {
            List<WebElement> productsFound = getDriver().findElements(By.xpath(XPATH_productName));
            int counter = 0;
            for (WebElement element : productsFound) {
                String value = element.getText();
                System.out.println(">>> index = " +counter+ ", value = " +value);
                if (element.getText().equalsIgnoreCase(productName)) {
                    element.click();
                    found = true;
                    break;
                }
                counter++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return found;
    }


    public ProductSearchResultsPageObject(WebDriver driver) {
        super(driver);
    }
}
