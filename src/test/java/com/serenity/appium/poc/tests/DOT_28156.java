package com.serenity.appium.poc.tests;

import com.serenity.appium.poc.WineAppTest;
import com.serenity.appium.poc.utils.Regression1;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class DOT_28156 extends WineAppTest{

    @Test
    @Category({Regression1.class})
    public void DOT_28156_verifyRedeemAndRemoveCertificate(){
        appSteps.completeQuickOnboarding();
        String emailId = "jphtest@yopmail.com";
        appSteps.performLoginFromHomepage(emailId);

        appSteps.gotoShopptingCartAndEmptyIt();
        appSteps.gotoHomeTab();
        appSteps.verifyHomepageToProductSearch();
        appSteps.searchForProduct("Paola Lanzavecchia Alba Essentia");
        appSteps.verifySelectProductFromSearchResults(1);

        appSteps.gotoCARTScreen();
        appSteps.verifyProductPricesOnCartPageWithAndWithoutCertificate();
    }
}
