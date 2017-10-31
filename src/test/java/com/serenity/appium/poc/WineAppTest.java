package com.serenity.appium.poc;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Managed;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.BeforeClass;
import org.junit.AfterClass;

import org.openqa.selenium.WebDriver;

import com.serenity.appium.poc.serenity.WordPressLoginSteps;
import com.serenity.appium.poc.serenity.WineAppSteps;

import static com.serenity.appium.poc.AppiumServerController.startAppiumServer;
import static com.serenity.appium.poc.AppiumServerController.stopAppiumServer;

@RunWith(SerenityRunner.class)
public class WineAppTest {

    @Managed(uniqueSession = false)
    public WebDriver webdriver;

    @Steps
    public WineAppSteps wineAppSteps;

    @BeforeClass
    public static void startAppium() {
        //startAppiumServer();
    }

    @AfterClass
    public static void stopAppium() {
        //stopAppiumServer();
    }

    @Test
    public void verifyWineAppSearchByValidWineNameAndSortActions(){
        try{
            wineAppSteps.completeSplashScreenChecks();
            wineAppSteps.doWineSearch();
            wineAppSteps.selectWineNameFromSearchResultView();
            wineAppSteps.performSortAction();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
