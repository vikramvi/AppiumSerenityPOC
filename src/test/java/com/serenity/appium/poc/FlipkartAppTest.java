package com.serenity.appium.poc;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Managed;

import org.junit.Test;
import org.junit.runner.RunWith;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebDriver;

import com.serenity.appium.poc.cucumber_related.FlipkartLoginSteps;

import org.junit.AfterClass;
import org.junit.BeforeClass;

import com.serenity.appium.poc.AppiumGridSetup;

@RunWith(SerenityRunner.class)
public class FlipkartAppTest{
    
    @Managed 
    WebDriver appiumDriver;
    
    @Steps
    public FlipkartLoginSteps userSteps;
         
    /*static FlipkartAppParallelTest mobileGrid = new FlipkartAppParallelTest();
    
    @BeforeClass
    public static void startAppium() {
	mobileGrid.mobileGridSetup();
    }

    @AfterClass
    public static void stopAppium() {
	mobileGrid.mobileGridTearDown();
    }*/
     
    //Selenium Grid setup POC - parallel run on multiple devices 
    @Test
    public void verifyInvalidLogin(){
    	try{        	
            	userSteps.loginPageInvalidDataInput();    
            	userSteps.enterLoginData("123456");
            	userSteps.checkErrorMessage();
    	}catch(Exception e){	    
    	        e.printStackTrace();
    	}
    }
    
    @Test
    public void verifyYahooInvalid(){
    	try{        	
            	userSteps.loginPageInvalidDataInput();    
            	userSteps.enterLoginData("Yahoo");
            	userSteps.checkErrorMessage();
    	}catch(Exception e){	    
    	        e.printStackTrace();
    	}
    }
      
    @Test
    public void verifyGmailInvalid(){
    	try{        	
            	userSteps.loginPageInvalidDataInput();    
            	userSteps.enterLoginData("Gmail");
            	userSteps.checkErrorMessage();
    	}catch(Exception e){	    
    	        e.printStackTrace();
    	}
    }
    
}

