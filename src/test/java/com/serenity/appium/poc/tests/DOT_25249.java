package com.serenity.appium.poc.tests;

import com.serenity.appium.poc.WineAppTest;
import com.serenity.appium.poc.utils.FindStore;
import com.serenity.appium.poc.utils.Regression1;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class DOT_25249 extends WineAppTest {

    @Test // verified on iOS, Android on 5/22/18
    @Category({Regression1.class, FindStore.class})
    public void DOT_25249_verifySpiritsHours(){
        try{
            wineAppSteps.completeQuickOnboarding();
            wineAppSteps.verifyChangeStoreLookupOption();
            wineAppSteps.verifySelectStoreFromSearchResults("Greenville", "Greenville");
            wineAppSteps.verifyShowSpiritsHours();
            wineAppSteps.verifySpiritsHours();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
