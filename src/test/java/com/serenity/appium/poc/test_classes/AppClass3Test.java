package com.serenity.appium.poc.test_classes;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Managed;

import org.junit.Test;
import org.junit.runner.RunWith;

import org.openqa.selenium.WebDriver;

import com.serenity.appium.poc.cucumber_related.FlipkartLoginSteps;

@RunWith(SerenityRunner.class)
public class AppClass3Test {
    @Managed 
    WebDriver appiumDriver;
    
    @Steps
    public FlipkartLoginSteps userSteps;
    
    @Test
    public void verifyClass3_InvalidLogin(){
    	try{        	
            	userSteps.loginPageInvalidDataInput();    
            	userSteps.enterLoginData("Class3-123456");
            	userSteps.checkErrorMessage();
    	}catch(Exception e){	    
    	        e.printStackTrace();
    	}
    }
    
    @Test
    public void verifyClass3_YahooInvalid(){
    	try{        	
            	userSteps.loginPageInvalidDataInput();    
            	userSteps.enterLoginData("Class3-Yahoo");
            	userSteps.checkErrorMessage();
    	}catch(Exception e){	    
    	        e.printStackTrace();
    	}
    }
      
    @Test
    public void verifyClass3_GmailInvalid(){
    	try{        	
            	userSteps.loginPageInvalidDataInput();    
            	userSteps.enterLoginData("Class3-Gmail");
            	userSteps.checkErrorMessage();
    	}catch(Exception e){	    
    	        e.printStackTrace();
    	}
    }
    
}
