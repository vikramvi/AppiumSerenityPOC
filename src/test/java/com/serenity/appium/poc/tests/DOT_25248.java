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
            wineAppSteps.completeQuickOnboarding();
            wineAppSteps.verifyChangeStoreLookupOption();
            wineAppSteps.verifySelectStoreFromSearchResults("MD", "Laurel (Corridor)");
            wineAppSteps.verifyShowTastingHours();
            List<DayOfWeek> tastingDays = new ArrayList<DayOfWeek>(Arrays.asList(DayOfWeek.THURSDAY, DayOfWeek.FRIDAY, DayOfWeek.SATURDAY, DayOfWeek.SUNDAY));
            wineAppSteps.verifyTastingHours(TastingHoursPageObject.TastingType.WINE, tastingDays);
            wineAppSteps.verifyTastingHours(TastingHoursPageObject.TastingType.SPIRITS, tastingDays);
            wineAppSteps.verifyStoreDetailsToHomepage();
            wineAppSteps.verifySelectStoreFromSearchResults("Chesterfield", "Chesterfield");
            wineAppSteps.verifyShowTastingHours();
            tastingDays.remove(DayOfWeek.THURSDAY);
            wineAppSteps.verifyTastingHours(TastingHoursPageObject.TastingType.BEER, tastingDays);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
