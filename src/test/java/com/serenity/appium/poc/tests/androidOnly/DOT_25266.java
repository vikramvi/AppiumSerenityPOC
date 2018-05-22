package com.serenity.appium.poc.tests.androidOnly;

import com.serenity.appium.poc.WineAppTest;
import com.serenity.appium.poc.utils.Regression1;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class DOT_25266 extends WineAppTest {

    @Test // verified on Android on 2/28/18
    @Category({Regression1.class})
    public void DOT_25266_verifyRepeatedSearches(){
        wineAppSteps.completeQuickOnboarding();
        wineAppSteps.verifyHomepageToProductSearch();
        wineAppSteps.searchForProduct("clown shoes");
        wineAppSteps.verifySelectProductFromSearchResults(1);
        wineAppSteps.verifyProductDetailsToSearchResults();
        wineAppSteps.verifySelectProductFromSearchResults(2);
    }
}
