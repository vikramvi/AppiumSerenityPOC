package com.serenity.appium.poc.tests;

import com.serenity.appium.poc.WineAppTest;
import com.serenity.appium.poc.utils.Regression1;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class DOT_28162 extends WineAppTest{

    @Test
    @Category({Regression1.class})
    public void DOT_28162_verifyRedeemCertificateInCartOutsideOfandMoreArea() {
        appSteps.completeQuickOnboarding();
        String emailId = "jphtest7@yopmail.com";
        appSteps.performLoginFromHomepage(emailId);

        appSteps.gotoShopptingCartAndEmptyIt();
        appSteps.gotoHomeTab();

        appSteps.verifyChangeStoreLookupOption();
        appSteps.verifySelectStoreFromSearchResults("Fremont", "Fremont");
        appSteps.performShopThisStoreAction();

        appSteps.verifyHomepageToProductSearch();
        appSteps.searchForProduct("Nemiroff Honey Pepper Vodka");
        appSteps.verifySelectProductFromSearchResults(1);

        appSteps.gotoCARTScreen();
        appSteps.verifyRewardTextOnCartScreen(true);

        appSteps.changeStoreFromCartPage("NY", "River Edge");
        appSteps.performShopThisStoreAction();
        appSteps.verifyRewardTextOnCartScreen(false);
    }
}
