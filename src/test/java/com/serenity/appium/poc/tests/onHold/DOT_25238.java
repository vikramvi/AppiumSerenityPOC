package com.serenity.appium.poc.tests.onHold;

import com.serenity.appium.poc.WineAppTest;
import com.serenity.appium.poc.utils.FindStore;
import com.serenity.appium.poc.utils.Regression1;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class DOT_25238 extends WineAppTest {

    @Test // verified on iOS, Android on 5/22/18
    @Category({Regression1.class, FindStore.class})
    public void DOT_25238_verifyClosestStoreByIpAddress(){
        try{
            appSteps.completeQuickOnboarding();
            appSteps.verifyHomepageStoreDetails("North Miami");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
