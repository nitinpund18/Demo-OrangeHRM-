package com.orangehrmlive.training;

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

public class TrainingPage 
{
	@FindBy (css ="#list_item_add") private WebElement addbtn;
	@FindBy (xpath="//*[@id='addCourse_title']") private WebElement title;
	@FindBy (xpath="//*[@id='addCourse_company']") private WebElement company;
	@FindBy (xpath="//*[@id='addCourse_coordinator_empName']") private WebElement coordinator;
	@FindBy (xpath="//*[@id='frmAddCourse']/div[2]/div[1]/div/input") private WebElement subunit;
	@FindBy (xpath="//*[@id='btnSaveCourse']") private WebElement savebtn;
	@FindBy (xpath="//*[@id=\"top-ribbon-menu\"]/div/div[2]/a/i") private WebElement backArrow;
	@FindBy (xpath="//*[@class='highlight bordered']//tr/td[2]/a") private List<WebElement> TableSkills;
	@FindBy (xpath="(//*[@class='autoComplete-title'])[1]") private WebElement firstSuggetion;

	public TrainingPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	public void clickAddBtn(WebDriver driver)
	{	
		driver.switchTo().frame("noncoreIframe");
		addbtn.click();
	}
	public void setNewTrainingDetails(WebDriver driver, String titleValue, String CoordiName, String companyName)
	{
		title.sendKeys(titleValue);
		coordinator.sendKeys(CoordiName); 
		firstSuggetion.click();
		company.sendKeys(companyName);		
		Actions act = new Actions(driver);
		act.click(subunit).perform();
		act.sendKeys(Keys.ARROW_DOWN).perform();
		act.sendKeys(Keys.ARROW_DOWN).perform();
		act.sendKeys(Keys.ARROW_DOWN).perform();
		act.sendKeys(Keys.ENTER).perform();		
	}
	public void clickSavebtn() 
	{
		savebtn.click();
	}
	public void clickBackArrow()
	{
		backArrow.click();
	}
	public String getNewlyAddedSkill(String expSkill, WebDriver driver)
	{
		String flag="";
		WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOfAllElements(TableSkills));
		for(WebElement skill:TableSkills)
		{
			String actSkill = skill.getText();
			if(actSkill.equals(expSkill))
			{
				flag=actSkill;
			}
		}
		return flag;
	}
}
