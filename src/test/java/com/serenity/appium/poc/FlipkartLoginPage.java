package com.serenity.appium.poc;

import net.thucydides.core.pages.PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.WebElement;

public class FlipkartLoginPage extends PageObject {
   
     @FindBy(id="com.flipkart.android:id/btn_mlogin")
     private WebElement existingUsersignIn;
     
     @FindBy(id="com.flipkart.android:id/mobileNo")
     private WebElement userId;
     
     @FindBy(id="com.flipkart.android:id/et_password")
     private WebElement password;
     
     @FindBy(id="com.flipkart.android:id/btn_mlogin")
     private WebElement login_Button;
     
     @FindBy(id="com.flipkart.android:id/pageLevelError")
     private WebElement error_text;
     
    
    public void gotoLoginPage(){
      try{
        	WebDriverWait wait = new WebDriverWait(getDriver(), 60);
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.flipkart.android:id/btn_mlogin")) );
                if( element(existingUsersignIn).isDisplayed() ){
                     element(existingUsersignIn).click();               
                }else{
                    System.out.println("SIGN IN LINK MISSING");
                }
      }catch(Exception e){
	  e.printStackTrace();
      }
    }
    
    public void enterInvalidCredentials(String input){
	if( element(userId).isDisplayed() ){
            element(userId).sendKeys(input);
            element(password).sendKeys("invalidPwd");       
            element(login_Button).click();
	}else{
	    System.out.println("EMAIL INPUT FIELD IS MISSING");
	}
    }
        
    public boolean isErrorMessageShown(){
	WebDriverWait wait = new WebDriverWait(getDriver(), 60);
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.flipkart.android:id/pageLevelError")) );
        return element(error_text).getText().contentEquals("Please enter valid Email ID/Mobile Number");
    }
    
}
