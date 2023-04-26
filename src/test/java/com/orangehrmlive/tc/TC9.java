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
import com.orangehrmlive.training.TrainingPage;

import net.bytebuddy.utility.RandomString;

public class TC9 extends Baseclass
{
	LoginPage lp;
	HomePage hp;
	EmpMgtPage emp;
	HRAdmPage hap;
	GeneralInfoPage gip;
	LeavesPage leaveP;
	static String AnyNewName;
	TrainingPage tp;
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
	tp= new TrainingPage(driver);
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
		try{ hp.clickTraining(); }
		catch(ElementNotInteractableException e)
		{
			hp.clickMore();
			try { hp.clickTraining(); }
			catch(ElementNotInteractableException e2) {
				hp.clickMore();
				hp.clickTraining();	
			}
		}
		driver.navigate().refresh();
		tp.clickAddBtn(driver);					
		tp.setNewTrainingDetails(driver,"Automation Training","Nina Patel","OrangeHRM");
		tp.clickSavebtn();
		tp.clickBackArrow();
		String actSkill = tp.getNewlyAddedSkill("Automation Training", driver);	
		Assert.assertEquals(actSkill, "Automation Training");
	}
	@AfterClass
	public void tearDown() throws InterruptedException
	{
		Thread.sleep(2000);
		driver.quit();
	}
}
