package com.orangehrmlive.tc;

import java.io.IOException;

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


public class TC15 extends Baseclass
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
	@Test (priority=1)
	public void verifyTableRowCount()
	{
		try{ hp.clickRecruitment(); }
		catch(ElementNotInteractableException e)
		{
			hp.clickMore();
			try { hp.clickRecruitment(); }
			catch(ElementNotInteractableException e2) {
				hp.clickMore();
				hp.clickRecruitment();	
			}
		}
		rp.clickAllCandidate();
		int countOfRows = rp.getCountOfCandidateName();
		try {
			for(int i=0; i<rp.getTotalPageCount()-1; i++) {
				try {
					rp.naigateToNextPageOfTable(driver);
					countOfRows=countOfRows+rp.getCountOfCandidateName();
				}
				catch (Exception e){ }
			}
		}
		catch(Exception e){	}
		int actCountInTableHeader = rp.getTableHeaderCount(driver);
		Assert.assertEquals(actCountInTableHeader, countOfRows);
	}

	@AfterClass
	public void tearDown() throws InterruptedException
	{
		Thread.sleep(2000);
		driver.quit();
	}

}

