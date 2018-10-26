package com.serenity.appium.poc.tests;

import com.serenity.appium.poc.WineAppTest;
import com.serenity.appium.poc.utils.Regression1;
import com.serenity.appium.poc.utils.Utils;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class DOT_28674 extends WineAppTest {

    @Test
    @Category({Regression1.class})
    public void DOT_28674_verifyCigarsinCartAfterSwitchToDeliveryFromISP(){
        appSteps.completeQuickOnboarding();
        appSteps.performLoginFromHomepage(Utils.getRandomLoginIdWithCertificate());

        appSteps.gotoShopptingCartAndEmptyIt();
        appSteps.gotoHomeTab();

        appSteps.verifyChangeStoreLookupOption();
        appSteps.verifySelectStoreFromSearchResults("Pleasant Hill, CA", "Pleasant Hill");
        appSteps.performShopThisStoreAction();

        appSteps.verifyHomepageToProductSearch();
        appSteps.searchForProduct("146683233");
        appSteps.verifySelectProductFromSearchResults(1);
        appSteps.gotoCARTScreen();

        appSteps.VerifyChangeToDelivery_And_ChooseCancelOption_ItemsNotAvailalbeDialog();
        appSteps.VerifyChooseProceedOption_ItemsNotAvailalbeDialog();
    }
}
