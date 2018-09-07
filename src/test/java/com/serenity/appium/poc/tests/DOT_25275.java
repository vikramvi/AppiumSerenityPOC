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
            appSteps.completeQuickOnboarding();
            appSteps.verifyHomepageToProductSearch();
            String expected = "riesling";
            appSteps.searchForProduct("reisling");
            appSteps.verifyNameInProductSearchResults(expected);
            appSteps.verifyProductSearchResultsToHomepage();
            appSteps.verifyHomepageToProductSearch();
            appSteps.searchForProduct("reiseling");
            appSteps.verifyNameInProductSearchResults(expected);
            appSteps.verifyProductSearchResultsToHomepage();
            appSteps.verifyHomepageToProductSearch();
            appSteps.searchForProduct("resling");
            appSteps.verifyNameInProductSearchResults(expected);
            appSteps.verifyProductSearchResultsToHomepage();
            appSteps.verifyHomepageToProductSearch();

            expected = "amaretto";
            appSteps.searchForProduct("ameratto");
            appSteps.verifyNameInProductSearchResults(expected);
            appSteps.verifyProductSearchResultsToHomepage();
            appSteps.verifyHomepageToProductSearch();
            appSteps.searchForProduct("amereto");
            appSteps.verifyNameInProductSearchResults(expected);
            appSteps.verifyProductSearchResultsToHomepage();
            appSteps.verifyHomepageToProductSearch();
            appSteps.searchForProduct("ameretta");
            appSteps.verifyNameInProductSearchResults(expected);
            appSteps.verifyProductSearchResultsToHomepage();
            appSteps.verifyHomepageToProductSearch();
            appSteps.searchForProduct("ameretto");
            appSteps.verifyNameInProductSearchResults(expected);
            appSteps.verifyProductSearchResultsToHomepage();
            appSteps.verifyHomepageToProductSearch();
            appSteps.searchForProduct("amerrato");
            appSteps.verifyNameInProductSearchResults(expected);
            appSteps.verifyProductSearchResultsToHomepage();
            appSteps.verifyHomepageToProductSearch();

            expected = "hoegaarden";
            appSteps.searchForProduct("hoeegarden");
            appSteps.verifyNameInProductSearchResults(expected);
            appSteps.verifyProductSearchResultsToHomepage();
            appSteps.verifyHomepageToProductSearch();
            appSteps.searchForProduct("hoegarten");
            appSteps.verifyNameInProductSearchResults(expected);
            appSteps.verifyProductSearchResultsToHomepage();
            appSteps.verifyHomepageToProductSearch();
            appSteps.searchForProduct("hoegarden");
            appSteps.verifyNameInProductSearchResults(expected);
            appSteps.verifyProductSearchResultsToHomepage();
            appSteps.verifyHomepageToProductSearch();
            appSteps.searchForProduct("hogarten");
            appSteps.verifyNameInProductSearchResults(expected);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
