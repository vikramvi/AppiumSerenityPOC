package com.serenity.appium.poc.tests;

import com.serenity.appium.poc.WineAppTest;
import com.serenity.appium.poc.utils.FindStore;
import com.serenity.appium.poc.utils.Regression1;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class DOT_28678 extends WineAppTest {

    @Test
    @Category({Regression1.class, FindStore.class})
    public void DOT_28678_verifyListWithMultipleItems(){
        appSteps.completeQuickOnboarding();
        String emailId = "twemailwith35items@yopmail.com";
        appSteps.performLoginFromHomepage(emailId);

        appSteps.verifyLongListWithItemsMoreThanThirty();
    }
}
