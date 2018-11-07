package com.serenity.appium.poc.pages.home;

import com.serenity.appium.poc.pages.MobilePageObject;
import com.serenity.appium.poc.utils.Scrolling;
import com.serenity.appium.poc.utils.Utils;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ListsSectionPageObject extends MobilePageObject{

//    @AndroidFindBy(xpath= "//android.view.ViewGroup/android.widget.TextView[@text='MY LISTS']")
    @AndroidFindBy(xpath= "//android.widget.TextView[@content-desc='header-title' and @text='MY LISTS']")
    private WebElement MyListsSectionTitle;

//    @AndroidFindBy(xpath="//android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup")
//    @AndroidFindBy(xpath="//android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup")
    @AndroidFindBy(accessibility = "link-shopping-list")
    private List<WebElement> listsCount;

    @AndroidFindBy(xpath="//android.view.ViewGroup/android.widget.Button/android.widget.TextView[@text='VIEW ALL']")
    private WebElement ViewAllListsButton;


    public ListsSectionPageObject(WebDriver driver) {
        super(driver);
    }

    public boolean isViewAllButtonDisplayed(int waitTime){
        if(Utils.isVisible(getDriver(), ViewAllListsButton, waitTime)){
            return true;
        }
        return false;
    }

    public boolean isMyListsSectionDisplayed() {
        boolean displayed = false;
        int scrollDownCounter = 0;
        if (isAndroid()) {
            displayed = isViewAllButtonDisplayed(2);

                while ( !displayed && (scrollDownCounter < 4) ) {
                    Scrolling.androidSwipe(Scrolling.AndroidDirection.DOWN, 0.85, 0.15);
                    displayed = isViewAllButtonDisplayed(1);
                    scrollDownCounter++;
                }
        }

        //iOS TBD

        return displayed;
    }

    public void clickViewAllListsButton(){
        ViewAllListsButton.click();
    }

    public int getMyListsCount(){
        return listsCount.size();
    }

}
