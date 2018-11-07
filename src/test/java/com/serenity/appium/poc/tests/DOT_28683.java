package com.serenity.appium.poc.tests;

import com.serenity.appium.poc.WineAppTest;
import com.serenity.appium.poc.utils.Regression1;
import com.serenity.appium.poc.utils.Utils;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class DOT_28683 extends WineAppTest{

    @Test
    @Category({Regression1.class})
    public void DOT_28683_verifyNoThirdPartyPickupForISPUserInWisconsin() {
        appSteps.completeQuickOnboarding();
        appSteps.performLoginFromHomepage(Utils.getRandomLoginIdWithCertificate());

        appSteps.gotoShopptingCartAndEmptyIt();
        appSteps.gotoHomeTab();

        appSteps.verifyChangeStoreLookupOption();
        appSteps.verifySelectStoreFromSearchResults("Madison", "Madison");
        appSteps.performShopThisStoreAction();

        appSteps.verifyHomepageToProductSearch();
        appSteps.searchForProduct("29901-3");
        appSteps.verifySelectProductFromSearchResults(1);
        appSteps.gotoCARTScreen();
        appSteps.gotoOrderReviewScreen();
        appSteps.verifyThirdPartyPickupOptionIsNotDisplayed();
    }

}
