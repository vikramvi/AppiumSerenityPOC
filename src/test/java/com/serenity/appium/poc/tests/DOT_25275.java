package com.serenity.appium.poc.tests;

import com.serenity.appium.poc.WineAppTest;
import com.serenity.appium.poc.utils.Regression1;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class DOT_25275 extends WineAppTest {

    @Test // verified on iOS, Android on 5/22/18
    @Category({Regression1.class})
    public void DOT_25275_verifyProductMisspellings(){
        try{
            wineAppSteps.completeOnboardingAllowingLocation();
            wineAppSteps.verifyHomepageToProductSearch();
            String expected = "riesling";
            wineAppSteps.searchForProduct("reisling");
            wineAppSteps.verifyNameInProductSearchResults(expected);
            wineAppSteps.verifyProductSearchResultsToHomepage();
            wineAppSteps.verifyHomepageToProductSearch();
            wineAppSteps.searchForProduct("reiseling");
            wineAppSteps.verifyNameInProductSearchResults(expected);
            wineAppSteps.verifyProductSearchResultsToHomepage();
            wineAppSteps.verifyHomepageToProductSearch();
            wineAppSteps.searchForProduct("resling");
            wineAppSteps.verifyNameInProductSearchResults(expected);
            wineAppSteps.verifyProductSearchResultsToHomepage();
            wineAppSteps.verifyHomepageToProductSearch();

            expected = "amaretto";
            wineAppSteps.searchForProduct("ameratto");
            wineAppSteps.verifyNameInProductSearchResults(expected);
            wineAppSteps.verifyProductSearchResultsToHomepage();
            wineAppSteps.verifyHomepageToProductSearch();
            wineAppSteps.searchForProduct("amereto");
            wineAppSteps.verifyNameInProductSearchResults(expected);
            wineAppSteps.verifyProductSearchResultsToHomepage();
            wineAppSteps.verifyHomepageToProductSearch();
            wineAppSteps.searchForProduct("ameretta");
            wineAppSteps.verifyNameInProductSearchResults(expected);
            wineAppSteps.verifyProductSearchResultsToHomepage();
            wineAppSteps.verifyHomepageToProductSearch();
            wineAppSteps.searchForProduct("ameretto");
            wineAppSteps.verifyNameInProductSearchResults(expected);
            wineAppSteps.verifyProductSearchResultsToHomepage();
            wineAppSteps.verifyHomepageToProductSearch();
            wineAppSteps.searchForProduct("amerrato");
            wineAppSteps.verifyNameInProductSearchResults(expected);
            wineAppSteps.verifyProductSearchResultsToHomepage();
            wineAppSteps.verifyHomepageToProductSearch();

            expected = "hoegaarden";
            wineAppSteps.searchForProduct("hoeegarden");
            wineAppSteps.verifyNameInProductSearchResults(expected);
            wineAppSteps.verifyProductSearchResultsToHomepage();
            wineAppSteps.verifyHomepageToProductSearch();
            wineAppSteps.searchForProduct("hoegarten");
            wineAppSteps.verifyNameInProductSearchResults(expected);
            wineAppSteps.verifyProductSearchResultsToHomepage();
            wineAppSteps.verifyHomepageToProductSearch();
            wineAppSteps.searchForProduct("hoegarden");
            wineAppSteps.verifyNameInProductSearchResults(expected);
            wineAppSteps.verifyProductSearchResultsToHomepage();
            wineAppSteps.verifyHomepageToProductSearch();
            wineAppSteps.searchForProduct("hogarten");
            wineAppSteps.verifyNameInProductSearchResults(expected);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
