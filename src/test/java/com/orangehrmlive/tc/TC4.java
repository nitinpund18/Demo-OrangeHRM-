package com.orangehrmlive.tc;

import static org.testng.Assert.assertNotEquals;

import java.io.IOException;
import java.time.Duration;
import java.time.Period;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.orangehrmlive.base.Baseclass;
import com.orangehrmlive.employeeMgt.EmpMgtPage;
import com.orangehrmlive.login.HomePage;
import com.orangehrmlive.login.LoginPage;

public class TC4 extends Baseclass
{
	LoginPage lp;
	HomePage hp;
	EmpMgtPage emp;
	@BeforeClass 
	public void setup() throws InterruptedException, IOException
	{
	browserlaunch();
	lp= new LoginPage(driver);
	hp= new HomePage(driver);
	emp= new EmpMgtPage(driver);
	}
	@BeforeClass (dependsOnMethods = "setup")
	public void loginToApp() throws IOException
	{
		lp.enterCredentials();
		lp.clickloginbutton();
	}

	@Test
	public void verifyUserPersonalInfo() throws InterruptedException
	{
		hp.clickEmpMgt();
		emp.clickMyInfo();
		driver.navigate().refresh();
	      try{
	    	  emp.personalInfo();
	      }
	      catch(StaleElementReferenceException e){
	    	  emp.personalInfo();
	      }
	}
	@Test (dependsOnMethods = "verifyUserPersonalInfo")
	public void verifyPayableCTC()
	{
		emp.clickSalary();
		double actSalary = emp.getPayableCTC(driver);	
		Assert.assertNotEquals(actSalary, 0.0);
	}

	@AfterClass
	public void tearDown()
	{
		driver.quit();
	}
}
