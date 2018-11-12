package com.serenity.appium.poc.tests;

import com.serenity.appium.poc.WineAppTest;
import com.serenity.appium.poc.utils.Regression1;
import com.serenity.appium.poc.utils.Utils;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class DOT_28160 extends WineAppTest{

    @Test
    @Category({Regression1.class})
    public void DOT_28160_PDPChangeStore_ShowsStoresWithInStockInventoryNearCurrentStore(){
        appSteps.completeQuickOnboarding();
        appSteps.verifyUserIsOnHomeTab();

        appSteps.verifyChangeStoreLookupOption();
        appSteps.verifySelectStoreFromSearchResults("Mountain View", "Mountain View");
        appSteps.performShopThisStoreAction();

        appSteps.verifyHomepageToProductSearch();
        appSteps.searchForProduct("Nemiroff Honey Pepper Vodka");
        appSteps.verifySelectProductFromSearchResults(1);

        appSteps.verifyChangeStoreFromProductDetailsAndChooseAnotherNearByStore();
    }
}
