package com.serenity.appium.poc.tests.isolatedRun;

import com.serenity.appium.poc.WineAppTest;
import com.serenity.appium.poc.utils.Enums;
import org.junit.Test;

public class DOT_25256 extends WineAppTest {

    @Test // verified on iOS, Android on 5/22/18
    public void DOT_25256_verifyLimitedAvailability() {
        try{
            appSteps.completeQuickOnboarding();
            appSteps.verifyChangeStoreLookupOption();
            appSteps.verifySelectStoreFromSearchResults("Boynton Beach", "Boynton Beach");
            appSteps.verifyShopThisStore();
            appSteps.verifyHomepageToProductSearch();
            String token = "dom perignon jeff koons brut vintage";
            appSteps.searchForProduct(token);
            //           appSteps.verifySearchResultCount(1);
            appSteps.selectProductFromSearchResults(token);
            appSteps.verifyInStoreAvailabilityInProductDetails(Enums.InStoreAvailability.LIMITED);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
