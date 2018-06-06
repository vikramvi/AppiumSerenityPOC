package com.serenity.appium.poc.pages.account;

import com.serenity.appium.poc.pages.MobilePageObject;
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

    @AndroidFindBy(accessibility = "button-update-preferences")
    private WebElement BUTTON_update;

    @AndroidFindBy(accessibility =  "button-floating-return")
    private WebElement BUTTON_return;

    public PreferencesPageObject(WebDriver driver) {
        super(driver);
    }

    public boolean isPageTitleCorrect() {
        boolean found = TEXT_pageTitle.getText().equals(expectedTitle);
        int i = 0;
        while (i<12 && !found) {
            try {
                Thread.sleep(250);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            String actual = TEXT_pageTitle.getText();
            found = actual.equals(expectedTitle);
            i++;
        }
        return found;
    }

    public enum Preferences {
        SPIRITS("checkbox-spirits-unchecked", "checkbox-spirits-checked"),
        WINE("checkbox-wine-unchecked", "checkbox-wine-checked"),
        BEER("checkbox-beer-unchecked", "checkbox-beer-checked"),
        CIGARS("checkbox-cigars-unchecked", "checkbox-cigars-checked"),
        PROMOTIONS("checkbox-promotions-unchecked", "checkbox-promotions-checked"),
        EVENTS("checkbox-events-unchecked", "checkbox-events-checked");
        String unchecked;
        String checked;
        Preferences(String unchecked, String checked) {
            this.unchecked = unchecked;
            this.checked = checked;
        }
        public boolean isChecked(WebDriver driver) {
            return Utils.isVisible(driver, MobileBy.AccessibilityId(checked), 3);
        }
        public boolean isUnchecked(WebDriver driver) {
            return Utils.isVisible(driver, MobileBy.AccessibilityId(unchecked), 3);
        }
        public boolean check(WebDriver driver) {
            return Utils.tryClicking(driver, MobileBy.AccessibilityId(unchecked));
        }
        public boolean uncheck(WebDriver driver) {
            return Utils.tryClicking(driver, MobileBy.AccessibilityId(checked));
        }

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
