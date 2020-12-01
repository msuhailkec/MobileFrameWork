package com.pom;




import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.support.PageFactory;


import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class LoginPOM {
	
	private AndroidDriver<AndroidElement> driver; 
	
	public LoginPOM(AndroidDriver<AndroidElement> driver) {
		this.driver = driver; 
		//PageFactory.initElements(new AppiumFieldDecorator(driver), this);
		PageFactory.initElements(new AppiumFieldDecorator(driver,Duration.ofSeconds(30)), this);
	}
	@AndroidFindBy(xpath="//*[@text='Dismiss']")
	private AndroidElement removeToast;
	
	
	@AndroidFindBy(xpath="//*[@text='Invalid password']")
	private AndroidElement invalid;
	 
	
	@AndroidFindBy(xpath="//*[@text='Enter an e-mail address or username']")
	private AndroidElement userName;
	
	@AndroidFindBy(xpath="//*[@text='Password']")
	private AndroidElement password;
	
	@AndroidFindBy(xpath="//*[@text='Sign in']")
	private AndroidElement loginoption; 
	
	@AndroidFindBy(accessibility="Sign in")
	private AndroidElement signinBtn; 
	
	@AndroidFindBy(xpath="//android.widget.ImageView[@content-desc='Settings']")
	private AndroidElement settings; 
	
	@AndroidFindBy(xpath="//*[@text='msuhail.kec']")
	private AndroidElement userID;
	
	@AndroidFindBy(xpath="//*[@text='Sign out']")
	private AndroidElement signout;
	
	public void sendUserName(String userName) {
		this.userName.clear();
		this.userName.sendKeys(userName);
	}
	
	public void sendPassword(String password) {
		this.password.clear(); 
		this.password.sendKeys(password); 
	}
	
	public void clickLoginOption() {
		this.loginoption.click(); 
	}
	public void clickDismiss() {
		this.removeToast.click(); 
		
	}
	
	public void clickSignIn() {
		this.signinBtn.click(); 
		
	}
	
	public void clickSignOut()
	{
		signout.click();
	}
	
	
	
	public boolean invalidLogin()
	{
		return invalid.getText().equalsIgnoreCase("Invalid password");
	}
	
	public boolean userLogedIn()
	{
		
		settings.click();
		if(userID.isDisplayed())
			return true;
		return false;
		
	}
	
	public boolean successfullLogout()
	{
		
		if(signout.isDisplayed())
			return true;
		else
			return false;
		
	}
}
