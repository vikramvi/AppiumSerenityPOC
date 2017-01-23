package com.serenity.appium.poc.ios;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Managed;

import org.junit.Test;
import org.junit.runner.RunWith;

import org.openqa.selenium.WebDriver;

import org.junit.AfterClass;
import org.junit.BeforeClass;

import static com.serenity.appium.poc.AppiumServerVikram.startAppiumServer;
import static com.serenity.appium.poc.AppiumServerVikram.stopAppiumServer;

import com.serenity.appium.poc.cucumber_related.SauceiOSAppSteps;

@RunWith(SerenityRunner.class)
public class SauceiOSAppTest {

    @Managed 
    WebDriver appiumDriver;
    
    @Steps
    public SauceiOSAppSteps iOSsteps;
    
    @BeforeClass
    public static void startAppium() {
        startAppiumServer();
    }

    @AfterClass
    public static void stopAppium() {
        stopAppiumServer();
    }
    
    @Test
    public void verifySauceiOSAppTest(){
	iOSsteps.enterEmailID();
    }
    
}
