package com.orangehrmlive.tc;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
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

public class TC12 extends Baseclass
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
	public void moveQuickAccessWidget()
	{
		String titleOfQuickAccesswidget = emp.getTitleOfQuickAccessWidget();
		emp.moveWidget(driver);
		String firstWidgetTitle= emp.getTitleOfFirstWidget();
		System.out.println(titleOfQuickAccesswidget);
		System.out.println(firstWidgetTitle);
		Assert.assertNotEquals(firstWidgetTitle, titleOfQuickAccesswidget);
	}
	@AfterClass
	public void tearDown() throws InterruptedException
	{
		Thread.sleep(2000);
		driver.quit();
	}
}
