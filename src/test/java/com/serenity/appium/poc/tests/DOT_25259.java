package com.serenity.appium.poc.tests;

import com.serenity.appium.poc.WineAppTest;
import com.serenity.appium.poc.utils.Enums;
import org.junit.Test;

public class DOT_25259 extends WineAppTest {

    @Test // verified on iOS on 5/22/18
    public void DOT_25259_verifyWasltLabelOnPrice() {
        try{
            appSteps.completeQuickOnboarding();
            appSteps.verifyChangeStoreLookupOption();
            appSteps.verifySelectStoreFromSearchResults("Bellevue", "Bellevue");
            appSteps.verifyShopThisStore();
            appSteps.verifyHomepageToProductSearch();
            String token = "mascarade liqueur";
            appSteps.searchForProduct(token);
            //           appSteps.verifySearchResultCount(1);
            appSteps.verifyFeeInProductSearchResults(Enums.Fees.WASLT);
            appSteps.selectProductFromSearchResults(token);
            appSteps.verifyFeeInProductDetails(Enums.Fees.WASLT);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
