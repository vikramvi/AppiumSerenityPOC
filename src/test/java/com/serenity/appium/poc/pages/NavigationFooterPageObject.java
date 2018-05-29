package com.serenity.appium.poc.pages;

import com.serenity.appium.poc.utils.StoreDataParser;
import com.serenity.appium.poc.utils.Utils;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSFindBy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class NavigationFooterPageObject extends MobilePageObject {

    @AndroidFindBy(accessibility = "tab-home")
    @iOSFindBy(accessibility = "\uE81A HOME \uE81A HOME")
    private WebElement BUTTON_home;

    @AndroidFindBy(accessibility = "tab-browse")
    @iOSFindBy(accessibility = "\uE83F BROWSE \uE83F BROWSE")
    private WebElement BUTTON_browse;

    @AndroidFindBy(accessibility = "tab-My Store")
    @iOSFindBy(accessibility = "\uE82D MY STORE \uE82D MY STORE")
    private WebElement BUTTON_myStore;

    @AndroidFindBy(accessibility = "tab-cart")
    @iOSFindBy(accessibility = "\uE80C CART \uE80C CART")
    private WebElement BUTTON_shoppingCart;

    @AndroidFindBy(accessibility = "tab-more")
    @iOSFindBy(accessibility = "\uE82A MORE \uE82A MORE")
    private WebElement BUTTON_moreMenu;

    public NavigationFooterPageObject(WebDriver driver) {
        super(driver);
    }

    public boolean isHomeButtonPresent() {
        return Utils.isVisible(getDriver(), BUTTON_home, 5);
    }

    public boolean isBrowseButtonPresent() {
        return Utils.isVisible(getDriver(), BUTTON_browse, 5);
    }

    public boolean isMyStoreButtonPresent() {
        return Utils.isVisible(getDriver(), BUTTON_myStore, 5);
    }

    public boolean isShoppingCartButtonPresent() {
        return Utils.isVisible(getDriver(), BUTTON_shoppingCart, 5);
    }

    public boolean isMoreMenuButtonPresent() {
        return Utils.isVisible(getDriver(), BUTTON_moreMenu, 5);
    }

    public boolean clickHomeButton() {
        return Utils.tryClicking(BUTTON_home);
    }

    public boolean clickBrowseButton() {
        return Utils.tryClicking(BUTTON_browse);
    }

    public boolean clickMyStoreButton() {
        return Utils.tryClicking(BUTTON_myStore);
    }

    public boolean clickShoppingCartButton() {
        return Utils.tryClicking(BUTTON_shoppingCart);
    }

    public boolean clickMoreMenuButton() {
        return Utils.tryClicking(BUTTON_moreMenu);
    }
}
