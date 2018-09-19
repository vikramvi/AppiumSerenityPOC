package com.serenity.appium.poc.tests;

import com.serenity.appium.poc.WineAppTest;
import com.serenity.appium.poc.utils.Regression1;
import org.junit.Test;
import org.junit.experimental.categories.Category;


public class DOT_26741 extends WineAppTest {

    @Test
    @Category({Regression1.class})
    public void DOT_26741_verifySwitchToDeliveryOptionForItemWithOptionOnlyTobePickedFromShop(){
        appSteps.completeQuickOnboarding();

        String emailId = "jphtest7@yopmail.com";
        appSteps.performLoginFromHomepage(emailId);

        appSteps.gotoHomeTab();
        appSteps.verifyChangeStoreLookupOption();
        appSteps.verifySelectStoreFromSearchResults("Fremont", "Fremont");
        appSteps.verifyShopThisStore();

        String token = "Glen Single Malt Cigar";
        appSteps.verifyHomepageToProductSearch();
        appSteps.searchForProduct(token);
        appSteps.verifySelectProductFromSearchResults(1);
        appSteps.gotoCARTScreen();

        appSteps.verifyItemNotEligibleForHomeDelivery_RemovalFromCart_AlongWithOtherItemsInCart();
    }
}
