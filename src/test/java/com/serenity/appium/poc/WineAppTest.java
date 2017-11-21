package com.serenity.appium.poc;

import com.serenity.appium.poc.pages.MobilePageObject;
import com.serenity.appium.poc.utils.FindStore;
import com.serenity.appium.poc.utils.Properties;
import com.serenity.appium.poc.utils.Regression1;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Managed;

import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.junit.BeforeClass;
import org.junit.AfterClass;

import org.openqa.selenium.WebDriver;

import com.serenity.appium.poc.serenity.WineAppSteps;

@RunWith(SerenityRunner.class)
public class WineAppTest {

    @Managed(uniqueSession = false)
    public WebDriver webdriver;

    @Steps
    public WineAppSteps wineAppSteps;

    @BeforeClass
    public static void startAppium() {
        //startAppiumServer();

        //NOTE: the following can only be used if the platform is passed in as a MVN argument (e.g. clean verify test -e -DtestEnvironment=iOS -Dmaven.surefire.debug)
        String platform = System.getProperty("testEnvironment");
        switch (platform) {
            case ("Android"):
                MobilePageObject.setAndroid(true);
                MobilePageObject.setIOS(false);
                break;
            case ("iOS"):
                MobilePageObject.setIOS(true);
                MobilePageObject.setAndroid(false);
                break;
            default:
                try {
                    throw new IllegalAccessException("No match for plaform!");
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
        }
    }

    @AfterClass
    public static void stopAppium() {
        //stopAppiumServer();
    }

//    @Test
//    public void verifyWineAppSearchByValidWineNameAndSortActions(){
//        try{
//            wineAppSteps.completeQuickOnboarding();
//            wineAppSteps.initiateProductSearch();
//            wineAppSteps.selectProductNameFromSearchSuggestions();
//            wineAppSteps.verifySearchResultCount(1);
//            wineAppSteps.performSortAction();
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//    }
//    @Test //DOT-25134
//    @Category({Regression1.class, FindStore.class})
//    public void verifyClosestStoreOnHomepage(){
//        Properties properties = new Properties();
//        String expectedTitle = "BOYNTON BEACH";
//        String expectedLocation = "Boynton Town Center";
//        if (properties.isSauceLabsRun()) {
//            expectedTitle = "FREMONT";
//            expectedLocation = "Pacific Commons";
//        }
//        try{
//            wineAppSteps.completeOnboardingAllowingLocation();
//            wineAppSteps.verifyHomepageStoreDetails(expectedTitle, expectedLocation);
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//    }
    @Test //DOT-25137
    @Category({Regression1.class, FindStore.class})
    public void verifyClosestStoreOnHomepage(){
        Properties properties = new Properties();
        String expectedTitle = "BOYNTON BEACH";
        String expectedLocation = "Boynton Town Center";
        if (properties.isSauceLabsRun()) {
            expectedTitle = "FREMONT";
            expectedLocation = "Pacific Commons";
        }
        try{
            wineAppSteps.completeOnboardingAllowingLocation();
            wineAppSteps.verifyChangeStoreLookupOption();
            wineAppSteps.verifySelectNewStore();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
//    @Test //DOT-25238
//    @Category({Regression1.class, FindStore.class})
//    public void verifyDefaultStoreOnHomescreen(){
//        try{
//            wineAppSteps.completeQuickOnboarding();
//            wineAppSteps.verifyHomepageStoreDetails("SACRAMENTO (ARDEN)", "Sacramento- Arden Way and Howe Ave.");
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//    }
//    @Test //DOT-25239
//    @Category({Regression1.class, FindStore.class})
//    public void verifyOptionToChangeStores(){
//        try{
//            wineAppSteps.completeQuickOnboarding();
//            wineAppSteps.verifyChangeStoreLookupOption();
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//    }
}
