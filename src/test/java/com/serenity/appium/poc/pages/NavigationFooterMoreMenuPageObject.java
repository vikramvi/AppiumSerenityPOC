package com.serenity.appium.poc.pages;

import com.serenity.appium.poc.utils.Utils;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSFindBy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class NavigationFooterMoreMenuPageObject extends MobilePageObject {

//    @AndroidFindBy(xpath = "/android.view.ViewGroup[@content-desc=\"SIGN OUT\"]/android.widget.TextView[1]")
    @AndroidFindBy(accessibility = "SIGN OUT")
    @iOSFindBy(accessibility = "SIGN OUT")
    private WebElement BUTTON_signOut;

//    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc=\"NOTIFICATION SETTINGS\"]/android.widget.TextView[1]")
    @AndroidFindBy(accessibility = "NOTIFICATION SETTINGS")
    @iOSFindBy(accessibility = "NOTIFICATION SETTINGS")
    private WebElement BUTTON_notificationSettings;

//    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc=\"TERMS AND CONDITIONS\"]/android.widget.TextView[1]\n")
    @AndroidFindBy(accessibility = "TERMS AND CONDITIONS")
    @iOSFindBy(accessibility = "TERMS AND CONDITIONS")
    private WebElement BUTTON_termsAndConditions;

//    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc=\"ACCOUNT\"]/android.widget.TextView[1]")
    @AndroidFindBy(accessibility = "ACCOUNT")
    @iOSFindBy(accessibility = "ACCOUNT")
    private WebElement BUTTON_account;

//    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc=\"CUSTOMER SERVICE\"]/android.widget.TextView[1]")
    @AndroidFindBy(accessibility = "CUSTOMER SERVICE")
    @iOSFindBy(accessibility = "CUSTOMER SERVICE")
    private WebElement BUTTON_customerService;

//    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc=\"HELP\"]/android.widget.TextView[1]")
    @AndroidFindBy(accessibility = "HELP")
    @iOSFindBy(accessibility = "HELP")
    private WebElement BUTTON_help;

//    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc=\"MY ORDERS\"]/android.widget.TextView[1]")
    @AndroidFindBy(accessibility = "MY ORDERS")
    @iOSFindBy(accessibility = "MY ORDERS")
    private WebElement BUTTON_myOrders;

//    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc=\"MESSAGES\"]/android.widget.TextView[1]")
    @AndroidFindBy(accessibility = "MESSAGES")
    @iOSFindBy(accessibility = "MESSAGES")
    private WebElement BUTTON_messages;

    @AndroidFindBy(accessibility = "touchableIcon-payment")
    private WebElement BUTTON_Payment;


    public NavigationFooterMoreMenuPageObject(WebDriver driver) {
        super(driver);
    }

    public boolean isSignOutButtonPresent() {
        return Utils.isVisible(getDriver(), BUTTON_signOut, 5);
    }

    public boolean isNotificationSettingsButtonPresent() {
        return Utils.isVisible(getDriver(), BUTTON_notificationSettings, 5);
    }

    public boolean isTermsAndConditionsButtonPresent() {
        return Utils.isVisible(getDriver(), BUTTON_termsAndConditions, 5);
    }

    public boolean isAccountButtonPresent() {
        return Utils.isVisible(getDriver(), BUTTON_account, 5);
    }

    public boolean isCustomerServiceButtonPresent() {
        return Utils.isVisible(getDriver(), BUTTON_customerService, 5);
    }

    public boolean isHelpButtonPresent() {
        return Utils.isVisible(getDriver(), BUTTON_help, 5);
    }

    public boolean isMyOrdersButtonPresent() {
        return Utils.isVisible(getDriver(), BUTTON_myOrders, 5);
    }

    public boolean isMessagesButtonPresent() {
        return Utils.isVisible(getDriver(), BUTTON_messages, 5);
    }

    public boolean clickSignOutButton() {
        return Utils.tryClicking(BUTTON_signOut);
    }

    public boolean clickNotificationSettingsButton() {
        return Utils.tryClicking(BUTTON_notificationSettings);
    }

    public boolean clickTermsAndConditionsButton() {
        return Utils.tryClicking(BUTTON_termsAndConditions);
    }

    public boolean clickAccountButton() {
        if(Utils.isVisible(getDriver(), BUTTON_account, 10)) {
            return Utils.tryClicking(BUTTON_account);
        }
        return false;
    }

    public boolean clickCustomerServiceButton() {
        return Utils.tryClicking(BUTTON_customerService);
    }

    public boolean clickHelpButton() {
        return Utils.tryClicking(BUTTON_help);
    }

    public boolean clickMyOrdersButton() {
        return Utils.tryClicking(BUTTON_myOrders);
    }

    public boolean clickMessagesButton() {
        return Utils.tryClicking(BUTTON_messages);
    }
}
