package com.serenity.appium.poc.pages.storeDetails;

import com.serenity.appium.poc.pages.MobilePageObject;
import com.serenity.appium.poc.utils.ScheduleParser;
import com.serenity.appium.poc.utils.Utils;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSFindBy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.time.DayOfWeek;

public class SpiritsHoursPageObject extends MobilePageObject {

    @AndroidFindBy(xpath = "//*[@text='SPIRITS HOURS']")
    @iOSFindBy(xpath = "//*[@name='SPIRITS HOURS']")
    private WebElement TEXT_TABNAME_spiritsHours;

    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc=\"grid-spirits-hours\"]")
    @iOSFindBy(accessibility = "grid-spirits-hours")
    private WebElement TEXT_GRID_spiritsHours;

//    private By TEXT_TABNAME_spiritsHours = Utils.isAndroidPlatform(driver)
//            ? MobileBy.xpath("//*[@text='SPIRITS HOURS']")
//            : MobileBy.xpath("//*[@name='SPIRITS HOURS']");
    public boolean isSpiritsHoursTabPresent() {
        try {
            //driver.findElement(TEXT_TABNAME_spiritsHours);
            return TEXT_TABNAME_spiritsHours.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    public void clickSpiritsHoursTab() {
        //driver.findElement(TEXT_TABNAME_spiritsHours).click();
        Utils.tryClicking(TEXT_TABNAME_spiritsHours);
    }
//    private By TEXT_GRID_spiritsHours =
//            Utils.isAndroidPlatform(driver)
//                    ? MobileBy.xpath("//android.view.ViewGroup[@content-desc=\"grid-spirits-hours\"]")
//                    : MobileBy.AccessibilityId("grid-spirits-hours");
    public boolean isSpiritsHoursGridPresent() {
        try {
            //driver.findElement(TEXT_GRID_spiritsHours);
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

    public SpiritsHoursPageObject(WebDriver driver) {
        super(driver);
    }
}
