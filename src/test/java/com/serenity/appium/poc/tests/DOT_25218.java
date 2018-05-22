package com.serenity.appium.poc.tests;

import com.serenity.appium.poc.WineAppTest;
import com.serenity.appium.poc.utils.FindStore;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class DOT_25218 extends WineAppTest {

    @Test // verified on iOS, Android on 5/22/18
    @Category({FindStore.class})
    public void DOT_25218_verifyGrowlerStoreAbsence() {
        try {
            wineAppSteps.completeQuickOnboarding();
            wineAppSteps.verifyChangeStoreLookupOption();
            wineAppSteps.verifySelectStoreFromSearchResults("33435", "Boca Raton");
            wineAppSteps.verifyGrowlerStation(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
