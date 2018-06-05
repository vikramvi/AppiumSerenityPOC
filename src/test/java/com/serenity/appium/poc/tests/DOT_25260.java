package com.serenity.appium.poc.tests;

import com.serenity.appium.poc.WineAppTest;
import com.serenity.appium.poc.utils.Enums;
import org.junit.Test;

public class DOT_25260 extends WineAppTest {

    @Test // verified on iOS on 5/22/18
    public void DOT_25260_verifyCrvLabelOnPrice() {
        try{
            appSteps.completeQuickOnboarding();
            appSteps.verifyChangeStoreLookupOption();
            appSteps.verifySelectStoreFromSearchResults("Fremont", "Fremont");
            appSteps.verifyShopThisStore();
            appSteps.verifyHomepageToProductSearch();
            String token = "modelo especial chelada";
            appSteps.searchForProduct(token);
            appSteps.verifyFeeInProductSearchResults(Enums.Fees.CRV);
            appSteps.selectProductFromSearchResults(token);
            appSteps.verifyFeeInProductDetails(Enums.Fees.CRV);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
