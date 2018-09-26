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
            List<DayOfWeek> spiritsTastingDays = new ArrayList<DayOfWeek>(Arrays.asList(DayOfWeek.MONDAY, DayOfWeek.TUESDAY, DayOfWeek.WEDNESDAY, DayOfWeek.THURSDAY, DayOfWeek.FRIDAY, DayOfWeek.SATURDAY, DayOfWeek.SUNDAY));
            List<DayOfWeek> wineTastingDays = new ArrayList<DayOfWeek>(Arrays.asList(DayOfWeek.THURSDAY, DayOfWeek.FRIDAY, DayOfWeek.SATURDAY));
            List<DayOfWeek> beerTastingDays = new ArrayList<DayOfWeek>(Arrays.asList(DayOfWeek.FRIDAY, DayOfWeek.SATURDAY, DayOfWeek.SUNDAY));

            appSteps.completeQuickOnboarding();
            appSteps.verifyChangeStoreLookupOption();
            appSteps.verifySelectStoreFromSearchResults("Claymont", "Claymont");
            appSteps.verifyShowTastingHours();
            appSteps.verifyTastingHours(TastingHoursPageObject.TastingType.SPIRITS, spiritsTastingDays, true);

            appSteps.verifyStoreDetailsToHomepage();
            appSteps.verifySelectStoreFromSearchResults("Chesterfield", "Chesterfield");
            appSteps.verifyShowTastingHours();
            appSteps.verifyTastingHours(TastingHoursPageObject.TastingType.WINE, wineTastingDays, true);
            appSteps.verifyTastingHours(TastingHoursPageObject.TastingType.BEER, beerTastingDays, false);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
