package com.serenity.appium.poc.tests;

import com.serenity.appium.poc.WineAppTest;
import com.serenity.appium.poc.utils.Regression1;
import com.serenity.appium.poc.utils.Utils;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class verifyToastMessageOnProfileScreen extends WineAppTest {

    @Test
    @Category({Regression1.class})
    public void verifyToastMessage() {
        appSteps.completeQuickOnboarding();
//        String emailId = "jphtest@yopmail.com";
        appSteps.performLoginFromHomepage(Utils.getRandomLoginId());
        appSteps.verifyToastMessageOnProfilePage();
    }

}
