package com.serenity.appium.poc.pages.home;

import com.serenity.appium.poc.pages.MobilePageObject;
import com.serenity.appium.poc.pages.NavigationFooterMoreMenuPageObject;
import com.serenity.appium.poc.pages.NavigationFooterPageObject;
import com.serenity.appium.poc.utils.Scrolling;
import com.serenity.appium.poc.utils.StoreDataParser;
import com.serenity.appium.poc.utils.Utils;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSFindBy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class MyStoreHeaderPageObject extends MobilePageObject {

    @iOSFindBy(accessibility = "touchable-store-data")
    private WebElement TOUCHABLE_TEXT_storeData;

    @AndroidFindBy(accessibility = "text-store-name")
    private WebElement TEXT_storeTitle;

    @AndroidFindBy(accessibility = "text-hours")
    private WebElement TEXT_openUntilAtHour;

    @iOSFindBy(accessibility = "button-header-sign in")
    @AndroidFindBy(accessibility = "button-header-sign in")
    private WebElement BUTTON_signIn;

    @iOSFindBy(accessibility = "button-header-create account")
    @AndroidFindBy(accessibility = "button-header-create account")
    private WebElement BUTTON_createAccount;

    @iOSFindBy(accessibility = "button-header-my rewards")
    @AndroidFindBy(accessibility = "button-header-my rewards")
    private WebElement BUTTON_myRewards;

    @iOSFindBy(accessibility = "button-header-change store")
    @AndroidFindBy(accessibility = "button-header-change store")
    private WebElement BUTTON_changeStore;

    @iOSFindBy(accessibility = "button-header-my orders")
    @AndroidFindBy(accessibility = "button-header-my orders")
    private WebElement BUTTON_myOrders;

    @AndroidFindBy(xpath="//android.widget.ScrollView/android.view.ViewGroup/android.widget.TextView[@text='&MORE REWARDS']")
    private WebElement MoreRewardsSectionTitle;

    @AndroidFindBy(xpath="//android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[2]/android.widget.TextView[@text='WHAT CAN WE HELP YOU FIND?']")
    private WebElement HomeTabSearchField;

    @AndroidFindBy(xpath="//android.widget.ScrollView//android.view.ViewGroup/android.widget.TextView[@text='TOTAL WINE NOW DELIVERS!']")
    private WebElement TWDeliveryHeadline;

    @AndroidFindBy(xpath="//android.widget.ScrollView/android.view.ViewGroup//android.widget.TextView[@text='CHECK YOUR AREA FOR AVAILABILITY' or contains(@text,'DELIVERING TO')]")
    private WebElement TWCheckYourAreaForAvailabilityButton;

    private NavigationFooterMoreMenuPageObject navigationFooterMoreMenuPageObject;
    private NavigationFooterPageObject navigationFooterPageObject;


    public MyStoreHeaderPageObject(WebDriver driver) {
        super(driver);
    }

    public boolean isMyOrdersPresent() {
        return Utils.isVisible(getDriver(), BUTTON_myOrders, 15);
    }

    public boolean isSearchFieldPresent(){
        return Utils.isVisible(getDriver(), HomeTabSearchField, 15);
    }

    public boolean clickTouchableStoreData() {
        boolean result = false;
        if (isAndroid()) {
            if (Utils.isVisible(getDriver(), TEXT_storeTitle, 5)) {
                result = Utils.tryClicking(TEXT_storeTitle);
            } else {
                throw new IllegalStateException("Touchable store data not present to click!");
            }
        } else {
            if (Utils.isVisible(getDriver(), TOUCHABLE_TEXT_storeData, 5)) {
                result = Utils.tryClicking(TOUCHABLE_TEXT_storeData);
            } else {
                throw new IllegalStateException("Touchable store data not present to click!");
            }
        }
        return result;
    }

    public boolean clickSignIn() {
        if(Utils.isVisible(getDriver(), BUTTON_signIn, 5 )) {
            return Utils.tryClicking(BUTTON_signIn);
        }else{
            //User is already logged in, log out first
            if(Utils.isVisible(getDriver(), BUTTON_myOrders, 2 )){
                navigationFooterPageObject.clickMoreMenuButton();

                    if(navigationFooterMoreMenuPageObject.isSignOutButtonPresent()) {
                        navigationFooterMoreMenuPageObject.clickSignOutButton();

                            if (Utils.isVisible(getDriver(), BUTTON_signIn, 10)) {
                                return Utils.tryClicking(BUTTON_signIn);
                            }
                    }
            }
        }
        return false;
    }
    public boolean isMyRewardsPresent() { return Utils.isVisible(getDriver(), BUTTON_myRewards, 5);}
    public boolean clickMyRewards() {
        if (isMyRewardsPresent()) {
            return Utils.tryClicking(BUTTON_myRewards);
        }
        return false;
    }

    public String getStoreDataForIos() {
        String result = noResultsFound;
        try {
            result = TOUCHABLE_TEXT_storeData.getText();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public String getTitleFromStoreDataForIos() {
        String result = noResultsFound;
        try {
            String stream = getStoreDataForIos();
            result = StoreDataParser.getTitle(stream).trim();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public String getOpenCloseHourFromStoreDataForIos() {
        String result = noResultsFound;
        try {
            String stream = getStoreDataForIos();
            result = StoreDataParser.getOpenCloseHour(stream);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public String getTitleFromStoreData() {
        String result = isAndroid()
                ? TEXT_storeTitle.getText()
                : getTitleFromStoreDataForIos();
        return result;
    }

    public String getOpenInfoFromStoreData() {
        String result = isAndroid()
                ? TEXT_openUntilAtHour.getText()
                : getOpenCloseHourFromStoreDataForIos();
        return result;
    }

    public boolean isChangeStoreOptionPresent() {
        return Utils.isVisible(getDriver(), BUTTON_changeStore, 25);
    }

    public boolean clickChangeStore() {
        return Utils.tryClicking(BUTTON_changeStore);
    }

    public void clickMyOrders(){
        Utils.tryClicking(BUTTON_myOrders);
    }

    public boolean clickCreateAccount() {
        if(Utils.isVisible(getDriver(), BUTTON_createAccount, 20 )) {
            return Utils.tryClicking(BUTTON_createAccount);
        }else{
            //User is already logged in, log out first
            if(Utils.isVisible(getDriver(), BUTTON_myOrders, 2 )){
                navigationFooterPageObject.clickMoreMenuButton();

                if(navigationFooterMoreMenuPageObject.isSignOutButtonPresent()) {
                    navigationFooterMoreMenuPageObject.clickSignOutButton();

                    if (Utils.isVisible(getDriver(), BUTTON_createAccount, 10)) {
                        return Utils.tryClicking(BUTTON_createAccount);
                    }
                }
            }
        }


        return false;
    }

    public boolean isMoreRewardsSectionDisplayed() {
        boolean displayed = false;
        int scrollDownCounter = 0;
        if (isAndroid()) {
            displayed = Utils.isVisible(getDriver(), MoreRewardsSectionTitle, 1);

            while ( !displayed && (scrollDownCounter < 1) ) {
                Scrolling.androidSwipe(Scrolling.AndroidDirection.DOWN);
                displayed = Utils.isVisible(getDriver(), MoreRewardsSectionTitle, 1);
                scrollDownCounter++;
            }
        }

        //iOS TBD

        return displayed;
    }

    public boolean isCheckYourAreaForAvailabilityButtonVisible(){
        if(Utils.isVisible(getDriver(), TWDeliveryHeadline, 2)){
            return true;
        }
        return false;
    }

    public void clickCheckYourAreaForAvailabilityButton(){
        TWCheckYourAreaForAvailabilityButton.click();
    }

    public String getCheckYourAreaForAvailabilityButtonText(){
        return  TWCheckYourAreaForAvailabilityButton.getText();
    }

}
