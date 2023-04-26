package com.orangehrmlive.leaves;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LeavesPage 
{
	@FindBy (xpath="//*[@id='top_level_menu_item_menu_item_48']/a") private WebElement leaveListTab;
	@FindBy (xpath="//*[@id='from']") private WebElement fromDate;
	@FindBy (xpath="//*[@id='to']") private WebElement toDate;
	@FindBy (xpath="//*[@id='pending_approval']") private WebElement pendingApprovalchkbox;
	@FindBy (xpath="//table[@class='highlight bordered']//tr/td[2]") private List<WebElement> LeavesTaken;
	
	
	public LeavesPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	public String getAttributeofLeaveList()
	{
		String leavesListAttribute = leaveListTab.getAttribute("class");
		return leavesListAttribute;
	}
	
	public String getDefaultFromDate()
	{
		String date = fromDate.getText();
		return date;
	}
	public String getDefaultToDate()
	{
		String date = toDate.getText();
		return date;
	}
	public boolean verfifyPendingApprovalchkBox()
	{
		boolean defaultStatus = pendingApprovalchkbox.isSelected();
		return defaultStatus;
	}
	public int verifyLeavesTaken()
	{
		int leaves = LeavesTaken.size();
		return leaves;
	}
}
