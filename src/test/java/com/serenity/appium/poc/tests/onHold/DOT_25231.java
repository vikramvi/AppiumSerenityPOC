package com.serenity.appium.poc.tests.onHold;

import com.serenity.appium.poc.WineAppTest;
import com.serenity.appium.poc.utils.FindStore;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class DOT_25231 extends WineAppTest {

    @Test // verified on iOS, Android on 5/22/18
    @Category({FindStore.class})
    public void DOT_25231_verifyOnDeckGrowlerSelection() {
        try {
            appSteps.completeQuickOnboarding();
            appSteps.verifyChangeStoreLookupOption();
            appSteps.verifySelectStoreFromSearchResults("Vancouver", "Vancouver");
            appSteps.verifySelectGrowlerStationPageOnDeckSection();
            appSteps.verifyLastGrowlerStationCardBreweryNameLabel();
            appSteps.verifyLastGrowlerStationCardBeerNameLabel();
            appSteps.verifyLastGrowlerStationCardLabels();
            appSteps.verifyLastGrowlerStationCardValues();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
