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
import com.orangehrmlive.login.HomePage;
import com.orangehrmlive.login.LoginPage;import net.bytebuddy.utility.RandomString;

public class TC7 extends Baseclass
{
	LoginPage lp;
	HomePage hp;
	EmpMgtPage emp;
	HRAdmPage hap;
	GeneralInfoPage gip;
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
	}
	@BeforeClass (dependsOnMethods = "setup")
	public void loginToApp() throws IOException
	{
		lp.enterCredentials();
		lp.clickloginbutton();
	}

	@Test (priority=1)
	public void setNewNameofOrganization() 
	{
		hp.clickHrAdm();
		hap.clickOrg();
		hap.clickGenInfo();
		String currentOrgName = gip.getOrgName();
		System.out.println(currentOrgName);
		AnyNewName = currentOrgName+"new";
		gip.setOrgName(AnyNewName);
	}
	@Test (priority=2)
	public void verifyNumberofEmpField()
	{	
		Assert.assertFalse(gip.verifyEmpField());
		gip.clickSavebtn();
	}
	@Test (priority=3)
	public void verifyChangedOrganisationName()
	{
		hp.navigateToUserProfile(driver);
		hp.clickSetting();
		hp.clickAbout();
		String OrgName = (hp.getOrgName());
		String unwantedStringPart = "Company Name: ";
		String actOrgName = OrgName.substring(unwantedStringPart.length());
		String expOrgName =AnyNewName;
		Assert.assertEquals(expOrgName, actOrgName);
	}
	@AfterClass
	public void tearDown()
	{
		driver.quit();
	}
}
