package com.serenity.appium.poc.tests;

import com.serenity.appium.poc.WineAppTest;
import com.serenity.appium.poc.utils.Regression1;
import com.serenity.appium.poc.utils.Utils;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class DOT_28164 extends WineAppTest {

    @Test
    @Category({Regression1.class})
    public void DOT_28164_verifyMoreRewardsEligibilityAndInfoUnderMyOrders(){
        appSteps.completeQuickOnboarding();
        appSteps.performLoginFromHomepage(Utils.getRandomLoginIdWithCertificate());

        appSteps.gotoShopptingCartAndEmptyIt();
        appSteps.gotoHomeTab();

        appSteps.verifyChangeStoreLookupOption();
        appSteps.verifySelectStoreFromSearchResults("Boynton Beach", "Boynton Beach");
        appSteps.performShopThisStoreAction();

        appSteps.verifyAndMoreRewardSection();

        appSteps.verifyHomepageToProductSearch();
        appSteps.searchForProduct("94613750-1");
        appSteps.verifySelectProductFromSearchResults(1);
        appSteps.gotoCARTScreen();

        appSteps.verifyRewardEligibility_and_ApplyReward();

        //WIP
        //Place Order
        //Check under My Orders
    }

}