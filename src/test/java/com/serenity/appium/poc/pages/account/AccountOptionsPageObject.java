package com.serenity.appium.poc.pages.account;

import com.serenity.appium.poc.pages.MobilePageObject;
import com.serenity.appium.poc.utils.Utils;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSFindBy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AccountOptionsPageObject extends MobilePageObject {

    @iOSFindBy(accessibility = "header-title")
    @AndroidFindBy(accessibility = "header-title")
    private WebElement TEXT_pageTitle;
    private static final String expectedTitle = "ACCOUNT";

    @AndroidFindBy(accessibility = "touchableIcon-profile")
    @iOSFindBy(accessibility = "touchableIcon-profile")
    private WebElement BUTTON_profile;

    @AndroidFindBy(accessibility = "touchableIcon-preferences")
    @iOSFindBy(accessibility = "touchableIcon-preferences")
    private WebElement BUTTON_preferences;

    @AndroidFindBy(accessibility = "touchableIcon-addresses")
    @iOSFindBy(accessibility = "touchableIcon-addresses")
    private WebElement BUTTON_addresses;

    @AndroidFindBy(accessibility = "touchableIcon-payment")
    @iOSFindBy(accessibility = "touchableIcon-payment")
    private WebElement BUTTON_payment;

    @AndroidFindBy(accessibility = "touchableIcon-notifications")
    @iOSFindBy(accessibility = "touchableIcon-notifications")
    private WebElement BUTTON_notifications;

    @AndroidFindBy(accessibility = "touchableIcon-shoppingLists")
    @iOSFindBy(accessibility = "touchableIcon-shoppingLists")
    private WebElement BUTTON_shoppingLists;

    @AndroidFindBy(accessibility = "touchableIcon-rate-app")
    @iOSFindBy(accessibility = "touchableIcon-rate-app")
    private WebElement BUTTON_rateApp;

    public AccountOptionsPageObject(WebDriver driver) {
        super(driver);
    }

    public boolean isProfileButtonPresent() {
        return Utils.isVisible(getDriver(), BUTTON_profile, 5);
    }

    public boolean isPreferencesButtonPresent() {
        return Utils.isVisible(getDriver(), BUTTON_preferences, 5);
    }

    public boolean isAddressesButtonPresent() {
        return Utils.isVisible(getDriver(), BUTTON_addresses, 5);
    }

    public boolean isPaymentButtonPresent() {
        return Utils.isVisible(getDriver(), BUTTON_payment, 5);
    }

    public boolean isNotificationsButtonPresent() {
        return Utils.isVisible(getDriver(), BUTTON_notifications, 5);
    }

    public boolean isShoppingListsButtonPresent() {
        return Utils.isVisible(getDriver(), BUTTON_shoppingLists, 5);
    }

    public boolean isRateThisAppButtonPresent() {
        return Utils.isVisible(getDriver(), BUTTON_rateApp, 5);
    }

    public boolean clickProfileButton() {
        return Utils.tryClicking(BUTTON_profile);
    }

    public boolean clickPreferencesButton() {
        return Utils.tryClicking(BUTTON_preferences);
    }

    public boolean clickAddressesButton() {
        return Utils.tryClicking(BUTTON_addresses);
    }

    public boolean clickPaymentButton() {
        return Utils.tryClicking(BUTTON_payment);
    }

    public boolean clickNotificationsButton() {
        return Utils.tryClicking(BUTTON_notifications);
    }

    public boolean clickShoppingListsButton() {
        return Utils.tryClicking(BUTTON_shoppingLists);
    }

    public boolean clickRateThisAppButton() {
        return Utils.tryClicking(BUTTON_rateApp);
    }

    public boolean isPageTitleCorrect() {
        return Utils.isPageTitleCorrectAfterPolling(TEXT_pageTitle, expectedTitle);
    }
}
