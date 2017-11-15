package com.serenity.appium.poc.cucumber;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.thucydides.core.annotations.Steps;

import com.serenity.appium.poc.serenity.WineAppSteps;

public class WineAppScenarioSteps {
    @Steps
    WineAppSteps wineAppSteps;

    @Given("User completes splash screen action")
    public void completeSplashScreenActions(){
        wineAppSteps.completeQuickOnboarding();
    }

    @When("User enters valid wine name")
    public void enterValidWineName(){
        wineAppSteps.doWineSearch();
    }

    @When("User selects wine name from search result")
    public void selectWineNameFromSearchResults(){
        wineAppSteps.selectWineNameFromSearchResultView();
    }

    @Then("User can do sort action")
    public void performSortAction(){
        wineAppSteps.performSortAction();
    }

}
