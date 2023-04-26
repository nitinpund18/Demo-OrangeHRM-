package com.orangehrmlive.tc;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.Assert;
import org.testng.Reporter;
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


public class TcTable5 extends Baseclass
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
	String newMiddleName="";
	@Test (priority=1)
	public void PrintEmpWithSubUnitQATEAM()
	{
		hp.clickEmpMgt();
		ArrayList<ArrayList<String>> TData= emp.tableData(driver);
		String expColumn = "Location";
		String expOfficeLocationValue="India Office";
		String expSuperviosrName = "Miguel Mason";	
		int IndiaOfficeColumnIndex = TData.get(0).indexOf(expColumn);	
		for(int i=1; i<TData.size();i++)
		{	
			if(expOfficeLocationValue.equals(TData.get(i).get(6)) && expSuperviosrName.equals(TData.get(i).get(7)) )
			{
				Reporter.log(TData.get(i).get(1));
			}
		}
	}
	@AfterClass
	public void tearDown() throws InterruptedException
	{
		Thread.sleep(2000);
		driver.quit();
	}
}

