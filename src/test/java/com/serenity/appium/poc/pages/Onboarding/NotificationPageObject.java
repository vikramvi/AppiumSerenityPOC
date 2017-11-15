package com.serenity.appium.poc.pages.Onboarding;


import com.serenity.appium.poc.pages.MobilePageObject;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSFindBy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class NotificationPageObject extends MobilePageObject {

    @AndroidFindBy(xpath="//android.widget.TextView[@text='not right now']")
//    @AndroidFindBy(accessibility = "link-decline")
//    @iOSFindBy(xpath="//XCUIElementTypeButton[@name='link-decline']")
    @iOSFindBy(accessibility = "link-decline")
    private WebElement LINK_notRightNow;

    @AndroidFindBy(xpath="//android.widget.TextView[@text='ENABLE NOTIFICATIONS']")
    @iOSFindBy(xpath="//XCUIElementTypeButton[@name='STAY CONNECTED']")
    private WebElement BUTTON_enableNotifications;

    public NotificationPageObject(WebDriver driver) {
        super(driver);
    }

    public boolean declineReceivingNotifications(){
        try{
            LINK_notRightNow.click();
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean approveReceivingNotifications(){
        try{
            BUTTON_enableNotifications.click();
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

}
