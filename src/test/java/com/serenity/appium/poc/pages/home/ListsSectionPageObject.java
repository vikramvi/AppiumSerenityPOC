package com.serenity.appium.poc.pages.home;

import com.serenity.appium.poc.pages.MobilePageObject;
import com.serenity.appium.poc.utils.Scrolling;
import com.serenity.appium.poc.utils.Utils;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ListsSectionPageObject extends MobilePageObject{

    @AndroidFindBy(xpath= "//android.view.ViewGroup/android.widget.TextView[@text='MY LISTS']")
    private WebElement MyListsSectionTitle;

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
                    Scrolling.androidSwipe(Scrolling.AndroidDirection.DOWN);
                    displayed = isViewAllButtonDisplayed(2);
                    scrollDownCounter++;
                }
        }

        //iOS TBD

        return displayed;
    }

    public void clickViewAllListsButton(){
        ViewAllListsButton.click();
    }

}
