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
	WebDriverWait wait = new WebDriverWait(getDriver(), 60);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.flipkart.android:id/btn_mlogin")) );
        element(existingUsersignIn).click();
        
        element(userId).sendKeys("dummyName");
        element(password).sendKeys("invalidPwd");
        
        element(login_Button).click();
        
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.flipkart.android:id/pageLevelError")) );
        element(error_text).getText().contentEquals("Invalid login details");
    }
    
}
