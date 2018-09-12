package com.serenity.appium.poc.tests;

import com.serenity.appium.poc.WineAppTest;
import com.serenity.appium.poc.pages.storeDetails.TastingHoursPageObject;
import com.serenity.appium.poc.utils.FindStore;
import com.serenity.appium.poc.utils.Regression1;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DOT_25248 extends WineAppTest {

    @Test // verified on iOS, Android on 5/22/18
    @Category({Regression1.class, FindStore.class})
    public void DOT_25248_verifyAllTastingHourTypes(){
        try{
            appSteps.completeQuickOnboarding();
            appSteps.verifyChangeStoreLookupOption();
            appSteps.verifySelectStoreFromSearchResults("MD", "Laurel (Corridor)");
            appSteps.verifyShowTastingHours();
            List<DayOfWeek> tastingDays = new ArrayList<DayOfWeek>(Arrays.asList(DayOfWeek.MONDAY, DayOfWeek.TUESDAY, DayOfWeek.WEDNESDAY, DayOfWeek.THURSDAY, DayOfWeek.FRIDAY, DayOfWeek.SATURDAY, DayOfWeek.SUNDAY));
            appSteps.verifyTastingHours(TastingHoursPageObject.TastingType.WINE, tastingDays, false);
            appSteps.verifyTastingHours(TastingHoursPageObject.TastingType.SPIRITS, tastingDays, true);
            appSteps.verifyStoreDetailsToHomepage();
            appSteps.verifySelectStoreFromSearchResults("Chesterfield", "Chesterfield");
            appSteps.verifyShowTastingHours();
            tastingDays.remove(DayOfWeek.MONDAY);
            tastingDays.remove(DayOfWeek.TUESDAY);
            tastingDays.remove(DayOfWeek.WEDNESDAY);
            tastingDays.remove(DayOfWeek.THURSDAY);
            appSteps.verifyTastingHours(TastingHoursPageObject.TastingType.BEER, tastingDays, false);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
