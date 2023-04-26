package com.orangehrmlive.login;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.orangehrmlive.util.Utility;

import dev.failsafe.internal.util.Assert;

public class LoginPage 
{
	@FindBy (xpath ="//div[@class='organization-logo shadow']/img") private WebElement logo;
	@FindBy (xpath ="//div[@class='login-form shadow']") private WebElement loginpanel;
	@FindBy (xpath ="//input[@id='txtUsername']") private WebElement username;
	@FindBy (xpath ="//input[@id='txtPassword']") private WebElement password;
	@FindBy (xpath ="//button[@type='submit']") private WebElement loginbtn;
	
	public LoginPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	public boolean logoStatus()
	{
		boolean actLogoStatus = logo.isDisplayed();
		return actLogoStatus;
	}
	public boolean LoginPanelStatus()
	{
		boolean actLoginPanelStatus = loginpanel.isDisplayed();
		return actLoginPanelStatus;
	}
	public void enterCredentials() throws IOException
	{
		
		username.sendKeys(Utility.getPropertyData("un"));
		password.sendKeys(Utility.getPropertyData("pwd"));
	}
	public void clickloginbutton()
	{
		loginbtn.click();
	}
}
