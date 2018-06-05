package com.serenity.appium.poc.tests;

import com.serenity.appium.poc.WineAppTest;
import com.serenity.appium.poc.utils.FindStore;
import com.serenity.appium.poc.utils.Properties;
import com.serenity.appium.poc.utils.Regression1;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class DOT_25134 extends WineAppTest {

    @Test // verified on iOS, Android on 5/22/18
    @Category({Regression1.class, FindStore.class})
    public void DOT_25134_verifyClosestStoreOnHomepage(){
        Properties properties = new Properties();
        String expectedTitle = "BOYNTON BEACH";
        if (properties.isSauceLabsRun()) {
            expectedTitle = "FREMONT";
        }
        try{
            appSteps.completeOnboardingAllowingLocation();
            appSteps.verifyHomepageStoreDetails(expectedTitle);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
