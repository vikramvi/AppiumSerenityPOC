package com.serenity.appium.poc.tests;

import com.serenity.appium.poc.WineAppTest;
import com.serenity.appium.poc.utils.Enums;
import org.junit.Test;

public class DOT_25259 extends WineAppTest {

    @Test // verified on iOS on 5/22/18
    public void DOT_25259_verifyWasltLabelOnPrice() {
        try{
            wineAppSteps.completeQuickOnboarding();
            wineAppSteps.verifyChangeStoreLookupOption();
            wineAppSteps.verifySelectStoreFromSearchResults("Bellevue", "Bellevue");
            wineAppSteps.verifyShopThisStore();
            wineAppSteps.verifyHomepageToProductSearch();
            String token = "mascarade liqueur";
            wineAppSteps.searchForProduct(token);
            //           wineAppSteps.verifySearchResultCount(1);
            wineAppSteps.verifyFeeInProductSearchResults(Enums.Fees.WASLT);
            wineAppSteps.selectProductFromSearchResults(token);
            wineAppSteps.verifyFeeInProductDetails(Enums.Fees.WASLT);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
