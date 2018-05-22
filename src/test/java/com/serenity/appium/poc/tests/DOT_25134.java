package com.serenity.appium.poc.tests;

import com.serenity.appium.poc.WineAppTest;
import com.serenity.appium.poc.serenity.WineAppSteps;
import com.serenity.appium.poc.utils.FindStore;
import com.serenity.appium.poc.utils.Properties;
import com.serenity.appium.poc.utils.Regression1;
import com.serenity.appium.poc.utils.Utils;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;

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
            wineAppSteps.completeOnboardingAllowingLocation();
            wineAppSteps.verifyHomepageStoreDetails(expectedTitle);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
