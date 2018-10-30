package com.serenity.appium.poc.tests;

import com.serenity.appium.poc.WineAppTest;
import com.serenity.appium.poc.utils.Regression1;
import com.serenity.appium.poc.utils.Utils;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class DOT_28899 extends WineAppTest {

    @Test
    @Category({Regression1.class})
    public void DOT_28899_verifyNoTabBarOnCheckoutPages() {
        appSteps.completeQuickOnboarding();
        appSteps.performLoginFromHomepage(Utils.getRandomLoginIdWithCertificate());

        appSteps.gotoShopptingCartAndEmptyIt();

        appSteps.gotoHomeTab();

        appSteps.verifyChangeStoreLookupOption();
        appSteps.verifySelectStoreFromSearchResults("Boynton Beach", "Boynton Beach");
        appSteps.performShopThisStoreAction();

        appSteps.verifyHomepageToProductSearch();
        appSteps.searchForProduct("94613750-1");
        appSteps.verifySelectProductFromSearchResults(1);
        appSteps.gotoCARTScreen();

        appSteps.gotoOrderReviewScreen();
        appSteps.verifyNavigationFooterInOrderingFlow();
    }
}