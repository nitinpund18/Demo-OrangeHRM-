package com.orangehrmlive.hrAdm;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class HRAdmPage 
{
	@FindBy (xpath="(//a[@class='top-level-menu-item'])[1]") private WebElement manageUserRoleTab;
	@FindBy (xpath="//tbody[@ng-if='!listData.staticBody']//td[2]") private List<WebElement> tableRecord;
	@FindBy (xpath="(//input[@type='text'])[2]") private WebElement recordLimit;
	@FindBy (xpath="//li[@class='summary']") private WebElement totalRecordcount;
	@FindBy (xpath="//*[@id='top_level_menu_item_menu_item_12']/a") private WebElement orgTab;
	@FindBy (xpath="(//*[contains(text(),'General Information')])[2]") private WebElement genInfoSubTab;

	

	public HRAdmPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	public void clickManageUserRoleTab()
	{
		manageUserRoleTab.click();
	}
	public int displayedRecord()
	{
		int defaultRecordCount=tableRecord.size();	
		return defaultRecordCount;
	}
	public int recordLimitPerPage()
	{
		int perPageRecordLimit = Integer.parseInt(recordLimit.getAttribute("value"));
		return perPageRecordLimit;
	}
	public int recordInRightCorner()
	{
		String[] strValue = totalRecordcount.getText().split(" ");
		int numValue = Integer.parseInt(strValue[strValue.length-1]);
		return numValue;
	}
	public void clickOrg()
	{
		orgTab.click();
	}
	public void clickGenInfo()
	{
		genInfoSubTab.click();
	}
	
}
