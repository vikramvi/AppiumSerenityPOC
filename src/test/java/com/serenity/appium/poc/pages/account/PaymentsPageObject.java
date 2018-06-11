package com.serenity.appium.poc.pages.account;

import com.serenity.appium.poc.pages.MobilePageObject;
import com.serenity.appium.poc.utils.Utils;
import io.appium.java_client.MobileBy;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSFindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PaymentsPageObject extends MobilePageObject {

    @iOSFindBy(accessibility = "header-title")
    @AndroidFindBy(accessibility = "header-title")
    private WebElement TEXT_pageTitle;
    private static final String expectedTitle = "PAYMENTS";

    @AndroidFindBy(accessibility = "text-confirmation-delete-card")
    private WebElement TEXT_deleteCreditCardConfirmation;

    @AndroidFindBy(accessibility = "button-confirm-card-delete")
    private WebElement BUTTON_confirmCreditCardDelete;

    @AndroidFindBy(accessibility = "button-decline-card-delete")
    private WebElement BUTTON_declineCreditCardDelete;

    @AndroidFindBy(accessibility = "button-add-payment")
    private WebElement BUTTON_addNewPayment;

    @AndroidFindBy(accessibility =  "button-floating-return")
    private WebElement BUTTON_return;

    public enum CreditCardField {
        LAST4("(//android.widget.TextView[@content-desc='text-card-last4'])[%d]"),
        EXPIRATION("(//android.widget.TextView[@content-desc='text-card-expiration'])[%d]");
        String xpathPattern;
        CreditCardField(String xpathPattern) {
            this.xpathPattern = xpathPattern;
        }
        public String getText(WebDriver driver, int addressNumber) {
            String xpath = String.format(xpathPattern, addressNumber);
            String text = driver.findElement(By.xpath(xpath)).getText();
            return text;
        }
    }

    public enum CreditCardButton {
        DELETE("(//android.widget.Button[@content-desc='button-delete-payment'])[%d]"),
        EDIT("(//android.view.ViewGroup[@content-desc='button-update-payment'])[%d]");
        String xpathPattern;
        CreditCardButton(String xpathPattern) {
            this.xpathPattern = xpathPattern;
        }
        public void click(WebDriver driver, int addressNumber) {
            String xpath = String.format(xpathPattern, addressNumber);
            driver.findElement(By.xpath(xpath)).click();
        }
    }

    By BY_creditCardDeleteConfirmationText = MobileBy.AccessibilityId("text-confirmation-delete-card");
    public boolean isCreditCardDeleteConfirmationTextPresent() {
        return Utils.isVisible(getDriver(), BY_creditCardDeleteConfirmationText, 3);
    }

    public enum CreditCardDeleteConfirmation {
        CONFIRM("button-confirm-card-delete"),
        DECLINE("button-decline-card-delete");
        String accessibilityId;
        CreditCardDeleteConfirmation(String accessibilityId) {
            this.accessibilityId = accessibilityId;
        }
        public void click(WebDriver driver) {
            driver.findElement(MobileBy.AccessibilityId(accessibilityId)).click();
        }
    }

    public PaymentsPageObject(WebDriver driver) {
        super(driver);
    }

    public boolean isPageTitleCorrect() {
        return Utils.isPageTitleCorrectAfterPolling(TEXT_pageTitle, expectedTitle);
    }

    public boolean clickAndroidAddNewPaymentButton() {
        return Utils.tryClicking(getDriver(), BUTTON_addNewPayment);
    }

    public boolean clickAddNewPaymentButton() {
        if (isIOS()) {
            return Utils.clickIosUpdateButton();
        } else {
            return clickAndroidAddNewPaymentButton();
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
