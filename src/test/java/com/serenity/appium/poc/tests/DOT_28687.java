package com.serenity.appium.poc.tests;

import com.serenity.appium.poc.WineAppTest;
import com.serenity.appium.poc.utils.Regression1;
import com.serenity.appium.poc.utils.Utils;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class DOT_28687 extends WineAppTest {

    @Test
    @Category({Regression1.class})
    public void DOT_28685_verifyPDPItemDetailsTable() {
        appSteps.completeQuickOnboarding();

        appSteps.verifyChangeStoreLookupOption();
        appSteps.verifySelectStoreFromSearchResults("Mountain View", "Mountain View");
        appSteps.performShopThisStoreAction();

        appSteps.verifyHomepageToProductSearch();
        appSteps.searchForProduct("Redbridge Gluten Free Beer");
        appSteps.verifySelectProductFromSearchResults(1);

        appSteps.verifyProductBrandCountryState("Redbridge Gluten Free Beer", "United States / Missouri");
        appSteps.clickPDPMagnifyingGlassIconToGoToSearchPage();

        appSteps.searchForProduct("Penn's Best");
        appSteps.verifySelectProductFromSearchResults(1);

        appSteps.verifyProductBrandCountryState("Penn's Best", "United States / New York");
    }
}