package com.orangehrmlive.qualification.skills;

import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class SkillsPage 
{
	@FindBy (xpath="//i[text()='add']") private WebElement addBtn;
	@FindBy (xpath="//*[@id='name']") private WebElement name;
	@FindBy (xpath="//textarea[@sf-changed='form']") private WebElement description;
	@FindBy (xpath="//*[text()='Save']") private WebElement okbtn;
	
	public SkillsPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	public void clickAddBtn()
	{
		addBtn.click();
	}
	public void addNewSkill(String str1, String str2)
	{
		name.sendKeys(str1);
		description.sendKeys(str2);
		okbtn.click();
	}
	public String getAddedSkill(String skillName, WebDriver driver)
	{
		WebElement skillInRecord = driver.findElement(By.xpath("//span[text()='"+skillName+"']"));
		String addedSkill = skillInRecord.getText();
		return addedSkill;
	}
	
}
