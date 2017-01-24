package com.serenity.appium.poc.ios;

import net.thucydides.core.pages.PageObject;
import net.thucydides.core.webdriver.WebDriverFacade;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.WebElement;

import io.appium.java_client.MobileElement;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import io.appium.java_client.pagefactory.iOSFindBy;

public class SauceiOSAppPage extends PageObject{

    @FindBy(xpath="//XCUIElementTypeApplication[1]//XCUIElementTypeTextField[contains(@name,'fbemail')]")
    private WebElement emailTextField;
    
    //@iOSFindBy(xpath="//XCUIElementTypeApplication[1]//XCUIElementTypeTextField[contains(@name,'fbemail')]")
    //private MobileElement emailTextField; 
    
    //https://github.com/serenity-bdd/serenity-core/issues/660
    //http://appium.github.io/java-client/io/appium/java_client/ios/IOSDriver.html#method.summary
    public IOSDriver<IOSElement> iOSDriver() {
	return (IOSDriver<IOSElement>) ((WebDriverFacade) getDriver()).getProxiedDriver();
   }
    
    public void inputEmail(){
	if(element(emailTextField).isDisplayed()){
        	element(emailTextField).type("iOS@apple.net");
        	//element(emailTextField).sendKeys(Keys.RETURN);	 not working on emulator
        	//getDriver().hideKeyboard(); ??
        	iOSDriver().hideKeyboard();
        	System.out.println("test");
	}else{
	        System.out.println("test");
	}
    }
    
}
