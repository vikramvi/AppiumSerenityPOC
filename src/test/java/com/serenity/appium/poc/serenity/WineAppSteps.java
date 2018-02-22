package com.serenity.appium.poc.serenity;

import com.serenity.appium.poc.pages.*;
import com.serenity.appium.poc.pages.home.MyStoreHeaderPageObject;
import com.serenity.appium.poc.pages.home.SearchSectionPageObject;
import com.serenity.appium.poc.pages.onboarding.LocationPageObject;
import com.serenity.appium.poc.pages.onboarding.LoyaltyPageObject;
import com.serenity.appium.poc.pages.onboarding.NotificationPageObject;
import com.serenity.appium.poc.pages.onboarding.SplashPageObject;

import com.serenity.appium.poc.pages.storeDetails.*;
import com.serenity.appium.poc.utils.StoreDataParser;
import net.thucydides.core.steps.ScenarioSteps;
import net.thucydides.core.annotations.Step;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.DayOfWeek;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class WineAppSteps extends ScenarioSteps {

    private LocationPageObject locationPageObject;
    private LoyaltyPageObject loyaltyPageObject;
    private MobilePageObject mobilePageObject;
    private MyStoreHeaderPageObject myStoreHeaderPageObject;
    private NotificationPageObject notificationPageObject;
    private ProductSearchPageObject productSearchPageObject;
    private ProductSearchResultsPageObject productSearchResultsPageObject;
    private SearchSectionPageObject searchSection;
    private SpiritsHoursPageObject spiritsHoursPageObject;
    private SplashPageObject splashPageObject;
    private StoreDataHeaderPageObject storeDataHeaderPageObject;
    private StoreDetailsCommonPageObject storeDetailsCommonPageObject;
    private GrowlerSectionPageObject growlerSectionPageObject;
    private GrowlerStationPageObject growlerStationPageObject;
    private StoreHoursPageObject storeHoursPageObject;
    private StoreIconsPageObject storeIconsPageObject;
    private StoreMapPageObject storeMapPageObject;
    private StoreSearchPageObject storeSearchPageObject;
    private TastingHoursPageObject tastingHoursPageObject;
    private WineAppPageObject wineAppPageObject;

    private static final Logger LOGGER = LoggerFactory.getLogger(WineAppSteps.class);

    String wineName = "Krug Vintage";

    @Step
    public void completeOnboardingAllowingLocation() {
        LOGGER.info("Completing onboarding, declining all options but Location...");
        assertThat(splashPageObject.startOnboarding()).isTrue();
        assertThat(locationPageObject.allowLocationTracking()).isTrue();
        assertThat(notificationPageObject.declineReceivingNotifications()).isTrue();
        assertThat(loyaltyPageObject.declineLoyaltyLogin()).isTrue();
    }

    @Step
    public void completeQuickOnboarding() {
        LOGGER.info("Completing onboarding, declining all options...");
        assertThat(splashPageObject.startOnboarding()).isTrue();
        assertThat(locationPageObject.declineLocationTracking()).isTrue();
        assertThat(notificationPageObject.declineReceivingNotifications()).isTrue();
        assertThat(loyaltyPageObject.declineLoyaltyLogin()).isTrue();
    }

    @Step
    public void verifyHomepageToProductSearch() {
        LOGGER.info("Clicking search proxy on homepage to see product search page...");
        assertThat(myStoreHeaderPageObject.isChangeStoreOptionPresent()).isTrue();
        assertThat(searchSection.triggerSearchPage()).isTrue();
        assertThat(productSearchPageObject.isSearchFieldPresent()).isTrue();
    }

    @Step
    public void initiateProductSearch(String searchToken) {
        LOGGER.info("Initiating a product search...");
        assertThat(myStoreHeaderPageObject.isChangeStoreOptionPresent()).isTrue();
        assertThat(searchSection.triggerSearchPage()).isTrue();
        assertThat(productSearchPageObject.typeSearchTerm(searchToken)).isTrue();
    }

    @Step
    public void searchForProduct(String searchToken) {
        LOGGER.info("Searching for a product from the homepage...");
        assertThat(productSearchPageObject.enterSearchTerm(searchToken)).isTrue();
        assertThat(productSearchResultsPageObject.getResultsCount().length()>0).isTrue();
    }

    @Step
    public void selectProductNameFromSearchSuggestions(String suggestion) {
        LOGGER.info("Selecting search suggestion...");
        assertThat(productSearchPageObject.selectSearchSuggestion(suggestion)).isTrue();
    }

    @Step
    public void selectProductFromSearchResults(String productName) {
        LOGGER.info("Selecting product from search results...");
        assertThat(productSearchResultsPageObject.selectProductForAndroid(productName)).isTrue();
    }

    @Step
    public void performSortAction() {
        LOGGER.info("Performing sort action...");
        assertThat(wineAppPageObject.performSortActionByHighPrice()).isTrue();
    }

    @Step
    public void verifyHomepageStoreDetails(String expectedTitle, String expectedLocation) {
        LOGGER.info("Verifying homepage store details:  \nExpected title = " + expectedTitle + "\nExpected address = " + expectedLocation);
        assertThat(myStoreHeaderPageObject.getTitleFromStoreData()).isEqualToIgnoringCase(expectedTitle);
        assertThat(myStoreHeaderPageObject.getOpenInfoFromStoreData()).matches(StoreDataParser.openCloseRegex);
    }

    @Step
    public void verifyHomepageToStoreDetails() {
        LOGGER.info("Clicking touchable store data to see store details page...");
        assertThat(myStoreHeaderPageObject.clickTouchableStoreData()).isTrue();
        assertThat(storeIconsPageObject.isCallStoreIconPresent()).isTrue();
    }

    @Step
    public void verifyChangeStoreLookupOption() {
        LOGGER.info("Clicking change store and verifying that the geo search field is displayed...");
        assertThat(myStoreHeaderPageObject.isChangeStoreOptionPresent()).isTrue();
        assertThat(myStoreHeaderPageObject.clickChangeStore()).isTrue();
//        assertThat(storeSearchPageObject.isSearchFieldPresent()).isTrue();
        assertThat(storeSearchPageObject.isSearchButtonPresent()).isTrue();
    }

    @Step
    public void verifySearchResultCount(int expected) {
        LOGGER.info("Checking the number of search results...");
        assertThat(productSearchResultsPageObject.getResultsCount()).isEqualTo(Integer.toString(expected));
    }

    @Step
    public void verifyProductSearchResults(String token) {
        LOGGER.info("Verifying search results contain " +token+ "...");
        assertThat(productSearchResultsPageObject.isTokenPresentInResults(token)).isTrue();

    }

    @Step
    public void verifySelectProductFromSearchResults(int productNumber) {
        LOGGER.info("Verifying product selection from search results...");
        String name = productSearchResultsPageObject.getAndroidProductName(productNumber);
        assertThat(productSearchResultsPageObject.selectProductForAndroid(productNumber)).isTrue();
        //TODO: verify name on product details page; return; get product 2 name and repeat
    }


    @Step
    public void verifyProductSearchResultsToHomepage() {
        LOGGER.info("Returning from search results to Homepage...");
        assertThat(productSearchResultsPageObject.clickReturn()).isTrue();
        assertThat(myStoreHeaderPageObject.isChangeStoreOptionPresent()).isTrue();
    }

    @Step
    public void verifyStoreDataInNewSearchResults(String searchToken, String title, String address1, String address2,
                                                  String cityStateZip, String phoneNumber, String openCloseHour) {
        LOGGER.info("Entering search token then verifying the specified store data is in the list...");
        assertThat(storeSearchPageObject.enterSearchToken(searchToken)).isTrue();
        assertThat(storeSearchPageObject.verifyStoreInList(title, address1, address2, cityStateZip,
                        phoneNumber, openCloseHour)).isTrue();
    }

    @Step
    public void verifySelectStoreFromSearchResults(String storeName) {
        LOGGER.info("Selecting the specified store in the search results list...");
        assertThat(storeSearchPageObject.selectStore(storeName)).isTrue();
    }

    @Step
    public void verifySelectStoreFromSearchResults(String searchToken, String storeName) {
        LOGGER.info("Entering search term then selecting the specified store in the search results list...");
        assertThat(storeSearchPageObject.enterSearchToken(searchToken)).isTrue();
        assertThat(storeSearchPageObject.selectStore(storeName)).isTrue();
    }

    @Step
    public void verifyStoreDetailsIcons() {
        LOGGER.info("Verifying that the top 3 icons are present on the store details page...");
        assertThat(storeIconsPageObject.isCallStoreIconPresent()).isTrue();
        assertThat(storeIconsPageObject.isGetDirectionsIconPresent()).isTrue();
        assertThat(storeIconsPageObject.isMyStoreIconPresent()).isTrue();
    }

    @Step
    public void verifyStoreDetailsHeaderData(String title, String address1, String address2,
                                             String cityStateZip, String openCloseHour) {
        LOGGER.info("Verifying the store address and open until/at data...");
        assertThat(storeDataHeaderPageObject.getTitle()).isEqualToIgnoringCase(title);
        assertThat(storeDataHeaderPageObject.getAddress1()).isEqualToIgnoringCase(address1);
        assertThat(storeDataHeaderPageObject.getAddress2()).isEqualToIgnoringCase(address2);
        assertThat(storeDataHeaderPageObject.getCityStateZip()).isEqualToIgnoringCase(cityStateZip);
        assertThat(storeDataHeaderPageObject.getOpenCloseHour()).isEqualToIgnoringCase(openCloseHour);
    }

    @Step
    public void verifyStoreDetailsMapLoad() {
        LOGGER.info("Verifying that the map is present on the store details page and loads...");
        assertThat(storeMapPageObject.isStoreMapThumbnailPresent()).isTrue();
        assertThat(storeMapPageObject.clickStoreMapThumbnail()).isTrue();
        assertThat(storeMapPageObject.isActualStoreMapLoaded()).isTrue();
    }

    @Step
    public void verifyStoreDetailsStoreHours() {
        LOGGER.info("Verifying that the store hours contains all days of the week...");
        assertThat(storeHoursPageObject.isStoreHoursTabPresent()).isTrue();
        assertThat(storeHoursPageObject.isShowingHoursForAllDays()).isTrue();
    }

    @Step
    public void verifyShowSpiritsHours() {
        LOGGER.info("Clicking tab to show spirits hours...");
        assertThat(spiritsHoursPageObject.isSpiritsHoursTabPresent()).isTrue();
        assertThat(spiritsHoursPageObject.clickSpiritsHoursTab()).isTrue();
    }

    @Step
    public void verifySpiritsHours() {
        LOGGER.info("Verifying the format of all spirits hours...");
        assertThat(spiritsHoursPageObject.isShowingHoursForAllDays()).isTrue();
    }

    @Step
    public void verifyShowTastingHours() {
        LOGGER.info("Clicking tab to show sampling hours...");
        assertThat(tastingHoursPageObject.isTastingHoursTabPresent()).isTrue();
        assertThat(tastingHoursPageObject.clickTastingHoursTab()).isTrue();
    }

    @Step
    public void verifyTastingHours(TastingHoursPageObject.TastingType tastingType, List<DayOfWeek> tastingDays) {
        LOGGER.info("Verifying that sampling hours for " +tastingType.name()+ " are only on specific days...");
        assertThat(tastingHoursPageObject.isShowingHoursForSelectDays(tastingType, tastingDays)).isTrue();
    }

    @Step
    public void verifyReturn() {
        LOGGER.info("Returning back one screen...");
        tastingHoursPageObject.clickReturnButton();
    }

    @Step
    public void verifyStoreDetailsToHomepage() {
        LOGGER.info("Returning back one screen...");
        storeDetailsCommonPageObject.clickReturnButton();
        assertThat(storeSearchPageObject.isSearchButtonPresent()).isTrue();
    }

    @Step
    public void verifyDirectionsLoad() {
        LOGGER.info("Verifying that the map is present on the store details page...");
        assertThat(storeMapPageObject.isStoreMapThumbnailPresent()).isTrue();
        assertThat(storeIconsPageObject.clickGetDirections()).isTrue();
        assertThat(storeMapPageObject.isActualStoreMapLoaded()).isTrue();
    }

    //----- G R O W L E R --------------------------------------------------------------------->

    @Step
    public void verifyGrowlerStation(boolean expected) {
        LOGGER.info("Verifying presence of Growler station in store details = " + expected + "...");
        if (expected) {
            assertThat(growlerSectionPageObject.isGrowlerStationSectionDisplayed()).isTrue();
            assertThat(growlerSectionPageObject.doesGrowlerCopyMatch()).isTrue();
        } else {
            assertThat(growlerSectionPageObject.isGrowlerStationSectionDisplayed()).isFalse();
        }

    }

    @Step
    public void verifySelectGrowlerStationPage() {
        LOGGER.info("Verifying transition to Growler Station page...");
        assertThat(growlerSectionPageObject.clickFindOutMore()).isTrue();
        assertThat(growlerStationPageObject.doesGrowlerPageTitleMatch()).isTrue();
        assertThat(growlerStationPageObject
                .doesGrowlerSectionTitleMatch(GrowlerStationPageObject.SectionTitle.CURRENT_SELECTIONS))
                .isTrue();
    }

    @Step
    public void verifyInitialGrowlerStationCardBreweryNameLabels() {
        LOGGER.info("Verifying Growler Station card Brewery name labels ...");
        assertThat(growlerStationPageObject.isBreweryLabelPresentOnCard(1)).isTrue();
        assertThat(growlerStationPageObject.isBreweryLabelPresentOnCard(2)).isTrue();
    }

    @Step
    public void verifyInitialGrowlerStationCardBeerNameLabels() {
        LOGGER.info("Verifying Growler Station card Beer name labels ...");
        assertThat(growlerStationPageObject.isBeerLabelPresentOnCard(1)).isTrue();
        assertThat(growlerStationPageObject.isBeerLabelPresentOnCard(2)).isTrue();
    }

    @Step
    public void verifyInitialGrowlerStationCardLabels() {
        LOGGER.info("Verifying Growler Station card ABV, IBU, and growler size labels ...");
        assertThat(growlerStationPageObject.areCorrectLabelsPresentOnCard(1)).isTrue();
        assertThat(growlerStationPageObject.areCorrectLabelsPresentOnCard(2)).isTrue();
    }

    @Step
    public void verifyInitialGrowlerStationCardValues() {
        LOGGER.info("Verifying Growler Station card ABV, IBU values and both growler fill prices...");
        assertThat(growlerStationPageObject.areValuesValidOnCard(1)).isTrue();
        assertThat(growlerStationPageObject.areValuesValidOnCard(2)).isTrue();
    }

    @Step
    public void verifySelectGrowlerStationPageOnDeckSection() {
        LOGGER.info("Verifying transition to Growler Station page's On Deck section...");
        assertThat(growlerSectionPageObject.clickFindOutMore()).isTrue();
        assertThat(growlerStationPageObject.doesGrowlerPageTitleMatch()).isTrue();
        assertThat(growlerStationPageObject
                .doesGrowlerSectionTitleMatch(GrowlerStationPageObject.SectionTitle.ON_DECK)).isTrue();
    }

    @Step
    public void verifyLastGrowlerStationCardBreweryNameLabel() {
        LOGGER.info("Verifying Growler Station card Brewery name label on last card...");
        assertThat(growlerStationPageObject
                .isLabelPresentOnLastCard(GrowlerStationPageObject.GrowlerCard.BREWERY)).isTrue();
    }

    @Step
    public void verifyLastGrowlerStationCardBeerNameLabel() {
        LOGGER.info("Verifying Growler Station card Beer name label on last card...");
        assertThat(growlerStationPageObject
                .isLabelPresentOnLastCard(GrowlerStationPageObject.GrowlerCard.BEER)).isTrue();
    }

    @Step
    public void verifyLastGrowlerStationCardLabels() {
        LOGGER.info("Verifying Growler Station card ABV, IBU, and growler size labels on last card...");
        assertThat(growlerStationPageObject.areCorrectLabelsPresentOnLastCard()).isTrue();
    }

    @Step
    public void verifyLastGrowlerStationCardValues() {
        LOGGER.info("Verifying Growler Station card ABV, IBU values and both growler fill prices on last card...");
        assertThat(growlerStationPageObject.areValuesValidOnLastCard()).isTrue();
    }

}
