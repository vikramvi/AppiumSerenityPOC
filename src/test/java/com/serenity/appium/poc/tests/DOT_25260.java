package com.serenity.appium.poc.tests;

import com.serenity.appium.poc.WineAppTest;
import com.serenity.appium.poc.utils.Enums;
import org.junit.Test;

public class DOT_25260 extends WineAppTest {

    @Test // verified on iOS on 5/22/18
    public void DOT_25260_verifyCrvLabelOnPrice() {
        try{
            wineAppSteps.completeQuickOnboarding();
            wineAppSteps.verifyChangeStoreLookupOption();
            wineAppSteps.verifySelectStoreFromSearchResults("Fremont", "Fremont");
            wineAppSteps.verifyShopThisStore();
            wineAppSteps.verifyHomepageToProductSearch();
            String token = "modelo especial chelada";
            wineAppSteps.searchForProduct(token);
            wineAppSteps.verifyFeeInProductSearchResults(Enums.Fees.CRV);
            wineAppSteps.selectProductFromSearchResults(token);
            wineAppSteps.verifyFeeInProductDetails(Enums.Fees.CRV);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
