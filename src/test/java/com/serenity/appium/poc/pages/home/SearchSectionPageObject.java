package com.serenity.appium.poc.pages.home;

import com.serenity.appium.poc.pages.MobilePageObject;
import com.serenity.appium.poc.utils.Utils;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSFindBy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SearchSectionPageObject extends MobilePageObject {

    @AndroidFindBy(xpath="//android.widget.TextView[@text='WHAT CAN WE HELP YOU FIND?']")
    @iOSFindBy(accessibility="WHAT CAN WE HELP YOU FIND?")
    private WebElement FIELD_searchPlaceholder;

    public SearchSectionPageObject(WebDriver driver) { super(driver); }

    public boolean triggerSearchPage(){
        try{
            if(Utils.isVisible(getDriver(), FIELD_searchPlaceholder, 10)) {
                FIELD_searchPlaceholder.click();
                return true;
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
