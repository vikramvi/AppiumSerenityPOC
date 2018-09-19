package com.serenity.appium.poc.tests;

import com.serenity.appium.poc.WineAppTest;
import com.serenity.appium.poc.utils.Regression1;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class DOT_27707 extends WineAppTest{

    @Test
    @Category({Regression1.class})
    public void DOT_27707_verifyDeleteShoppingList(){
        appSteps.completeQuickOnboarding();

        String emailId = "jphtest7@yopmail.com";
        appSteps.performLoginFromHomepage(emailId);

        appSteps.gotoHomeTab();
        String listName = "create_list_and_delete";

        appSteps.verifyListDeletionActions(listName);

    }
}
