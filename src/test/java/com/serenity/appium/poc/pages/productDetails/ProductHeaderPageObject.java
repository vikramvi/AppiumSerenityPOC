package com.serenity.appium.poc.pages.productDetails;

import com.serenity.appium.poc.pages.MobilePageObject;
import com.serenity.appium.poc.utils.Utils;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSFindBy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ProductHeaderPageObject extends MobilePageObject {

    @AndroidFindBy(accessibility = "touchableIcon-search-products-product-detail")
    @iOSFindBy(accessibility = "touchableIcon-search-products-product-detail")
    private WebElement TOUCHABLE_ICON_searchProducts;

    @AndroidFindBy(accessibility = "touchableIcon-view-cart-product-detail")
    @iOSFindBy(accessibility = "touchableIcon-view-cart-product-detail")
    private WebElement TOUCHABLE_ICON_viewShoppingCart;

    public boolean isSearchProductsIconPresent() {
        return TOUCHABLE_ICON_searchProducts.isDisplayed();
    }

    public boolean isViewCartIconPresent() {
        return TOUCHABLE_ICON_viewShoppingCart.isDisplayed();
    }

    public boolean clickSearchProducts() {
        return Utils.tryClicking(TOUCHABLE_ICON_searchProducts);
    }

    public boolean clickViewCart() {
        return Utils.tryClicking(TOUCHABLE_ICON_viewShoppingCart);
    }

    public ProductHeaderPageObject(WebDriver driver) {
        super(driver);
    }
}
