package com.serenity.appium.poc.tests.androidOnly;

import com.serenity.appium.poc.WineAppTest;
import com.serenity.appium.poc.utils.Regression1;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class DOT_25791 extends WineAppTest {

    @Test // verified on
    @Category({Regression1.class})
    public void DOT_25791_verifyPreferencesPage(){
        appSteps.completeQuickOnboarding();
        appSteps.performLoginFromHomepage();
        appSteps.verifyHomepageToPreferences();
        appSteps.verifyPreferencesInterestsContent();
        appSteps.verifyPreferencesCommunicationsContent();
        appSteps.verifyPreferencesToAccount();
    }
}
