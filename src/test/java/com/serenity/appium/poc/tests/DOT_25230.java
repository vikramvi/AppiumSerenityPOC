package com.serenity.appium.poc.tests;

import com.serenity.appium.poc.WineAppTest;
import com.serenity.appium.poc.utils.FindStore;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class DOT_25230 extends WineAppTest {

    @Test // verified on iOS, Android on 5/22/18
    @Category({FindStore.class})
    public void DOT_25230_verifyCurrentGrowlerSelection() {
        try {
            wineAppSteps.completeQuickOnboarding();
            wineAppSteps.verifyChangeStoreLookupOption();
            wineAppSteps.verifySelectStoreFromSearchResults("Coral Springs", "Coral Springs");
            wineAppSteps.verifySelectGrowlerStationPage();
            wineAppSteps.verifyInitialGrowlerStationCardBreweryNameLabels();
            wineAppSteps.verifyInitialGrowlerStationCardBeerNameLabels();
            wineAppSteps.verifyInitialGrowlerStationCardLabels();
            wineAppSteps.verifyInitialGrowlerStationCardValues();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
