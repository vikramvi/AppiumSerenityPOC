package com.serenity.appium.poc;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Managed;

import org.junit.Test;
import org.junit.runner.RunWith;

import org.openqa.selenium.WebDriver;

import com.serenity.appium.poc.cucumber_related.FlipkartLoginSteps;

import org.junit.AfterClass;
import org.junit.BeforeClass;

import static com.serenity.appium.poc.AppiumServerActions.startAppiumServer;
import static com.serenity.appium.poc.AppiumServerActions.stopAppiumServer;

@RunWith(SerenityRunner.class)
public class FlipkartAppSingleDeviceRunIT {
    @Managed 
    WebDriver appiumDriver;
    
    @Steps
    public FlipkartLoginSteps userSteps;
     
    @BeforeClass
    public static void startAppium() {
        startAppiumServer();
    }

    @AfterClass
    public static void stopAppium() {
        stopAppiumServer();
    }
     
    //Single device run POC
    @Test
    public void singleDeviceTestCase(){
    	try{        	
            	userSteps.loginPageInvalidDataInput();    
            	userSteps.enterLoginData("single device");
            	userSteps.checkErrorMessage();
    	}catch(Exception e){	    
    	        e.printStackTrace();
    	}
    }
}
