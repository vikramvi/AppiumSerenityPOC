package com.serenity.appium.poc.tests.androidOnly;

import com.serenity.appium.poc.WineAppTest;
import com.serenity.appium.poc.utils.Regression1;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class DOT_25266 extends WineAppTest {

    @Test // verified on Android on 2/28/18
    @Category({Regression1.class})
    public void DOT_25266_verifyRepeatedSearches(){
        appSteps.completeQuickOnboarding();
        appSteps.verifyHomepageToProductSearch();
        appSteps.searchForProduct("clown shoes");
        appSteps.verifySelectProductFromSearchResults(1);
        appSteps.verifyProductDetailsToSearchResults();
        appSteps.verifySelectProductFromSearchResults(2);
    }
}
