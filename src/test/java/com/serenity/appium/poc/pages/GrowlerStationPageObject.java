package com.serenity.appium.poc.pages;

import com.serenity.appium.poc.utils.Scrolling;
import io.appium.java_client.MobileBy;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSFindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class GrowlerStationPageObject extends MobilePageObject {

    @AndroidFindBy(accessibility = "header-title")
    @iOSFindBy(accessibility="header-title")
    private WebElement TEXT_headerTitle;

    By BY_headerTitle = MobileBy.AccessibilityId("header-title");
    public boolean isHeaderTitleDisplayed() {
        try {
            setImplicitTimeout(0, TimeUnit.SECONDS);
            withTimeoutOf(2, TimeUnit.SECONDS).waitForPresenceOf(BY_headerTitle);
            resetImplicitTimeout();
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

    By BY_sectionTitle1 = MobileBy.AccessibilityId("growler-section-title");
    public boolean isSectionTitleDisplayed(By byReference) {
        try {
//            withTimeoutOf(1, TimeUnit.SECONDS).waitForPresenceOf(BY_sectionTitle);
            setImplicitTimeout(0, TimeUnit.SECONDS);
            new WebDriverWait(getDriver(), 1).until(ExpectedConditions.visibilityOfElementLocated(byReference));
            resetImplicitTimeout();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public String getSectionTitle(By byReference) {
        String result = getDriver().findElement(byReference).getText();
        return result;
    }

   public enum SectionTitle {
        CURRENT_SELECTIONS("This week's selection", MobileBy.AccessibilityId("growler-section-title")),
        ON_DECK("On deck", MobileBy.xpath("(//XCUIElementTypeStaticText[@name=\"growler-section-title\"])[2]"));
        private String title;
        private By by;
        SectionTitle(String title, By by) {
            this.title = title;
            this.by = by;
        }
        public String getTitle() {
            return title;
        }
        public By getByReference() {
            return by;
        }
    }

    public boolean doesGrowlerSectionTitleMatch(SectionTitle expected) {
        boolean matched = false;
        String actual = "";
        boolean displayed = isSectionTitleDisplayed(expected.getByReference());
        if (displayed) {
            actual = getSectionTitle(expected.getByReference());
            matched = actual.equalsIgnoreCase(expected.getTitle());
        }
        int i =0;
        while (!matched && i<2) {
            if (Scrolling.scrollDown()) {
                displayed = isSectionTitleDisplayed(expected.getByReference());
                if (displayed) {
                    actual = getSectionTitle(expected.getByReference());
                    matched = actual.equalsIgnoreCase(expected.getTitle());
                }
            }
            i++;
        }
        return matched;
    }

    public enum GrowlerCard {
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
                "(//XCUIElementTypeStaticText[@name='growler-large-label'])[%d]"),
        LARGE_FILL_COST(
                "(//android.widget.TextView[@content-desc='growler-large-price'])[%d]",
                "(//XCUIElementTypeStaticText[@name='growler-large-price'])[%d]");

        private String androidXpathPattern;
        private String iosXpathPattern;
        GrowlerCard(String androidXpathPattern, String iosXpathPattern) {
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
        public String getNonIndexedXpath() {
            String xpathPattern =isAndroid() ? androidXpathPattern : iosXpathPattern;
            String result = xpathPattern.replace("(","").replace(")[%d]", "");
            return result;
        }
    }

    public boolean areCorrectLabelsPresentOnCard(int cardIndex) {
        boolean result = false;
        if (GrowlerCard.ABV_LABEL.getText(getDriver(), cardIndex).equals("ABV")) {
            if (GrowlerCard.IBU_LABEL.getText(getDriver(), cardIndex).equals("IBU")) {
                if (GrowlerCard.SMALL_FILL_LABEL.getText(getDriver(), cardIndex).equals("32 OZ")) {
                    if (GrowlerCard.LARGE_FILL_LABEL.getText(getDriver(), cardIndex).equals("64 OZ")) {
                        result = true;
                    }
                }
            }
        }
        return result;
    }

    private int getLastCardIndex() {
        String xpath = GrowlerCard.BREWERY.getNonIndexedXpath();
        int index = getDriver().findElements(By.xpath(xpath)).size();
        return index;
    }

    public boolean areCorrectLabelsPresentOnLastCard() {
        return areCorrectLabelsPresentOnCard(getLastCardIndex());
    }

    public boolean isBreweryLabelPresentOnCard(int cardIndex) {
        return GrowlerCard.BREWERY.isDisplayed(getDriver(), cardIndex);
    }

    public boolean isLabelPresentOnLastCard(GrowlerCard growlerCardElement) {
        return growlerCardElement.isDisplayed(getDriver(), getLastCardIndex());
    }

    public boolean isBeerLabelPresentOnCard(int cardIndex) {
        return GrowlerCard.BEER.isDisplayed(getDriver(), cardIndex);
    }

    private static String abvRegex = "([1-9]?[0-9]\\.[0-9]{2}%)";
    private static String ibuRegex = "(\\-|[1-9][0-9]{0,1}|1[01][0-9]|120)";
    private static String priceRegex = "(\\$(1[0-9][0-9]|[1-9][0-9]{0,1}).[0-9]{2})";
    public boolean areValuesValidOnCard(int cardIndex) {
        boolean result = false;
        if (GrowlerCard.ABV_VALUE.getText(getDriver(), cardIndex).matches(abvRegex)) {
            if (GrowlerCard.IBU_VALUE.getText(getDriver(), cardIndex).matches(ibuRegex)) {
                if (GrowlerCard.SMALL_FILL_COST.getText(getDriver(), cardIndex).matches(priceRegex)) {
                    if (GrowlerCard.LARGE_FILL_COST.getText(getDriver(), cardIndex).matches(priceRegex)) {
                        result = true;
                    }
                }
            }
        }
        return result;
    }

    public boolean areValuesValidOnLastCard() {
        return areValuesValidOnCard(getLastCardIndex());
    }

    public GrowlerStationPageObject(WebDriver driver) { super(driver); }
}
