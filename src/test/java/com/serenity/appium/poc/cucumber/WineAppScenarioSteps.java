package com.serenity.appium.poc.cucumber;

import com.serenity.appium.poc.serenity.AppSteps;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.thucydides.core.annotations.Steps;

public class WineAppScenarioSteps {
    @Steps
    AppSteps wineAppSteps;

    @Given("User completes splash screen action")
    public void completeSplashScreenActions(){
        wineAppSteps.completeQuickOnboarding();
    }

    @When("User enters valid wine name")
    public void enterValidWineName(){
        wineAppSteps.initiateProductSearch("Billecart Salmon");
    }

    @When("User selects wine name from search result")
    public void selectWineNameFromSearchResults(){
        wineAppSteps.selectProductNameFromSearchSuggestions("Billecart Salmon");
    }

    @Then("User can do sort action")
    public void performSortAction(){
        wineAppSteps.performSortAction();
    }

}
