package com.serenity.appium.poc.tests;

import com.serenity.appium.poc.WineAppTest;
import com.serenity.appium.poc.utils.FindStore;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class DOT_25231 extends WineAppTest {

    @Test // verified on iOS, Android on 5/22/18
    @Category({FindStore.class})
    public void DOT_25231_verifyOnDeckGrowlerSelection() {
        try {
            wineAppSteps.completeQuickOnboarding();
            wineAppSteps.verifyChangeStoreLookupOption();
            wineAppSteps.verifySelectStoreFromSearchResults("Vancouver", "Vancouver");
            wineAppSteps.verifySelectGrowlerStationPageOnDeckSection();
            wineAppSteps.verifyLastGrowlerStationCardBreweryNameLabel();
            wineAppSteps.verifyLastGrowlerStationCardBeerNameLabel();
            wineAppSteps.verifyLastGrowlerStationCardLabels();
            wineAppSteps.verifyLastGrowlerStationCardValues();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
