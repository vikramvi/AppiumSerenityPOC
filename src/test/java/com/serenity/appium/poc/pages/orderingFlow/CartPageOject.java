package com.serenity.appium.poc.pages.orderingFlow;

import com.serenity.appium.poc.pages.MobilePageObject;
import com.serenity.appium.poc.utils.Scrolling;
import com.serenity.appium.poc.utils.Utils;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class CartPageOject extends MobilePageObject {

    @AndroidFindBy(xpath = "//android.widget.TextView[@content-desc='header-title' and @text='CART']")
    private WebElement TEXT_pageTitle;
    private static final String expectedTitle = "CART";

    @AndroidFindBy(xpath="//android.view.ViewGroup[2]//android.view.ViewGroup[4]/android.widget.Button")
    private WebElement MyReward_ApplyButton;

    @AndroidFindBy(xpath="//android.view.ViewGroup[2]//android.view.ViewGroup[3]/android.widget.Button")
    private WebElement MyReward_RemoveButton;

    @AndroidFindBy(xpath="//android.view.ViewGroup[2]/android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[3]/android.widget.TextView[3]")
    private WebElement rewardAppliedText;

    @AndroidFindBy(xpath="//android.widget.ScrollView/android.view.ViewGroup//android.widget.TextView[1][contains(@text,'REWARD')]")
    private WebElement MyRewardText;

    @AndroidFindBy(xpath="//android.view.ViewGroup[2]/android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[2]/android.view.ViewGroup/android.widget.TextView[4]")
    private WebElement cartItemPrice;

    @AndroidFindBy(xpath="//android.view.ViewGroup/android.view.ViewGroup[3]/android.view.ViewGroup/android.widget.TextView[2]")
    private WebElement TotalAmount;

    @AndroidFindBy(xpath="//android.widget.ScrollView//android.widget.Button/android.widget.TextView[@text='X']")
    private WebElement crossIcon;

    @AndroidFindBy(xpath="//android.widget.ScrollView/android.view.ViewGroup//android.view.ViewGroup[2]/android.widget.TextView[@text='Delete']")
    private WebElement cartItemDeleteButton;

    @AndroidFindBy(accessibility="touchableIcon-option-picker")
    private WebElement ChooseDeliveryTimeControl;

    @AndroidFindBy(xpath="//android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[1]/android.widget.Button[2]/android.widget.TextView[contains(@text,'Delivery')]")
    private WebElement SwitchToDeliveryLink;

    @AndroidFindBy(xpath="//android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[1]/android.widget.Button[2]/android.widget.TextView[contains(@text,'Pickup')]")
    private WebElement SwitchToPickupLink;

    @AndroidFindBy(xpath="//android.widget.Button[@content-desc=\"button-change-delivery-address\"]/android.widget.TextView[contains(@text,'Delivery to')]")
    private WebElement DeliveryToLink;

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

    @AndroidFindBy(accessibility = "button-change-store")
    private WebElement ChangeStoreButton;

    @AndroidFindBy(xpath="//android.widget.ScrollView/android.view.ViewGroup//android.widget.TextView[@text='Your subtotal must be greater than your Reward to apply.']")
    private WebElement MyRewardText_ForSubtotal_LessThan_RewardToApply;

    @AndroidFindBy(xpath="//android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[3]/android.widget.Button")
    private WebElement AddACouponAddButton;

    public CartPageOject(WebDriver driver){
        super(driver);
    }


    public boolean isPageTitleCorrect() {
        if(Utils.isVisible(getDriver(), TEXT_pageTitle, 20)) {
            return Utils.isPageTitleCorrectAfterPolling(TEXT_pageTitle, expectedTitle);
        }
        return false;
    }

    public boolean isMyRewardTextShown(){
        return  Utils.isVisible(getDriver(), MyRewardText, 1);
    }

    public boolean isMyRewardText_ForSubTotal_LesserSubtotalThanRewardShown(){
        return  Utils.isVisible(getDriver(), MyRewardText_ForSubtotal_LessThan_RewardToApply, 1);
    }

    public boolean clickMyRewardApply(){
        if( Utils.isVisible(getDriver(), rewardAppliedText, 3) && Utils.isVisible(getDriver(), MyReward_RemoveButton, 2) ){
            LOGGER.info("clickMyRewardApply PASS - Reward is already applied, not doing any action atm");
            return true;
        }

        if(Utils.isVisible(getDriver(), MyReward_ApplyButton, 3 )){
            MyReward_ApplyButton.click();
            LOGGER.info("clickMyRewardApply PASS - Reward has been explicitly applied");
            return true;
        }

        LOGGER.error("clickMyRewardApply FAILED");
        return false;
    }

    String xPathForPostAndPreDiscountItemPrice = "//android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[2]/android.view.ViewGroup/android.widget.TextView[2]";
    String xPathForRewardPrice                 = "//android.view.ViewGroup[2]/android.widget.ScrollView/android.view.ViewGroup/android.widget.TextView[4]";
    String xPathForSubTotalPostDiscount        = "//android.view.ViewGroup[2]/android.widget.ScrollView/android.view.ViewGroup/android.widget.TextView[6]";

    public boolean checksAfterApplyingReward(String itemPriceDisplayedOnProductDetailsPage){

        boolean checks = true;

        if( Utils.isVisible(getDriver(), rewardAppliedText, 10) && Utils.isVisible(getDriver(), MyReward_RemoveButton, 10) ) {

            String preAndPostRewardItemPrices = getDriver().findElement(By.xpath(xPathForPostAndPreDiscountItemPrice)).getText();

            float postDiscountPrice  =  Float.parseFloat( preAndPostRewardItemPrices.split(" ")[0].trim().replace("$", "") );

            Pattern pattern=Pattern.compile("([0-9]+[.][0-9]+)");
            Matcher matcher=pattern.matcher(preAndPostRewardItemPrices.split(" ")[1]);

            float preDiscountPrice = 0.0f;
            while(matcher.find()) {
                preDiscountPrice = Float.parseFloat(matcher.group().toString());
            }
            //Won't work as strange character is getting into the string " 29.99"
            //Float preDiscountPrice   =  Float.parseFloat( preAndPostRewardItemPrices.split(" ")[1].trim().replace("$", "") );

            float rewardPrice = Float.parseFloat( getDriver().findElement(By.xpath(xPathForRewardPrice)).getText().replace("$", "") );

            if(preDiscountPrice != Float.parseFloat( itemPriceDisplayedOnProductDetailsPage.replace("$", "") )){
                LOGGER.error("Item Original Price info missing");
                checks = false;
            }

            if(rewardPrice != 5.0f){
                LOGGER.error("Reward amount $5 info missing");
                checks = false;
            }

            float difference = Math.abs(preDiscountPrice - (postDiscountPrice + myRewardPrice));
            if (!(difference < 0.00001)){
                checks = false;
                LOGGER.error("Post applying reward price is wrong");
            }

            float SubTotalPostDiscount = Float.parseFloat( getDriver().findElement(By.xpath(xPathForSubTotalPostDiscount)).getText().replace("$", ""));
            if(SubTotalPostDiscount != postDiscountPrice){
                checks = false;
                LOGGER.error("Subtotal post discount amount is wrong " + SubTotalPostDiscount);
            }
        }

        return checks;
    }

    String xPathForItemPriceWithoutDiscount = "//android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[2]/android.view.ViewGroup/android.widget.TextView[2]";
    String xPathForSubTotalWithoutDiscount  = "//android.view.ViewGroup[2]/android.widget.ScrollView/android.view.ViewGroup/android.widget.TextView[3]";

    public boolean checksWithoutReward(String itemPriceDisplayedOnProductDetailsPage){
        boolean checks = true;

        Float ItemPriceWithoutDiscount = Float.parseFloat( getDriver().findElement(By.xpath(xPathForItemPriceWithoutDiscount)).getText().replace("$", ""));
        Float SubTotalWithoutDiscount =  Float.parseFloat( getDriver().findElement(By.xpath(xPathForSubTotalWithoutDiscount)).getText().replace("$", ""));

        if(ItemPriceWithoutDiscount != Float.parseFloat(itemPriceDisplayedOnProductDetailsPage.replace("$", "")) ){
            checks = false;
            LOGGER.error("Item price without discount is wrong " + ItemPriceWithoutDiscount);
        }

        if(SubTotalWithoutDiscount != Float.parseFloat(itemPriceDisplayedOnProductDetailsPage.replace("$", "")) ){
            checks = false;
            LOGGER.error("Subtotal without discount is wrong " + SubTotalWithoutDiscount);
        }

        return checks;
    }


    public boolean checkPricesBeforeAndAfterApplyingCertificate(String itemPriceDisplayedOnProductDetailsPage){

        boolean isRewardApplied = false;

        if( Utils.isVisible(getDriver(), rewardAppliedText, 3) && Utils.isVisible(getDriver(), MyReward_RemoveButton, 2) ){
            isRewardApplied = true;

            if(!checksAfterApplyingReward(itemPriceDisplayedOnProductDetailsPage)){
                LOGGER.error("checksAfterApplyingReward method FAILED");
                return false;
            }

            MyReward_RemoveButton.click();
        }

        if( Utils.isVisible(getDriver(), MyReward_ApplyButton, 15) ){

            if( !checksWithoutReward(itemPriceDisplayedOnProductDetailsPage) ){
                LOGGER.error("checksWithoutReward method FAILED");
                return false;
            }

            if(!isRewardApplied){
                MyReward_ApplyButton.click();

                if(!checksAfterApplyingReward(itemPriceDisplayedOnProductDetailsPage)){
                    LOGGER.error("checksAfterApplyingReward method FAILED");
                    return false;
                }
            }

            return true;
        }

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

                    float difference = Math.abs(itemPriceBeforeRewardIsApplied - (itemPriceAfterRewardIsApplied + myRewardPrice));
                    if (difference < 0.00001) {
                        return true;
                    } else {
                        LOGGER.error("Reward could NOT be applied");
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

    public boolean deleteAllCartItemsOneByOne(){

        if( isPageTitleCorrect() ){
            if(Utils.isVisible(getDriver(), SecureCheckoutButton, 2)){ }
            else if( Utils.isVisible(getDriver(), NoItemInCartTitle, 2) ) {
                return true;
            }
        }

        String cartItemRows_xpath = "//android.view.ViewGroup/android.widget.Button/android.widget.TextView[@text='X']";

        int cartItems = getDriver().findElements(By.xpath(cartItemRows_xpath)).size();

        if(cartItems == 0){
          LOGGER.info("No Items Found in the Cart");
          return true;
        }

        for(int itemCount = 1; itemCount <= cartItems; itemCount++){
            crossIcon.click();

            if(Utils.isVisible(getDriver(),cartItemDeleteButton, 5 )){
                cartItemDeleteButton.click();

                if(isPageTitleCorrect()){
                    continue;
                }else{
                    LOGGER.error("deleteAllCartItemsOneByOne - Error 1");
                    return false;
                }
            }else{
                LOGGER.error("deleteAllCartItemsOneByOne - Error 2");
                return false;
            }

        }

        return true;
    }

    public boolean clickSwitchToDeliveryLink(){
        if( Utils.isVisible( getDriver(), SwitchToDeliveryLink, 1 ) ){
            SwitchToDeliveryLink.click();
            return true;
        }
        LOGGER.error("Switch to Delivery link is missing on CART screen");
        return false;
    }

    public boolean clickDeliverToLink(){
        if( Utils.isVisible( getDriver(), SwitchToPickupLink, 1 ) ){
            DeliveryToLink.click();
            return true;
        }
        LOGGER.error("Switch to Pickup link is missing on CART screen");
        return false;
    }

    public boolean selectContinueForChangingToDeliveryDialog(){
          if(clickSwitchToDeliveryLink()){
              ChangingToDeliveryContinueButton.click();
              return true;
          }else if(clickDeliverToLink()){
              return true;
          }
          return false;
    }

    public boolean isSomeItemsNotAvailableTitleDialogShown(){
         if( Utils.isVisible( getDriver(), SomeItemsNotAvailableTitle, 20) ){
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

    public boolean chooseCancelOnSomeItemsNotAvailableDialog(){
        if( Utils.isVisible( getDriver(), SomeItemsNotAvailableTitle, 20) ){
            SomeItemsNotAvailableCancelButton.click();
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

    String xPathForTopMostItem = "//android.view.ViewGroup/android.view.ViewGroup[2]/android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[2]/android.view.ViewGroup/android.widget.TextView[1]";
    public String getTopMostItemName(){
      return getDriver().findElement(By.xpath(xPathForTopMostItem)).getText();
    }


    public boolean isAddressUpdatedToastMessageSeen(){
        boolean isToastMessageSeen =  Utils.verifyToastMessageAndClose("SUCCESS", "Your address has been confirmed");

        if(!isToastMessageSeen) {
            LOGGER.error("Address Updated toast message did NOT display");
        }

        return isToastMessageSeen;
    }

    public boolean isMinimumOrderThresholdToastMessageSeen(){
        boolean isToastMessageSeen = Utils.verifyToastMessageAndClose("MINIMUM AMOUNT NOT MET", "does not meet our minimum threshold of $35.00");

        if(!isToastMessageSeen) {
            LOGGER.error("Minimum Amount Not Met toast message did NOT display");
        }

        return isToastMessageSeen;
    }

    public boolean isDeliveryTimeWindowToastMessageSeen(){
        boolean isToastMessageSeen =  Utils.verifyToastMessageAndClose("DELIVERY TIME WINDOW NOT SELECTED", "You have not chosen a delivery time window");

        if(!isToastMessageSeen) {
            LOGGER.error("Delivery Time Window Not Selected toast message did NOT display");
        }

        return isToastMessageSeen;
    }

    @AndroidFindBy(xpath = "//android.widget.ScrollView/android.view.ViewGroup//android.view.ViewGroup/android.widget.Button[2]/android.widget.TextView[@text='+']")
    private WebElement plusIconAgainstItem;

    public void increaseItemQuantityByOne(){
        if(Utils.isClickable(getDriver(), plusIconAgainstItem, 5)) {
            plusIconAgainstItem.click();
        }
    }

    public void doChangeStoreAction(){
        ChangeStoreButton.click();
    }

    public List<String> getListOfItemsAddedToCart(){
        try {
            List<String> items = new ArrayList<>();
            List<String> itemsWithDuplicates = new ArrayList<>();
            String lastItem = null;

            //initial screen
            String XPATHPattern_FirstScreenItems = "//android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[%d]/android.view.ViewGroup/android.widget.TextView[1]";
            for (int itemCount = 2; itemCount <= 4; itemCount++) {
                String actualXPath = String.format(XPATHPattern_FirstScreenItems, itemCount);

                items.add(getDriver().findElement(By.xpath(actualXPath)).getText());
            }

            for(int tryCount = 1; tryCount < 3; tryCount++) {

                if( Utils.isVisible(getDriver(), AddACouponAddButton, 1) ){
                    break;
                }

                Scrolling.scrollDown(0.70, 0.20);
                Utils.waitFor(1000);

                //scroll down till end
                String XPATHPattern_ScrolledScreenItems = "//android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[%d]/android.view.ViewGroup/android.widget.TextView[1]";
                for (int itemCount = 2; itemCount <= 4; itemCount++) {
                    String actualXPath = String.format(XPATHPattern_ScrolledScreenItems, itemCount);

                    try {
                        items.add(getDriver().findElement(By.xpath(actualXPath)).getText());
                    }catch (Exception e){
                        break;
                    }

                }

                if(items.get(items.size() -1).equals(lastItem)){
                    break;
                }else {
                    lastItem = items.get(items.size() - 1);
                }
            }

            //removes duplicates
            itemsWithDuplicates = items.stream().distinct().collect(Collectors.toList());

            return  itemsWithDuplicates;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

}
