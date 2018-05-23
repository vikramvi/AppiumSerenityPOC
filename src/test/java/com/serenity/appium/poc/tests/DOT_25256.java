package com.serenity.appium.poc.tests;

import com.serenity.appium.poc.WineAppTest;
import com.serenity.appium.poc.utils.Enums;
import org.junit.Test;

public class DOT_25256 extends WineAppTest {

    @Test // verified on iOS, Android on 5/22/18
    public void DOT_25256_verifyLimitedAvailability() {
        try{
            wineAppSteps.completeQuickOnboarding();
            wineAppSteps.verifyChangeStoreLookupOption();
            wineAppSteps.verifySelectStoreFromSearchResults("Boynton Beach", "Boynton Beach");
            wineAppSteps.verifyShopThisStore();
            wineAppSteps.verifyHomepageToProductSearch();
            String token = "dom perignon jeff koons brut vintage";
            wineAppSteps.searchForProduct(token);
            //           wineAppSteps.verifySearchResultCount(1);
            wineAppSteps.selectProductFromSearchResults(token);
            wineAppSteps.verifyInStoreAvailabilityInProductDetails(Enums.InStoreAvailability.LIMITED);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
