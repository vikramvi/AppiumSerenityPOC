package com.serenity.appium.poc.tests;

import com.serenity.appium.poc.WineAppTest;
import com.serenity.appium.poc.utils.Enums;
import org.junit.Test;

public class DOT_25261 extends WineAppTest {

    @Test // verified on iOS, Android on 5/22/18
    public void DOT_25261_verifyDepositLabelOnPrice() {
        try{
            wineAppSteps.completeQuickOnboarding();
            wineAppSteps.verifyHomepageToProductSearch();
            wineAppSteps.searchForProduct("modelo especial 1/2 keg");
            wineAppSteps.verifySearchResultCount(1);
            wineAppSteps.verifyFeeInProductSearchResults(Enums.Fees.DEPOSIT);
            wineAppSteps.selectProductFromSearchResults("modelo especial");
            wineAppSteps.verifyFeeInProductDetails(Enums.Fees.DEPOSIT);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
