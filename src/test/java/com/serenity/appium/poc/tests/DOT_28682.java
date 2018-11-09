package com.serenity.appium.poc.tests;

import com.serenity.appium.poc.WineAppTest;
import com.serenity.appium.poc.utils.Regression1;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class DOT_28682 extends WineAppTest{

    @Test
    @Category({Regression1.class})
    public void DOT_28682_verifyChangingStoreInViewAllEventsSection() {
        appSteps.completeQuickOnboarding();
        appSteps.verifyUserIsOnHomeTab();

        appSteps.verifyChangeStoreLookupOption();
        appSteps.verifySelectStoreFromSearchResults("Mountain View", "Mountain View");
        appSteps.performShopThisStoreAction();

        appSteps.verifyUserIsOnHomeTab();
        appSteps.verifyChangeStoreFlowFromViewAllEventsPage();
    }

}
