package com.serenity.appium.poc.pages.storeDetails;

import com.serenity.appium.poc.pages.MobilePageObject;
import com.serenity.appium.poc.pages.NavigationFooterPageObject;
import com.serenity.appium.poc.pages.orderingFlow.CartPageOject;
import com.serenity.appium.poc.utils.Utils;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSFindBy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.serenity.appium.poc.pages.home.MyStoreHeaderPageObject;

public class StoreIconsPageObject extends MobilePageObject {

    @AndroidFindBy(accessibility = "touchableIcon-get-directions")
    @iOSFindBy(accessibility = "touchableIcon-get-directions")
    private WebElement TOUCHABLE_ICON_getDirections;

    @AndroidFindBy(accessibility = "touchableIcon-bookmark-myStore")
    @iOSFindBy(accessibility = "touchableIcon-bookmark-myStore")
    private WebElement TOUCHABLE_ICON_shopThisStore;

    @AndroidFindBy(accessibility = "touchableIcon-change-store")
    @iOSFindBy(accessibility = "touchableIcon-change-store")
    private WebElement TOUCHABLE_ICON_changeStore;

    @AndroidFindBy(accessibility = "button-shop-this-store")
    private WebElement ShopThisStoreButton;

    @AndroidFindBy(xpath = "//android.widget.Button[@content-desc='touchableIcon-bookmark-myStore']/android.widget.TextView[2][@text='SHOPPING NOW']")
    private WebElement ShoppingNowButtonText;

    @AndroidFindBy(xpath="//android.view.ViewGroup[2]/android.widget.TextView[2][@text='CHANGING YOUR STORE?']")
    private WebElement ChangeStoreConfirmationDialogTitle;

    @AndroidFindBy(xpath = "//android.widget.Button[@content-desc=\"change-shopping-method\"]/android.widget.TextView[@text='CONTINUE']")
    private WebElement ChangeStoreConfirmationDialogContinueButton;

    private MyStoreHeaderPageObject myStoreHeaderPageObject;
    private NavigationFooterPageObject navigationFooterPageObject;
    private CartPageOject cartPageOject;

    public boolean isChangeStoreIconPresent() {
        if(Utils.isVisible(getDriver(), TOUCHABLE_ICON_changeStore, 10 )) {
            return TOUCHABLE_ICON_changeStore.isDisplayed();
        }
        return false;
    }

    public boolean clickChangeStore() {
        return Utils.tryClicking(TOUCHABLE_ICON_changeStore);
    }

    public boolean isGetDirectionsIconPresent() {
        return TOUCHABLE_ICON_getDirections.isDisplayed();
    }

    public boolean clickGetDirections() {
        return Utils.tryClicking(TOUCHABLE_ICON_getDirections);
    }

    public boolean isMyStoreIconPresent() {
        return TOUCHABLE_ICON_shopThisStore.isDisplayed();
    }

    public void clickShopThisStoreIcon() {
         if(isChangeStoreIconPresent()) {
             if (Utils.isVisible(getDriver(), ShoppingNowButtonText, 1)) {
                 LOGGER.info("Already in expected store, no need to change");
                 navigationFooterPageObject.clickHomeButton();

             }else if (Utils.isVisible(getDriver(), ShopThisStoreButton, 4)) {
                 ShopThisStoreButton.click();
                 LOGGER.info("Clicked ShopThisStoreButton");

                 //temp fix MOB-2246
                 if (Utils.isVisible(getDriver(), ChangeStoreConfirmationDialogTitle, 3)) {
                     ChangeStoreConfirmationDialogContinueButton.click();
                     LOGGER.info("Clicked ChangeStoreConfirmationDialogContinueButton");
                 }
             }else{
                 LOGGER.error("clickShopThisStoreIcon FAILED");
             }
         }else {
             LOGGER.error("isChangeStoreIconPresent FAILED");
         }
    }

    public StoreIconsPageObject(WebDriver driver) {
        super(driver);
    }
}
