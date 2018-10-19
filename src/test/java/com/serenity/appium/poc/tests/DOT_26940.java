package com.serenity.appium.poc.tests;

import com.serenity.appium.poc.WineAppTest;
import com.serenity.appium.poc.utils.Regression1;
import com.serenity.appium.poc.utils.Utils;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class DOT_26940 extends WineAppTest{

    @Test
    @Category({Regression1.class})
    public void DOT_26940_verifyToastMessagesForMinimumThresholdForDeliveryAndToChooseTimeWindowForDelivery() {
        appSteps.completeQuickOnboarding();

//        String emailId = "jphtest@yopmail.com";
        appSteps.performLoginFromHomepage(Utils.getRandomLoginId());
        appSteps.gotoShopptingCartAndEmptyIt();

        appSteps.gotoHomeTab();
        appSteps.verifyChangeStoreLookupOption();
        appSteps.verifySelectStoreFromSearchResults("Mountain View", "Mountain View");
        appSteps.performShopThisStoreAction();

        String token = "Sweet Red Wine";
        appSteps.verifyHomepageToProductSearch();
        appSteps.searchForProduct(token);
        appSteps.verifySelectProductFromSearchResults(1);
        appSteps.gotoCARTScreen();

        //TEMP FIX MOB-2243
        appSteps.gotoHomeTab();
        appSteps.gotoCartTab();

        appSteps.verifyToastMessagesOnCartScreen();
    }
}
