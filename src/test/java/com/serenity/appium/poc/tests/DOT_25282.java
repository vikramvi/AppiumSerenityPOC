package com.serenity.appium.poc.tests;

import com.serenity.appium.poc.WineAppTest;
import com.serenity.appium.poc.utils.Regression1;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.util.Arrays;
import java.util.List;

public class DOT_25282 extends WineAppTest {

    @Test
    @Category({Regression1.class})
    public void DOT_25282_verifySearchRefinement_DepartmentsCategoriesSubcategofies(){
        String searchTerm = "Beer";
        String secondLevelFilter = "Style";
        List<String> thirdLevelFilters = Arrays.asList("American Amber/Red Ale", "American Double/Imperial IPA");

        appSteps.completeQuickOnboarding();
        appSteps.verifyHomepageToProductSearch();
        appSteps.searchForProduct(searchTerm);
        appSteps.verifyFilterSecondAndThirdLevelSelections(searchTerm, secondLevelFilter, thirdLevelFilters);
    }

}
