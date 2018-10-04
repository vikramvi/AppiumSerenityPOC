package com.serenity.appium.poc.pages.account;

import com.serenity.appium.poc.pages.MobilePageObject;
import com.serenity.appium.poc.utils.Utils;
import io.appium.java_client.*;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSFindBy;
import net.thucydides.core.webdriver.WebDriverFacade;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Arrays;
import java.util.List;

import static io.appium.java_client.touch.offset.PointOption.point;

public class ProfilePageObject extends MobilePageObject {

    @AndroidFindBy(xpath = "//android.widget.TextView[@content-desc='header-title' and @text='PROFILE']")
    private WebElement TEXT_pageTitle;
    private static final String expectedTitle = "PROFILE";

    @AndroidFindBy(accessibility = "android-input-First Name")
    @iOSFindBy(accessibility = "ios-input-First-Name")
    private WebElement INPUT_firstName;

    @AndroidFindBy(accessibility = "android-input-Last Name")
    @iOSFindBy(accessibility = "ios-input-Last-Name")
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

    public boolean isPageTitleCorrect() {
        if(Utils.isVisible(getDriver(), TEXT_pageTitle, 15)) {
            return Utils.isPageTitleCorrectAfterPolling(TEXT_pageTitle, expectedTitle);
        }
        return false;
    }

    public boolean isToastMessageDisplayed(){
        boolean isToastMessageSeen = false;

        for(int count=0; count < 40; count++){

            String tempXML = getDriver().getPageSource();

            if( tempXML.contains("SUCCESS") && tempXML.contains("User updated successfully") ) {
                LOGGER.info("Toast message displayed 1: " + "SUCCESS" + "  User updated successfully");
                return true;
            }

            Utils.waitFor(50);
        }

        LOGGER.error("Toast message did NOT display");
        return isToastMessageSeen;
    }

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
            return Utils.clickIosUpdateButton();
        } else {
            return clickAndroidUpdateButton();
        }
    }

//    public boolean clickIosUpdateButton() {
//        try {
////            IosProfileButtonSelector.selectButton(IosProfileButtonSelector.ButtonPosition.ABOVE_KEYBOARD_UPDATE);
//            IosProfileButtonSelector.selectButton(IosProfileButtonSelector.ButtonPosition.NORMAL_UPDATE);
//            return true;
//        } catch (Exception e) {
//            e.printStackTrace();
//            return false;
//        }
//    }

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

//    public boolean clickIosReturnButton() {
//        try {
////            IosProfileButtonSelector.selectButton(IosProfileButtonSelector.ButtonPosition.ABOVE_KEYBOARD_RETURN);
//            IosProfileButtonSelector.selectButton(IosProfileButtonSelector.ButtonPosition.NORMAL_RETURN);
//            return true;
//        } catch (Exception e) {
//            e.printStackTrace();
//            return false;
//        }
//    }
    public void clearField(WebElement element) {
//        WebDriver facade = getDriver();
//        WebDriver driver = ((WebDriverFacade) facade).getProxiedDriver();
//        TouchAction touchAction = new TouchAction((MobileDriver) driver);
//        LongPressOptions longPressOptions = new LongPressOptions();
        if (isAndroid()) {
            element.click();
            element.clear();
        } else {
            WebDriver facade = getDriver();
            WebDriver driver = ((WebDriverFacade) facade).getProxiedDriver();
            TouchAction touchAction = new TouchAction((IOSDriver) driver);
            int x = element.getLocation().x + element.getSize().width - 10;
            int y = element.getLocation().y + element.getSize().height -10;
            touchAction.tap(point(x, y)).perform();
            element.clear();
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
        }
    }

}
