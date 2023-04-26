package com.orangehrmlive.tc;

import java.io.IOException;
import org.testng.Assert;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.orangehrmlive.base.Baseclass;
import com.orangehrmlive.employeeMgt.EmpMgtPage;
import com.orangehrmlive.hrAdm.HRAdmPage;
import com.orangehrmlive.login.HomePage;
import com.orangehrmlive.login.LoginPage;

public class TC5 extends Baseclass
{
	LoginPage lp;
	HomePage hp;
	EmpMgtPage emp;
	HRAdmPage hap;
	@BeforeClass 
	public void setup() throws InterruptedException, IOException
	{
	browserlaunch();
	lp= new LoginPage(driver);
	hp= new HomePage(driver);
	emp= new EmpMgtPage(driver);
	hap= new HRAdmPage(driver);
	}
	@BeforeClass (dependsOnMethods = "setup")
	public void loginToApp() throws IOException
	{
		lp.enterCredentials();
		lp.clickloginbutton();
	}

	@Test
	public void verifyDefaultPerPageRecord() throws InterruptedException
	{
		hp.clickHrAdm();
		hap.clickManageUserRoleTab();
		int perPageRecordLimit = hap.recordLimitPerPage();
		if(perPageRecordLimit!=50)
		{
			Assert.fail();
		}
	}
	@Test (dependsOnMethods ="verifyDefaultPerPageRecord")
	public void verifyTotalRecordInTable() throws InterruptedException
	{
		int tableRecordCount = hap.displayedRecord();
		int recordCountInCorner = hap.recordInRightCorner();
		Assert.assertEquals(tableRecordCount,recordCountInCorner );
	}
	@AfterClass
	public void tearDown()
	{
		driver.quit();
	}
}
