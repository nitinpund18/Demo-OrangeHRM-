package com.orangehrmlive.performance;

import java.time.Duration;
import java.time.Period;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PerformancePage 
{
	@FindBy (xpath="//*[@id='top-ribbon-menu']/div[3]/top-level-menu-item/div/a") private WebElement moreTab;
	@FindBy (xpath="(//*[@id='top-ribbon-menu']//div[2]/a)[3]") private WebElement goalsTab;
	@FindBy (xpath="//*[text()='My Goals']") private WebElement myGoalTab;
	@FindBy (xpath="//*[@id='top-ribbon-menu']/div[1]/div/a/i") private WebElement homebtn;	
	
	public PerformancePage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	public void clickMoreTab()
	{	
		moreTab.click();
	}
	public void clickMyGoalsTab(WebDriver driver)
	{	
		Actions act = new Actions(driver);
		act.moveToElement(goalsTab);
		moreTab.click();
	}
	public void clickHomeBtn()
	{	
		homebtn.click();
	}
}
