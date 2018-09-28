package com.serenity.appium.poc.pages.orderingFlow;

import com.serenity.appium.poc.pages.MobilePageObject;
import com.serenity.appium.poc.utils.Utils;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CartPageOject extends MobilePageObject {

    @AndroidFindBy(accessibility = "header-title")
    private WebElement TEXT_pageTitle;
    private static final String expectedTitle = "CART";

    @AndroidFindBy(xpath="//android.view.ViewGroup[2]//android.view.ViewGroup[4]/android.widget.Button")
    private WebElement MyReward_ApplyButton;

    @AndroidFindBy(xpath="//android.view.ViewGroup[2]//android.view.ViewGroup[3]/android.widget.Button")
    private WebElement MyReward_RemoveButton;

    @AndroidFindBy(xpath="//android.view.ViewGroup[2]/android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[3]/android.widget.TextView[3]")
    private WebElement rewardAppliedText;

    @AndroidFindBy(xpath="//android.view.ViewGroup[2]/android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[2]/android.view.ViewGroup/android.widget.TextView[4]")
    private WebElement cartItemPrice;

    @AndroidFindBy(xpath="//android.view.ViewGroup/android.view.ViewGroup[3]/android.view.ViewGroup/android.widget.TextView[2]")
    private WebElement TotalAmount;

    @AndroidFindBy(xpath="//android.view.ViewGroup[2]/android.view.ViewGroup/android.widget.Button[2]/android.widget.TextView[@text='X']")
    private WebElement crossIcon;

    @AndroidFindBy(xpath="//android.view.ViewGroup[2]/android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[2]/android.view.ViewGroup[2]")
    private WebElement cartItemDeleteButton;

    @AndroidFindBy(accessibility="touchableIcon-option-picker")
    private WebElement ChooseDeliveryTimeControl;

    @AndroidFindBy(xpath="//android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[1]/android.widget.Button[2]")
    private WebElement SwitchToDeliveryLink;

    @AndroidFindBy(xpath="//android.view.ViewGroup/android.widget.Button/android.view.ViewGroup[2]/android.widget.TextView[2][@text='CHANGING TO DELIVERY']")
    private WebElement ChangingToDeliveryPopUpTitle;

    @AndroidFindBy(xpath="//android.widget.Button[@content-desc='change-shopping-method']")
    private WebElement ChangingToDeliveryContinueButton;

    @AndroidFindBy(xpath="//android.widget.Button[@content-desc='cancel-shopping-method']")
    private WebElement ChangingToDeliveryCancelButton;

    @AndroidFindBy(xpath="//android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[1]/android.widget.TextView[2][@text='SOME ITEMS NOT AVAILABLE']")
    private WebElement SomeItemsNotAvailableTitle;

    @AndroidFindBy(xpath="//android.widget.Button[@content-desc='button-signIn-home']")
    private WebElement SomeItemsNotAvailableProceedButton;

    @AndroidFindBy(xpath="//android.widget.Button[@content-desc='button-signUp-home']")
    private WebElement SomeItemsNotAvailableCancelButton;

    @AndroidFindBy(xpath="//android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[1]/android.widget.TextView[@text='NO ITEMS IN CART']")
    private WebElement NoItemInCartTitle;

    @AndroidFindBy(xpath="//android.view.ViewGroup[2]/android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[2]/android.widget.Button")
    private WebElement ContinueShoppingLink;

    @AndroidFindBy(xpath="//android.widget.TextView[@text='SECURE CHECKOUT']")
    private WebElement SecureCheckoutButton;


    public CartPageOject(WebDriver driver){
        super(driver);
    }


    public boolean isPageTitleCorrect() {
        if(Utils.isVisible(getDriver(), TEXT_pageTitle, 15)) {
            return Utils.isPageTitleCorrectAfterPolling(TEXT_pageTitle, expectedTitle);
        }
        return false;
    }

    public boolean clickMyRewardApply(){
        if( Utils.isVisible(getDriver(), rewardAppliedText, 3) && Utils.isVisible(getDriver(), MyReward_RemoveButton, 2) ){
            System.out.println("clickMyRewardApply - 1 PASS");
            return true;
        }

        if(Utils.isVisible(getDriver(), MyReward_ApplyButton, 3 )){
            MyReward_ApplyButton.click();
            System.out.println("clickMyRewardApply - 2 PASS");
            return true;
        }

        System.out.println("clickMyRewardApply - 1 FAILED");
        return false;
    }

    private int myRewardPrice = 5;
    public boolean isRewardAppliedSuccessfully(){

        float itemPriceBeforeRewardIsApplied, itemPriceAfterRewardIsApplied;

        if( Utils.isVisible(getDriver(), cartItemPrice, 10) ){

            itemPriceBeforeRewardIsApplied = Float.parseFloat( cartItemPrice.getText().replace("$", "").trim() );

            if (clickMyRewardApply()) {

                if( Utils.isVisible(getDriver(), cartItemPrice, 10) &&
                        Utils.isVisible(getDriver(), rewardAppliedText, 10) &&
                                Utils.isVisible(getDriver(), MyReward_RemoveButton, 10)  ){

                    itemPriceAfterRewardIsApplied = Float.parseFloat( cartItemPrice.getText().replace("$", "").trim() );

                    System.out.println("--->   " +  itemPriceBeforeRewardIsApplied + "    " + itemPriceAfterRewardIsApplied );

//                        if (itemPriceBeforeRewardIsApplied == itemPriceAfterRewardIsApplied + myRewardPrice) {
                    float difference = Math.abs(itemPriceBeforeRewardIsApplied - (itemPriceAfterRewardIsApplied + myRewardPrice));
                    if (difference < 0.00001) {
                        return true;
                    } else {
                        System.out.println("error 2");
                        LOGGER.error("COUPON could NOT be applied");
                    }

                } else {
                    LOGGER.error("Reward not applied successfully");
                }
            } else {
                LOGGER.error("Reward could NOT be applied");
            }

        }
        return false;
    }


    public boolean deleteCartItem(){
        crossIcon.click();

        if(Utils.isVisible(getDriver(),cartItemDeleteButton, 5 )){
            cartItemDeleteButton.click();
            return true;
        }
        return false;
    }

    public boolean deleteAllCartItemsOnebyOne(){

        if( isPageTitleCorrect() && Utils.isVisible(getDriver(), NoItemInCartTitle, 10) ){
            return true;
        }

        String cartItemRows_xpath = "//android.view.ViewGroup/android.widget.Button[2]/android.widget.TextView[@text='X']";

        int cartItems = getDriver().findElements(By.xpath(cartItemRows_xpath)).size();

        for(int itemCount = 1; itemCount <= cartItems; itemCount++){
            crossIcon.click();

            if(Utils.isVisible(getDriver(),cartItemDeleteButton, 5 )){
                cartItemDeleteButton.click();

                if(isPageTitleCorrect()){
                    continue;
                }else{
                    return false;
                }

            }

        }

        return true;
    }

    public boolean clickSwitchToDeliveryLink(){
        if( Utils.isVisible( getDriver(), SwitchToDeliveryLink, 10 ) ){
            SwitchToDeliveryLink.click();
            return true;
        }

        return false;
    }

    public boolean selectContinueForChangingToDeliveryDialog(){
          if(clickSwitchToDeliveryLink()){
              ChangingToDeliveryContinueButton.click();
              return true;
          }
          return false;
    }

    public boolean chooseProccedOnSomeItemsNotAvailableDialog(){
        if( Utils.isVisible( getDriver(), SomeItemsNotAvailableTitle, 20) ){
            SomeItemsNotAvailableProceedButton.click();
            return true;
        }

        return false;
    }

    public boolean isContinueShoppingLinkShown(){
        if( Utils.isVisible(getDriver(), NoItemInCartTitle, 10 ) ){
            return Utils.isVisible(getDriver(), ContinueShoppingLink, 5);
        }

        return false;
    }

    public boolean chooseDeliveryTime(){
        //TBD
        // Blocked - https://totalwine.atlassian.net/browse/MOB-2151
        return false;
    }

    public void clickSecureCheckout(){
        SecureCheckoutButton.click();
    }
}
