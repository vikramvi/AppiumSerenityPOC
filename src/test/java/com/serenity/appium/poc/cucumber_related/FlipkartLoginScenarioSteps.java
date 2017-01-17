package com.serenity.appium.poc.cucumber_related;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.thucydides.core.annotations.Steps;

import com.serenity.appium.poc.cucumber_related.FlipkartLoginSteps;

public class FlipkartLoginScenarioSteps {
    
        @Steps
        FlipkartLoginSteps  loginSteps;
	
	@Given("User is on loing page")
	public void gotoLoginPage(){
	    loginSteps.loginPageInvalidDataInput();
	}
        
	@When("Enter invalid credentials")
	public void enterInvalidData(){
	    loginSteps.enterLoginData("cucumber invalid step");
	}
	
	@Then("User is shown error message")
	public void checkErrorMessage(){
	    loginSteps.checkErrorMessage();
	}
	
}
