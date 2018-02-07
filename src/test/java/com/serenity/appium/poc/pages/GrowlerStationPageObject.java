package com.serenity.appium.poc.pages;

import com.serenity.appium.poc.utils.Scrolling;
import io.appium.java_client.MobileBy;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSFindBy;
import net.thucydides.core.webdriver.WebdriverAssertionError;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.concurrent.TimeUnit;

public class GrowlerStationPageObject extends MobilePageObject {

    @AndroidFindBy(accessibility = "header-title")
    @iOSFindBy(accessibility="header-title")
    private WebElement TEXT_headerTitle;

    By BY_headerTitle = MobileBy.AccessibilityId("header-title");
    public boolean isHeaderTitleDisplayed() {
        try {
            withTimeoutOf(2, TimeUnit.SECONDS).waitForPresenceOf(BY_headerTitle);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public String getHeaderTitle() {
        String result = TEXT_headerTitle.getText();
        return result;
    }

    private String TEXT_expectedHeaderTitle = "WHAT'S ON TAP?";
    public boolean doesGrowlerPageTitleMatch() {
        String actual = getHeaderTitle();
        boolean result = actual.equals(TEXT_expectedHeaderTitle);
        return result;
    }

    By BY_sectionTitle = MobileBy.AccessibilityId("growler-section-title");
    public boolean isSectionTitleDisplayed() {
        try {
            withTimeoutOf(1, TimeUnit.SECONDS).waitForPresenceOf(BY_sectionTitle);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public String getSectionTitle() {
        String result = getDriver().findElement(BY_sectionTitle).getText();
        return result;
    }

    public boolean doesGrowlerSectionTitleMatch(String expected) {
        boolean matched = false;
        int i =0;
        boolean displayed = isSectionTitleDisplayed();
        while (!displayed && i<2) {
            if (Scrolling.scrollDown()) {
                displayed = isSectionTitleDisplayed();
            }
            i++;
        }
        if (displayed) {
            String actual = getSectionTitle();
            matched = actual.equals(expected);
        }
        return matched;
    }

    private enum AndroidGrowlerCard {
        BREWERY(
                "(//android.widget.TextView[@content-desc='growler-card-header-brewery'])[%d]",
                "(//XCUIElementTypeStaticText[@name='growler-card-header-brewery'])[%d]"),
        BEER(
                "(//android.widget.TextView[@content-desc='growler-card-header-beer'])[%d]",
                "(//XCUIElementTypeStaticText[@name='growler-card-header-beer'])[%d]"),
        ABV_LABEL(
                "(//android.widget.TextView[@content-desc='growler-card-abv-label'])[%d]",
                "(//XCUIElementTypeStaticText[@name='growler-card-abv-label'])[%d]"),
        ABV_VALUE(
                "(//android.widget.TextView[@content-desc='growler-card-abv-value'])[%d]",
                "(//XCUIElementTypeStaticText[@name='growler-card-abv-value'])[%d]"),
        IBU_LABEL(
                "(//android.widget.TextView[@content-desc='growler-card-ibu-label'])[%d]",
                "(//XCUIElementTypeStaticText[@name='growler-card-ibu-label'])[%d]"),
        IBU_VALUE(
                "(//android.widget.TextView[@content-desc='growler-card-ibu-value'])[%d]",
                "(//XCUIElementTypeStaticText[@name='growler-card-ibu-value'])[%d]"),
        SMALL_FILL_LABEL(
                "(//android.widget.TextView[@content-desc='growler-small-label'])[%d]",
                "(//XCUIElementTypeStaticText[@name='growler-small-label'])[%d]"),
        SMALL_FILL_COST(
                "(//android.widget.TextView[@content-desc='growler-small-price'])[%d]",
                "(//XCUIElementTypeStaticText[@name='growler-small-price'])[%d]"),
        LARGE_FILL_LABEL(
                "(//android.widget.TextView[@content-desc='growler-large-label'])[%d]",
                "(//XCUIElementTypeStaticText[@name='growler-small-price'])[%d]"),
        LARGE_FILL_COST(
                "(//android.widget.TextView[@content-desc='growler-large-price'])[%d]",
                "(//XCUIElementTypeStaticText[@name='growler-large-price'])[%d]");

        private String androidXpathPattern;
        private String iosXpathPattern;
        AndroidGrowlerCard(String androidXpathPattern, String iosXpathPattern) {
            this.androidXpathPattern = androidXpathPattern;
            this.iosXpathPattern = iosXpathPattern;
        }
        public boolean isDisplayed(WebDriver driver, int index) {
            String xpathPattern = iosXpathPattern;
            if (isAndroid()) {
                xpathPattern = androidXpathPattern;
            }
            String xpath = String.format(xpathPattern, index);
            boolean result = driver.findElement(By.xpath(xpath)).isDisplayed();
            return result;
        }
        public String getText(WebDriver driver, int index) {
            String xpathPattern = iosXpathPattern;
            if (isAndroid()) {
                xpathPattern = androidXpathPattern;
            }
            String xpath = String.format(xpathPattern, index);
            String result = driver.findElement(By.xpath(xpath)).getText();
            return result;
        }
    }

    public GrowlerStationPageObject(WebDriver driver) { super(driver); }
}
