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

public class SpiritsHoursPageObject extends MobilePageObject {

    @AndroidFindBy(xpath = "//*[@text='SPIRITS HOURS']")
    @iOSFindBy(xpath = "//*[@name='SPIRITS HOURS']")
    private WebElement TEXT_TABNAME_spiritsHours;

    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc=\"grid-spirits-hours\"]")
    @iOSFindBy(accessibility = "grid-spirits-hours")
    private WebElement TEXT_GRID_spiritsHours;

    public boolean isSpiritsHoursTabPresent() {
        try {
            return TEXT_TABNAME_spiritsHours.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    public boolean clickSpiritsHoursTab() {
        return Utils.tryClicking(TEXT_TABNAME_spiritsHours);
    }
    public boolean isSpiritsHoursGridPresent() {
        try {
            return TEXT_GRID_spiritsHours.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    private final String XPATH_androidSpiritsGridElement = "//android.view.ViewGroup[@content-desc='grid-spirits-hours']/android.widget.TextView";
    public String getAllSpiritsHoursData() {
        String result = "";
        if (isAndroid()) {
            result = Utils.getAllAndroidGridData(XPATH_androidSpiritsGridElement);
        } else {
            result = TEXT_GRID_spiritsHours.getText();
        }
        return result;
    }
    public String getSpiritsHours(DayOfWeek day) {
        String result = "";
        String stream = getAllSpiritsHoursData();
        result = ScheduleParser.getHoursForDay(stream, day);
        return result;
    }

    public boolean isShowingHoursForAllDays() {
        String hoursStream = getAllSpiritsHoursData();
        List<DayOfWeek> actualDays = ScheduleParser.getDayTimeDaysFromScheduleStream(hoursStream, true);
        List<DayOfWeek> expectedDays = new ArrayList<DayOfWeek>(Arrays.asList(DayOfWeek.values()));
        return actualDays.equals(expectedDays);
    }

    public SpiritsHoursPageObject(WebDriver driver) {
        super(driver);
    }
}
