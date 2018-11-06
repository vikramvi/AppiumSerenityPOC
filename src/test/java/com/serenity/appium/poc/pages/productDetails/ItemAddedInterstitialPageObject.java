package com.serenity.appium.poc.pages.productDetails;

import com.serenity.appium.poc.pages.MobilePageObject;
import com.serenity.appium.poc.utils.Utils;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class ItemAddedInterstitialPageObject extends MobilePageObject {

    @AndroidFindBy(accessibility = "header-title")
    private WebElement TEXT_pageTitle;

    @AndroidFindBy(xpath="//android.view.ViewGroup/android.view.ViewGroup[2]/android.widget.Button[2]/android.widget.TextView[@text='VIEW CART']")
    private WebElement viewCartButton;

    @AndroidFindBy(xpath="//android.widget.ScrollView//android.widget.HorizontalScrollView//android.view.ViewGroup[1]/android.widget.Button")
    private WebElement leftSideItemAddToCartButton;

    @AndroidFindBy(xpath="//android.widget.ScrollView//android.widget.HorizontalScrollView//android.view.ViewGroup[2]/android.widget.Button")
    private WebElement rightSideItemAddToCartButton;


    public ItemAddedInterstitialPageObject(WebDriver driver) {
        super(driver);
    }

    public boolean isPageTitleCorrect() {
        if(Utils.isVisible(getDriver(), TEXT_pageTitle, 10)) {
            return Utils.isPageTitleCorrectAfterPolling(TEXT_pageTitle, "ITEM ADDED");
        }
        return false;
    }

    public void clickViewCartButton(){
        viewCartButton.click();
    }

    private String XPATH_RentAccessoriesTitle = "//android.widget.ScrollView/android.view.ViewGroup/android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup/android.widget.TextView";
    public boolean isRentAccessoriesTitlePresent(){

        if(getDriver().findElement(By.xpath(XPATH_RentAccessoriesTitle)).getText().equals("DON'T FORGET TO RENT THESE KEG ACCESSORIES")){
            return true;
        }
        return false;
    }

    public void clickRentLeftSideItem(){
        leftSideItemAddToCartButton.click();
    }

    public void clickRentRightSideItem(){
        rightSideItemAddToCartButton.click();
    }

    public boolean isToastMessageDisplayed(){
        return Utils.isToastMessageDisplayed("SUCCESS", "Your item was added.");
    }


    String XPATH_ItemAddedToCart = "//android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup/android.widget.TextView[3]";
    String XPATH_ItemsForRent = "//android.widget.TextView[@content-desc='product-name']";
    public List<String> getListOfItemsFromInterstitialPage() {
        try {
            List<String> itemsFromItemAddedScreen = new ArrayList<>();

            itemsFromItemAddedScreen.add(getDriver().findElement(By.xpath(XPATH_ItemAddedToCart)).getText());
            itemsFromItemAddedScreen.add(getDriver().findElements(By.xpath(XPATH_ItemsForRent)).get(0).getText());
            itemsFromItemAddedScreen.add(getDriver().findElements(By.xpath(XPATH_ItemsForRent)).get(1).getText());

            return itemsFromItemAddedScreen;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
