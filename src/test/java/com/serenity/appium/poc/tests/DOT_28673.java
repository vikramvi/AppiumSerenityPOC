package com.serenity.appium.poc.tests;

import com.serenity.appium.poc.WineAppTest;
import com.serenity.appium.poc.utils.Regression1;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class DOT_28673 extends WineAppTest{

    @Test
    @Category({Regression1.class})
    public void DOT_28673_verifyCreditCardIsNotCreatingDuplicateEntries(){
        appSteps.completeQuickOnboarding();

        appSteps.createRandomUserFromHomepage();

        appSteps.addNewCreditCardFromHomeMoreOptionsAccount();

        appSteps.gotoHomeTab();
        appSteps.verifyHomepageToProductSearch();
        appSteps.searchForProduct("Hennessy black");
        appSteps.verifySelectProductFromSearchResults(1);

        appSteps.gotoCARTScreen();
        appSteps.gotoOrderReviewScreen();

        appSteps.completeOrderAndVerify();
        appSteps.verifyNumberOfCreditCardsDisplayedForNewSignUp();
    }
}
