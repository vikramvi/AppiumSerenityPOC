package com.serenity.appium.poc.serenity;

import com.serenity.appium.poc.pages.MyStoreHeaderPageObject;
import com.serenity.appium.poc.pages.Onboarding.LocationPageObject;
import com.serenity.appium.poc.pages.Onboarding.LoyaltyPageObject;
import com.serenity.appium.poc.pages.Onboarding.NotificationPageObject;
import com.serenity.appium.poc.pages.Onboarding.SplashPageObject;
import com.serenity.appium.poc.pages.StoreSearchPage;
import com.serenity.appium.poc.pages.WineAppPageObject;

import com.serenity.appium.poc.utils.StoreDataParser;
import net.thucydides.core.steps.ScenarioSteps;
import net.thucydides.core.annotations.Step;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.assertj.core.api.Assertions.assertThat;

public class WineAppSteps extends ScenarioSteps {

    private WineAppPageObject wineAppPageObject;
    private SplashPageObject splashPageObject;
    private LocationPageObject locationPageObject;
    private NotificationPageObject notificationPageObject;
    private LoyaltyPageObject loyaltyPageObject;
    private MyStoreHeaderPageObject myStoreHeaderPageObject;
    private StoreSearchPage storeSearchPage;

    private static final Logger LOGGER = LoggerFactory.getLogger(WineAppSteps.class);

    String winName = "Sweet Red Wine";

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
    public void doWineSearch() {
        assertThat(wineAppPageObject.performSearchActionWithValidWineName(winName)).isTrue();
    }

    @Step
    public void selectWineNameFromSearchResultView() {
        assertThat(wineAppPageObject.selectWineNameFromSearchResults(winName)).isTrue();
    }

    @Step
    public void performSortAction() {
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
        assertThat(myStoreHeaderPageObject.clickChangeStore()).isTrue();
        assertThat(storeSearchPage.isSearchFieldPresent()).isTrue();
    }
}
