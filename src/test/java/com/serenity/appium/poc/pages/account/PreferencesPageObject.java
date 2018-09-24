package com.serenity.appium.poc.pages.account;

import com.serenity.appium.poc.pages.MobilePageObject;
import com.serenity.appium.poc.utils.Scrolling;
import com.serenity.appium.poc.utils.Utils;
import io.appium.java_client.MobileBy;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSFindBy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class PreferencesPageObject extends MobilePageObject {

    @iOSFindBy(accessibility = "header-title")
    @AndroidFindBy(accessibility = "header-title")
    private WebElement TEXT_pageTitle;
    private static final String expectedTitle = "PREFERENCES";

    @iOSFindBy(accessibility = "text-preferred-store-title")
    @AndroidFindBy(accessibility = "text-preferred-store-title")
    private WebElement TEXT_preferredStoreTitle;

    @iOSFindBy(accessibility = "button-change-store")
    @AndroidFindBy(accessibility = "button-change-store")
    private WebElement BUTTON_changePreferredStore;

    @iOSFindBy(accessibility = "text-product-interests-subheading")
    @AndroidFindBy(accessibility = "text-product-interests-subheading")
    private WebElement TEXT_productInterestsSubheading;

    @AndroidFindBy(accessibility = "button-update-preferences")
    private WebElement BUTTON_update;

    @AndroidFindBy(accessibility =  "button-floating-return")
    private WebElement BUTTON_return;

    public PreferencesPageObject(WebDriver driver) {
        super(driver);
    }

    public boolean isPageTitleCorrect() {
        if(Utils.isVisible(getDriver(), TEXT_pageTitle, 15)) {
            return Utils.isPageTitleCorrectAfterPolling(TEXT_pageTitle, expectedTitle);
        }
        return false;
    }

    public enum Headers {
        STORE("preferred store", false),
        INTERESTS("product interests", false),
        COMMUNICATIONS("promotional communication", true);
        String title;
        String id;
        boolean offscreen;
        Headers(String title, boolean offscreen) {
            this.title = title.toUpperCase();
            this.id = "text-header-" + title;
            this.offscreen = offscreen;
        }
        public boolean isVisible(WebDriver driver) {
            if (offscreen) {
                Scrolling.scrollDown();
            }
            boolean result = Utils.isVisible(driver, MobileBy.AccessibilityId(id), 3);
//            WebElement element = driver.findElement(MobileBy.AccessibilityId(id));
//            boolean result = element.isDisplayed();

            return result;
        }
    }

    private static final String expectedProductInterestsSubheader =
            "Let us know your interests and we will send you information regarding your selection.";
    public boolean verifyProductInterestsSubheading() {
        String actual = TEXT_productInterestsSubheading.getText();
        boolean result = actual.equals(expectedProductInterestsSubheader);
        return result;
    }


    private static final String uncheckedPattern = "checkbox-%s-unchecked";
    private static final String checkedPattern = "checkbox-%s-checked";
    public enum Preferences {
//        SPIRITS("checkbox-spirits-unchecked", "checkbox-spirits-checked"),
//        WINE("checkbox-wine-unchecked", "checkbox-wine-checked"),
//        BEER("checkbox-beer-unchecked", "checkbox-beer-checked"),
//        CIGARS("checkbox-cigars-unchecked", "checkbox-cigars-checked"),
//        PROMOTIONS("checkbox-promotions-unchecked", "checkbox-promotions-checked"),
//        EVENTS("checkbox-events-unchecked", "checkbox-events-checked");
        SPIRITS,
        WINE,
        BEER,
        CIGARS,
        PROMOTIONS,
        EVENTS;
        String unchecked;
        String checked;
        Preferences() {
            this.unchecked = String.format(uncheckedPattern, this.name().toLowerCase());
            this.checked = String.format(checkedPattern, this.name().toLowerCase());
        }
        public boolean isChecked(WebDriver driver, String productName) {
            return Utils.isVisible(driver, MobileBy.AccessibilityId("checkbox-"+ productName +"-checked"), 2);
        }
        public boolean isUnchecked(WebDriver driver, String productName) {
            return Utils.isVisible(driver, MobileBy.AccessibilityId("checkbox-"+ productName +"-unchecked"), 2);
        }
        public boolean check(WebDriver driver, String productName) {
            return Utils.tryClicking(driver, MobileBy.AccessibilityId("checkbox-"+ productName +"-unchecked"));
        }
        public boolean uncheck(WebDriver driver, String productName) {
            return Utils.tryClicking(driver, MobileBy.AccessibilityId("checkbox-"+ productName +"-checked"));
        }
        public boolean isVisible(WebDriver driver, String productName) {
            boolean result = isChecked(driver, productName) || isUnchecked(driver, productName);
            return result;
        }
        public boolean setToChecked(WebDriver driver, String productName) {
            try {
                if (isUnchecked(driver, productName)) {
                    check(driver, productName);
                }
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
        public boolean setToUnchecked(WebDriver driver, String productName) {
            try {
                if (isChecked(driver, productName)) {
                    uncheck(driver, productName);
                }
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
    }

    public boolean isAndroidUpdateButtonVisible() {
        return Utils.isVisible(getDriver(), BUTTON_update, 3);
    }

    public boolean clickAndroidUpdateButton() {
        return Utils.tryClicking(getDriver(), BUTTON_update);
    }

    public boolean clickUpdateButton() {
        if (isIOS()) {
            return Utils.clickIosUpdateButton();
        } else {
            return clickAndroidUpdateButton();
        }
    }

    public boolean isAndroidReturnButtonVisible() {
        return Utils.isVisible(getDriver(), BUTTON_return, 3);
    }

    public boolean clickAndroidReturn() {
        return Utils.tryClicking(BUTTON_return);
    }

    public boolean clickReturnButton() {
        if (isIOS()) {
            return Utils.clickIosReturnButton();
        } else {
            return clickAndroidReturn();
        }
    }
}
