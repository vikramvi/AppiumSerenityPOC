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

    @AndroidFindBy(accessibility = "header-title")
    private WebElement TEXT_pageTitle;

    @AndroidFindBy(xpath="//android.view.ViewGroup/android.view.ViewGroup[2]/android.widget.Button[2]/android.widget.TextView[@text='VIEW CART']")
    private WebElement viewCartButton;


    public NavigationFooterPageObject(WebDriver driver) {
        super(driver);
    }

    public boolean isHomeButtonPresent() {
        return Utils.isVisible(getDriver(), BUTTON_home, 5);
    }

    public boolean isHomeButtonPresent(int maxTime) {
        if(Utils.isVisible(getDriver(), BUTTON_home, maxTime)) {
            return true;
        }
        LOGGER.error("isHomeButtonPresent failed even after " + maxTime + " seconds");
        return false;
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
        if(Utils.isVisible(getDriver(),BUTTON_home, 10 )){
            return Utils.tryClicking(BUTTON_home);
        }else{
            return false;
        }
    }

    public boolean clickBrowseButton() {
        return Utils.tryClicking(BUTTON_browse);
    }

    public boolean clickMyStoreButton() {
        return Utils.tryClicking(BUTTON_myStore);
    }

    public boolean clickShoppingCartButton() {
        if(Utils.isClickable(getDriver(), BUTTON_shoppingCart, 10)) {
            return Utils.tryClicking(BUTTON_shoppingCart);
        }else{
            return false;
        }
    }

    public boolean isPageTitleCorrect() {
        if(Utils.isVisible(getDriver(), TEXT_pageTitle, 10)) {
            return Utils.isPageTitleCorrectAfterPolling(TEXT_pageTitle, "ITEM ADDED");
        }
        return false;
    }

    public boolean clickMoreMenuButton() {
        return Utils.tryClicking(BUTTON_moreMenu);
    }

    public void clickViewCartButton(){
         viewCartButton.click();
    }
}
