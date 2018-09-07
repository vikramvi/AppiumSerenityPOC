package com.serenity.appium.poc.pages.onboarding;


import com.serenity.appium.poc.pages.MobilePageObject;
import com.serenity.appium.poc.utils.Utils;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSFindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.serenity.appium.poc.utils.Utils;


public class LocationPageObject extends MobilePageObject {

    @AndroidFindBy(accessibility = "button-confirm-FIND MY STORE")
    @iOSFindBy(accessibility = "button-confirm-FIND MY STORE")
    private WebElement BUTTON_allowLocation;

    @AndroidFindBy(accessibility = "link-decline-FIND MY STORE")
    @iOSFindBy(accessibility = "link-decline-FIND MY STORE")
    private WebElement LINK_turnOnLater;

    public LocationPageObject(WebDriver driver) {
        super(driver);
    }

    public boolean allowLocationTracking() {
        try {
              if (Utils.isVisible(getDriver(),BUTTON_allowLocation, 10 )) {
                  BUTTON_allowLocation.click();
                  if (isAndroid()) {
                      By BUTTON_androidConfirmAllowLocation = By.id("com.android.packageinstaller:id/permission_allow_button");
                      if (getDriver().findElements(BUTTON_androidConfirmAllowLocation).size() > 0) {
                          System.out.println(">>> clicking Confirm on Allow Location...");
                          getDriver().findElement(BUTTON_androidConfirmAllowLocation).click();
                      }
                  } else {
                      Utils.tryClickingAllow();
                  }
                  return true;
              } else {
                  LOGGER.error("allowLocationTracking() > BUTTON_allowLocation NOT found ");
                  return false;
              }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean declineLocationTracking() {
        if (Utils.isVisible(getDriver(), LINK_turnOnLater, 10 )) {
            return Utils.tryClicking(LINK_turnOnLater);
        }
        return false;
    }
}
