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
import java.util.ArrayList;
import java.util.Arrays;
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
    public void initiateProductSearch(String searchToken) {
        LOGGER.info("Initiating a product search...");
        assertThat(myStoreHeaderPageObject.isChangeStoreOptionPresent()).isTrue();
        assertThat(searchSection.triggerSearchPage()).isTrue();
//        assertThat(wineAppPageObject.performSearchActionWithValidWineName(winName)).isTrue();
//        assertThat(productSearchPageObject.typeSearchTerm(wineName)).isTrue();
        assertThat(productSearchPageObject.typeSearchTerm(searchToken)).isTrue();
    }

    @Step
    public void selectProductNameFromSearchSuggestions(String suggestion) {
        LOGGER.info("Selecting search suggestion...");
//        assertThat(wineAppPageObject.selectWineNameFromSearchResults(wineName)).isTrue();
//        assertThat(productSearchPageObject.selectSearchSuggestion(wineName)).isTrue();
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
//        assertThat(myStoreHeaderPageObject.scrollToStore()).isTrue();
        assertThat(myStoreHeaderPageObject.getTitleFromStoreData()).isEqualTo(expectedTitle);
//        assertThat(myStoreHeaderPageObject.getLocationFromStoreData()).isEqualTo(expectedLocation);
        assertThat(myStoreHeaderPageObject.getOpenInfoFromStoreData()).matches(StoreDataParser.openCloseRegex);
    }

    @Step
    public void verifyHomepageToStoreDetails() {
        LOGGER.info("Clicking touchable store data to see store details page...");
        assertThat(myStoreHeaderPageObject.clickTouchableStoreData()).isTrue();
    }

    @Step
    public void verifyChangeStoreLookupOption() {
        LOGGER.info("Clicking change store and verifying that the geo search field is displayed...");
//        assertThat(myStoreHeaderPageObject.scrollToStore()).isTrue();
        assertThat(myStoreHeaderPageObject.isChangeStoreOptionPresent()).isTrue();
        assertThat(myStoreHeaderPageObject.clickChangeStore()).isTrue();
        assertThat(storeSearchPageObject.isSearchFieldPresent()).isTrue();
    }

    @Step
    public void verifySearchResultCount(int expected) {
        LOGGER.info("Checking the number of search results...");
        assertThat(productSearchResultsPageObject.getResultsCount()).isEqualTo(Integer.toString(expected));
    }

    public void verifySelectNewStore(String searchToken, String storeName) {
        verifySelectNewStore(searchToken, "", storeName);
    }
    @Step
    public void verifySelectNewStore(String searchToken, String oldSearchToken, String storeName) {
        LOGGER.info("Entering search token then selecting the specified store in the list...");
        assertThat(storeSearchPageObject.enterSearchToken(searchToken, oldSearchToken)).isTrue();
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
    public void verifyStoreDetailsHeaderData(String address1, String address2, String cityStateZip, String openCloseHour) {
        LOGGER.info("Verifying the store address and open until/at data...");
        assertThat(storeDataHeaderPageObject.getAddress1()).isEqualTo(address1);
        assertThat(storeDataHeaderPageObject.getAddress2()).isEqualTo(address2);
        assertThat(storeDataHeaderPageObject.getCityStateZip()).isEqualTo(cityStateZip);
        assertThat(storeDataHeaderPageObject.getOpenCloseHour()).isEqualTo(openCloseHour);
    }

    @Step
    public void verifyStoreDetailsMapLoad() {
        LOGGER.info("Verifying that the map is present on the store details page...");
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
    public void verifyShowTastingHours() {
        LOGGER.info("Clicking tab to show tasting hours...");
        assertThat(tastingHoursPageObject.isTastingHoursTabPresent()).isTrue();
        assertThat(tastingHoursPageObject.clickTastingHoursTab()).isTrue();
    }

    @Step
    public void verifyStoreTastingHours(TastingHoursPageObject.TastingType tastingType, List<DayOfWeek> tastingDays) {
        LOGGER.info("Verifying that tasting hours for " +tastingType.name()+ " are only on specific days...");
        assertThat(tastingHoursPageObject.isShowingHoursForSelectDays(tastingType, tastingDays)).isTrue();
    }

    @Step
    public void verifyReturn() {
        LOGGER.info("Returning back one screen...");
        tastingHoursPageObject.clickReturnButton();
    }
}
