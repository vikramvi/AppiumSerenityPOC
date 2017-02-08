package com.serenity.appium.poc.cucumber_related;

import com.serenity.appium.poc.FlipkartLoginPage;

import net.thucydides.core.steps.ScenarioSteps;
import net.thucydides.core.annotations.Step;

import static org.assertj.core.api.Assertions.assertThat;

public class FlipkartLoginSteps extends ScenarioSteps{
      private static FlipkartLoginPage loginPage;
       
     @Step
     public void loginPageInvalidDataInput(){
	 assertThat( loginPage.gotoLoginPage() ).isTrue();
     }
     
     @Step
     public void enterLoginData(String input){
	 loginPage.enterInvalidCredentials(input);
     }
    
     @Step
     public void checkErrorMessage(){
	 assertThat( loginPage.isErrorMessageShown() ).isTrue();
     }
     
}