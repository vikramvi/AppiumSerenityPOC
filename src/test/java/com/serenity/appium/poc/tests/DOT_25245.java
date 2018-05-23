package com.serenity.appium.poc.tests;

import com.serenity.appium.poc.WineAppTest;
import com.serenity.appium.poc.utils.FindStore;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class DOT_25245 extends WineAppTest {

    @Test // verified on iOS, Android on 5/22/18
    @Category({FindStore.class})
    public void DOT_25245_verifyStoreAddress() {
        String title = "Laurel (Corridor)";
        String address1 = "Laurel Corridor";
        String address2 = "3335 Corridor Marketplace";
        String cityStateZip = "Laurel, MD 20724";
        String phoneNumber = "(301) 617-8507";
        String hours = "8:00 AM - 11:00 PM";
        String openUntil = "Open Until 11 PM";
        try {
            wineAppSteps.completeQuickOnboarding();
            wineAppSteps.verifyChangeStoreLookupOption();
            wineAppSteps.verifyStoreDataInNewSearchResults(
                    "MD", title, address1, address2, cityStateZip, phoneNumber, hours);
            wineAppSteps.verifySelectStoreFromSearchResults(title);
            wineAppSteps.verifyStoreDetailsHeaderData(
                    title, address1, address2, cityStateZip, openUntil);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
