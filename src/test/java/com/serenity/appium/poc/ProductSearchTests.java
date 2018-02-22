package com.serenity.appium.poc;

import com.serenity.appium.poc.pages.MobilePageObject;
import com.serenity.appium.poc.serenity.WineAppSteps;
import com.serenity.appium.poc.utils.Regression1;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;

@RunWith(SerenityRunner.class)
public class ProductSearchTests {

    @Managed(uniqueSession = false)
    public WebDriver webdriver;

    @Steps
    public WineAppSteps wineAppSteps;

    @BeforeClass
    public static void startAppium() {
//        startAppiumServer();

        //NOTE: the following can only be used if the platform is passed in as a MVN argument (e.g. clean verify test -e -DtestEnvironment=iOS -Dmaven.surefire.debug)
        String platform = System.getProperty("testEnvironment");
        switch (platform) {
            case ("Android"):
                MobilePageObject.setAndroid(true);
                MobilePageObject.setIOS(false);
                break;
            case ("iOS"):
                MobilePageObject.setIOS(true);
                MobilePageObject.setAndroid(false);
                break;
            default:
                try {
                    throw new IllegalAccessException("No match for plaform!");
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
        }
    }

    @AfterClass
    public static void stopAppium() {
//        stopAppiumServer();
    }

//    @Test
//    public void verifyWineAppSearchByValidWineNameAndSortActions(){
//        try{
//            wineAppSteps.completeQuickOnboarding();
//            String term = "oregon spirit wheat\n"; // "Billecart Salmon";
//            wineAppSteps.initiateProductSearch(term);
////            wineAppSteps.initiateProductSearch("");
////            wineAppSteps.selectProductNameFromSearchSuggestions(term);
//            wineAppSteps.selectProductFromSearchResults("Billecart Salmon Extra Brut");
////            wineAppSteps.verifySearchResultCount(1);
////            wineAppSteps.performSortAction();
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//    }
//    @Test //
//    @Category({Regression1.class})
//    public void DOT_25275_verifyProductMisspellings(){
//        try{
//            wineAppSteps.completeQuickOnboarding();
//            wineAppSteps.verifyHomepageToProductSearch();
////            wineAppSteps.searchForProduct("pig hefe");
////            wineAppSteps.searchForProduct("bud light 1/2");
////            wineAppSteps.searchForProduct("chateau");
//            String expected = "riesling";
//            wineAppSteps.searchForProduct("reisling");
//            wineAppSteps.verifyProductSearchResults(expected);
//            wineAppSteps.verifyProductSearchResultsToHomepage();
//            wineAppSteps.verifyHomepageToProductSearch();
//            wineAppSteps.searchForProduct("reiseling");
//            wineAppSteps.verifyProductSearchResults(expected);
//            wineAppSteps.verifyProductSearchResultsToHomepage();
//            wineAppSteps.verifyHomepageToProductSearch();
//            wineAppSteps.searchForProduct("resling");
//            wineAppSteps.verifyProductSearchResults(expected);
//            wineAppSteps.verifyProductSearchResultsToHomepage();
//            wineAppSteps.verifyHomepageToProductSearch();
//
//            expected = "amaretto";
//            wineAppSteps.searchForProduct("ameratto");
//            wineAppSteps.verifyProductSearchResults(expected);
//            wineAppSteps.verifyProductSearchResultsToHomepage();
//            wineAppSteps.verifyHomepageToProductSearch();
//            wineAppSteps.searchForProduct("amereto");
//            wineAppSteps.verifyProductSearchResults(expected);
//            wineAppSteps.verifyProductSearchResultsToHomepage();
//            wineAppSteps.verifyHomepageToProductSearch();
//            wineAppSteps.searchForProduct("ameretta");
//            wineAppSteps.verifyProductSearchResults(expected);
//            wineAppSteps.verifyProductSearchResultsToHomepage();
//            wineAppSteps.verifyHomepageToProductSearch();
//            wineAppSteps.searchForProduct("ameretto");
//            wineAppSteps.verifyProductSearchResults(expected);
//            wineAppSteps.verifyProductSearchResultsToHomepage();
//            wineAppSteps.verifyHomepageToProductSearch();
//            wineAppSteps.searchForProduct("amerrato");
//            wineAppSteps.verifyProductSearchResults(expected);
//            wineAppSteps.verifyProductSearchResultsToHomepage();
//            wineAppSteps.verifyHomepageToProductSearch();
//
//            expected = "hoegaarden";
//            wineAppSteps.searchForProduct("hoeegarden");
//            wineAppSteps.verifyProductSearchResults(expected);
//            wineAppSteps.verifyProductSearchResultsToHomepage();
//            wineAppSteps.verifyHomepageToProductSearch();
//            wineAppSteps.searchForProduct("hoegarten");
//            wineAppSteps.verifyProductSearchResults(expected);
//            wineAppSteps.verifyProductSearchResultsToHomepage();
//            wineAppSteps.verifyHomepageToProductSearch();
//            wineAppSteps.searchForProduct("hoegarden");
//            wineAppSteps.verifyProductSearchResults(expected);
//            wineAppSteps.verifyProductSearchResultsToHomepage();
//            wineAppSteps.verifyHomepageToProductSearch();
//            wineAppSteps.searchForProduct("hogarten");
//            wineAppSteps.verifyProductSearchResults(expected);
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//    }

    @Test //
    @Category({Regression1.class})
    public void DOT_25266_verifyRepeatedSearches(){
        wineAppSteps.completeQuickOnboarding();
        wineAppSteps.verifyHomepageToProductSearch();
        wineAppSteps.searchForProduct("vivonne");
        wineAppSteps.verifySelectProductFromSearchResults(1);
    }


    }
