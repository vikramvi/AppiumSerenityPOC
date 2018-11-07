package com.serenity.appium.poc.pages.productDetails;

import com.serenity.appium.poc.pages.MobilePageObject;
import com.serenity.appium.poc.utils.IosPdpDataParser;
import com.serenity.appium.poc.utils.Scrolling;
import com.serenity.appium.poc.utils.Utils;
import io.appium.java_client.MobileBy;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSFindBy;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

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

    @iOSFindBy(accessibility = "product-data-stream")
    private WebElement TEXT_iosProductDataStream;

    @AndroidFindBy(xpath="//android.widget.Button[@content-desc=\"button-shopping-options\"]/android.widget.TextView[@text='CONTACT STORE']")
    private WebElement BUTTON_ContactStore;

    @AndroidFindBy(xpath="//android.view.ViewGroup[@content-desc=\"product-data-stream\"]/android.widget.TextView[@text='PRODUCT AVAILABILITY']")
    private WebElement productAvailabilityHeader;

    @AndroidFindBy(accessibility = "product-message-not-eligible-for-INSTORE_PICKUP")
    private WebElement productAvailabilityMessage;

    @AndroidFindBy(xpath="//android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[1]/android.view.ViewGroup/android.view.ViewGroup")
    private WebElement productImage;

    @AndroidFindBy(accessibility = "touchableIcon-search-products-product-detail")
    private WebElement magnifyingGlassIcon;

    @AndroidFindBy(xpath="//android.view.ViewGroup[@content-desc='product-data-stream']/android.widget.TextView[4]")
    private WebElement itemDetailsTableSection;


    public boolean clickReturn() {
        return Utils.tryClicking(BUTTON_return);
    }
    public boolean clickAddToCart() {
        if(Utils.isVisible(getDriver(), BUTTON_addToCart, 10)) {
            return Utils.tryClicking(BUTTON_addToCart);
        }else{
            return false;
        }
    }

    private boolean isProductRating(String data) {
        return (data.length() > 1) && (data.length() < 4) && (StringUtils.isNumeric(data));
    }

//    private String XPATH_PATTERN_productScore =
//            "(//android.view.ViewGroup[@content-desc=\"product-price\"])[%d]/preceding-sibling::android.view.ViewGroup[@content-desc='product-rating']";
//    public boolean isAndroidProductScorePresent(int productNumber) {
//        String xpath = String.format(XPATH_PATTERN_productScore, productNumber);
//        int count = getDriver().findElements(By.xpath(xpath)).size();
//        return (count > 0);
//    }
//
//    public String getAndroidProductScore(int productNumber) {
//        String xpath = String.format(XPATH_PATTERN_productScore, productNumber);
//        String score = getDriver().findElement(By.xpath(xpath)).getText();
//        return score;
//    }

    private By BY_productRating = MobileBy.AccessibilityId("product-rating");
    public String getAndroidProductRating() {
        String result = "NOT FOUND!";
        if (Utils.isVisible(getDriver(), BY_productRating, 2)) {
            result = getDriver().findElement(BY_productRating).getText();
        }
        return result;
    }

    private By BY_productRatingSource = MobileBy.AccessibilityId("product-rating-source");
    public String getAndroidProductRatingSource() {
        String result = "NOT FOUND!";
        if (Utils.isVisible(getDriver(), BY_productRatingSource, 2)) {
            result = getDriver().findElement(BY_productRatingSource).getText();
        }
        return result;
    }

//    public String getAndroidProductPrice() {
//        String result = TEXT_everydayLowPrice.getText();
//        return result;
//    }
//
//    private By BY_productFee = MobileBy.AccessibilityId("product-details-fee");
//    public String getAndroidProductFee() {
//        String result = getDriver().findElement(BY_productFee).getText();
//        return result;
//    }
//
//    public String getAndroidProductName() {
//        String result = TEXT_productName.getText();
//        return result;
//    }
//
//    private String XPATH_PATTERN_productSize = "(//android.widget.TextView[@content-desc=\"product-packaging\"])[%d]";
//    public String getAndroidProductSize(int productNumber) {
//        String xpath = String.format(XPATH_PATTERN_productSize, productNumber);
//        String result = getDriver().findElement(By.xpath(xpath)).getText();
//        return result;
//    }
    private By BY_productSize = MobileBy.AccessibilityId("product-details-packaging");
    public String getAndroidProductSize(int productNumber) {
        String result = getDriver().findElement(BY_productSize).getText();
        return result;
    }

    private By BY_ispChangeButton = MobileBy.AccessibilityId("button-change-store");
    public boolean clickAndroidChangeStoreButton() {
        return Utils.tryClicking(getDriver(), BY_ispChangeButton);
    }

    private By BY_deliveryConfirmAddressButton = MobileBy.AccessibilityId("button-delivery-confirm");
    public boolean clickAndroidConfirmDeliveryAddressButton() {
        return Utils.tryClicking(getDriver(), BY_deliveryConfirmAddressButton);
    }

    private By BY_deliveryChangeAddressButton = MobileBy.AccessibilityId("button-delivery-change");
    public boolean clickAndroidChangeDeliveryAddressButton() {
        return Utils.tryClicking(getDriver(), BY_deliveryChangeAddressButton);
    }

    private By BY_ispAvailableMessage = MobileBy.AccessibilityId("product-message-available-for-INSTORE_PICKUP");
    public String getAndroidProductInStoreAvailability() {
//        String message = getDriver().findElement(BY_ispAvailableMessage).getText();
        String message = Utils.getTextFromByHandle(getDriver(), BY_ispAvailableMessage, true);
        return message;
    }

    private By BY_ispStoreName = MobileBy.AccessibilityId("store-name-for-isp");
    public String getAndroidIspStoreName() {
        String message = getDriver().findElement(BY_ispStoreName).getText();
        return message;
    }

    private By BY_deliveryAvailableMessage = MobileBy.AccessibilityId("product-message-available-for-DELIVERY");
    public String getAndroidDeliveryAvailableMessage() {
        String message = getDriver().findElement(BY_deliveryAvailableMessage).getText();
        return message;
    }

    private By BY_deliveryNotEligibleMessage = MobileBy.AccessibilityId("product-message-not-eligible-for-DELIVERY");
    public String getAndroidDeliveryNotEligibleMessage() {
        String message = getDriver().findElement(BY_deliveryNotEligibleMessage).getText();
        return message;
    }

//    public boolean selectProductForAndroid(int productNumber) {
//        return Utils.tryClicking(TEXT_productName);
//    }

    //------------------ Android --^

    public String getProductName() {
        String result = noResultsFound;
//        if (isAndroid()) {
//            result = getAndroidProductName();
//        } else {
//            result = getIosProductName();
//        }
        if(Utils.isVisible(getDriver(),TEXT_productName, 15 )){
          result = TEXT_productName.getText();
        }

        return result.trim();
    }

    private By BY_productFee = MobileBy.AccessibilityId("product-details-fee");
    public String getProductFee() {
        String result = noResultsFound;
//        if (isAndroid()) {
//            result = getAndroidProductFee();
//        } else {
//            result = getIosProductFee();
//        }
        result = getDriver().findElement(BY_productFee).getText();
        return result;
    }

    public String getProductPrice() {
        String result = noResultsFound;
//        if (isAndroid()) {
//            result = getAndroidProductPrice();
//        } else {
//            result = getIosProductPrice();
//        }
        result = TEXT_everydayLowPrice.getText();
        return result;
    }

    private By BY_multiOptionPackaging = MobileBy.AccessibilityId("text-multi-option");
    public String getMultiOptionPackaging() {
        String result = noResultsFound;
        result = getDriver().findElement(BY_multiOptionPackaging).getText();
        return result;
    }




    public String getProductInStoreAvailability() {
        String result = noResultsFound;
        if (isAndroid()) {
            result = getAndroidProductInStoreAvailability();
        } else {
            result = getIosProductInStoreAvailability();
        }
        return result;
    }


//------------------ iOS --v

//    private String XPATH_PATTERN_iosStoreTitleInList = "(//XCUIElementTypeOther[starts-with(@name,'%s')])[2]";
    public String getProductDataStreamForIos() {
        String result = noResultsFound;
        try {
            result = TEXT_iosProductDataStream.getAttribute("label");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
//    public String getIosProductName() {
//        String result = noResultsFound;
//        try {
//            String stream = getProductDataStreamForIos();
//            result = IosPdpDataParser.ProductAttribute.TITLE.getData(stream);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return result;
//    }
    public String getIosProductCategories() {
        String result = noResultsFound;
        try {
            String stream = getProductDataStreamForIos();
            result = IosPdpDataParser.ProductAttribute.CATEGORIES.getData(stream);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
//    public String getIosProductPrice() {
//        String result = noResultsFound;
//        try {
//            String stream = getProductDataStreamForIos();
//            result = IosPdpDataParser.ProductAttribute.PRICE.getData(stream);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return result;
//    }
//    public String getIosProductFee() {
//        String result = noResultsFound;
//        try {
//            String stream = getProductDataStreamForIos();
//            result = IosPdpDataParser.ProductAttribute.FEE.getData(stream);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return result;
//    }
    public String getIosLiquorSize() {
        String result = noResultsFound;
        try {
            String stream = getProductDataStreamForIos();
            result = IosPdpDataParser.ProductAttribute.LIQUOR_SIZE.getData(stream);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    public String getIosWineSize() {
        String result = noResultsFound;
        try {
            String stream = getProductDataStreamForIos();
            result = IosPdpDataParser.ProductAttribute.WINE_SIZE.getData(stream);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    public String getIosBeerSize() {
        String result = noResultsFound;
        try {
            String stream = getProductDataStreamForIos();
            result = IosPdpDataParser.ProductAttribute.BEER_SIZE.getData(stream);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    public String getIosProductInStoreAvailability() {
        String result = noResultsFound;
        try {
            String stream = getProductDataStreamForIos();
            result = IosPdpDataParser.ProductAttribute.IN_STORE_AVAILABILITY.getData(stream);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    public String getIosProductDeliveryAvailability() {
        String result = noResultsFound;
        try {
            String stream = getProductDataStreamForIos();
            result = IosPdpDataParser.ProductAttribute.DELIVERY_AVAILABILITY.getData(stream);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    public String getIosProductSku() {
        String result = noResultsFound;
        try {
            String stream = getProductDataStreamForIos();
            result = IosPdpDataParser.ProductAttribute.SKU.getData(stream);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }


    public MainProductDetailsPageObject(WebDriver driver) {
        super(driver);
    }

    public boolean isProductAvailabilityMessageShown() {

        if ( Utils.isVisible(getDriver(), productAvailabilityHeader, 1)){

             if(Utils.isVisible(getDriver(), productAvailabilityMessage, 1 ) &&
                 productAvailabilityMessage.getText().equals("Produced in very limited amounts. Not currently available.")) {
                 return true;
             }
        }

        return false;
    }

    public boolean scrollToProductAvailability() {

        boolean displayed = false;
        int scrollDownCounter = 0;

        if (isAndroid()) {

                displayed = isProductAvailabilityMessageShown();

                while (!displayed && (scrollDownCounter < 2)) {
                    Scrolling.androidSwipe(Scrolling.AndroidDirection.DOWN);
                    displayed = isProductAvailabilityMessageShown();
                    scrollDownCounter++;
                }
        }

        //iOS TBD

        return displayed;
    }

    public boolean clickProductImageToEnlarge(){
        productImage.click();
        Utils.waitFor(500);

        if(!Utils.isVisible(getDriver(), TEXT_productName, 1)){
            return true;
        }
        return false;
    }

    String XPATH_Pattern = "//android.widget.ImageView[@content-desc=\"strategy-%s-%s\"]";
    public boolean isSellStrategyHeaderShown(String expectedValue){
        String[] splitString = expectedValue.split("\\s+");
        String XPATH =  String.format(XPATH_Pattern, splitString[0].toLowerCase(), splitString[1].toLowerCase());

        if( getDriver().findElements(By.xpath(XPATH)).size() > 0 ){
            return true;
        }
        return false;
    }

    public void clickMagnifyingGlassIcon(){
        magnifyingGlassIcon.click();
    }

    public String getItemDetailsTableSectionContent(){
        return itemDetailsTableSection.getText();
    }

}
