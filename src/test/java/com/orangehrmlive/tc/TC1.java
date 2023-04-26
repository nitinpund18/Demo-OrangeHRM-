package com.orangehrmlive.tc;

import static org.testng.Assert.assertTrue;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.orangehrmlive.base.Baseclass;
import com.orangehrmlive.login.HomePage;
import com.orangehrmlive.login.LoginPage;

public class TC1 extends Baseclass
{
	LoginPage lp;
	HomePage hp;
	@BeforeClass
	public void setup() throws InterruptedException, IOException
	{
	browserlaunch();
	lp= new LoginPage(driver);
	hp= new HomePage(driver);
	}
	@Test (priority=1)
	public void verifylogo()
	{
		Assert.assertTrue(lp.logoStatus());
	}
	@Test (priority = 2)
	public void verifyLoginPanel()
	{
		Assert.assertTrue(lp.LoginPanelStatus());
	}
	@Test (priority = 3, dependsOnMethods = "verifyLoginPanel" )
	public void login() throws IOException
	{
		lp.enterCredentials();
		lp.clickloginbutton();
	}
	@Test (priority = 4)
	public void HomepageNavigation()
	{
		String actTitle = driver.getTitle();
		String expTitle = "Employee Management";
		Assert.assertEquals(actTitle, expTitle);
	}
	@Test (priority = 5)
	public void verifyEmpMgt()
	{
		Assert.assertTrue(hp.employeeMgt());
	}
	@AfterClass
	public void tearDown() throws InterruptedException
	{
		Thread.sleep(2000);
		driver.quit();
	}
}
