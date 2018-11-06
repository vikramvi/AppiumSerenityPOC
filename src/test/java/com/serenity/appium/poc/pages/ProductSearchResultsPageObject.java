package com.serenity.appium.poc.pages;

import com.serenity.appium.poc.utils.*;
import io.appium.java_client.MobileBy;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSFindBy;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ProductSearchResultsPageObject extends MobilePageObject {

    @AndroidFindBy(accessibility = "count-search-results")
    @iOSFindBy(accessibility = "search results count")
    private WebElement TEXT_searchResultsCount;

    @AndroidFindBy(accessibility = "button-floating-return")
    @iOSFindBy(accessibility = "button-floating-return")
    private WebElement BUTTON_return;

    @AndroidFindBy(accessibility = "button-product-sort")
    private WebElement SortButton;

    @AndroidFindBy(accessibility = "button-refine-product")
    private WebElement FilterButton;

    @AndroidFindBy(accessibility =  "touchable-product-filter-option")
    private WebElement DepartmentFilterButton;

    @AndroidFindBy(accessibility = "button-apply")
    private WebElement DoneButton;

    @AndroidFindBy(accessibility = "button-reset-password")
    private WebElement ResetButton;

    @AndroidFindBy(accessibility = "count-search-results")
    private WebElement topRightCornerItemsCount;

    @AndroidFindBy(accessibility = "count-sorted-search-results")
    private WebElement topRightCornerItemsCountWithFilter;

    @AndroidFindBy(accessibility = "button-floating-close-list-modal")
    private WebElement selectAListCloseButton;


    public ProductSearchResultsPageObject(WebDriver driver) {
        super(driver);
    }

    public boolean clickReturn() {
        return Utils.tryClicking(BUTTON_return);
    }

    private final String REGEX_count = "([0-9]*?,?[0-9]+)(\\sITEMS?)";
    public String getResultsCount() {
        String result = "NOT FOUND!";

        if( Utils.isVisible(getDriver(), TEXT_searchResultsCount, 20) ) {

            String itemCount = TEXT_searchResultsCount.getText();
            Pattern pattern = Pattern.compile(REGEX_count);
            Matcher matcher = pattern.matcher(itemCount);
            if (matcher.find()) {
                result = matcher.group(1);
            } else {
                throw new IllegalStateException("No match for item count!");
            }
            return result;
        }else{
            return "";
        }
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

        if(Utils.isVisible(getDriver(), getDriver().findElement(By.xpath(xpath)), 20)) {
            String result = getDriver().findElement(By.xpath(xpath)).getText();
            return result;
        }
        return "";
    }

    private String XPATH_PATTERN_productSize = "(//android.widget.TextView[@content-desc=\"product-packaging\"])[%d]";
    public String getAndroidProductSize(int productNumber) {
        String xpath = String.format(XPATH_PATTERN_productSize, productNumber);
        String result = getDriver().findElement(By.xpath(xpath)).getText();
        return result;
    }

    public boolean selectProductForAndroid(int productNumber) {
        String xpath = String.format(XPATH_PATTERN_productName, productNumber);

        if( Utils.isVisible( getDriver(), getDriver().findElement(By.xpath(xpath)), 10 )){
            return Utils.tryClicking(xpath);
        }
        return false;
    }

    public boolean selectProduct(int productNumber) {
        if (isAndroid()) {
            return selectProductForAndroid(productNumber);
        }else{
            //iOS TBD
        }

        return false;
    }

    public String getProductName(int productNumber){
        if (isAndroid()) {
            return getAndroidProductName(productNumber);
        }else{
            return getIosProductNames(productNumber);
        }
    }

    String XPathHeartIconOnFirstSearchItem = "//android.view.ViewGroup[contains(@content-desc, 'product-name')]/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup/android.widget.Button";
    String XPathSelectAListTitle = "//android.view.ViewGroup/android.widget.Button/android.view.ViewGroup[2]/android.widget.TextView[@text='SELECT A LIST']";
    public boolean clickHeartIconOfFirstSearchResultItem(){

        getDriver().findElement(By.xpath(XPathHeartIconOnFirstSearchItem)).click();

        if(Utils.isVisible(getDriver(), getDriver().findElement(By.xpath(XPathSelectAListTitle)), 5)){
            return true;
        }

        return false;
    }

    public void clickCheckboxAgainstTopXListsAndCloseDialog(int listCount){

        String XPath_ForListCheckBoxPattern = "//android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[%d]/android.view.ViewGroup/android.widget.Button/android.widget.TextView";

        for(int count=1; count <= listCount; count++){
            String XPath_ForListCheckBox = String.format(XPath_ForListCheckBoxPattern, count);

            getDriver().findElement(By.xpath(XPath_ForListCheckBox)).click();

            //MOB-2284
            //Hard Coding - current after checking/ un-checking none of the parameters gets updated. This needs to be fixed
            Utils.waitFor(2000);
        }

        selectAListCloseButton.click();
        Utils.waitFor(2000);
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


    public void tapFilterButton(){
        if(Utils.isVisible(getDriver(), FilterButton, 15 )) {
            FilterButton.click();
        }
    }

    public void selectDepartment_FirstFilter(){
        if(Utils.isVisible(getDriver(), DepartmentFilterButton, 15 )) {
            DepartmentFilterButton.click();
        }
    }

    public boolean verifyDepartment_SecondFilterOptions(){
        List<String> expectedDepartmentCategories = Arrays.asList("BEER", "ACCESSORIES & MORE", "WINE");
        List<String> actualDeparmentCategories = new ArrayList<>();


        //verify Department categories
        String xPathPattern_ActualDepartmentCategory = "//android.view.ViewGroup[%d]/android.view.ViewGroup/android.view.ViewGroup/android.widget.TextView[2]";

        if(Utils.isVisible(getDriver(), getDriver().findElement(By.xpath(String.format(xPathPattern_ActualDepartmentCategory, 2))),10 )) {

            for (int count = 2; count <= 4; count++) {
                String xpath = String.format(xPathPattern_ActualDepartmentCategory, count);
                String actualDepartmentCategory = getDriver().findElement(By.xpath(xpath)).getText();
                actualDeparmentCategories.add(actualDepartmentCategory);
            }

            if (expectedDepartmentCategories.equals(actualDeparmentCategories)) {
                return true;
            }else{
                return false;
            }
        }

        return false;
    }

    public boolean selectOneOfTheFirstFilterOptionsUnderDEPARTMENTFilter(String searchTerm){

        String actualXpathSearchTerm, actualXPathSearchTermCheckBox,
                                    XPath_SearchTerm = "//android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[%d]/android.view.ViewGroup/android.view.ViewGroup/android.widget.TextView[2]",
                            XPath_SearchTermCheckBox = "//android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[%d]/android.view.ViewGroup/android.view.ViewGroup/android.widget.TextView[1]";

        for(int tryCount = 2; tryCount <= 5; tryCount++) {

            actualXpathSearchTerm         = String.format(XPath_SearchTerm, tryCount);
            actualXPathSearchTermCheckBox = String.format(XPath_SearchTermCheckBox, tryCount);

            if(getDriver().findElement(By.xpath(actualXpathSearchTerm)).getText().equalsIgnoreCase(searchTerm)) {
                getDriver().findElement(By.xpath(actualXPathSearchTermCheckBox)).click();
                break;
            }

            if(tryCount == 4){
                return false;
            }
        }

        if(verifyFilterDoneButtonCount(1)){
            return true;
        }

        return false;
    }

    public boolean SelectSecondLevelCategoryAndSelectMultipleThirdLevelCategories(String secondLevelFilter, List<String> thirdLevelFilters){
        try {
             int countForBeerSubCategory = 0;

             String xPathPattern_BeerSubCategory = "//android.widget.Button[@content-desc='touchable-product-filter-option'][%d]/android.widget.TextView[1]";

             if(Utils.isVisible(getDriver(), getDriver().findElement(By.xpath(String.format(xPathPattern_BeerSubCategory, 1))), 5)) {
                 for (int count = 1; count <= 13; count++) {
                     String xpath = String.format(xPathPattern_BeerSubCategory, count);

                     if (getDriver().findElement(By.xpath(xpath)).getText().trim().equalsIgnoreCase(secondLevelFilter)) {
                         getDriver().findElement(By.xpath(xpath)).click();
                         countForBeerSubCategory = count;
                         break;
                     }
                 }
             }else{
                 LOGGER.error("Error 1");
             }

             String xPathPattern_BeerSub_SubCategory = "//android.view.ViewGroup[1]/android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[%d]/android.view.ViewGroup/android.view.ViewGroup/android.widget.TextView[2]";
             String xPathPattern_BeerSub_SubCategoryCount = "//android.view.ViewGroup[1]/android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[%d]/android.view.ViewGroup/android.view.ViewGroup/android.widget.TextView[3]";

             int  thirdLevelFilterCount = 0 ;

             if (Utils.isVisible(getDriver(), getDriver().findElement(By.xpath(String.format(xPathPattern_BeerSub_SubCategory, 2))), 5)) {
                 for (int count = 2; count <= 6; count++) {

                     String xpathForThirdLevelFilterName  = String.format(xPathPattern_BeerSub_SubCategory, count);
                     String xpathForThirdLevelFilterCount = String.format(xPathPattern_BeerSub_SubCategoryCount, count);

                     if (getDriver().findElement(By.xpath(xpathForThirdLevelFilterName)).getText().trim().equalsIgnoreCase(thirdLevelFilters.get(0))) {

                         thirdLevelFilterCount = Integer.parseInt(getDriver().findElement(By.xpath(xpathForThirdLevelFilterCount)).getText() );

                         getDriver().findElement(By.xpath(xpathForThirdLevelFilterName)).click();
                         break;
                     }

                 }
             }

             if(!verifyFilterDoneButtonCount(2)){
                 LOGGER.error("Error 2");
                 return false;
             }


             int postApplyingThirdFilterCount = 0;

             if (getDriver().findElement(By.xpath(String.format(xPathPattern_BeerSubCategory, countForBeerSubCategory))).getText().contains(secondLevelFilter.toUpperCase())) {

                 postApplyingThirdFilterCount = Integer.parseInt( topRightCornerItemsCountWithFilter.getText().replaceAll("[^0-9]", "") );

                 getDriver().findElement(By.xpath(String.format(xPathPattern_BeerSubCategory, countForBeerSubCategory))).click();
             } else {
                 LOGGER.error("Error 3");
                 return false;
             }


             if (Utils.isVisible(getDriver(), getDriver().findElement(By.xpath(String.format(xPathPattern_BeerSub_SubCategory, 2))), 5)) {
                 //scroll to 2nd screen to click American Double/ Imperial IPA
                 Scrolling.scrollDown();

                 for (int count = 2; count <= 6; count++) {

                     String xpathForThirdLevelFilterName  = String.format(xPathPattern_BeerSub_SubCategory, count);
                     String xpathForThirdLevelFilterCount = String.format(xPathPattern_BeerSub_SubCategoryCount, count);

                     if (getDriver().findElement(By.xpath(xpathForThirdLevelFilterName)).getText().trim().equalsIgnoreCase(thirdLevelFilters.get(1))) {

                         thirdLevelFilterCount = thirdLevelFilterCount + Integer.parseInt(getDriver().findElement(By.xpath(xpathForThirdLevelFilterCount)).getText() );

                         getDriver().findElement(By.xpath(xpathForThirdLevelFilterName)).click();
                         break;
                     }

                 }
             } else {
                 LOGGER.error("Error 4");
                 return false;
             }

             if(verifyFilterDoneButtonCount(3)){

                 postApplyingThirdFilterCount = Integer.parseInt( getDriver().findElement(By.xpath("//android.view.ViewGroup[1]/android.view.ViewGroup/android.widget.TextView[2][contains(@text,'ITEM')]")).getText().replaceAll("[^0-9]", "") );

                 if( postApplyingThirdFilterCount!= thirdLevelFilterCount ) {
                     LOGGER.error("Error 5");
                     return false;
                 }

                 return true;
             }

             return false;

        }catch (Exception e){
            e.printStackTrace();
            LOGGER.error("Error 6");
            return false;
        }
    }

    public boolean verifyFilterDoneButtonCount(int expectedCount){

        if( Utils.isClickable(getDriver(), DoneButton, 20) && Utils.isClickable(getDriver(), ResetButton, 20)){
            LOGGER.info("BUTTON --- " + getDriver().findElement(By.xpath("//android.widget.Button[@content-desc='button-apply']/android.widget.TextView")).getText());
            if (getDriver().findElement(By.xpath("//android.widget.Button[@content-desc='button-apply']/android.widget.TextView")).getText().equalsIgnoreCase("DONE (" + expectedCount + ")")) {
                return true;
            }
        }
        return false;
    }

    String XPATH_Pattern = "//android.widget.ImageView[@content-desc=\"strategy-%s-%s\"]";
    public boolean isProductSearchResultsSellStrategyShown(String expectedValue){
        String[] splitString = expectedValue.split("\\s+");
        String XPATH =  String.format(XPATH_Pattern, splitString[0].toLowerCase(), splitString[1].toLowerCase());

        if( getDriver().findElements(By.xpath(XPATH)).size() > 0 ){
            return true;
        }
        return false;
    }

}
