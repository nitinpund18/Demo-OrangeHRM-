package com.orangehrmlive.tc;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.orangehrmlive.base.Baseclass;
import com.orangehrmlive.employeeMgt.EmpMgtPage;
import com.orangehrmlive.hrAdm.GeneralInfoPage;
import com.orangehrmlive.hrAdm.HRAdmPage;
import com.orangehrmlive.leaves.LeavesPage;
import com.orangehrmlive.login.HomePage;
import com.orangehrmlive.login.LoginPage;import net.bytebuddy.utility.RandomString;

public class TC8 extends Baseclass
{
	LoginPage lp;
	HomePage hp;
	EmpMgtPage emp;
	HRAdmPage hap;
	GeneralInfoPage gip;
	LeavesPage leaveP;
	static String AnyNewName;
	@BeforeClass 
	public void setup() throws InterruptedException, IOException
	{
	browserlaunch();
	lp= new LoginPage(driver);
	hp= new HomePage(driver);
	emp= new EmpMgtPage(driver);
	hap= new HRAdmPage(driver);
	gip= new GeneralInfoPage(driver);
	leaveP= new LeavesPage(driver);
	}
	@BeforeClass (dependsOnMethods = "setup")
	public void loginToApp() throws IOException
	{
		lp.enterCredentials();
		lp.clickloginbutton();
	}
	@Test (priority=1)
	public void VerifyDefaultNavigationToLeaveList()
	{
		hp.clickLeaveOption();
		boolean result = (leaveP.getAttributeofLeaveList()).contains("active");
		Assert.assertTrue(result);
	}
	@Test (priority=2)
	public void verifyDatesTextBox() 
	{
		String fromDate = leaveP.getDefaultFromDate();
		String toDate = leaveP.getDefaultToDate();
		Assert.assertNotNull(fromDate);
		Assert.assertNotNull(toDate);
	}
	@Test (priority=2)
	public void verfifyPendingApprovalChkboxStstus()
	{
		boolean defaultStatus = leaveP.verfifyPendingApprovalchkBox();
		Assert.assertTrue(defaultStatus);
	}
	@Test (priority=3)
	public void verfifyLeavesInTable()
	{
		
		int leavesTaken = leaveP.verifyLeavesTaken();
		if(!(leavesTaken>=0))
		{
			Assert.fail();
		}
	}
	@AfterClass
	public void tearDown() throws InterruptedException
	{
		Thread.sleep(2000);
		driver.quit();
	}
}
