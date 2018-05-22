package com.serenity.appium.poc.tests;

import com.serenity.appium.poc.WineAppTest;
import com.serenity.appium.poc.utils.FindStore;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class DOT_25216 extends WineAppTest {

    @Test // verified on iOS, Android on 5/22/18
    @Category({FindStore.class})
    public void DOT_25216_verifyGrowlerStorePresence() {
        try {
            wineAppSteps.completeQuickOnboarding();
            wineAppSteps.verifyChangeStoreLookupOption();
            wineAppSteps.verifySelectStoreFromSearchResults("Coral Springs", "Coral Springs");
            wineAppSteps.verifyGrowlerStation(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
