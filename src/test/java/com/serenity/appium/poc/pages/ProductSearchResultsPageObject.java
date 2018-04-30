package com.serenity.appium.poc.pages;

import com.serenity.appium.poc.utils.Enums;
import com.serenity.appium.poc.utils.IosPlpDataParser;
import com.serenity.appium.poc.utils.IosPlpProductSelector;
import com.serenity.appium.poc.utils.Utils;
import io.appium.java_client.MobileBy;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSFindBy;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
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
        String itemCount = getResultsCount().replace(",", "");
        int result = Integer.parseInt(itemCount);
        return result;
    }

    private boolean isSoloSearchResult() {
        return getResultsCountInteger() == 1;
    }

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

    private String XPATH_PATTERN_productPrice = "(//android.view.ViewGroup[@content-desc='product-price'])[%d]/android.widget.TextView";
    public String getAndroidProductPrice(int productNumber) {
        String xpath = String.format(XPATH_PATTERN_productPrice, productNumber);
        String result = getDriver().findElement(By.xpath(xpath)).getText();
        return result;
    }

    private String XPATH_PATTERN_productFee = "(//android.view.ViewGroup[@content-desc='product-price'])[%d]/android.widget.TextView[2]";
    public String getAndroidProductFee(int productNumber) {
        String xpath = String.format(XPATH_PATTERN_productFee, productNumber);
        String result = getDriver().findElement(By.xpath(xpath)).getText();
        return result;
    }

    private String XPATH_PATTERN_productName = "(//android.widget.TextView[@content-desc=\"product-name\"])[%d]";
    public String getAndroidProductName(int productNumber) {
        String xpath = String.format(XPATH_PATTERN_productName, productNumber);
        String result = getDriver().findElement(By.xpath(xpath)).getText();
        return result;
    }

    private String XPATH_PATTERN_productSize = "(//android.widget.TextView[@content-desc=\"product-packaging\"])[%d]";
    public String getAndroidProductSize(int productNumber) {
        String xpath = String.format(XPATH_PATTERN_productSize, productNumber);
        String result = getDriver().findElement(By.xpath(xpath)).getText();
        return result;
    }

    public boolean selectProductForAndroid(int productNumber) {
        String xpath = String.format(XPATH_PATTERN_productName, productNumber);
        return Utils.tryClicking(xpath);
    }

    //------------------ iOS -->

//    private static String productNamesRegex = "((\\w+\\s?)+)\\!((\\w+\\s?)+)?";
    private static String productNamesRegex = "(product-name-|product-names-)((\\w+\\.?-?\\s?)+)\\!((\\w+\\.?-?\\s?)+)?";
    public boolean selectProduct(String productName) {
        boolean found = false;
        if (isAndroid()) {
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
        } else {
            for (int i=1; i<=2; i++) {
                String actualNames = getIosProductNames(i);
                Pattern pattern = Pattern.compile(productNamesRegex);
                Matcher matcher = pattern.matcher(actualNames);
                if (matcher.find()) {
                    String name1 = matcher.group(2);
                    if (name1.equalsIgnoreCase(productName)) {
                        if (i==1) {
                            IosPlpProductSelector.selectProduct(IosPlpProductSelector.ProductPosition.FIRST_ITEM_1ST_ROW);
                        } else {
                            IosPlpProductSelector.selectProduct(IosPlpProductSelector.ProductPosition.FIRST_ITEM_2ND_ROW);
                        }
                        found = true;
                        break;
                    } else if (matcher.group(4) != null) {
                        String name2 = matcher.group(4);
                        if (name2.equalsIgnoreCase(productName)) {
                            if (i==1) {
                                IosPlpProductSelector.selectProduct(IosPlpProductSelector.ProductPosition.SECOND_ITEM_1ST_ROW);
                            } else {
                                IosPlpProductSelector.selectProduct(IosPlpProductSelector.ProductPosition.SECOND_ITEM_2ND_ROW);
                            }
                            found = true;
                            break;
                        }
                    }
                } else {
                    throw new IllegalStateException("No match to product names!");
                }
            }
        }
        return found;
    }


    private By BY_iosResultStream = MobileBy.xpath("//XCUIElementTypeScrollView/XCUIElementTypeOther");
    private String getIosResultStream() {
        String stream = getDriver().findElement(BY_iosResultStream).getAttribute("name");
        return stream;
    }

    private String XPATH_productRow = "//XCUIElementTypeOther[starts-with(@name, 'product-names')]";
    private String XPATH_singleProduct = "//XCUIElementTypeOther[starts-with(@name, 'product-name-')]";
    private String getIosProductNames(int row) {
        int count = getResultsCountInteger();
        String names = "NOT FOUND!";
        List<WebElement> elements = null;
        if (count > 0) {
            if (row == 1) {
                if (count > 1) {
                    elements = getDriver().findElements(By.xpath(XPATH_productRow));
                } else {
                    elements = getDriver().findElements(By.xpath(XPATH_singleProduct));
                }
                if (elements.size() > 0) {
                    names = elements.get(0).getAttribute("name");
                }
            } else if (row == 2) {
                if (count > 3) {
                    elements = getDriver().findElements(By.xpath(XPATH_productRow));
                    names = elements.get(1).getAttribute("name");
                } else if (count ==3) {
                    elements = getDriver().findElements(By.xpath(XPATH_singleProduct));
                    names = elements.get(0).getAttribute("name");
                }
            } else {
                throw new IllegalStateException("Attempt to access products in row " +row+ " with item count of " +count+ "!");
            }
        } else {
            throw new IllegalStateException("No items found!");
        }
        return names;
    }

    public boolean isNamePresentInTopResults(String expected) {
        boolean found = false;
        int count = getResultsCountInteger();
        if (isAndroid()) {
            int i = 1;
            int max =  count > 4 ? 4 : count;
            String actual = null;
            actual = getAndroidProductName(i);
            found = actual.contains(expected.toUpperCase());
            System.out.println("i=" +i+ "; actualName=" +actual+ "; expected=" +expected);
            while ((i<max) && found) {
                i++;
                actual = getAndroidProductName(i);
                found = actual.contains(expected.toUpperCase());
                System.out.println("i=" +i+ "; actualName=" +actual+ "; expected=" +expected);
            }
        } else {
            List<WebElement> elements = null;
            if (count > 1) {
                elements = getDriver().findElements(By.xpath(XPATH_productRow));
                for (WebElement element:elements) {
                    String productNames = "";
                    productNames = element.getAttribute("name").toLowerCase();
                    System.out.println("Product names = " + productNames);
                    found = (StringUtils.countMatches(productNames, expected) == 2);
                    if (!found) {
                        break;
                    }
                }
            }
            if ((count < 6) && (count % 2 == 1)) {
                if ((count == 1) || (count > 1 && found)) {
                    elements = getDriver().findElements(By.xpath(XPATH_singleProduct));
                    boolean singleProductCount = elements.size() == 1;
                    if (singleProductCount) {
                        String productName = elements.get(0).getAttribute("name").toLowerCase();
                        found = (StringUtils.countMatches(productName, expected) == 1);
                    } else {
                        throw new IllegalStateException("More than one single product in PLP!");
                    }
                }
            }
        }
        return found;
    }

    public boolean isPriceAttributePresentInResults(Enums.Fees fee) {
        boolean found = false;
        int count = getResultsCountInteger();
        String expected = fee.getLabel();
        if (isAndroid()) {
            int i = 1;
            int max =  count > 4 ? 4 : count;
            String actual = null;
            actual = getAndroidProductFee(i);
            found = actual.contains(expected.toUpperCase());
            System.out.println("i=" +i+ "; actual=" +actual+ "; expected=" +expected);
            while ((i<max) && found) {
                i++;
                actual = getAndroidProductPrice(i);
                found = actual.contains(expected.toUpperCase());
                System.out.println("i=" +i+ "; actual=" +actual+ "; expected=" +expected);
            }
        } else {
            List<WebElement> elements = null;
            int i = 0;
            if (count > 1) {
                elements = getDriver().findElements(By.xpath(XPATH_productRow));
                for (WebElement element:elements) {
                    String productNames = "";
                    String data = "";
//                    data = element.getAttribute("label").toLowerCase();
                    data = element.getAttribute("label");
                    System.out.println("Product data = " + data);
//                    found = (StringUtils.countMatches(data, expected) == 2);
                    String actual1 = IosPlpDataParser.getProductAttributeFromRow(data, fee.getProductType(), IosPlpDataParser.Column.LEFT, IosPlpDataParser.ProductAttribute.FEE);
                    String actual2 = IosPlpDataParser.getProductAttributeFromRow(data, fee.getProductType(), IosPlpDataParser.Column.RIGHT, IosPlpDataParser.ProductAttribute.FEE);
                    found = (actual1.equals(expected) && actual2.equals(expected));
                    i++;
                    if (!found || (i > 2)) {
                        break;
                    }
                }
            }
            if ((count % 2 == 1) && (i < 3)){
                if ((count == 1) || (count > 1 && found)) {
                    elements = getDriver().findElements(By.xpath(XPATH_singleProduct));
                    boolean singleProductCount = elements.size() == 1;
                    if (singleProductCount) {
                        String data = elements.get(0).getAttribute("label");
                        System.out.println("Product data = " + data);
                        found = (StringUtils.countMatches(data, expected) == 1);
                    } else {
                        throw new IllegalStateException("More than one single product in PLP!");
                    }
                }
            }
        }
        return found;
    }

    public ProductSearchResultsPageObject(WebDriver driver) {
        super(driver);
    }

}
