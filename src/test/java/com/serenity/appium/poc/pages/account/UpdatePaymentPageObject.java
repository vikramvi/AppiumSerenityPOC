package com.serenity.appium.poc.pages.account;

import com.serenity.appium.poc.pages.MobilePageObject;
import com.serenity.appium.poc.utils.Scrolling;
import com.serenity.appium.poc.utils.Utils;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSFindBy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class UpdatePaymentPageObject extends MobilePageObject {

    @iOSFindBy(accessibility = "header-title")
    @AndroidFindBy(accessibility = "header-title")
    private WebElement TEXT_pageTitle;
    private static final String expectedTitle = "UPDATE PAYMENT";

    @AndroidFindBy(accessibility = "android-input-Card Number")
    private WebElement INPUT_cardNumber;

    @AndroidFindBy(accessibility = "android-input-Expiration MM / YY")
    private WebElement INPUT_expirationDate;

    @AndroidFindBy(accessibility = "android-input-Security Code (CVV)")
    private WebElement INPUT_cvv;

    @AndroidFindBy(accessibility = "android-input-Street Address")
    private WebElement INPUT_address1;

    @AndroidFindBy(accessibility = "android-input-Apt., Suite, Bldg (Optional)")
    private WebElement INPUT_address2;

    @AndroidFindBy(accessibility = "android-input-City")
    private WebElement INPUT_city;

    @AndroidFindBy(accessibility = "android-input-State Abbr.")
    private WebElement INPUT_state;

    @AndroidFindBy(accessibility = "android-input-Zip Code")
    private WebElement INPUT_zipcode;

    @AndroidFindBy(accessibility = "button-add-update-payment")
    private WebElement BUTTON_updatePayment;

    @AndroidFindBy(accessibility =  "button-floating-return")
    private WebElement BUTTON_return;

    @AndroidFindBy(accessibility = "button-confirm-address")
    private WebElement CONFIRM_ADDRESS;

    @AndroidFindBy(accessibility = "text-header-payment details")
    private WebElement paymentDetailsTitle;

    @AndroidFindBy(accessibility = "text-header-billing address")
    private WebElement billingAddressTitle;

    @AndroidFindBy(xpath = "//android.widget.TextView[@content-desc='header-title']")
    private WebElement postCreditCardAdditionScreenTitle;

    @AndroidFindBy(accessibility = "button-delete-payment")
    private WebElement paymentsScreenCreditCardEntryDeleteButton;


    public UpdatePaymentPageObject(WebDriver driver) {
        super(driver);
    }

    public boolean isPageTitleCorrect() {
        if(Utils.isVisible(getDriver(), TEXT_pageTitle, 15 )) {
            return Utils.isPageTitleCorrectAfterPolling(TEXT_pageTitle, expectedTitle);
        }
        return false;
    }

    public boolean isAddNewPaymentScreenDisplayed(){
        if( Utils.isVisible(getDriver(),paymentDetailsTitle, 10 ) && Utils.isVisible(getDriver(),billingAddressTitle, 10 ) ){
            return true;
        }
        return false;
    }

    public boolean isAddNewPaymentScreenDisplayed(int waitTime){
        if( Utils.isVisible(getDriver(),paymentDetailsTitle, waitTime ) && Utils.isVisible(getDriver(),billingAddressTitle, waitTime ) ){
            return true;
        }
        return false;
    }

    public enum Field {CARD_NUMBER, EXPIRATION, CVV, ADDRESS1, ADDRESS2, CITY, STATE, ZIP};

    public void enterText(Field field, String text) {
        WebElement element = getElement(field);
        element.sendKeys(text);
    }

    public String getText(Field field) {
        WebElement element = getElement(field);
        String result = element.getText();
        return result;
    }

    private WebElement getElement(Field field) {
        WebElement element = null;
        switch (field) {
            case CARD_NUMBER:
                element = INPUT_cardNumber;
                break;
            case EXPIRATION:
                element = INPUT_expirationDate;
                break;
            case CVV:
                element = INPUT_cvv;
                break;
            case ADDRESS1:
                element = INPUT_address1;
                break;
            case ADDRESS2:
                element = INPUT_address2;
                break;
            case CITY:
                element = INPUT_city;
                break;
            case STATE:
                element = INPUT_state;
                if (!Utils.isVisible(getDriver(), element, 3)) {
                    Scrolling.scrollDown();
                }
                break;
            case ZIP:
                element = INPUT_zipcode;
                if (!Utils.isVisible(getDriver(), element, 3)) {
                    Scrolling.scrollDown();
                }
        }
        return element;
    }

    public boolean clickAndroidUpdatePaymentButton() {
        if(Utils.isClickable(getDriver(), BUTTON_updatePayment, 5 )) {
            return Utils.tryClicking(getDriver(), BUTTON_updatePayment);
        }
        return false;
    }

    public boolean clickUpdatePaymentButton() {
        if (isIOS()) {
            return Utils.clickIosUpdateButton();
        } else {
            return clickAndroidUpdatePaymentButton();
        }
    }

    public boolean isCreditCardAddedSuccessfully(){
        if(Utils.isVisible(getDriver(), paymentsScreenCreditCardEntryDeleteButton, 20)){
            return true;
        }
        return false;
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

    public boolean addNewAddress(){ //TBD
        INPUT_address1.sendKeys("");
        INPUT_city.sendKeys("");
        INPUT_state.sendKeys("");
        INPUT_zipcode.sendKeys("");
        CONFIRM_ADDRESS.click();

        return false;
    }
}
