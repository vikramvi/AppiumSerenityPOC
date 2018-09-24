package com.serenity.appium.poc.tests.onHold;

import com.serenity.appium.poc.WineAppTest;
import com.serenity.appium.poc.utils.FindStore;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class DOT_25246 extends WineAppTest {

    @Test // verified on iOS, Android on 5/22/18
    @Category({FindStore.class})
    public void DOT_25246_verifyNewStoreMap() {
        try {
            appSteps.completeQuickOnboarding();
            appSteps.verifyHomepageToStoreDetails();
            appSteps.verifyStoreDetailsMapLoad();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
