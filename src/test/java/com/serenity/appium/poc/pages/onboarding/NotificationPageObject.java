package com.serenity.appium.poc.pages.onboarding;


import com.serenity.appium.poc.pages.MobilePageObject;
import com.serenity.appium.poc.utils.Utils;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSFindBy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class NotificationPageObject extends MobilePageObject {

    @AndroidFindBy(accessibility = "button-confirm-STAY CONNECTED")
    @iOSFindBy(accessibility = "button-confirm-STAY CONNECTED")
    private WebElement BUTTON_enableNotifications;

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
        boolean result = false;
        if (Utils.isVisible(getDriver(), LINK_notRightNow, 2)) {
            System.out.println(">>> clicking Not Right Now on Notifications...");
            result = Utils.tryClicking(LINK_notRightNow);
        } else {
            System.out.println(">>> Not Right Now link not visible!");
        }
        return result;
    }

}
