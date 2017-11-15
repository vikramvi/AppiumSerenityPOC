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

import static com.serenity.appium.poc.AppiumServerController.startAppiumServer;
import static com.serenity.appium.poc.AppiumServerController.stopAppiumServer;

@RunWith(SerenityRunner.class)
public class WordPressAppTest {
    
    @Managed(uniqueSession = false)
    public WebDriver webdriver;
    
    @Steps
    public WordPressLoginSteps userSteps;
     
    @BeforeClass
    public static void startAppium() {
        startAppiumServer();
    }

    @AfterClass
    public static void stopAppium() {
        stopAppiumServer();
    }
     
    @Test
    public void verifyInvalidLogin(){
    	try{        	
            	userSteps.loginPageInvalidDataInput();    
            	userSteps.enterLoginData();
            	userSteps.checkErrorMessage();
    	}catch(Exception e){	    
    	        e.printStackTrace();
    	}
    }
      
}

