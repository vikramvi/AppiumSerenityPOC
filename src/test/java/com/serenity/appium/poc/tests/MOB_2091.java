package com.serenity.appium.poc.tests;

import com.serenity.appium.poc.WineAppTest;
import com.serenity.appium.poc.utils.Regression1;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class MOB_2091 extends WineAppTest{

    @Test
    @Category({Regression1.class})
    public void MOB_2091_verifyCouponUsageOnCheckoutScreen(){
        appSteps.completeQuickOnboarding();
        String emailId = "jphtest7@yopmail.com";
        appSteps.performLoginFromHomepage(emailId);

        appSteps.gotoHomeTab();
        appSteps.verifyHomepageToProductSearch();
        appSteps.searchForProduct("Hoegaarden");
        appSteps.verifySelectProductFromSearchResults(1);

        appSteps.gotoCARTScreen();
        appSteps.verifyMyRewardGetsAppliedSuccessfully();
    }
}
