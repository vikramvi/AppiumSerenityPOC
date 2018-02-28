package com.serenity.appium.poc.pages;

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

public class ProductSearchResultsPageObject extends MobilePageObject {

    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc=\"search results count\"]/android.widget.TextView")
    @iOSFindBy(accessibility = "search results count")
    private WebElement TEXT_searchResultsCount;

    @AndroidFindBy(accessibility =  "button-floating-return")
    @iOSFindBy(accessibility = "button-floating-return")
    private WebElement BUTTON_return;

    public boolean clickReturn() {
        return Utils.tryClicking(BUTTON_return);
    }

    private final String REGEX_count = "([0-9]*?,?[0-9]+)(\\sITEMS?)";
    public String getResultsCount() {
        String result = "NOT FOUND!";
        String itemCount = TEXT_searchResultsCount.getText();
        Pattern pattern = Pattern.compile(REGEX_count);
        Matcher matcher = pattern.matcher(itemCount);
        if (matcher.find()) {
            result = matcher.group(1);
        } else {
            throw new IllegalStateException("No match for item count!");
        }
        return result;
    }

    public int getResultsCountInteger() {
        String itemCount = getResultsCount();
        int result = Integer.parseInt(itemCount);
        return result;
    }

    private boolean isSoloSearchResult() {
        return getResultsCountInteger() == 1;
    }

//    private String XPATH_PATTERN_soloProductAttribute = "//android.widget.ScrollView//android.widget.TextView[%d]";
//    private String XPATH_PATTERN_productAttributeOfMany =
//            "//android.widget.ScrollView//android.view.ViewGroup[%d]//android.view.ViewGroup[%d]/android.widget.TextView[%d]";
//
//    private String getAndroidProductAttributeXpath(int productNumber, int attributeNumber) {
//        String xpath = null;
//        if (isSoloSearchResult()) {
//            if (productNumber == 1) {
//                xpath = String.format(XPATH_PATTERN_soloProductAttribute, attributeNumber);
//            } else {
//                throw new IllegalStateException("Cannot produce xpath for product number " + productNumber + " when only 1 result exists!");
//            }
//        } else {
//            int row = 0;
//            int col = 0;
//            switch (productNumber) {
//                case 1: row = 1;
//                        col = 1;
//                        break;
//                case 2: row = 1;
//                        col = 2;
//                        break;
//                case 3: row = 2;
//                        col = 1;
//                        break;
//                case 4: row = 2;
//                        col = 2;
//                        break;
//            }
//            xpath = String.format(XPATH_PATTERN_productAttributeOfMany, row, col, attributeNumber);
//        }
//        return xpath;
//    }

//    private String getAndroidProductAttribute(int productNumber, int attributeNumber) {
//        String result = "NOT FOUND!";
//        String xpath = getAndroidProductAttributeXpath(productNumber, attributeNumber);
//        result = getDriver().findElement(By.xpath(xpath)).getText();
//        return result;
//    }

    private boolean isProductRating(String data) {
        return (data.length() > 1) && (data.length() < 4) && (StringUtils.isNumeric(data));
    }

//    public boolean isAndroidProductScorePresent(int productNumber) {
//        String data = getAndroidProductAttribute(productNumber, 1);
//        boolean result = isProductRating(data);
//        return result;
//    }


//    public boolean isAndroidProductScorePresent(int productNumber) {
//        String data = getAndroidProductAttribute(productNumber, 1);
//        boolean result = isProductRating(data);
//        return result;
//    }

    private String XPATH_PATTERN_productScore =
            "(//android.view.ViewGroup[@content-desc=\"product-price\"])[%d]/preceding-sibling::android.view.ViewGroup[@content-desc='product-rating']";
    public boolean isAndroidProductScorePresent(int productNumber) {
        String xpath = String.format(XPATH_PATTERN_productScore, productNumber);
        int count = getDriver().findElements(By.xpath(xpath)).size();
        return (count > 0);
    }

//    public String getAndroidProductScore(int productNumber) {
//        String score = getAndroidProductAttribute(productNumber, 1);
//        return score;
//    }

    public String getAndroidProductScore(int productNumber) {
        String xpath = String.format(XPATH_PATTERN_productScore, productNumber);
        String score = getDriver().findElement(By.xpath(xpath)).getText();
        return score;
    }

//    private String getAndroidProductPriceNominee(int productNumber) {
//        int attributeNumber = 1;
//        if (isAndroidProductScorePresent(productNumber)) {
//            attributeNumber++;
//        }
//        String nominee = getAndroidProductAttribute(productNumber, attributeNumber);
//        return nominee;
//    }
//
//    public boolean isAndroidProductPricePresent(int productNumber) {
//        String nominee = getAndroidProductPriceNominee(productNumber);
//        boolean result = nominee.startsWith("$") && nominee.contains(".");
//        return result;
//    }
//
//    public String getAndroidProductPrice(int productNumber) {
//        String result = "NOT FOUND!";
//        if (isAndroidProductPricePresent(productNumber)) {
//            result = getAndroidProductPriceNominee(productNumber);
//        } else {
//            throw new IllegalStateException("Cannot return price for product #" + productNumber + " when none exists!");
//        }
//        return result;
//    }

    private String XPATH_PATTERN_productPrice = "(//android.view.ViewGroup[@content-desc='product-price'])[%d]/android.widget.TextView";
    public String getAndroidProductPrice(int productNumber) {
        String xpath = String.format(XPATH_PATTERN_productPrice, productNumber);
        String result = getDriver().findElement(By.xpath(xpath)).getText();
        return result;
    }

//    public String getAndroidProductName(int productNumber) {
//        int attributeNumber = 2;
//        if (isAndroidProductScorePresent(productNumber)) {
//            attributeNumber++;
//        }
//        String name = getAndroidProductAttribute(productNumber, attributeNumber);
//        return name;
//    }

    private String XPATH_PATTERN_productName = "(//android.widget.TextView[@content-desc=\"product-name\"])[%d]";
    public String getAndroidProductName(int productNumber) {
        String xpath = String.format(XPATH_PATTERN_productName, productNumber);
        String result = getDriver().findElement(By.xpath(xpath)).getText();
        return result;
    }

//    public String getAndroidProductSize(int productNumber) {
//        int attributeNumber = 3;
//        if (isAndroidProductScorePresent(productNumber)) {
//            attributeNumber++;
//        }
//        String size = getAndroidProductAttribute(productNumber, attributeNumber);
//        return size;
//    }

    private String XPATH_PATTERN_productSize = "(//android.widget.TextView[@content-desc=\"product-packaging\"])[%d]";
    public String getAndroidProductSize(int productNumber) {
        String xpath = String.format(XPATH_PATTERN_productSize, productNumber);
        String result = getDriver().findElement(By.xpath(xpath)).getText();
        return result;
    }

//    public boolean selectProductForAndroid(int productNumber) {
//        String xpath = null;
//        if (isSoloSearchResult()) {
//            if (productNumber == 1) {
//                xpath = String.format(XPATH_PATTERN_soloProductAttribute, 1);
//            } else {
//                throw new IllegalStateException("Trying to click product #" + productNumber + " when only 1 exists!");
//            }
//        } else {
//            xpath = String.format(XPATH_PATTERN_productAttributeOfMany, productNumber, 1);
//        }
//        return Utils.tryClicking(xpath);
//    }

    public boolean selectProductForAndroid(int productNumber) {
        String xpath = String.format(XPATH_PATTERN_productName, productNumber);
        return Utils.tryClicking(xpath);
    }

    public boolean selectProductForAndroid(String productName) {
        boolean found = false;
        try {
            int max = getResultsCountInteger();
            for (int i = 1; i <= max; i++) {
                String value = getAndroidProductName(i);
                System.out.println(">>> index = " + i + ", value = " + value);
                if (value.equalsIgnoreCase(productName)) {
                    selectProductForAndroid(i);
                    found = true;
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return found;
    }


//------------------ iOS -->

    private By BY_iosResultStream = MobileBy.xpath("//XCUIElementTypeScrollView/XCUIElementTypeOther");
    private String getIosResultStream() {
        String stream = getDriver().findElement(BY_iosResultStream).getAttribute("name");
        return stream;
    }

    public boolean isTokenPresentInResults(String token) {
        boolean found = false;
        int i = 1;
        int count = getResultsCountInteger();
        int max =  count > 4 ? 4 : count;
        String name = getAndroidProductName(i);
        found = name.contains(token.toUpperCase());
        System.out.println("i=" +i+ "; name=" +name+ "; token=" +token);
        while ((i<max) && found) {
            i++;
            name = getAndroidProductName(i);
            found = name.contains(token.toUpperCase());
            System.out.println("i=" +i+ "; name=" +name+ "; token=" +token);
        }
        return found;
    }

    public ProductSearchResultsPageObject(WebDriver driver) {
        super(driver);
    }

}
