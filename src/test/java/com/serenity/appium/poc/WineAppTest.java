package com.serenity.appium.poc;

import com.serenity.appium.poc.serenity.AppSteps;
import com.serenity.appium.poc.utils.*;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Managed;

import org.junit.runner.RunWith;
import org.junit.BeforeClass;
import org.junit.AfterClass;

import org.openqa.selenium.WebDriver;

import static com.serenity.appium.poc.AppiumServerController.startAppiumServer;
import static com.serenity.appium.poc.AppiumServerController.stopAppiumServer;

@RunWith(SerenityRunner.class)
public class WineAppTest {

    @Managed(uniqueSession = false)
    public WebDriver webdriver;

    @Steps
    public AppSteps appSteps;

    @BeforeClass
    public static void startAppium() {
        startAppiumServer();

        //NOTE: the following can only be used if the platform is passed in as a MVN argument (e.g. clean verify test -e -DtestEnvironment=iOS -Dmaven.surefire.debug)
        Utils.setPlatform();
    }

    @AfterClass
    public static void stopAppium() {

        stopAppiumServer();
    }

//    @Test
//    public void verifyWineAppSearchByValidWineNameAndSortActions(){
//        try{
//            appSteps.completeQuickOnboarding();
//            String term = "oregon spirit wheat\n"; // "Billecart Salmon";
//            appSteps.initiateProductSearch(term);
////            appSteps.initiateProductSearch("");
////            appSteps.selectProductNameFromSearchSuggestions(term);
//            appSteps.selectProductFromSearchResults("Billecart Salmon Extra Brut");
////            appSteps.verifySearchResultCount(1);
////            appSteps.performSortAction();
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//    }
//-------------------------------------------------------------------------
//    @Test
//    public void verifyWineAppSearchByValidWineNameAndSortActions(){
//        try{
//            appSteps.completeQuickOnboarding();
//            appSteps.tempDriver();
//            String x = webdriver.getPageSource();
//            if (!x.isEmpty()) {
//                String y = "not empty";
//            }
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//    }
//-------------------------------------------------------------------------
//    @Test // verified on iOS, Android on 5/22/18
//    @Category({Regression1.class, FindStore.class})
//    public void DOT_25134_verifyClosestStoreOnHomepage(){
//        Properties properties = new Properties();
//        String expectedTitle = "BOYNTON BEACH";
//        if (properties.isSauceLabsRun()) {
//            expectedTitle = "FREMONT";
//        }
//        try{
//            appSteps.completeOnboardingAllowingLocation();
//            appSteps.verifyHomepageStoreDetails(expectedTitle);
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//    }
//    @Test // verified on iOS, Android on 5/22/18
//    @Category({FindStore.class})
//    public void DOT_25137_verifyNewStoreIcons() {
//        try {
//            appSteps.completeQuickOnboarding();
//            appSteps.verifyHomepageToStoreDetails();
//            appSteps.verifyStoreDetailsIcons();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//    @Test // verified on iOS, Android on 5/22/18
//    public void DOT_25139_verifyNewStoreHours() {
//        try {
////            appSteps.completeQuickOnboarding();
//            appSteps.completeOnboardingAllowingLocation();
//            appSteps.verifyChangeStoreLookupOption();
//            appSteps.verifySelectStoreFromSearchResults("33435", "Boca Raton");
//            appSteps.verifyStoreDetailsStoreHours();
//            appSteps.verifyShowTastingHours();
//            List<DayOfWeek> tastingDays = new ArrayList<DayOfWeek>(Arrays.asList(DayOfWeek.THURSDAY, DayOfWeek.FRIDAY, DayOfWeek.SATURDAY, DayOfWeek.SUNDAY));
//            appSteps.verifyTastingHours(TastingHoursPageObject.TastingType.WINE, tastingDays);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//    @Test // verified on iOS, Android on 5/22/18
//    @Category({FindStore.class})
//    public void DOT_25216_verifyGrowlerStorePresence() {
//        try {
//            appSteps.completeQuickOnboarding();
//            appSteps.verifyChangeStoreLookupOption();
//            appSteps.verifySelectStoreFromSearchResults("Coral Springs", "Coral Springs");
//            appSteps.verifyGrowlerStation(true);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//    @Test // verified on iOS, Android on 5/22/18
//    @Category({FindStore.class})
//    public void DOT_25218_verifyGrowlerStoreAbsence() {
//        try {
//            appSteps.completeQuickOnboarding();
//            appSteps.verifyChangeStoreLookupOption();
//            appSteps.verifySelectStoreFromSearchResults("33435", "Boca Raton");
//            appSteps.verifyGrowlerStation(false);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//    @Test // verified on iOS, Android on 5/22/18
//    @Category({FindStore.class})
//    public void DOT_25230_verifyCurrentGrowlerSelection() {
//        try {
//            appSteps.completeQuickOnboarding();
//            appSteps.verifyChangeStoreLookupOption();
//            appSteps.verifySelectStoreFromSearchResults("Coral Springs", "Coral Springs");
//            appSteps.verifySelectGrowlerStationPage();
//            appSteps.verifyInitialGrowlerStationCardBreweryNameLabels();
//            appSteps.verifyInitialGrowlerStationCardBeerNameLabels();
//            appSteps.verifyInitialGrowlerStationCardLabels();
//            appSteps.verifyInitialGrowlerStationCardValues();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//    @Test // verified on iOS, Android on 5/22/18
//    @Category({FindStore.class})
//    public void DOT_25231_verifyOnDeckGrowlerSelection() {
//        try {
//            appSteps.completeQuickOnboarding();
//            appSteps.verifyChangeStoreLookupOption();
//            appSteps.verifySelectStoreFromSearchResults("Coral Springs", "Coral Springs");
//            appSteps.verifySelectGrowlerStationPageOnDeckSection();
//            appSteps.verifyLastGrowlerStationCardBreweryNameLabel();
//            appSteps.verifyLastGrowlerStationCardBeerNameLabel();
//            appSteps.verifyLastGrowlerStationCardLabels();
//            appSteps.verifyLastGrowlerStationCardValues();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//    @Test // verified on iOS, Android on 5/22/18
//    @Category({Regression1.class, FindStore.class})
//    public void DOT_25238_verifyClosestStoreByIpAddress(){
//        try{
//            appSteps.completeQuickOnboarding();
//            appSteps.verifyHomepageStoreDetails("North Miami");
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//    }
//    @Test // verified on iOS, Android on 5/22/18
//    @Category({Regression1.class, FindStore.class})
//    public void DOT_25239_verifyOptionToChangeStores(){
//        try{
//            appSteps.completeQuickOnboarding();
//            appSteps.verifyChangeStoreLookupOption();
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//    }
//    @Test // verified on iOS, Android on 5/22/18
//    @Category({StoreDetails.class})
//    public void DOT_25243_verifyNewStoreDirections() {
//        try {
//            appSteps.completeQuickOnboarding();
//            appSteps.verifyHomepageToStoreDetails();
//            appSteps.verifyDirectionsLoad();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//    @Test // verified on iOS, Android on 5/22/18
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
//            appSteps.completeQuickOnboarding();
//            appSteps.verifyChangeStoreLookupOption();
//            appSteps.verifyStoreDataInNewSearchResults(
//                    "MD", title, address1, address2, cityStateZip, phoneNumber, hours);
//            appSteps.verifySelectStoreFromSearchResults(title);
//            appSteps.verifyStoreDetailsHeaderData(
//                    title, address1, address2, cityStateZip, openUntil);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//    @Test // verified on iOS, Android on 5/22/18
//    @Category({FindStore.class})
//    public void DOT_25246_verifyNewStoreMap() {
//        try {
//            appSteps.completeQuickOnboarding();
//            appSteps.verifyHomepageToStoreDetails();
//            appSteps.verifyStoreDetailsMapLoad();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//    @Test // verified on iOS, Android on 5/22/18
//    @Category({StoreDetails.class})
//    public void DOT_25247_verifyStoreHours(){
//        try{
//            appSteps.completeQuickOnboarding();
//            appSteps.verifyHomepageToStoreDetails();
//            appSteps.verifyStoreDetailsStoreHours();
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//    }
//    @Test // verified on iOS, Android on 5/22/18
//    @Category({Regression1.class, FindStore.class})
//    public void DOT_25248_verifyAllTastingHourTypes(){
//        try{
//            appSteps.completeQuickOnboarding();
//            appSteps.verifyChangeStoreLookupOption();
//            appSteps.verifySelectStoreFromSearchResults("MD", "Laurel (Corridor)");
//            appSteps.verifyShowTastingHours();
//            List<DayOfWeek> tastingDays = new ArrayList<DayOfWeek>(Arrays.asList(DayOfWeek.THURSDAY, DayOfWeek.FRIDAY, DayOfWeek.SATURDAY, DayOfWeek.SUNDAY));
//            appSteps.verifyTastingHours(TastingHoursPageObject.TastingType.WINE, tastingDays);
//            appSteps.verifyTastingHours(TastingHoursPageObject.TastingType.SPIRITS, tastingDays);
//            appSteps.verifyStoreDetailsToHomepage();
//            appSteps.verifySelectStoreFromSearchResults("Chesterfield", "Chesterfield");
//            appSteps.verifyShowTastingHours();
//            tastingDays.remove(DayOfWeek.THURSDAY);
//            appSteps.verifyTastingHours(TastingHoursPageObject.TastingType.BEER, tastingDays);
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//    }
//    @Test // verified on iOS, Android on 5/22/18
//    @Category({Regression1.class, FindStore.class})
//    public void DOT_25249_verifySpiritsHours(){
//        try{
//            appSteps.completeQuickOnboarding();
//            appSteps.verifyChangeStoreLookupOption();
//            appSteps.verifySelectStoreFromSearchResults("Greenville", "Greenville");
//            appSteps.verifyShowSpiritsHours();
//            appSteps.verifySpiritsHours();
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//    }
//    @Test // verified on iOS, Android on 5/22/18
//    public void DOT_25256_verifyLimitedAvailability() {
//        try{
//            appSteps.completeQuickOnboarding();
//            appSteps.verifyChangeStoreLookupOption();
//            appSteps.verifySelectStoreFromSearchResults("Boynton Beach", "Boynton Beach");
//            appSteps.verifyShopThisStore();
//            appSteps.verifyHomepageToProductSearch();
//            String token = "dom perignon jeff koons brut vintage";
//            appSteps.searchForProduct(token);
//            //           appSteps.verifySearchResultCount(1);
//            appSteps.selectProductFromSearchResults(token);
//            appSteps.verifyInStoreAvailabilityInProductDetails(Enums.InStoreAvailability.LIMITED);
//        } catch (Exception e){
//            e.printStackTrace();
//        }
//    }
//    @Test // verified on iOS on 5/22/18
//    public void DOT_25259_verifyWasltLabelOnPrice() {
//        try{
//            appSteps.completeQuickOnboarding();
//            appSteps.verifyChangeStoreLookupOption();
//            appSteps.verifySelectStoreFromSearchResults("Bellevue", "Bellevue");
//            appSteps.verifyShopThisStore();
//            appSteps.verifyHomepageToProductSearch();
//            String token = "mascarade liqueur";
//            appSteps.searchForProduct(token);
//            //           appSteps.verifySearchResultCount(1);
//            appSteps.verifyFeeInProductSearchResults(Enums.Fees.WASLT);
//            appSteps.selectProductFromSearchResults(token);
//            appSteps.verifyFeeInProductDetails(Enums.Fees.WASLT);
//        } catch (Exception e){
//            e.printStackTrace();
//        }
//    }
//    @Test // verified on iOS on 5/22/18
//    public void DOT_25260_verifyCrvLabelOnPrice() {
//        try{
//            appSteps.completeQuickOnboarding();
//            appSteps.verifyChangeStoreLookupOption();
//            appSteps.verifySelectStoreFromSearchResults("Fremont", "Fremont");
//            appSteps.verifyShopThisStore();
//            appSteps.verifyHomepageToProductSearch();
//            String token = "modelo especial chelada";
//            appSteps.searchForProduct(token);
//            appSteps.verifyFeeInProductSearchResults(Enums.Fees.CRV);
//            appSteps.selectProductFromSearchResults(token);
//            appSteps.verifyFeeInProductDetails(Enums.Fees.CRV);
//        } catch (Exception e){
//            e.printStackTrace();
//        }
//    }
//    @Test // verified on iOS, Android on 5/22/18
//    public void DOT_25261_verifyDepositLabelOnPrice() {
//        try{
//            appSteps.completeQuickOnboarding();
//            appSteps.verifyHomepageToProductSearch();
//            appSteps.searchForProduct("modelo especial 1/2 keg");
//            appSteps.verifySearchResultCount(1);
//            appSteps.verifyFeeInProductSearchResults(Enums.Fees.DEPOSIT);
//            appSteps.selectProductFromSearchResults("modelo especial");
//            appSteps.verifyFeeInProductDetails(Enums.Fees.DEPOSIT);
//        } catch (Exception e){
//            e.printStackTrace();
//        }
//    }
//    @Test // verified on iOS, Android on 5/22/18
//    @Category({Regression1.class})
//    public void DOT_25275_verifyProductMisspellings(){
//        try{
//            appSteps.completeOnboardingAllowingLocation();
//            appSteps.verifyHomepageToProductSearch();
////            appSteps.searchForProduct("pig hefe");
////            appSteps.searchForProduct("budweiser 1/2 keg");
////            appSteps.searchForProduct("chateau");
//            String expected = "riesling";
//            appSteps.searchForProduct("reisling");
//            appSteps.verifyNameInProductSearchResults(expected);
//            appSteps.verifyProductSearchResultsToHomepage();
//            appSteps.verifyHomepageToProductSearch();
//            appSteps.searchForProduct("reiseling");
//            appSteps.verifyNameInProductSearchResults(expected);
//            appSteps.verifyProductSearchResultsToHomepage();
//            appSteps.verifyHomepageToProductSearch();
//            appSteps.searchForProduct("resling");
//            appSteps.verifyNameInProductSearchResults(expected);
//            appSteps.verifyProductSearchResultsToHomepage();
//            appSteps.verifyHomepageToProductSearch();
//
//            expected = "amaretto";
//            appSteps.searchForProduct("ameratto");
//            appSteps.verifyNameInProductSearchResults(expected);
//            appSteps.verifyProductSearchResultsToHomepage();
//            appSteps.verifyHomepageToProductSearch();
//            appSteps.searchForProduct("amereto");
//            appSteps.verifyNameInProductSearchResults(expected);
//            appSteps.verifyProductSearchResultsToHomepage();
//            appSteps.verifyHomepageToProductSearch();
//            appSteps.searchForProduct("ameretta");
//            appSteps.verifyNameInProductSearchResults(expected);
//            appSteps.verifyProductSearchResultsToHomepage();
//            appSteps.verifyHomepageToProductSearch();
//            appSteps.searchForProduct("ameretto");
//            appSteps.verifyNameInProductSearchResults(expected);
//            appSteps.verifyProductSearchResultsToHomepage();
//            appSteps.verifyHomepageToProductSearch();
//            appSteps.searchForProduct("amerrato");
//            appSteps.verifyNameInProductSearchResults(expected);
//            appSteps.verifyProductSearchResultsToHomepage();
//            appSteps.verifyHomepageToProductSearch();
//
//            expected = "hoegaarden";
//            appSteps.searchForProduct("hoeegarden");
//            appSteps.verifyNameInProductSearchResults(expected);
//            appSteps.verifyProductSearchResultsToHomepage();
//            appSteps.verifyHomepageToProductSearch();
//            appSteps.searchForProduct("hoegarten");
//            appSteps.verifyNameInProductSearchResults(expected);
//            appSteps.verifyProductSearchResultsToHomepage();
//            appSteps.verifyHomepageToProductSearch();
//            appSteps.searchForProduct("hoegarden");
//            appSteps.verifyNameInProductSearchResults(expected);
//            appSteps.verifyProductSearchResultsToHomepage();
//            appSteps.verifyHomepageToProductSearch();
//            appSteps.searchForProduct("hogarten");
//            appSteps.verifyNameInProductSearchResults(expected);
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//    }

}
