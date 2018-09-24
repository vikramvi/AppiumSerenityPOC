package com.serenity.appium.poc.pages.orderingFlow;

import com.serenity.appium.poc.pages.MobilePageObject;
import com.serenity.appium.poc.utils.Scrolling;
import com.serenity.appium.poc.utils.Utils;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class OrderReview extends MobilePageObject {

    @AndroidFindBy(accessibility = "header-title")
    private WebElement TEXT_pageTitle;
    private static final String expectedTitle = "ORDER REVIEW";

    @AndroidFindBy(accessibility = "text-card-last4")
    WebElement CreditCardLastFourDigitDisplayText;

    @AndroidFindBy(accessibility = "text-card-expiration")
    WebElement CreditCardExpiryDisplayText;

    @AndroidFindBy(xpath="//android.widget.TextView[contains(@text,'of at least 21 years of age')]")
    WebElement AgreementAboutValidIdToVerifyAgeCardCheckbox;

    @AndroidFindBy(accessibility = "button-login")
    WebElement CompleteOrderButton;

    @AndroidFindBy(xpath="//android.view.ViewGroup/android.widget.Button/android.view.ViewGroup/android.widget.TextView[2][@text='YOUR ORDER IS COMPLETE']")
    WebElement YourOrderIsCompleteTitle;

    @AndroidFindBy(accessibility = "order-details")
    WebElement ViewDetailsButton;

    public OrderReview(WebDriver driver){
        super(driver);
    }

    public boolean isOrderReviewPageTitleCorrect() {
        if(Utils.isVisible(getDriver(), TEXT_pageTitle, 15 )) {
            return Utils.isPageTitleCorrectAfterPolling(TEXT_pageTitle, expectedTitle);
        }
        return false;
    }

    public void verifyCreditCardLastFourDigits(String creditCardNumber){
        System.out.println(CreditCardLastFourDigitDisplayText.getText());
    }

    public void verifyCreditCardExpiry(String expiryInfo){
        System.out.println(CreditCardExpiryDisplayText.getText());
    }

    public boolean clickAgreeToShowAgeProofCheckbox(){
        Scrolling.scrollDown();
        if(AgreementAboutValidIdToVerifyAgeCardCheckbox.isDisplayed()) {
            AgreementAboutValidIdToVerifyAgeCardCheckbox.click();
            return true;
        }
        return false;
    }

    public boolean completeOrderAndGotoViewDetailsScreen(){
        CompleteOrderButton.click();

        if(Utils.isVisible(getDriver(), YourOrderIsCompleteTitle, 20)){
            ViewDetailsButton.click();
            return true;
        }
        return false;
    }

}
