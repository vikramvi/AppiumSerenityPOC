package com.serenity.appium.poc.tests;

import com.serenity.appium.poc.WineAppTest;
import com.serenity.appium.poc.utils.Regression1;
import com.serenity.appium.poc.utils.Utils;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class DOT_25170 extends WineAppTest {

    @Test
    @Category({Regression1.class})
    public void DOT_25170_verifySearchStrategyTextOnSearchAndProductDetailPage() {
        appSteps.completeQuickOnboarding();
        appSteps.performLoginFromHomepage(Utils.getRandomLoginIdWithCertificate());

        appSteps.verifyChangeStoreLookupOption();
        appSteps.verifySelectStoreFromSearchResults("Sacramento", "Sacramento (Arden)");
        appSteps.performShopThisStoreAction();

        appSteps.verifyHomepageToProductSearch();
        appSteps.searchForProduct("Black Powder");

        String expectedSellStrategy = "Spirits Direct";
        appSteps.verifySellStrategyOnSearchResultsPage(expectedSellStrategy);
        appSteps.verifySelectProductFromSearchResults(1);
        appSteps.verifySellStrategyOnProductDetailsPage(expectedSellStrategy);
    }

}
