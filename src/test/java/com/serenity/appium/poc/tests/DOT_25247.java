package com.serenity.appium.poc.tests;

import com.serenity.appium.poc.WineAppTest;
import com.serenity.appium.poc.utils.StoreDetails;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class DOT_25247 extends WineAppTest {

    @Test // verified on iOS, Android on 5/22/18
    @Category({StoreDetails.class})
    public void DOT_25247_verifyStoreHours(){
        try{
            wineAppSteps.completeQuickOnboarding();
            wineAppSteps.verifyHomepageToStoreDetails();
            wineAppSteps.verifyStoreDetailsStoreHours();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
