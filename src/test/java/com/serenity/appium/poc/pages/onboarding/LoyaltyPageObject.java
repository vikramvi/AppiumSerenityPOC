package com.serenity.appium.poc.pages.onboarding;


import com.serenity.appium.poc.pages.MobilePageObject;
import com.serenity.appium.poc.utils.Utils;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSFindBy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoyaltyPageObject extends MobilePageObject {

    @AndroidFindBy(xpath="//android.widget.TextView[@text='no thanks']")
    @iOSFindBy(xpath="//XCUIElementTypeOther[@name='no thanks']")
    private WebElement LINK_noThanks;

    public LoyaltyPageObject(WebDriver driver) {
        super(driver);
    }

    public boolean declineLoyaltyLogin(){
        boolean result = false;
        if (Utils.isVisible(getDriver(), LINK_noThanks, 5)) {
            result = Utils.tryClicking(LINK_noThanks);
        } else {
            throw new IllegalStateException("Can't see 'no thanks' link for Loyalty!");
        }
        return result;
    }
}
