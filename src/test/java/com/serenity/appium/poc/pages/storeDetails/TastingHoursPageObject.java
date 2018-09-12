package com.serenity.appium.poc.pages.storeDetails;

import com.serenity.appium.poc.pages.MobilePageObject;
import com.serenity.appium.poc.utils.ScheduleParser;
import com.serenity.appium.poc.utils.Utils;
import io.appium.java_client.MobileBy;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSFindBy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;

import static com.serenity.appium.poc.utils.Utils.getAllAndroidGridData;

public class TastingHoursPageObject extends StoreDetailsCommonPageObject {

    @AndroidFindBy(xpath = "//*[@text='SAMPLING HOURS']")
    @iOSFindBy(xpath = "//*[@name='SAMPLING HOURS']")
    private WebElement TEXT_TABNAME_tastingHours;

    @AndroidFindBy(accessibility = "grid-tasting-hours")
    @iOSFindBy(accessibility = "grid-tasting-hours")
    private WebElement TEXT_GRID_tastingHours;

//    @iOSFindBy(accessibility = "button-floating-return")
//    @AndroidFindBy(accessibility = "button-floating-return")
//    private WebElement BUTTON_return;

    public TastingHoursPageObject(WebDriver driver) {
        super(driver);
    }

//    public boolean clickReturnButton() {
//        try {
//            BUTTON_return.click();
//            return true;
//        } catch (Exception e) {
//            return false;
//        }
//    }

    public boolean isTastingHoursTabPresent() {
        try {
                if(Utils.isVisible(getDriver(), TEXT_TABNAME_tastingHours, 5)) {
                    return TEXT_TABNAME_tastingHours.isDisplayed();
                }
                return false;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean clickTastingHoursTab() {
        return Utils.tryClicking(TEXT_TABNAME_tastingHours);
    }

    public boolean isTastingHoursGridPresent() {
        try {
            return TEXT_GRID_tastingHours.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public enum TastingType {WINE, BEER, SPIRITS};

    private final String XPATH_androidTastingGridElement = "//android.view.ViewGroup[@content-desc='grid-tasting-hours']/android.widget.TextView";
    private String XPATH_androidTastingGridElementPattern = XPATH_androidTastingGridElement + "[%d]";

    public String getTastingHours(TastingType type, DayOfWeek day) {
        String result = "NOT FOUND!";
        boolean typeEnded = false;
        if (isAndroid()) {
            int totalGridItems = getDriver().findElements(MobileBy.xpath(XPATH_androidTastingGridElement)).size();
            int typeStartIndex = 0;
            int typeEndIndex = 0;
            for (int i = 1; i <= (totalGridItems - 2); i += 3) {
                String xpath = String.format(XPATH_androidTastingGridElementPattern, i);
                String col1 = getDriver().findElement(MobileBy.xpath(xpath)).getText();
                if (col1.equalsIgnoreCase(type.toString())) {
                    typeStartIndex = i;
                } else if ((typeStartIndex > 0) && (col1.isEmpty())) {
                    typeEndIndex = i;
                }
            }
            for (int i = typeStartIndex; i <= typeEndIndex; i += 3) {
                String xpath = String.format(XPATH_androidTastingGridElementPattern, i + 1);
                String col2 = getDriver().findElement(MobileBy.xpath(xpath)).getText();
                if (col2.equalsIgnoreCase(day.toString())) {
                    xpath = String.format(XPATH_androidTastingGridElementPattern, i + 2);
                    result = getDriver().findElement(MobileBy.xpath(xpath)).getText();
                    break;
                }
            }
        } else {
            String stream = TEXT_GRID_tastingHours.getText();
            String parsedType = null;
            String parsedDay = null;
            while (!stream.isEmpty()) {
                if (ScheduleParser.isTypeDayTimeFound(stream)) {
                    parsedType = ScheduleParser.getTypeFromTypeDayTime(stream);
                    parsedDay = ScheduleParser.getDayFromTypeDayTime(stream);
                    if (parsedType.equalsIgnoreCase(type.toString())) {
                        if (parsedDay.equalsIgnoreCase(day.toString())) {
                            result = ScheduleParser.getHoursFromTypeDayTime(stream);
                            break;
                        }
                        stream = ScheduleParser.stripTypeDayTime(stream);
                        boolean dayTime = ScheduleParser.isDayTimeFound(stream);
                        while (dayTime && !stream.isEmpty()) {
                            parsedDay = ScheduleParser.getDayFromDayTime(stream);
                            if (parsedDay.equalsIgnoreCase(day.toString())) {
                                result = ScheduleParser.getHoursFromDayTime(stream);
                                break;
                            }
                            stream = ScheduleParser.stripDayTime(stream);
                            if (!stream.isEmpty()) {
                                dayTime = ScheduleParser.isDayTimeFound(stream);
                            }
                        }
                    } else {
                        stream = ScheduleParser.stripTypeDayTime(stream);
                    }
                } else if (ScheduleParser.isDayTimeFound(stream)) {
                    stream = ScheduleParser.stripDayTime(stream);
                }
            }

        }
        return result;
    }

    public List<DayOfWeek> getTastingHoursDays(TastingType type) {
        List<DayOfWeek> days = new ArrayList<DayOfWeek>();
        boolean typeEnded = false;
        if (isAndroid()) {
            int totalGridItems = getDriver().findElements(MobileBy.xpath(XPATH_androidTastingGridElement)).size();
            int typeStartIndex = 0;
            int typeEndIndex = 0;
            for (int i = 1; i <= (totalGridItems - 2); i += 3) {
                String xpath = String.format(XPATH_androidTastingGridElementPattern, i);
                String col1 = getDriver().findElement(MobileBy.xpath(xpath)).getText();
                if (!col1.isEmpty()) {
                    if (col1.equalsIgnoreCase(type.toString())) {
                        typeStartIndex = i;
                        typeEndIndex = i;
                    } else if (typeStartIndex > 0) {
                        break;
                    }
                } else if (typeStartIndex > 0) {
                    typeEndIndex = i;
                }
            }
            for (int i = typeStartIndex; i <= typeEndIndex; i += 3) {
                String xpath = String.format(XPATH_androidTastingGridElementPattern, i + 1);
                String col2 = getDriver().findElement(MobileBy.xpath(xpath)).getText();
                try {
                    days.add(DayOfWeek.valueOf(col2.toUpperCase()));
                } catch (Exception e) {
                    throw new IllegalStateException("Not a valid value for day --> " + col2 + "!");
                }
            }
        } else {
            String stream = TEXT_GRID_tastingHours.getText();
            String parsedType = null;
            String parsedDay = null;
            while (!stream.isEmpty()) {
                if (ScheduleParser.isTypeDayTimeFound(stream)) {
                    parsedType = ScheduleParser.getTypeFromTypeDayTime(stream);
                    parsedDay = ScheduleParser.getDayFromTypeDayTime(stream);
                    if (parsedType.equalsIgnoreCase(type.toString())) {
                        days.add(DayOfWeek.valueOf(parsedDay.toUpperCase()));
                        stream = ScheduleParser.stripTypeDayTime(stream);
                        boolean dayTime = ScheduleParser.isDayTimeFound(stream);
                        while (dayTime && !stream.isEmpty()) {
                            parsedDay = ScheduleParser.getDayFromDayTime(stream);
                            days.add(DayOfWeek.valueOf(parsedDay.toUpperCase()));
                            stream = ScheduleParser.stripDayTime(stream);
                            if (!stream.isEmpty()) {
                                dayTime = ScheduleParser.isDayTimeFound(stream);
                            }
                        }
                    } else {
                        stream = ScheduleParser.stripTypeDayTime(stream);
                    }
                } else if (ScheduleParser.isDayTimeFound(stream)) {
                    stream = ScheduleParser.stripDayTime(stream);
                }
            }
        }
        return days;
    }

    public String getAllTastingHoursData() {
        String result = "";
        if (isAndroid()) {
            result = getAllAndroidGridData(XPATH_androidTastingGridElement);
        } else {
            result = TEXT_GRID_tastingHours.getText();
        }
        return result;
    }

    public boolean isShowingHoursForSelectDays(TastingType type, List<DayOfWeek> expectedDays) {
//        String hoursStream = getAllTastingHoursData();
//        List<DayOfWeek> actualDays = ScheduleParser.getDayTimeDaysFromScheduleStream(hoursStream);
        List<DayOfWeek> actualDays = getTastingHoursDays(type);
        return actualDays.equals(expectedDays);
    }
}
