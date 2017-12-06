package com.serenity.appium.poc.pages;

import io.appium.java_client.TouchAction;
import io.appium.java_client.ios.IOSDriver;
import net.thucydides.core.ThucydidesSystemProperty;
import net.thucydides.core.util.EnvironmentVariables;
import net.thucydides.core.util.SystemEnvironmentVariables;
import net.thucydides.core.webdriver.WebDriverFacade;
import org.openqa.selenium.*;
import io.appium.java_client.pagefactory.*;

import java.util.HashMap;
import java.util.List;

public class WineAppPageObject extends MobilePageObject{

    @AndroidFindBy(xpath="//android.widget.Button[@content-desc='button-lets-begin']")
    @iOSFindBy(xpath="//XCUIElementTypeButton[@name='button-lets-begin']")
    private WebElement LetsBeginButton;

    @AndroidFindBy(xpath="//android.widget.Button[@content-desc='FIND MY STORE']")
    @iOSFindBy(xpath="//XCUIElementTypeButton[@name='FIND MY STORE']")
    private WebElement AllowLocationButton;

    @AndroidFindBy(xpath="//android.widget.TextView[@text='not right now']")
    @iOSFindBy(xpath="//XCUIElementTypeOther[@name='not right now']")
    private WebElement not_right_now_link;

    @AndroidFindBy(xpath="//android.widget.TextView[@text='no thanks']")
    @iOSFindBy(xpath="//XCUIElementTypeOther[@name='no thanks']")
    private WebElement no_thanks_link;

    @AndroidFindBy(xpath="//android.widget.TextView[@text='WHAT CAN WE HELP YOU FIND?']")
    @iOSFindBy(xpath="//XCUIElementTypeOther[@name='WHAT CAN WE HELP YOU FIND?']")
    private WebElement WhatCanWeHelpYouFindInputTextField;

    @AndroidFindBy(xpath="//android.widget.EditText[@text='WHAT CAN WE HELP YOU FIND?']")
    @iOSFindBy(xpath="//XCUIElementTypeOther[contains(@name,'WHAT CAN WE HELP YOU FIND?')]")
    private WebElement WhatCanWeHelpYouFindInputTextSearchField;

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

    @iOSFindBy(xpath="//XCUIElementTypeButton[@label='ADD TO CART']")
    private WebElement AddToCartButton;

    @iOSFindBy(xpath="//XCUIElementTypeButton[@label='VIEW CART']")
    private WebElement ViewCartButton;

    @iOSFindBy(xpath="//XCUIElementTypeOther/XCUIElementTypeScrollView/XCUIElementTypeOther/XCUIElementTypeOther[contains(@name,\"$\") and contains(@name,\"ML\") and contains(@name,\"$\") and contains(@name,\"ML\") ]")
    private WebElement itemsViewTopRow;


    public WineAppPageObject(WebDriver driver) {
        super(driver);
    }

    String appiumDriverType, iOSDeviceType;

    public void getSerenityPropertiesValues(){
        EnvironmentVariables variables = SystemEnvironmentVariables.createEnvironmentVariables();

        String baseUrl = variables.getProperty(ThucydidesSystemProperty.WEBDRIVER_BASE_URL);
        appiumDriverType = variables.getProperty("appium.platformName");
        iOSDeviceType    = variables.getProperty("appium.deviceName");
    }

    //TBD
    //How To Achieve The Best Lookup Performance
    //https://github.com/facebook/WebDriverAgent/wiki/How-To-Achieve-The-Best-Lookup-Performance
    public boolean completeSpalshScreenActions(){

        getSerenityPropertiesValues();

        try{
            LetsBeginButton.click();
            AllowLocationButton.click();

            boolean isAlertPresent = true;

            //if(getDriver().findElements(By.xpath("//XCUIElementTypeAlert[contains(@name,'access your location while you are using the app')]")).size() > 0 && isAlertPresent ) {

            if(appiumDriverType.equals("Android")){
                if(getDriver().findElement(By.xpath("//android.widget.Button[@text='Allow']")).isDisplayed()) {
                    getDriver().findElement(By.xpath("//android.widget.Button[@text='Allow']")).click();
                }
            }
            else if(appiumDriverType.equals("iOS")) {
                try {
                    Thread.sleep(3000);
                    JavascriptExecutor js = (JavascriptExecutor) getDriver();

                    HashMap<String, String> tapObject = new HashMap<String, String>();
                    tapObject.put("action", "accept");
                    tapObject.put("label", "Allow");

                    js.executeScript("mobile:alert", tapObject);
                } catch (Exception e) {
                    System.out.println("caught");
                }

                isAlertPresent = false;
            }
            //}

            //if(getDriver().findElements(By.id("com.android.packageinstaller:id/permission_allow_button")).size() > 0 && isAlertPresent ) {
              //  getDriver().findElement(By.id("com.android.packageinstaller:id/permission_allow_button")).click();
            //}

            not_right_now_link.click();
            no_thanks_link.click();
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean performSearchActionWithValidWineName(String winName){
        try{
            WhatCanWeHelpYouFindInputTextField.click();
            WhatCanWeHelpYouFindInputTextSearchField.sendKeys(winName);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean selectWineNameFromSearchResults(String winName){
        try{
            getDriver().findElement(By.xpath("//*[@*='"+winName +"']")).click();
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean performSortActionByHighPrice(){
        try{
            SortButton.click();
            //SortByPriceHighestFirstOption.click();

            getDriver().findElements(By.xpath("//XCUIElementTypeOther[contains(@name,'Name (Z-A)')]")).get(13).click();

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

    public boolean addAllItemsShownToUser_OnScreenForParticularSearchTermToCart(){
        try{

            if(appiumDriverType.equals("iOS")){
                return iOS_addAllItemsShownToUser_OnScreenForParticularSearchTermToCart();
            }else {

                //tapTopRowLeftSideItem
                //tapTopRowRightSideItem
                //tapBottomRowLeftSideItem
                //tapBottomRowLeftSideItem
                //get string for first row, extract 2 names for comparison

                //Android

                List<WebElement> wineBarnds = getDriver().findElements(By.xpath("//android.widget.TextView[2]"));

                int counter = 0;

                for (WebElement element : wineBarnds) {
                    //    if( element.getText().equals(wineBrandName) ){
                    element.click();
                    //  }
                    counter++;
                }

                return true;
            }
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }


    //Works fine with iPhone 7, iPhone SE, iPhone X, iPhone 8 Plus
    public boolean iOS_addAllItemsShownToUser_OnScreenForParticularSearchTermToCart(){
        try{

            Dimension mobileDimension = getDriver().manage().window().getSize();
            int xMobileCoordinate = mobileDimension.getWidth();
            int yMobileCoordinate = mobileDimension.getHeight();


            Point elementToClick = getDriver().findElement(By.xpath("//XCUIElementTypeOther/XCUIElementTypeScrollView/XCUIElementTypeOther/XCUIElementTypeOther[contains(@name,\"$\") and contains(@name,\"ML\") and contains(@name,\"$\") and contains(@name,\"ML\") ]")).getLocation();
            int xElementCoordinate = mobileDimension.getWidth();
            int yElementCoordinate = mobileDimension.getHeight();


            //TouchAction tapCoordinates = new TouchAction((MobileDriver)getDriver());
            TouchAction tapCoordinates = new TouchAction(((IOSDriver)((WebDriverFacade) getDriver()).getProxiedDriver()));

            //1st Item 1t row
            tapCoordinates.tap(xMobileCoordinate/4, yMobileCoordinate/3).perform();
            AddToCartButton.click();
            ViewCartButton.click();

            //Bug add same button from other screens
            //Refer to Add To Cart Page expected value : //XCUIElementTypeButton[@name="button-floating-return"]
            //Workaround click with co-ordinates on back button
            if(getDriver().findElements(By.xpath("//XCUIElementTypeStaticText[@name=\"ADD A COUPON\"]")).size() > 0) {

                if(!iOSDeviceType.equals("iPhone X")) {
                    tapCoordinates.tap(xMobileCoordinate / 9, (yMobileCoordinate / 11) * 10).perform();
                }else {
                    tapCoordinates.tap(40, 755).perform();
                }

            }else{
                System.out.println("FAILED");
            }



            //2nd Item 1st row
            if(itemsViewTopRow.isDisplayed()) {
                tapCoordinates.tap(xMobileCoordinate * 3 / 4, yMobileCoordinate / 3).perform();
                AddToCartButton.click();
                ViewCartButton.click();
                //Bug add same button from other screens
                if (getDriver().findElements(By.xpath("//XCUIElementTypeStaticText[@name=\"ADD A COUPON\"]")).size() > 0) {

                    if(!iOSDeviceType.equals("iPhone X")) {
                        tapCoordinates.tap(xMobileCoordinate / 9, (yMobileCoordinate / 11) * 10).perform();
                    }else {
                        tapCoordinates.tap(42, 755).perform();
                    }

                } else {
                    System.out.println("FAILED - 2");
                }
            }else{
                System.out.println("FAILED - 2");
            }

            //1st Item 2nd row
            if(itemsViewTopRow.isDisplayed()) {
                tapCoordinates.tap(xMobileCoordinate / 4, yMobileCoordinate * 2 / 3).perform();
                AddToCartButton.click();
                ViewCartButton.click();
                //Bug add same button from other screens
                if (getDriver().findElements(By.xpath("//XCUIElementTypeStaticText[@name=\"ADD A COUPON\"]")).size() > 0) {

                    if(!iOSDeviceType.equals("iPhone X")) {
                        tapCoordinates.tap(xMobileCoordinate / 9, (yMobileCoordinate / 11) * 10).perform();
                    }else {
                        tapCoordinates.tap(42, 755).perform();
                    }

                } else {
                    System.out.println("FAILED");
                }
            }else{
                System.out.println("FAILED - 2");
            }

            //2nd Item 2nd row
            if(itemsViewTopRow.isDisplayed()) {
                tapCoordinates.tap(xMobileCoordinate * 3 / 4, yMobileCoordinate * 2 / 3).perform();
                AddToCartButton.click();
                ViewCartButton.click();
                //Bug add same button from other screens
                if (getDriver().findElements(By.xpath("//XCUIElementTypeStaticText[@name=\"ADD A COUPON\"]")).size() > 0) {

                    if(!iOSDeviceType.equals("iPhone X")) {
                        tapCoordinates.tap(xMobileCoordinate / 9, (yMobileCoordinate / 11) * 10).perform();
                    }else {
                        tapCoordinates.tap(42, 755).perform();
                    }
                } else {
                    System.out.println("FAILED");
                }
            }else{
                System.out.println("FAILED - 2");
            }

            return true;

        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

}
