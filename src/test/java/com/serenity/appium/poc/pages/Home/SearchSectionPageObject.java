package com.serenity.appium.poc.pages.Home;

import com.serenity.appium.poc.pages.MobilePageObject;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSFindBy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SearchSectionPageObject extends MobilePageObject {

    //    @AndroidFindBy(xpath="//android.widget.TextView[@content-desc='inactive-search-bar-field']") // pending tw_mobile update
    @AndroidFindBy(xpath="//android.widget.TextView[@text='WHAT CAN WE HELP YOU FIND?']")
    @iOSFindBy(xpath="//XCUIElementTypeOther[@name='WHAT CAN WE HELP YOU FIND?']")
    private WebElement FIELD_searchPlaceholder;

    public SearchSectionPageObject(WebDriver driver) { super(driver); }

    public boolean initiateProductSearch(){
        try {
            FIELD_searchPlaceholder.click();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
