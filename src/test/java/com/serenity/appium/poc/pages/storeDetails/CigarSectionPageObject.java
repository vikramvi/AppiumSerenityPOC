package com.serenity.appium.poc.pages.storeDetails;

import com.serenity.appium.poc.pages.MobilePageObject;
import com.serenity.appium.poc.utils.Scrolling;
import io.appium.java_client.MobileBy;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSFindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.time.temporal.ChronoUnit;
import java.util.concurrent.TimeUnit;

public class CigarSectionPageObject extends MobilePageObject {

    @AndroidFindBy(accessibility = "button-see-all-cigars")
    @iOSFindBy(accessibility = "button-see-all-cigars")
    private WebElement BUTTON_seeAllCigars;

    @AndroidFindBy(xpath = "//android.widget.Button[@content-desc='button-see-all-cigars']/parent::*/android.widget.TextView")
    @iOSFindBy(xpath = "//XCUIElementTypeButton[@name='button-see-all-cigars']/parent::*/parent::*//XCUIElementTypeStaticText")
    private WebElement TEXT_cigarCopy;

    By BY_seeAllCigars = MobileBy.AccessibilityId("button-see-all-cigars");
    public boolean isSeeAllCigarsButtonDisplayed() {
        try {
            withTimeoutOf(2, TimeUnit.SECONDS).waitForPresenceOf(BY_seeAllCigars);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public String getCigarCopy() {
        String result = TEXT_cigarCopy.getText();
        return result;
    }

    private String TEXT_cigarCopySegment = "special-occasion cigar";
    public boolean doesCigarCopyMatch() {
        String actual = getCigarCopy();
        boolean result = actual.contains(TEXT_cigarCopySegment);
        return result;
    }

    public boolean isCigarSectionDisplayed() {

        boolean displayed = false;
        int i = 0;
        if (isAndroid()) {
            displayed = isSeeAllCigarsButtonDisplayed();
            while (!displayed && (i<4)) {
                Scrolling.androidSwipe(Scrolling.AndroidDirection.DOWN);
                displayed = isSeeAllCigarsButtonDisplayed();
                i++;
            }
        } else {
            displayed = isSeeAllCigarsButtonDisplayed();
            while (!displayed && (i<2)) {
                Scrolling.iosScroll(Scrolling.IosDirection.DOWN);
                displayed = isSeeAllCigarsButtonDisplayed();
                i++;
            }
        }

        return displayed;
    }

    public CigarSectionPageObject(WebDriver driver) {
        super(driver);
    }

}
