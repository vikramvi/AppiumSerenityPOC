package com.serenity.appium.poc.tests;

import com.serenity.appium.poc.WineAppTest;
import com.serenity.appium.poc.pages.storeDetails.TastingHoursPageObject;
import org.junit.Test;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DOT_25139 extends WineAppTest {

    @Test // verified on iOS, Android on 5/22/18
    public void DOT_25139_verifyNewStoreHours() {
        try {
//            wineAppSteps.completeQuickOnboarding();
            wineAppSteps.completeOnboardingAllowingLocation();
            wineAppSteps.verifyChangeStoreLookupOption();
            wineAppSteps.verifySelectStoreFromSearchResults("33435", "Boca Raton");
            wineAppSteps.verifyStoreDetailsStoreHours();
            wineAppSteps.verifyShowTastingHours();
            List<DayOfWeek> tastingDays = new ArrayList<>(Arrays.asList(DayOfWeek.THURSDAY, DayOfWeek.FRIDAY, DayOfWeek.SATURDAY, DayOfWeek.SUNDAY));
            wineAppSteps.verifyTastingHours(TastingHoursPageObject.TastingType.WINE, tastingDays);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
