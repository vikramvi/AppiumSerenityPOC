package com.serenity.appium.poc.pages.orderingFlow;

import com.serenity.appium.poc.pages.MobilePageObject;
import com.serenity.appium.poc.utils.Utils;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.HashMap;
import java.util.Map;

public class DeliveryAddressPageObject extends MobilePageObject{

    @AndroidFindBy(accessibility = "header-title")
    private WebElement TEXT_pageTitle;

    @AndroidFindBy(accessibility = "android-input-Street Address")
    private WebElement INPUT_address1;

    @AndroidFindBy(accessibility = "android-input-City")
    private WebElement INPUT_city;

    @AndroidFindBy(accessibility = "android-input-State")
    private WebElement INPUT_state;

    @AndroidFindBy(accessibility = "android-input-Zip Code")
    private WebElement INPUT_zipcode;

    @AndroidFindBy(accessibility = "button-confirm-address")
    private WebElement CONFIRM_ADDRESS;

    @AndroidFindBy(accessibility = "button-clear-form")
    private WebElement clearFormButton;

    @AndroidFindBy(xpath="//android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup//android.widget.TextView[@text='DELIVERY UNAVAILABLE']")
    private WebElement deliveryUnavailableDialogTitle;

    @AndroidFindBy(accessibility = "try-another-delivery-address")
    private WebElement tryAnotherAddressButton;

    public DeliveryAddressPageObject(WebDriver driver){
        super(driver);
    }

    public boolean isPageTitleCorrect() {
       if( Utils.isVisible(getDriver(), TEXT_pageTitle, 15)){
           if(TEXT_pageTitle.getText().contains("DELIVERY ADDRESS")){
               return true;
           }
       }
       return false;
    }

    public boolean addNewAddressForDelivery(){
        Map<Integer, String[]> addresses = new HashMap<Integer, String[]>();
        String[] deliveryDetailAddress1 = new String[]{"45500 Fremont boulevard", "Fremont", "CA", "94538"};

        Integer address1 = 1;
        addresses.put(address1, deliveryDetailAddress1);

        clearFormButton.click();

            if(isPageTitleCorrect()){
                INPUT_address1.sendKeys(addresses.get(address1)[0]);
                INPUT_city.sendKeys(addresses.get(address1)[1]);
                INPUT_state.sendKeys(addresses.get(address1)[2]);
                INPUT_zipcode.sendKeys(addresses.get(address1)[3]);
                CONFIRM_ADDRESS.click();
                return true;
            }
            return false;
    }

    public void clickConfirmAddress(){
        CONFIRM_ADDRESS.click();
    }

    public enum Field {STREET_ADDRESS, CITY, STATE, ZIP_CODE};

    public void enterText(Field field, String text) {
        WebElement element = getElement(field);
        element.sendKeys(text);
    }

    private WebElement getElement(Field field) {
        WebElement element = null;
        switch (field) {
            case STREET_ADDRESS:
                element = INPUT_address1;
                break;
            case CITY:
                element = INPUT_city;
                break;
            case STATE:
                element = INPUT_state;
                break;
            case ZIP_CODE:
                element = INPUT_zipcode;

        }
        return element;
    }

    public void clickClearFormButton(){
        clearFormButton.click();
    }

    public boolean isDeliveryUnavailableDialogShown(){
        if( Utils.isVisible(getDriver(),deliveryUnavailableDialogTitle, 10) ){
            return true;
        }
        return false;
    }

    public void clickTryAnotherAddressButton(){
        if(Utils.isVisible(getDriver(), tryAnotherAddressButton, 1)) {
            tryAnotherAddressButton.click();
        }
    }
}
