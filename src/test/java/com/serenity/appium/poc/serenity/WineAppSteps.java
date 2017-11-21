package com.serenity.appium.poc.serenity;

import com.serenity.appium.poc.pages.*;
import com.serenity.appium.poc.pages.home.MyStoreHeaderPageObject;
import com.serenity.appium.poc.pages.home.SearchSectionPageObject;
import com.serenity.appium.poc.pages.onboarding.LocationPageObject;
import com.serenity.appium.poc.pages.onboarding.LoyaltyPageObject;
import com.serenity.appium.poc.pages.onboarding.NotificationPageObject;
import com.serenity.appium.poc.pages.onboarding.SplashPageObject;

import com.serenity.appium.poc.pages.storeDetails.StoreIconsPageObject;
import com.serenity.appium.poc.utils.StoreDataParser;
import net.thucydides.core.steps.ScenarioSteps;
import net.thucydides.core.annotations.Step;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.assertj.core.api.Assertions.assertThat;

public class WineAppSteps extends ScenarioSteps {

    private LocationPageObject locationPageObject;
    private LoyaltyPageObject loyaltyPageObject;
    private MyStoreHeaderPageObject myStoreHeaderPageObject;
    private NotificationPageObject notificationPageObject;
    private ProductSearchPageObject productSearchPageObject;
    private ProductSearchResultsPageObject productSearchResultsPageObject;
    private SearchSectionPageObject searchSection;
    private SplashPageObject splashPageObject;
    private StoreIconsPageObject storeIconsPageObject;
    private StoreSearchPageObject storeSearchPageObject;
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
    public void initiateProductSearch() {
        LOGGER.info("Initiating a product search...");
        assertThat(myStoreHeaderPageObject.isChangeStoreOptionPresent()).isTrue();
        assertThat(searchSection.triggerSearchPage()).isTrue();
//        assertThat(wineAppPageObject.performSearchActionWithValidWineName(winName)).isTrue();
        assertThat(productSearchPageObject.typeSearchTerm(wineName)).isTrue();
    }

    @Step
    public void selectProductNameFromSearchSuggestions() {
        LOGGER.info("Selecting search suggestion...");
//        assertThat(wineAppPageObject.selectWineNameFromSearchResults(wineName)).isTrue();
        assertThat(productSearchPageObject.selectSearchSuggestion(wineName)).isTrue();
    }

    @Step
    public void performSortAction() {
        LOGGER.info("Performing sort action...");
        assertThat(wineAppPageObject.performSortActionByHighPrice()).isTrue();
    }

    @Step
    public void verifyHomepageStoreDetails(String expectedTitle, String expectedLocation) {
        LOGGER.info("Verifying homepage store details:  \nExpected title = " +expectedTitle+ "\nExpected address = " +expectedLocation);
        assertThat(myStoreHeaderPageObject.getTitleFromStoreData()).isEqualTo(expectedTitle);
        assertThat(myStoreHeaderPageObject.getLocationFromStoreData()).isEqualTo(expectedLocation);
        assertThat(myStoreHeaderPageObject.getOpenInfoFromStoreData()).matches(StoreDataParser.openCloseRegex);
    }

    @Step
    public void verifyChangeStoreLookupOption() {
        LOGGER.info("Clicking change store and verifying that the geo search field is displayed...");
        assertThat(myStoreHeaderPageObject.isChangeStoreOptionPresent()).isTrue();
        assertThat(myStoreHeaderPageObject.clickChangeStore()).isTrue();
        assertThat(storeSearchPageObject.isSearchFieldPresent()).isTrue();
    }

    @Step
    public void verifySearchResultCount(int expected) {
        LOGGER.info("Checking the number of search results...");
        assertThat(productSearchResultsPageObject.getResultsCount()).isEqualTo(Integer.toString(expected));
    }

    public void verifySelectNewStore() {
        LOGGER.info("Selecting the first store in the list and inspecting its details page...");
        assertThat(storeSearchPageObject.enterSearchToken("33435")).isTrue();
        assertThat(storeSearchPageObject.selectStore("SHOPPES AT ISLA VERDE")).isTrue();
    }

    public void verifyStoreDetailsIcons() {
        LOGGER.info("Verifying that the top 3 icons are present on the store details page...");
        assertThat(storeIconsPageObject.isCallStoreIconPresent()).isTrue();
        assertThat(storeIconsPageObject.isGetDirectionsIconPresent()).isTrue();
        assertThat(storeIconsPageObject.isMyStoreIconPresent()).isTrue();
    }
}
