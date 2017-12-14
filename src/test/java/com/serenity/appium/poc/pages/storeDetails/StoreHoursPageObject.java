package com.serenity.appium.poc.pages.storeDetails;

import com.serenity.appium.poc.pages.MobilePageObject;
import com.serenity.appium.poc.utils.ScheduleParser;
import com.serenity.appium.poc.utils.Utils;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSFindBy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StoreHoursPageObject extends MobilePageObject {

    @AndroidFindBy(xpath = "//*[@text='STORE HOURS']")
    @iOSFindBy(xpath = "//*[@name='STORE HOURS']")
    private WebElement TEXT_TABNAME_storeHours;

    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc=\\\"grid-store-hours\\\"]")
    @iOSFindBy(accessibility = "grid-store-hours")
    private WebElement TEXT_GRID_storeHours;

    public boolean isStoreHoursTabPresent() {
        try {
            return TEXT_TABNAME_storeHours.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    public boolean clickStoreHoursTab() {
        return Utils.tryClicking(TEXT_TABNAME_storeHours);
    }
    public boolean isStoreHoursGridPresent() {
        try {
            return TEXT_GRID_storeHours.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    private final String XPATH_androidStoreHoursGridElement = "//android.view.ViewGroup[@content-desc='grid-store-hours']/android.widget.TextView";
    public String getAllStoreHoursData() {
        String result = "";
        if (isAndroid()) {
            result = Utils.getAllAndroidGridData(XPATH_androidStoreHoursGridElement);
        } else {
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
        List<DayOfWeek> actualDays = ScheduleParser.getDayTimeDaysFromScheduleStream(hoursStream);
        List<DayOfWeek> expectedDays = new ArrayList<DayOfWeek>(Arrays.asList(DayOfWeek.values()));
        return actualDays.equals(expectedDays);
    }
}
