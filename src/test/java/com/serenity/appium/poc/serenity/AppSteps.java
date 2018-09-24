package com.serenity.appium.poc.serenity;

import com.serenity.appium.poc.pages.*;
import com.serenity.appium.poc.pages.account.*;
import com.serenity.appium.poc.pages.browse.BrowsePageObject;
import com.serenity.appium.poc.pages.home.*;
import com.serenity.appium.poc.pages.onboarding.*;
import com.serenity.appium.poc.pages.productDetails.MainProductDetailsPageObject;
import com.serenity.appium.poc.pages.storeDetails.*;
import com.serenity.appium.poc.pages.orderingFlow.*;
import com.serenity.appium.poc.utils.*;
import net.sourceforge.tess4j.TesseractException;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.DayOfWeek;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class AppSteps extends ScenarioSteps {

    private AccountOptionsPageObject accountOptionsPageObject;
    private BrowsePageObject browsePageObject;
    private CreateAccountPageObject createAccountPageObject;
    private GrowlerSectionPageObject growlerSectionPageObject;
    private GrowlerStationPageObject growlerStationPageObject;
    private IosPlpProductSelector iosPlpProductSelector;
    private LocationPageObject locationPageObject;
    private LoginPageObject loginPageObject;
    private LoyaltyPageObject loyaltyPageObject;
    private MainProductDetailsPageObject mainProductDetailsPageObject;
    private MobilePageObject mobilePageObject;
    private MyStoreHeaderPageObject myStoreHeaderPageObject;
    private NavigationFooterPageObject navigationFooterPageObject;
    private NavigationFooterMoreMenuPageObject navigationFooterMoreMenuPageObject;
    private NotificationPageObject notificationPageObject;
    private PaymentsPageObject paymentsPageObject;
    private PreferencesPageObject preferencesPageObject;
    private ProductSearchPageObject productSearchPageObject;
    private ProductSearchResultsPageObject productSearchResultsPageObject;
    private ProfilePageObject profilePageObject;
    private SearchSectionPageObject searchSection;
    private SpiritsHoursPageObject spiritsHoursPageObject;
    private SplashPageObject splashPageObject;
    private StoreDataHeaderPageObject storeDataHeaderPageObject;
    private StoreDetailsCommonPageObject storeDetailsCommonPageObject;
    private StoreHoursPageObject storeHoursPageObject;
    private StoreIconsPageObject storeIconsPageObject;
    private StoreMapPageObject storeMapPageObject;
    private StoreSearchPageObject storeSearchPageObject;
    private TastingHoursPageObject tastingHoursPageObject;
    private UpdatePaymentPageObject updatePaymentPageObject;
    private WineAppPageObject wineAppPageObject;
    private CartPageOject cartPageObject;
    private ListsSectionPageObject listsSectionPageObject;
    private MyListsPageObject myListsPageObject;
    private DeliveryAddressPageObject deliveryAddressPageObject;
    private OrderReview orderReviewPageObject;

    private static final Logger LOGGER = LoggerFactory.getLogger(AppSteps.class);

    String wineName = "Krug Vintage";

    /// --> temporary sandbox driver
    public void tempDriver()  throws TesseractException {
//        ReadScreenText readScreenText = new ReadScreenText();
        this.performLoginFromHomepage();
//        navigationFooterPageObject.clickBrowseButton();
//        navigationFooterPageObject.clickHomeButton();
        navigationFooterPageObject.clickMoreMenuButton();
//        navigationFooterMoreMenuPageObject.clickSignOutButton();
        navigationFooterMoreMenuPageObject.clickAccountButton();


// ----- A D D R E S S E S
//        accountOptionsPageObject.clickAddressesButton();

// ----- P A Y M E N T S
//         accountOptionsPageObject.clickPaymentButton();
//         assertThat(paymentsPageObject.isPageTitleCorrect()).isTrue();
//         String last4 = PaymentsPageObject.CreditCardField.LAST4.getText(getDriver(), 1);
//         String expiration = PaymentsPageObject.CreditCardField.EXPIRATION.getText(getDriver(), 1);
//         paymentsPageObject.clickAddNewPaymentButton();

//        PaymentsPageObject.CreditCardButton.DELETE.click(getDriver(), 1);
//        assertThat(paymentsPageObject.isCreditCardDeleteConfirmationTextPresent()).isTrue();
//        PaymentsPageObject.CreditCardDeleteConfirmation.DECLINE.click(getDriver());

//        PaymentsPageObject.CreditCardButton.EDIT.click(getDriver(), 1);
//        assertThat(updatePaymentPageObject.isPageTitleCorrect()).isTrue();
//        updatePaymentPageObject.enterText(UpdatePaymentPageObject.Field.CARD_NUMBER, "5472063333333330");
//        updatePaymentPageObject.enterText(UpdatePaymentPageObject.Field.EXPIRATION, "12/19");
//        updatePaymentPageObject.enterText(UpdatePaymentPageObject.Field.CVV, "120");
//        updatePaymentPageObject.clickUpdatePaymentButton();



// ----- P R E F E R E N C E S
        accountOptionsPageObject.clickPreferencesButton();
        assertThat(preferencesPageObject.isPageTitleCorrect()).isTrue();
//        assertThat(PreferencesPageObject.Preferences.SPIRITS.isUnchecked(getDriver())).isTrue();
//        assertThat(PreferencesPageObject.Preferences.SPIRITS.check(getDriver())).isTrue();
//        try {
//            Thread.sleep(250);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        preferencesPageObject.clickUpdateButton();
//        assertThat(PreferencesPageObject.Preferences.SPIRITS.isChecked(getDriver())).isTrue();
//        assertThat(PreferencesPageObject.Preferences.SPIRITS.uncheck(getDriver())).isTrue();
//        try {
//            Thread.sleep(250);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        preferencesPageObject.clickUpdateButton();
//        assertThat(PreferencesPageObject.Preferences.SPIRITS.isUnchecked(getDriver())).isTrue();

// ----- P R O F I L E
//        accountOptionsPageObject.clickProfileButton()
//        long random = Math.round(Math.random()*100);
//        String newFirstName = "joe"+random;
//        profilePageObject.enterFirstName(newFirstName, true);
//        profilePageObject.enterLastName("hennessey"+random, true);
//        profilePageObject.clickUpdateButton();
//        Utils.waitFor(1500);
//        profilePageObject.clickReturnButton();
//        accountOptionsPageObject.clickProfileButton();
//        String actual = profilePageObject.getFirstName();
//        assertThat(actual.equalsIgnoreCase(newFirstName)).isTrue();

//        readScreenText.readScreenText(getDriver());
//        browsePageObject.clickWineCard();
//        browsePageObject.clickWineTypesCategory();
//        browsePageObject.clickWineSubcategoryRedWine();
    }

    private static boolean isOnboardingCompleted = false;

    @Step
    public void completeOnboardingAllowingLocation() {
        try {

            LOGGER.info(" isOnboardingCompleted Flag value = " + isOnboardingCompleted );

            if(!isOnboardingCompleted) {

                LOGGER.info("Completing onboarding, declining all options but Location...");

                splashPageObject.startOnboarding();

                locationPageObject.allowLocationTracking();

                notificationPageObject.declineReceivingNotifications();

                loyaltyPageObject.declineLoyaltyLogin();

                isOnboardingCompleted = true;
            }

        }catch (Exception e){
            e.printStackTrace();
            isOnboardingCompleted = false;
        }
    }

    @Step
    public void completeQuickOnboarding() {

        LOGGER.info(" completeQuickOnboarding Flag value = " + isOnboardingCompleted);

        if(!isOnboardingCompleted){

            LOGGER.info("Completing onboarding, declining all options...");

            assertThat(splashPageObject.startOnboarding()).isTrue();
            assertThat(locationPageObject.declineLocationTracking()).isTrue();
            assertThat(notificationPageObject.declineReceivingNotifications()).isTrue();
            assertThat(loyaltyPageObject.declineLoyaltyLogin()).isTrue();

            isOnboardingCompleted = true;
        }
    }

    @Step
    public void performLoginFromHomepage() {
        LOGGER.info("Performing default login from homepage...");
        assertThat(myStoreHeaderPageObject.clickSignIn()).isTrue();
        assertThat(loginPageObject.confirmHeader()).isTrue();
        assertThat(loginPageObject.performDefaultLogin()).isTrue();
        assertThat(myStoreHeaderPageObject.isMyOrdersPresent()).isTrue();
    }

    @Step
    public void performLoginFromHomepage(String emailId) {
        LOGGER.info("Performing login from homepage with give email...");
        assertThat(myStoreHeaderPageObject.clickSignIn()).isTrue();
        assertThat(loginPageObject.confirmHeader()).isTrue();
        assertThat(loginPageObject.performLogin(emailId)).isTrue();
        assertThat( navigationFooterPageObject.isHomeButtonPresent(25) ).isTrue();
    }

    @Step
    public void createRandomUserFromHomepage() {
        LOGGER.info("Creating random user from homepage...");
        assertThat(myStoreHeaderPageObject.clickCreateAccount()).isTrue();
        createAccountPageObject.isPageTitleCorrect();
        createAccountPageObject.enterRandomFirstName();
        createAccountPageObject.enterRandomLastName();
        createAccountPageObject.enterRandomEmail();
        createAccountPageObject.enterDefaultPassword();
        createAccountPageObject.enterDefaultPasswordConfirmation();
        createAccountPageObject.enterFakePhoneNumber();
        createAccountPageObject.clickAgeConfirmation();
        createAccountPageObject.clickTermsConfirmation();
        createAccountPageObject.clickAndroidCreateAccountButton();
        assertThat(myStoreHeaderPageObject.isMyOrdersPresent()).isTrue();
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
        assertThat(productSearchResultsPageObject.getResultsCount().length() > 0).isTrue();
    }

    @Step
    public void selectProductNameFromSearchSuggestions(String suggestion) {
        LOGGER.info("Selecting search suggestion...");
        assertThat(productSearchPageObject.selectSearchSuggestion(suggestion)).isTrue();
    }

    @Step
    public void selectProductFromSearchResults(String productName) {
        LOGGER.info("Verifying selection of product from search results...");
        assertThat(productSearchResultsPageObject.selectProduct(productName)).isTrue();
        String actualName = mainProductDetailsPageObject.getProductName();
        assertThat(actualName).isEqualToIgnoringCase(productName);
    }

    @Step
    public void performSortAction() {
        LOGGER.info("Performing sort action...");
        assertThat(wineAppPageObject.performSortActionByHighPrice()).isTrue();
    }

    @Step
    public void verifyHomepageStoreDetails(String expectedTitle) {
        LOGGER.info("Verifying homepage store details:  \nExpected title = " + expectedTitle);
        assertThat(myStoreHeaderPageObject.getTitleFromStoreData()).isEqualToIgnoringCase(expectedTitle);
        assertThat(myStoreHeaderPageObject.getOpenInfoFromStoreData()).matches(StoreDataParser.openCloseRegex);
    }

    @Step
    public void verifyHomepageToStoreDetails() {
        LOGGER.info("Clicking touchable store data to see store details page...");
        assertThat(myStoreHeaderPageObject.clickTouchableStoreData()).isTrue();
        assertThat(storeIconsPageObject.isChangeStoreIconPresent()).isTrue();
    }

    @Step
    public void verifyHomepageToPreferences() {
        LOGGER.info("Clicking More > Account > Preferences...");
        assertThat(navigationFooterPageObject.clickMoreMenuButton()).isTrue();
        assertThat(navigationFooterMoreMenuPageObject.clickAccountButton()).isTrue();
        assertThat(accountOptionsPageObject.clickPreferencesButton()).isTrue();
        assertThat(preferencesPageObject.isPageTitleCorrect()).isTrue();
    }

    @Step
    public void verifyPreferencesInterestsContent() {
        LOGGER.info("Verifying Preferences Interests header and options...");
        assertThat(PreferencesPageObject.Headers.INTERESTS.isVisible(getDriver())).isTrue();
        assertThat(preferencesPageObject.verifyProductInterestsSubheading()).isTrue();
        assertThat(PreferencesPageObject.Preferences.WINE.isVisible(getDriver())).isTrue();
        assertThat(PreferencesPageObject.Preferences.SPIRITS.isVisible(getDriver())).isTrue();
        assertThat(PreferencesPageObject.Preferences.BEER.isVisible(getDriver())).isTrue();
        assertThat(PreferencesPageObject.Preferences.CIGARS.isVisible(getDriver())).isTrue();
     }

    @Step
    public void verifyPreferencesCommunicationsContent() {
        LOGGER.info("Verifying Preferences Communication header and options...");
        assertThat(PreferencesPageObject.Headers.COMMUNICATIONS.isVisible(getDriver())).isTrue();
        assertThat(PreferencesPageObject.Preferences.PROMOTIONS.uncheck(getDriver())).isTrue();
        assertThat(PreferencesPageObject.Preferences.EVENTS.uncheck(getDriver())).isTrue();
        assertThat(preferencesPageObject.clickUpdateButton()).isTrue();
        assertThat(Scrolling.scrollDown()).isTrue();
        assertThat(PreferencesPageObject.Preferences.PROMOTIONS.isUnchecked(getDriver())).isTrue();
        assertThat(PreferencesPageObject.Preferences.EVENTS.isUnchecked(getDriver())).isTrue();
    }

    @Step
    public void verifyPreferencesToAccount() {
        assertThat(preferencesPageObject.clickReturnButton()).isTrue();
        assertThat(accountOptionsPageObject.isPageTitleCorrect()).isTrue();
    }

    @Step
    public void verifyChangeStoreLookupOption() {
        LOGGER.info("Clicking change store and verifying that the geo search field is displayed...");
        assertThat(myStoreHeaderPageObject.isChangeStoreOptionPresent()).isTrue();
        assertThat(myStoreHeaderPageObject.clickChangeStore()).isTrue();
        assertThat(storeSearchPageObject.isSearchFieldPresent()).isTrue();
//        assertThat(storeSearchPageObject.isAndroidSearchButtonPresent()).isTrue();
    }

    @Step
    public void verifySearchResultCount(int expected) {
        LOGGER.info("Checking the number of search results...");
        assertThat(productSearchResultsPageObject.getResultsCount()).isEqualTo(Integer.toString(expected));
    }

    @Step
    public void verifyFeeInProductSearchResults(Enums.Fees fee) {
        LOGGER.info("Verifying search results contain " +fee.getLabel()+ "...");
        assertThat(productSearchResultsPageObject.isPriceAttributePresentInResults(fee)).isTrue();
    }

    @Step
    public void verifyNameInProductSearchResults(String name) {
        LOGGER.info("Verifying search results contain " +name+ "...");
        assertThat(productSearchResultsPageObject.isNamePresentInTopResults(name)).isTrue();
    }

//    @Step
//    public void verifyPresenceOfFeeInSearchResults(int productNumber, Enums.Fees fee) {
//        LOGGER.info("Verifying presence of " +fee.toString()+ " fee in search results...");
//        String actualPrice = productSearchResultsPageObject.getAndroidProductPrice(productNumber);
//        String expectedFee = fee.getText();
//        assertThat(actualPrice).contains(expectedFee);
//    }

    @Step
    public void verifySelectProductFromSearchResults(int productNumber) {
        LOGGER.info("Verifying product selection from search results...");
        String expectedName = productSearchResultsPageObject.getAndroidProductName(productNumber);
        assertThat(productSearchResultsPageObject.selectProductForAndroid(productNumber)).isTrue();
        String actualName = mainProductDetailsPageObject.getProductName();
        assertThat(actualName).isEqualToIgnoringCase(expectedName);
    }

    @Step
    public void verifyFeeInProductDetails(Enums.Fees fee) {
        LOGGER.info("Verifying presence of " +fee.toString()+ " fee in product details...");
        String actualFee = mainProductDetailsPageObject.getProductFee();
        String expectedFee = fee.getLabel();
        assertThat(actualFee).isEqualTo(expectedFee);
    }

    @Step
    public void verifyInStoreAvailabilityInProductDetails(Enums.InStoreAvailability availability) {
        LOGGER.info("Verifying presence of " +availability.toString()+ " in in-store availability in product details...");
        String actualInStoreAvailability = mainProductDetailsPageObject.getProductInStoreAvailability();
        String expectedInStoreAvailability = availability.getText();
        assertThat(actualInStoreAvailability).isEqualTo(expectedInStoreAvailability);
    }

    @Step
    public void verifyProductDetailsToSearchResults() {
        LOGGER.info("Clicking Return button to return from Product Details to Search Results...");
        assertThat(mainProductDetailsPageObject.clickReturn()).isTrue();
        assertThat(productSearchResultsPageObject.getResultsCountInteger()>0).isTrue();
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
        assertThat(storeIconsPageObject.isChangeStoreIconPresent()).isTrue();
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
    public void verifyTastingHours(TastingHoursPageObject.TastingType tastingType, List<DayOfWeek> tastingDays, boolean scrollDown) {
        LOGGER.info("Verifying that sampling hours for " +tastingType.name()+ " are only on specific days...");
        if (scrollDown) {
            Scrolling.scrollDown();
        }
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
        //assertThat(storeSearchPageObject.isAndroidSearchButtonPresent()).isTrue();
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

    @Step
    public void verifyShopThisStore() {
        LOGGER.info("Verifying clicking Shop This Store icon from store details page...");
        assertThat(storeIconsPageObject.clickShopThisStoreIcon()).isTrue();
    }

    @Step
    public void gotoHomeTab(){
        assertThat( navigationFooterPageObject.clickHomeButton() ).isTrue();
    }

    @Step
    public void gotoCARTScreen(){
        assertThat( mainProductDetailsPageObject.clickAddToCart() ).isTrue();
        assertThat( navigationFooterPageObject.isPageTitleCorrect() ).isTrue();
        assertThat( navigationFooterPageObject.clickShoppingCartButton() ).isTrue();
    }

    @Step
    public void gotoOrderReviewScreen(){
        cartPageObject.clickSecureCheckout();
    }

    @Step
    public void verifyMyRewardGetsAppliedSuccessfully(){
        cartPageObject.isPageTitleCorrect();
        assertThat( cartPageObject.isRewardAppliedSuccessfully() ).isTrue();
        cartPageObject.deleteCartItem();
    }

    @Step
    public void verifyNewListCreationFromMyListsScreenAndBrowseForSelectedList(String newListName){
        assertThat( listsSectionPageObject.isMyListsSectionDisplayed() ).isTrue();
        listsSectionPageObject.clickViewAllListsButton();

        myListsPageObject.clickCreateListButton();

        myListsPageObject.enterListName(newListName);
        assertThat( myListsPageObject.clickListNameSaveButton() ).isTrue();

        assertThat( myListsPageObject.isListNameVisible(newListName) ).isTrue();
        assertThat( myListsPageObject.clickArrowButtonAgainstParticularList(newListName) ).isTrue();
        assertThat( myListsPageObject.clickStartBrowsingButton() ).isTrue();
    }

    @Step
    public void addRichRelevanceItemToList_VerifyAndDeleteList(String newListName){
        browsePageObject.isWineCardPresent();
        browsePageObject.clickWineCard();
        browsePageObject.scrollToHotProductsSection();

        String itemNameToBeAddedToNewlyCreatedList = browsePageObject.getFirtItemNameWithHeartIcon();
        browsePageObject.clickFirstItemHeartIcon();
        browsePageObject.addSelectedIconByClickingHeartIconToList(newListName);

        browsePageObject.clickViewAllButton();
        assertThat( myListsPageObject.isMyListsScreenVisible() ).isTrue();
        myListsPageObject.clickArrowButtonAgainstParticularList(newListName);

        assertThat( browsePageObject.verifyItemNameAddedToTheList(itemNameToBeAddedToNewlyCreatedList) ).isTrue();
        assertThat( browsePageObject.clickDeleteListButton() ).isTrue();
        browsePageObject.clickDeleteConfirmationYesButton();
    }

    @Step
    public void verifyListDeletionActions(String listName){
        assertThat( listsSectionPageObject.isMyListsSectionDisplayed() ).isTrue();
        listsSectionPageObject.clickViewAllListsButton();

        myListsPageObject.clickCreateListButton();
        myListsPageObject.enterListName(listName);
        assertThat( myListsPageObject.clickListNameSaveButton() ).isTrue();
        assertThat( myListsPageObject.isListNameVisible(listName) ).isTrue();
        assertThat( myListsPageObject.clickArrowButtonAgainstParticularList(listName) ).isTrue();

        browsePageObject.clickDeleteListButton();
        browsePageObject.clickDeleteConfirmationNoButton();
        browsePageObject.clickListDetailPageReturnButton();
        assertThat( myListsPageObject.isListNameVisible(listName) ).isTrue();

        assertThat( myListsPageObject.clickArrowButtonAgainstParticularList(listName) ).isTrue();
        browsePageObject.clickDeleteListButton();
        browsePageObject.clickDeleteConfirmationYesButton();

        assertThat( myListsPageObject.isMyListsScreenVisible() ).isTrue();
        assertThat( myListsPageObject.isListNameVisible(listName) ).isFalse();
    }

    @Step
    public void verifyItemNotEligibleForHomeDelivery_RemovalFromCart_AlongWithOtherItemsInCart(){
        cartPageObject.selectContinueForChangingToDeliveryDialog();
        deliveryAddressPageObject.addNewAddressForDelivery();
        cartPageObject.chooseProccedOnSomeItemsNotAvailableDialog();
        assertThat( cartPageObject.isContinueShoppingLinkShown() ).isTrue();
    }

    @Step
    public void addNewCreditCardFromHomeMoreOptionsAccount(){
        navigationFooterPageObject.clickMoreMenuButton();
        navigationFooterMoreMenuPageObject.clickAccountButton();
        accountOptionsPageObject.clickPaymentButton();
        paymentsPageObject.clickAddNewPaymentButton();

        assertThat( updatePaymentPageObject.isAddNewPaymentScreenDisplayed() ).isTrue();
        updatePaymentPageObject.enterText(UpdatePaymentPageObject.Field.CARD_NUMBER, "5472063333333330");
        updatePaymentPageObject.enterText(UpdatePaymentPageObject.Field.EXPIRATION, "12/19");
        updatePaymentPageObject.enterText(UpdatePaymentPageObject.Field.CVV, "120");

        updatePaymentPageObject.enterText(UpdatePaymentPageObject.Field.ADDRESS1, "1880 N. Congress Ave");
        updatePaymentPageObject.enterText(UpdatePaymentPageObject.Field.CITY, "Boynton Beach");
        updatePaymentPageObject.enterText(UpdatePaymentPageObject.Field.STATE, "FL");
        updatePaymentPageObject.enterText(UpdatePaymentPageObject.Field.ZIP, "33426");

        updatePaymentPageObject.clickAndroidUpdatePaymentButton();
        assertThat(updatePaymentPageObject.isCreditCardAddedSuccessfully()).isTrue();
    }

    @Step
    public void completeOrderAndVerify(){
        orderReviewPageObject.isOrderReviewPageTitleCorrect();
        orderReviewPageObject.clickAgreeToShowAgeProofCheckbox();
        orderReviewPageObject.completeOrderAndGotoViewDetailsScreen();
    }

    @Step
    public void verifyNumberOfCreditCardsDisplayedForNewSignUp(){
        navigationFooterPageObject.clickMoreMenuButton();
        assertThat( navigationFooterMoreMenuPageObject.clickAccountButton() ).isTrue();
        assertThat( accountOptionsPageObject.clickPaymentButton() ).isTrue();
        assertThat( paymentsPageObject.getTotalNumberOfCreditCardsDisplayed()).isEqualTo(1);
    }

}
