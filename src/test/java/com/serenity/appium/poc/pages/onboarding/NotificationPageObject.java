package com.serenity.appium.poc.pages.onboarding;


import com.serenity.appium.poc.pages.MobilePageObject;
import com.serenity.appium.poc.utils.Utils;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSFindBy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class NotificationPageObject extends MobilePageObject {

//    @AndroidFindBy(xpath="//android.widget.TextView[@text='ENABLE NOTIFICATIONS']")
//    @iOSFindBy(xpath="//XCUIElementTypeButton[@name='STAY CONNECTED']")
    @AndroidFindBy(accessibility = "button-confirm-STAY CONNECTED")
    @iOSFindBy(accessibility = "button-confirm-STAY CONNECTED")
    private WebElement BUTTON_enableNotifications;

//    @AndroidFindBy(xpath="//android.widget.TextView[@text='not right now']")
//    @iOSFindBy(accessibility = "link-decline")
    @AndroidFindBy(accessibility = "link-decline-STAY CONNECTED")
    @iOSFindBy(accessibility = "link-decline-STAY CONNECTED")
    private WebElement LINK_notRightNow;

   public NotificationPageObject(WebDriver driver) {
        super(driver);
    }

    public boolean approveReceivingNotifications(){
        return Utils.tryClicking(BUTTON_enableNotifications);
    }

    public boolean declineReceivingNotifications(){
        return Utils.tryClicking(LINK_notRightNow);
    }

}
