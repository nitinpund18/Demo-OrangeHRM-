package com.orangehrmlive.tc;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
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
import com.orangehrmlive.login.LoginPage;
import com.orangehrmlive.performance.PerformancePage;

import net.bytebuddy.utility.RandomString;

public class TC10 extends Baseclass
{	
	LoginPage lp;
	HomePage hp;
	EmpMgtPage emp;
	HRAdmPage hap;
	GeneralInfoPage gip;
	LeavesPage leaveP;
	PerformancePage pp;
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
	pp= new PerformancePage(driver);
	}
	@BeforeClass (dependsOnMethods = "setup")
	public void loginToApp() throws IOException
	{
		lp.enterCredentials();
		lp.clickloginbutton();
	}
	@Test (priority=1)
	public void ValidateNavigationToGoalPage()
	{
		try{ hp.clickPerformance(); }
		catch(ElementNotInteractableException e)
		{
			hp.clickMore();
			try { hp.clickPerformance(); }
			catch(ElementNotInteractableException e2) {
				hp.clickMore();
				hp.clickPerformance();	
			}
		}
		pp.clickMoreTab();
		pp.clickMyGoalsTab(driver);
	}
	@Test (dependsOnMethods = "ValidateNavigationToGoalPage")
	public void validateNavigationFromHomeBtn()
	{
		pp.clickHomeBtn();
		String actLandingPageHeader = emp.getEmpMgtempMgtPageHeader();
		Assert.assertEquals(actLandingPageHeader, "Employee Management");
	}
	@AfterClass
	public void tearDown() throws InterruptedException
	{
		Thread.sleep(2000);
		driver.quit();
	}
}
