package com.serenity.appium.poc.pages.orderingFlow;

import com.serenity.appium.poc.pages.MobilePageObject;
import com.serenity.appium.poc.utils.Utils;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSFindBy;
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

    @AndroidFindBy(xpath="//android.view.ViewGroup[2]/android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[2]/android.view.ViewGroup/android.widget.Button[2]/android.widget.TextView")
    private WebElement crossIcon;

    @AndroidFindBy(xpath="//android.view.ViewGroup[2]/android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[2]/android.view.ViewGroup[2]")
    private WebElement cartItemDeleteButton;


    public CartPageOject(WebDriver driver){
        super(driver);
    }


    public boolean isPageTitleCorrect() {
        if(Utils.isVisible(getDriver(), TEXT_pageTitle, 10)) {
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

                if( Utils.isVisible(getDriver(), cartItemPrice, 10) ) {

                        itemPriceAfterRewardIsApplied = Float.parseFloat( cartItemPrice.getText().replace("$", "").trim() );

                        if (itemPriceBeforeRewardIsApplied == itemPriceAfterRewardIsApplied + myRewardPrice) {
                            return true;
                        }

                }
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
}
