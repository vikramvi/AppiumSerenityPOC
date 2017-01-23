package com.serenity.appium.poc.ios;

import net.thucydides.core.pages.PageObject;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.WebElement;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.iOSFindBy;

public class SauceiOSAppPage extends PageObject{

    @FindBy(xpath="//XCUIElementTypeApplication[1]//XCUIElementTypeTextField[contains(@name,'fbemail')]")
    private WebElement emailTextField;
    
    //@iOSFindBy(xpath="//XCUIElementTypeApplication[1]//XCUIElementTypeTextField[contains(@name,'fbemail')]")
    //private MobileElement emailTextField; 
    
    public void inputEmail(){
	if(element(emailTextField).isDisplayed()){
	element(emailTextField).type("iOS@apple.net");
	//element(emailTextField).sendKeys(Keys.RETURN);	 not working on emulator
	//getDriver().hideKeyboard(); ??
	System.out.println("test");
	}else{
	    System.out.println("test");
	}
    }
    
}
