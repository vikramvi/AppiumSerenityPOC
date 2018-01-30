package com.serenity.appium.poc.pages.storeDetails;

import com.serenity.appium.poc.pages.MobilePageObject;
import com.serenity.appium.poc.utils.Scrolling;
import com.serenity.appium.poc.utils.Utils;
import io.appium.java_client.MobileBy;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSFindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.concurrent.TimeUnit;

public class StoreGrowlerStationPageObject extends MobilePageObject {

    @AndroidFindBy(accessibility = "button-see-craft-beer-rotation")
    @iOSFindBy(accessibility = "button-see-craft-beer-rotation")
    private WebElement BUTTON_findOutMore;

    @AndroidFindBy(xpath = "//android.widget.Button[@content-desc='button-see-craft-beer-rotation']/parent::*/android.widget.TextView")
    @iOSFindBy(xpath = "//XCUIElementTypeButton[@name='button-see-craft-beer-rotation']/parent::*/parent::*//XCUIElementTypeStaticText")
    private WebElement TEXT_growlerCopy;

    By BY_findOutMore = MobileBy.AccessibilityId("button-see-craft-beer-rotation");
    public boolean isFindOutMoreButtonDisplayed() {
        try {
            withTimeoutOf(2, TimeUnit.SECONDS).waitForPresenceOf(BY_findOutMore);
            return true;
//            boolean result = Utils.isVisible(getDriver(), BUTTON_findOutMore, 2);
//            return result;
        } catch (Exception e) {
            return false;
        }
    }

    public String getGrowlerCopy() {
        String result = TEXT_growlerCopy.getText();
        return result;
    }

    private String TEXT_growlerCopySegment = "craft beers";
    public boolean doesGrowlerCopyMatch() {
        String actual = getGrowlerCopy();
        boolean result = actual.contains(TEXT_growlerCopySegment);
        return result;
    }

    public boolean isGrowlerStationSectionDisplayed() {
        setImplicitTimeout(0, TimeUnit.SECONDS);
        boolean displayed = false;
        if (isAndroid()) {
            displayed = isFindOutMoreButtonDisplayed();
            int i = 0;
            while (!displayed && (i<4)) {
                Scrolling.androidSwipe(Scrolling.AndroidDirection.DOWN);
                displayed = isFindOutMoreButtonDisplayed();
                if (displayed) {
                    break;
                }
                i++;
            }
        } else {
            Scrolling.iosScroll(Scrolling.IosDirection.DOWN);
            Scrolling.iosScroll(Scrolling.IosDirection.DOWN);
            displayed = BUTTON_findOutMore.isDisplayed();
        }
        resetImplicitTimeout();
        return displayed;
    }

    public StoreGrowlerStationPageObject(WebDriver driver) {
        super(driver);
    }

}
