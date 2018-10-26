package com.serenity.appium.poc.pages.orderingFlow;

import com.serenity.appium.poc.pages.MobilePageObject;
import com.serenity.appium.poc.utils.Utils;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.HashMap;
import java.util.List;
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

}
