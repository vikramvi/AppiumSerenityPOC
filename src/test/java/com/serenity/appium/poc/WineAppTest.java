package com.serenity.appium.poc;

import com.serenity.appium.poc.pages.MobilePageObject;
import com.serenity.appium.poc.pages.storeDetails.TastingHoursPageObject;
import com.serenity.appium.poc.utils.FindStore;
import com.serenity.appium.poc.utils.Properties;
import com.serenity.appium.poc.utils.Regression1;
import com.serenity.appium.poc.utils.StoreDetails;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Managed;

import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.junit.BeforeClass;
import org.junit.AfterClass;

import org.openqa.selenium.WebDriver;

import com.serenity.appium.poc.serenity.WineAppSteps;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RunWith(SerenityRunner.class)
public class WineAppTest {

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
//    @Test // verified on iOS on 2/12/18
//    @Category({Regression1.class, FindStore.class})
//    public void DOT_25134_verifyClosestStoreOnHomepage(){
//        Properties properties = new Properties();
//        String expectedTitle = "BOYNTON BEACH";
//        String expectedLocation = "Boynton Town Center";
//        if (properties.isSauceLabsRun()) {
//            expectedTitle = "FREMONT";
//            expectedLocation = "Pacific Commons";
//        }
//        try{
//            wineAppSteps.completeOnboardingAllowingLocation();
//            wineAppSteps.verifyHomepageStoreDetails(expectedTitle, expectedLocation);
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//    }
//    @Test // verified on iOS on 2/12/18
//    @Category({FindStore.class})
//    public void DOT_25137_verifyNewStoreIcons() {
//        try {
//            wineAppSteps.completeQuickOnboarding();
//            wineAppSteps.verifyChangeStoreLookupOption();
//            wineAppSteps.verifySelectStoreFromSearchResults("33435", "Boca Raton");
//            wineAppSteps.verifyStoreDetailsIcons();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//    @Test // verified on iOS on 2/12/18
//    public void DOT_25139_verifyNewStoreHours() {
//        try {
//            wineAppSteps.completeQuickOnboarding();
//            wineAppSteps.verifyChangeStoreLookupOption();
//            wineAppSteps.verifySelectStoreFromSearchResults("33435", "Boca Raton");
//            wineAppSteps.verifyStoreDetailsStoreHours();
//            wineAppSteps.verifyShowTastingHours();
//            List<DayOfWeek> tastingDays = new ArrayList<DayOfWeek>(Arrays.asList(DayOfWeek.THURSDAY, DayOfWeek.FRIDAY, DayOfWeek.SATURDAY, DayOfWeek.SUNDAY));
//            wineAppSteps.verifyTastingHours(TastingHoursPageObject.TastingType.WINE, tastingDays);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//    @Test // verified on iOS on 2/12/18
//    @Category({FindStore.class})
//    public void DOT_25216_verifyGrowlerStorePresence() {
//        try {
//            wineAppSteps.completeQuickOnboarding();
//            wineAppSteps.verifyChangeStoreLookupOption();
//            wineAppSteps.verifySelectStoreFromSearchResults("Coral Springs", "Coral Springs");
//            wineAppSteps.verifyGrowlerStation(true);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//    @Test // verified on iOS on 2/12/18
//    @Category({FindStore.class})
//    public void DOT_25218_verifyGrowlerStoreAbsence() {
//        try {
//            wineAppSteps.completeQuickOnboarding();
//            wineAppSteps.verifyHomepageToStoreDetails();
//            wineAppSteps.verifyGrowlerStation(false);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//    @Test // verified on iOS on 2/12/18
//    @Category({FindStore.class})
//    public void DOT_25230_verifyCurrentGrowlerSelection() {
//        try {
//            wineAppSteps.completeQuickOnboarding();
//            wineAppSteps.verifyChangeStoreLookupOption();
//            wineAppSteps.verifySelectStoreFromSearchResults("Coral Springs", "Coral Springs");
//            wineAppSteps.verifySelectGrowlerStationPage();
//            wineAppSteps.verifyInitialGrowlerStationCardBreweryNameLabels();
//            wineAppSteps.verifyInitialGrowlerStationCardBeerNameLabels();
//            wineAppSteps.verifyInitialGrowlerStationCardLabels();
//            wineAppSteps.verifyInitialGrowlerStationCardValues();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//    @Test // verified on iOS on 2/12/18
//    @Category({FindStore.class})
//    public void DOT_25231_verifyOnDeckGrowlerSelection() {
//        try {
//            wineAppSteps.completeQuickOnboarding();
//            wineAppSteps.verifyChangeStoreLookupOption();
//            wineAppSteps.verifySelectStoreFromSearchResults("Coral Springs", "Coral Springs");
//            wineAppSteps.verifySelectGrowlerStationPageOnDeckSection();
//            wineAppSteps.verifyLastGrowlerStationCardBreweryNameLabel();
//            wineAppSteps.verifyLastGrowlerStationCardBeerNameLabel();
//            wineAppSteps.verifyLastGrowlerStationCardLabels();
//            wineAppSteps.verifyLastGrowlerStationCardValues();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//    @Test // verified on iOS on 2/12/18
//    @Category({Regression1.class, FindStore.class})
//    public void DOT_25238_verifyClosestStoreByIpAddress(){
//        try{
//            wineAppSteps.completeQuickOnboarding();
//            wineAppSteps.verifyHomepageStoreDetails("Boca Raton", "Shops at Boca Center");
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//    }
//    @Test // verified on iOS on 2/12/18
//    @Category({Regression1.class, FindStore.class})
//    public void DOT_25239_verifyOptionToChangeStores(){
//        try{
//            wineAppSteps.completeQuickOnboarding();
//            wineAppSteps.verifyChangeStoreLookupOption();
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//    }
//    @Test // verified on iOS on 2/12/18
//    @Category({StoreDetails.class})
//    public void DOT_25243_verifyNewStoreMap() {
//        try {
//            wineAppSteps.completeQuickOnboarding();
//            wineAppSteps.verifyHomepageToStoreDetails();
//            wineAppSteps.verifyDirectionsLoad();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//    @Test // verified on iOS on 2/12/18
//    @Category({FindStore.class})
//    public void DOT_25245_verifyStoreAddress() {
//        String title = "Laurel (Corridor)";
//        String address1 = "Laurel Corridor";
//        String address2 = "3335 Corridor Marketplace";
//        String cityStateZip = "Laurel, MD 20724";
//        String phoneNumber = "(301) 617-8507";
//        String hours = "8:00 AM - 11:00 PM";
//        String openUntil = "Open Until 11 PM";
//        try {
//            wineAppSteps.completeQuickOnboarding();
//            wineAppSteps.verifyChangeStoreLookupOption();
//            wineAppSteps.verifyStoreDataInNewSearchResults(
//                    "MD", title, address1, address2, cityStateZip, phoneNumber, hours);
//            wineAppSteps.verifySelectStoreFromSearchResults(title);
//            wineAppSteps.verifyStoreDetailsHeaderData(
//                    title, address1, address2, cityStateZip, openUntil);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//    @Test // verified on iOS on 2/12/18
//    @Category({FindStore.class})
//    public void DOT_25246_verifyNewStoreMap() {
//        try {
//            wineAppSteps.completeQuickOnboarding();
//            wineAppSteps.verifyChangeStoreLookupOption();
//            wineAppSteps.verifySelectStoreFromSearchResults("Coral Springs", "Coral Springs");
//            wineAppSteps.verifyStoreDetailsMapLoad();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//    @Test // verified on iOS on 2/12/18
//    @Category({StoreDetails.class})
//    public void DOT_25247_verifyStoreHours(){
//        try{
//            wineAppSteps.completeQuickOnboarding();
//            wineAppSteps.verifyHomepageToStoreDetails();
//            wineAppSteps.verifyStoreDetailsStoreHours();
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//    }
//    @Test // verified on iOS on 2/12/18
//    @Category({Regression1.class, FindStore.class})
//    public void DOT_25248_verifyAllTastingHourTypes(){
//        try{
//            wineAppSteps.completeQuickOnboarding();
//            wineAppSteps.verifyChangeStoreLookupOption();
//            wineAppSteps.verifySelectStoreFromSearchResults("MD", "Laurel (Corridor)");
////            wineAppSteps.verifySelectStoreFromSearchResults("MD", "Alexandria");
//            wineAppSteps.verifyShowTastingHours();
//            List<DayOfWeek> tastingDays = new ArrayList<DayOfWeek>(Arrays.asList(DayOfWeek.THURSDAY, DayOfWeek.FRIDAY, DayOfWeek.SATURDAY, DayOfWeek.SUNDAY));
//            wineAppSteps.verifyTastingHours(TastingHoursPageObject.TastingType.WINE, tastingDays);
//            wineAppSteps.verifyTastingHours(TastingHoursPageObject.TastingType.SPIRITS, tastingDays);
//            wineAppSteps.verifyStoreDetailsToHomepage();
//            wineAppSteps.verifySelectStoreFromSearchResults("Chesterfield", "Chesterfield");
//            wineAppSteps.verifyShowTastingHours();
//            tastingDays.remove(DayOfWeek.THURSDAY);
//            wineAppSteps.verifyTastingHours(TastingHoursPageObject.TastingType.BEER, tastingDays);
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//    }
//    @Test // verified on iOS on 2/12/18
//    @Category({Regression1.class, FindStore.class})
//    public void DOT_25249_verifySpiritsHours(){
//        try{
//            wineAppSteps.completeQuickOnboarding();
//            wineAppSteps.verifyChangeStoreLookupOption();
//            wineAppSteps.verifySelectStoreFromSearchResults("Greenville", "Greenville");
//            wineAppSteps.verifyShowSpiritsHours();
//            wineAppSteps.verifySpiritsHours();
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

}
