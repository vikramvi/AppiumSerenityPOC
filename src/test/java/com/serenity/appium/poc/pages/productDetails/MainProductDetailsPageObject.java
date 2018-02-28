package com.serenity.appium.poc.pages.productDetails;

import com.serenity.appium.poc.pages.MobilePageObject;
import com.serenity.appium.poc.utils.Utils;
import io.appium.java_client.MobileBy;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSFindBy;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainProductDetailsPageObject extends MobilePageObject {

    @AndroidFindBy(accessibility =  "product-details-name")
    @iOSFindBy(accessibility = "product-details-name")
    private WebElement TEXT_productName;

    @AndroidFindBy(accessibility =  "product-details-price-normal")
    @iOSFindBy(accessibility = "product-details-price-normal")
    private WebElement TEXT_everydayLowPrice;

    @AndroidFindBy(accessibility =  "button-shopping-options")
    @iOSFindBy(accessibility = "button-shopping-options")
    private WebElement BUTTON_addToCart;

    @AndroidFindBy(accessibility =  "button-floating-return")
    @iOSFindBy(accessibility = "button-floating-return")
    private WebElement BUTTON_return;

    public boolean clickReturn() {
        return Utils.tryClicking(BUTTON_return);
    }
    public boolean clickAddToCart() {
        return Utils.tryClicking(BUTTON_addToCart);
    }

//    private final String REGEX_count = "([0-9]*?,?[0-9]+)(\\sITEMS?)";
//    public String getResultsCount() {
//        String result = "NOT FOUND!";
//        String itemCount = TEXT_searchResultsCount.getText();
//        Pattern pattern = Pattern.compile(REGEX_count);
//        Matcher matcher = pattern.matcher(itemCount);
//        if (matcher.find()) {
//            result = matcher.group(1);
//        } else {
//            throw new IllegalStateException("No match for item count!");
//        }
//        return result;
//    }
//
//    public int getResultsCountInteger() {
//        String itemCount = getResultsCount();
//        int result = Integer.parseInt(itemCount);
//        return result;
//    }
//
//    private boolean isSoloSearchResult() {
//        return getResultsCountInteger() == 1;
//    }

    private boolean isProductRating(String data) {
        return (data.length() > 1) && (data.length() < 4) && (StringUtils.isNumeric(data));
    }

    private String XPATH_PATTERN_productScore =
            "(//android.view.ViewGroup[@content-desc=\"product-price\"])[%d]/preceding-sibling::android.view.ViewGroup[@content-desc='product-rating']";
    public boolean isAndroidProductScorePresent(int productNumber) {
        String xpath = String.format(XPATH_PATTERN_productScore, productNumber);
        int count = getDriver().findElements(By.xpath(xpath)).size();
        return (count > 0);
    }

    public String getAndroidProductScore(int productNumber) {
        String xpath = String.format(XPATH_PATTERN_productScore, productNumber);
        String score = getDriver().findElement(By.xpath(xpath)).getText();
        return score;
    }

    public String getAndroidProductPrice() {
        String result = TEXT_everydayLowPrice.getText();
        return result;
    }

    private By BY_productFee = MobileBy.AccessibilityId("product-details-fee");
    public String getAndroidProductFee() {
        String result = getDriver().findElement(BY_productFee).getText();
        return result;
    }

    public String getAndroidProductName() {
        String result = TEXT_productName.getText();
        return result;
    }

    private String XPATH_PATTERN_productSize = "(//android.widget.TextView[@content-desc=\"product-packaging\"])[%d]";
    public String getAndroidProductSize(int productNumber) {
        String xpath = String.format(XPATH_PATTERN_productSize, productNumber);
        String result = getDriver().findElement(By.xpath(xpath)).getText();
        return result;
    }

    public boolean selectProductForAndroid(int productNumber) {
        return Utils.tryClicking(TEXT_productName);
    }


//------------------ iOS -->


    public MainProductDetailsPageObject(WebDriver driver) {
        super(driver);
    }

}
