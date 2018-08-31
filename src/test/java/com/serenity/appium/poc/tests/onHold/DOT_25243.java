package com.serenity.appium.poc.tests.onHold;

import com.serenity.appium.poc.WineAppTest;
import com.serenity.appium.poc.utils.StoreDetails;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class DOT_25243 extends WineAppTest {

    @Test // verified on iOS, Android on 5/22/18
    @Category({StoreDetails.class})
    public void DOT_25243_verifyNewStoreDirections() {
        try {
            appSteps.completeQuickOnboarding();
            appSteps.verifyHomepageToStoreDetails();
            appSteps.verifyDirectionsLoad();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}