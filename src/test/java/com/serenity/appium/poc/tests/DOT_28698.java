package com.serenity.appium.poc.tests;

import com.serenity.appium.poc.WineAppTest;
import com.serenity.appium.poc.utils.Regression1;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class DOT_28698 extends WineAppTest{

    @Test
    @Category({Regression1.class})
    public void DOT_28698_verifyCertificateUsageOnCheckoutScreen(){
        appSteps.completeQuickOnboarding();
        String emailId = "jphtest7@yopmail.com";
        appSteps.performLoginFromHomepage(emailId);

        appSteps.gotoShopptingCartAndEmptyIt();
        appSteps.gotoHomeTab();

        appSteps.verifyChangeStoreLookupOption();
        appSteps.verifySelectStoreFromSearchResults("Sacramento", "Sacramento (Arden)");
        appSteps.performShopThisStoreAction();

        appSteps.verifyHomepageToProductSearch();
        appSteps.searchForProduct("108148750-1");
        appSteps.verifySelectProductFromSearchResults(1);

        appSteps.gotoCARTScreen();
        appSteps.verifyMyRewardGetsAppliedSuccessfully();
    }
}
