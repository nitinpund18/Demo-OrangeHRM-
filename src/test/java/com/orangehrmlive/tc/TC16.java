package com.orangehrmlive.tc;

import java.io.IOException;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.ElementNotInteractableException;
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
import com.orangehrmlive.recruitment.RecruitmentPage;


public class TC16 extends Baseclass
{	
	LoginPage lp;
	HomePage hp;
	EmpMgtPage emp;
	HRAdmPage hap;
	GeneralInfoPage gip;
	LeavesPage leaveP;
	PerformancePage pp;
	RecruitmentPage rp;
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
	rp = new RecruitmentPage(driver);
	}
	@BeforeClass (dependsOnMethods = "setup")
	public void loginToApp() throws IOException
	{
		lp.enterCredentials();
		lp.clickloginbutton();
	}

	String newMiddleName="";
	@Test (priority=1)
	public void verifyInfoUpdateStatusMessage()
	{
		hp.clickEmpMgt();
		emp.clickFirstRowinTable();
		//updating middle name
		newMiddleName = RandomStringUtils.randomAlphabetic(6);
		emp.updateMiddleName(newMiddleName);
		String actUpdateMsg = emp.getUpdateMsgStatus();
		String expUpdateMsg ="Successfully Updated";
		Assert.assertEquals(actUpdateMsg, expUpdateMsg);	
	}
	@Test (dependsOnMethods = "verifyInfoUpdateStatusMessage")
	public void verifyUpdatedmiddleName()
	{
		emp.clickBackArrowBtn();
		driver.navigate().refresh();
		String actName = emp.getFirstNameInTable(driver);
		if(!(actName.contains(newMiddleName)))
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

