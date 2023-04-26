package com.orangehrmlive.hrAdm;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class GeneralInfoPage {
	@FindBy (xpath="//*[@id='name']") private WebElement orgName;
	@FindBy (xpath="//*[@id='numemp']") private WebElement empField;
	@FindBy (xpath="//button[@type='submit']") private WebElement savebtn;
	
	public GeneralInfoPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	public String getOrgName()
	{
		String actOrgName = orgName.getText();
		return actOrgName;
	}
	public void setOrgName(String newName)
	{
		orgName.clear();
		orgName.sendKeys(newName);
	}
	public boolean verifyEmpField()
	{
		boolean actEmpFieldStatus = empField.isEnabled();
		return actEmpFieldStatus;
	}
	public void clickSavebtn()
	{
		savebtn.click();
	}

}
