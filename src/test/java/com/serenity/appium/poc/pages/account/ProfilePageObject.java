package com.serenity.appium.poc.pages.account;

import com.serenity.appium.poc.pages.MobilePageObject;
import com.serenity.appium.poc.utils.IosProfileButtonSelector;
import com.serenity.appium.poc.utils.Utils;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSFindBy;
import io.appium.java_client.touch.LongPressOptions;
import net.thucydides.core.webdriver.WebDriverFacade;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.PerformsTouchActions;

import java.time.Duration;
import java.util.HashMap;

import static io.appium.java_client.touch.LongPressOptions.longPressOptions;
import static net.thucydides.core.webdriver.ThucydidesWebDriverSupport.getDriver;

public class ProfilePageObject extends MobilePageObject {

    @AndroidFindBy(accessibility = "android-input-First Name")
    @iOSFindBy(accessibility = "ios-input-First Name")
    private WebElement INPUT_firstName;

    @AndroidFindBy(accessibility = "android-input-Last Name")
    @iOSFindBy(accessibility = "ios-input-Last Name")
    private WebElement INPUT_lastName;

    @AndroidFindBy(accessibility = "android-input-Phone Number")
    @iOSFindBy(accessibility = "ios-input-Phone Number")
    private WebElement INPUT_phoneNumber;

    @AndroidFindBy(accessibility = "android-input-Password")
    @iOSFindBy(accessibility = "ios-input-Password")
    private WebElement INPUT_password;

    @AndroidFindBy(accessibility = "android-input-Confirmation")
    @iOSFindBy(accessibility = "ios-input-Confirmation")
    private WebElement INPUT_passwordConfirmation;

    @AndroidFindBy(accessibility = "android-input-Email")
    @iOSFindBy(accessibility = "ios-input-Email")
    private WebElement INPUT_email;

    @AndroidFindBy(accessibility = "touchable-date-picker")
    @iOSFindBy(accessibility = "touchable-date-picker")
    private WebElement DATE_PICKER_birthday;

    @AndroidFindBy(accessibility = "button-update-profile")
    private WebElement BUTTON_update;

    @AndroidFindBy(accessibility =  "button-floating-return")
    private WebElement BUTTON_return;


    public ProfilePageObject(WebDriver driver) {
        super(driver);
    }

    public boolean isFirstNameFieldPresent() {
        return Utils.isVisible(getDriver(), INPUT_firstName, 5);
    }

    public boolean isLastNameFieldPresent() {
        return Utils.isVisible(getDriver(), INPUT_lastName, 5);
    }

    public boolean isPhoneNumberFieldPresent() {
        return Utils.isVisible(getDriver(), INPUT_phoneNumber, 5);
    }

    public boolean isPasswordFieldPresent() {
        return Utils.isVisible(getDriver(), INPUT_password, 5);
    }

    public boolean isPasswordConfirmationFieldPresent() {
        return Utils.isVisible(getDriver(), INPUT_passwordConfirmation, 5);
    }

    public boolean isEmailFieldPresent() {
        return Utils.isVisible(getDriver(), INPUT_email, 5);
    }

    public boolean isBirthdayDatePickerPresent() {
        return Utils.isVisible(getDriver(), DATE_PICKER_birthday, 5);
    }

    public String getFirstName() {
        String value = INPUT_firstName.getText();
        return value;
    }

    public void enterFirstName(String firstName, boolean clearFirst){
        if (clearFirst) {
            clearField(INPUT_firstName);
        }
        INPUT_firstName.sendKeys(firstName);
    }

    public void enterLastName(String lastName, boolean clearFirst){
        if (clearFirst) {
            clearField(INPUT_lastName);
        }
        INPUT_lastName.sendKeys(lastName);
    }

    public void enterPhoneNumber(String phoneNumber, boolean clearFirst){
        if (clearFirst) {
            clearField(INPUT_phoneNumber);
        }
        INPUT_phoneNumber.sendKeys(phoneNumber);
    }

    public void enterPassword(String password){
        INPUT_password.sendKeys(password);
    }

    public void enterPasswordConfirmation(String confirmation){
        INPUT_passwordConfirmation.sendKeys(confirmation);
    }

    public void enterEmail(String email, boolean clearFirst){
        if (clearFirst) {
            clearField(INPUT_email);
        }
        INPUT_email.sendKeys(email);
    }

    public boolean clickAndroidUpdateButton() {
        return Utils.tryClicking(getDriver(), BUTTON_update);
    }

    public boolean clickUpdateButton() {
        if (isIOS()) {
            return clickIosUpdateButton();
        } else {
            return clickAndroidUpdateButton();
        }
    }

    public boolean clickIosUpdateButton() {
        try {
//            IosProfileButtonSelector.selectButton(IosProfileButtonSelector.ButtonPosition.ABOVE_KEYBOARD_UPDATE);
            IosProfileButtonSelector.selectButton(IosProfileButtonSelector.ButtonPosition.NORMAL_UPDATE);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean clickAndroidReturn() {
        return Utils.tryClicking(BUTTON_return);
    }

    public boolean clickReturnButton() {
        if (isIOS()) {
            return clickIosReturnButton();
        } else {
            return clickAndroidReturn();
        }
    }

    public boolean clickIosReturnButton() {
        try {
//            IosProfileButtonSelector.selectButton(IosProfileButtonSelector.ButtonPosition.ABOVE_KEYBOARD_RETURN);
            IosProfileButtonSelector.selectButton(IosProfileButtonSelector.ButtonPosition.NORMAL_RETURN);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    public void clearField(WebElement element) {
//        WebDriver facade = getDriver();
//        WebDriver driver = ((WebDriverFacade) facade).getProxiedDriver();
//        TouchAction touchAction = new TouchAction((MobileDriver) driver);
//        LongPressOptions longPressOptions = new LongPressOptions();
//        if (isAndroid()) {
            element.click();
            element.clear();
//        } else {
//            while (element.getText().length()>0) {
//                try {
//                    touchAction.longPress(longPressOptions().withElement(element(element)));
//                    touchAction.perform();
//                    element.click();
//                    element.sendKeys(Keys.BACK_SPACE);
//                    HashMap swipeObject = new HashMap();
//                    swipeObject.put("keycode", 67);
//                    ((JavascriptExecutor) getDriver()).executeScript("mobile: keyevent", swipeObject);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        }
    }

}