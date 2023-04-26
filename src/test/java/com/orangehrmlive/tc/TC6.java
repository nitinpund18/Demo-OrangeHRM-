package com.orangehrmlive.tc;

import java.io.IOException;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.orangehrmlive.base.Baseclass;
import com.orangehrmlive.employeeMgt.EmpMgtPage;
import com.orangehrmlive.hrAdm.HRAdmPage;
import com.orangehrmlive.login.HomePage;
import com.orangehrmlive.login.LoginPage;
import com.orangehrmlive.qualification.skills.SkillsPage;

public class TC6 extends Baseclass
{
	LoginPage lp;
	HomePage hp;
	SkillsPage sp;
	@BeforeClass 
	public void setup() throws InterruptedException, IOException
	{
	browserlaunch();
	lp= new LoginPage(driver);
	hp= new HomePage(driver);
	sp= new SkillsPage(driver);
	}
	@BeforeClass (dependsOnMethods = "setup")
	public void loginToApp() throws IOException
	{
		lp.enterCredentials();
		lp.clickloginbutton();
	}
	@Test
	public void VerifyUserAddNewSkill() 
	{
		hp.clickMoreTab();
		Actions act= new Actions(driver);
		act.moveToElement(hp.qalificationTab()).perform();;
		hp.clickSkillsSubtab();
		sp.clickAddBtn();
		String randstr=RandomStringUtils.randomAlphabetic(3);
		String skillName="Test Automation 1"+randstr;
		String desText=randstr;
		sp.addNewSkill(skillName, desText);
		driver.navigate().refresh();
		Assert.assertEquals(skillName,sp.getAddedSkill(skillName, driver));
	}
	
	@AfterClass
	public void tearDown()
	{
		driver.quit();
	}
}
