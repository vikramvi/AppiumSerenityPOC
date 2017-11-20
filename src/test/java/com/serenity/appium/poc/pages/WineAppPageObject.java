package com.serenity.appium.poc.pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import io.appium.java_client.pagefactory.*;
import org.openqa.selenium.WebDriver;

public class WineAppPageObject extends MobilePageObject{

//    @AndroidFindBy(xpath="//android.widget.Button[@content-desc='button-lets-begin']")
//    @iOSFindBy(xpath="//XCUIElementTypeButton[@name='button-lets-begin']")
//    private WebElement LetsBeginButton;
//
//    @AndroidFindBy(xpath="//android.widget.Button[@content-desc='FIND MY STORE']")
//    @iOSFindBy(xpath="//XCUIElementTypeButton[@name='FIND MY STORE']")
//    private WebElement AllowLocationButton;
//
//    @AndroidFindBy(xpath="//android.widget.TextView[@text='not right now']")
//    @iOSFindBy(xpath="//XCUIElementTypeOther[@name='not right now']")
//    private WebElement not_right_now_link;
//
//    @AndroidFindBy(xpath="//android.widget.TextView[@text='no thanks']")
//    @iOSFindBy(xpath="//XCUIElementTypeOther[@name='no thanks']")
//    private WebElement no_thanks_link;

////    @AndroidFindBy(xpath="//android.widget.TextView[@content-desc='inactive-search-bar-field']") // pending tw_mobile update
//    @AndroidFindBy(xpath="//android.widget.TextView[@text='WHAT CAN WE HELP YOU FIND?']")
//    @iOSFindBy(xpath="//XCUIElementTypeOther[@name='WHAT CAN WE HELP YOU FIND?']")
//    private WebElement WhatCanWeHelpYouFindInputTextField;
//
////    @AndroidFindBy(xpath="//android.widget.EditText[@content-desc='active-search-field']") // pending tw_mobile update
//    @AndroidFindBy(xpath="//android.widget.EditText[@text='WHAT CAN WE HELP YOU FIND?']")
//    @iOSFindBy(xpath="//XCUIElementTypeOther[contains(@name,'WHAT CAN WE HELP YOU FIND?')]")
//    private WebElement WhatCanWeHelpYouFindInputTextSearchField;

    @AndroidFindBy(xpath="//android.widget.TextView[@text='SORT']")
    @iOSFindBy(xpath="//XCUIElementTypeButton[@name='button-product-sort']")
    private WebElement SortButton;

    @AndroidFindBy(xpath="//android.widget.TextView[@text='Price (highest first)']")
    @iOSFindBy(xpath="//XCUIElementTypeOther[contains(@name,'Price (highest first)')]")
    private WebElement SortByPriceHighestFirstOption;


    //@iOSFindBy(xpath="//XCUIElementTypeOther[contains(@name,'Name (Z-A)')]")

    @AndroidFindBy(xpath="//android.widget.Button[@content-desc='button-apply']")
    @iOSFindBy(xpath="//XCUIElementTypeButton[@name='button-apply']")
    private WebElement DoneButton;


    public WineAppPageObject(WebDriver driver) {
        super(driver);
    }


//    public boolean completeOnboardingActions(){
//        try{
//            LetsBeginButton.click();
//            AllowLocationButton.click();
//
//            if(getDriver().findElements(By.id("com.android.packageinstaller:id/permission_allow_button")).size() > 0 ) {
//                getDriver().findElement(By.id("com.android.packageinstaller:id/permission_allow_button")).click();
//            }
//
//            not_right_now_link.click();
//            no_thanks_link.click();
//            return true;
//        }catch(Exception e){
//            e.printStackTrace();
//            return false;
//        }
//    }

//    public boolean performSearchActionWithValidWineName(String wineName){
//        try{
//            WhatCanWeHelpYouFindInputTextField.click();
//            WhatCanWeHelpYouFindInputTextSearchField.sendKeys(wineName);
//            return true;
//        }catch (Exception e){
//            e.printStackTrace();
//            return false;
//        }
//    }

//    public boolean selectWineNameFromSearchResults(String winName){
//        try{
//            getDriver().findElement(By.xpath("//*[@*='"+winName +"']")).click();
//            return true;
//        }catch (Exception e){
//            e.printStackTrace();
//            return false;
//        }
//    }

    public boolean performSortActionByHighPrice(){
        try{
            SortButton.click();
            //SortByPriceHighestFirstOption.click();
            if (isIOS()) {
                getDriver().findElements(By.xpath("//XCUIElementTypeOther[contains(@name,'Name (Z-A)')]")).get(13).click();
            } else {
               // WebElement x = getDriver().findElement(By.xpath("//android.view.ViewGroup[9]/android.view.ViewGroup/android.widget.TextView[2][contains(text(),'Name (Z-A)')]"));
//                WebElement x = getDriver().findElement(By.xpath("//*[text()='Name (Z-A)']"));
//                x.click();
                System.out.println(">>> Android sort element not yet identified!");
            }

            if(DoneButton.isDisplayed()) {
                DoneButton.click();
            }else{
                return false;
            }
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

}
