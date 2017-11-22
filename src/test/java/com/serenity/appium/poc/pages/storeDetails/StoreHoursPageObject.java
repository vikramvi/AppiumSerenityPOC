package com.serenity.appium.poc.pages.storeDetails;

import com.serenity.appium.poc.pages.MobilePageObject;
import com.serenity.appium.poc.utils.ScheduleParser;
import com.serenity.appium.poc.utils.Utils;
import io.appium.java_client.MobileBy;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSFindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.DayOfWeek;

public class StoreHoursPageObject extends MobilePageObject {

    @AndroidFindBy(xpath = "//*[@text='STORE HOURS']")
    @iOSFindBy(xpath = "//*[@name='STORE HOURS']")
    private WebElement TEXT_TABNAME_storeHours;

    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc=\\\"grid-store-hours\\\"]")
    @iOSFindBy(accessibility = "grid-store-hours")
    private WebElement TEXT_GRID_storeHours;

//    private By TEXT_TABNAME_storeHours = Utils.isAndroidPlatform(driver)
//            ? MobileBy.xpath("//*[@text='STORE HOURS']")
//            : MobileBy.xpath("//*[@name='STORE HOURS']");
    public boolean isStoreHoursTabPresent() {
        try {
            //driver.findElement(TEXT_TABNAME_storeHours);
            return TEXT_TABNAME_storeHours.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    public void clickStoreHoursTab() {
        //driver.findElement(TEXT_TABNAME_storeHours).click();
        Utils.tryClicking(TEXT_TABNAME_storeHours);
    }
//    private By TEXT_GRID_storeHours =
//            Utils.isAndroidPlatform(driver)
//                    ? MobileBy.xpath("//android.view.ViewGroup[@content-desc=\"grid-store-hours\"]")
//                    : MobileBy.AccessibilityId("grid-store-hours");
    public boolean isStoreHoursGridPresent() {
        try {
            return TEXT_GRID_storeHours.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    private final String XPATH_androidStoreGridElement = "//android.view.ViewGroup[@content-desc='grid-store-hours']/android.widget.TextView";
    public String getAllStoreHoursData() {
        String result = "";
        if (isAndroid()) {
            result = Utils.getAllAndroidGridData(XPATH_androidStoreGridElement);
        } else {
            //result = driver.findElement(TEXT_GRID_storeHours).getText();
            result = TEXT_GRID_storeHours.getText();
        }
        return result;
    }
    public String getStoreHours(DayOfWeek day) {
        String result = "";
        String stream = getAllStoreHoursData();
        result = ScheduleParser.getHoursForDay(stream, day);
        return result;
    }

    public StoreHoursPageObject(WebDriver driver) {
        super(driver);
    }

    public boolean isShowingHoursForAllDays() {
        String hoursStream = getAllStoreHoursData();
        return true ;
    }
}
