package com.orangehrmlive.tc;

import static org.testng.Assert.assertNotEquals;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.orangehrmlive.base.Baseclass;
import com.orangehrmlive.login.HomePage;
import com.orangehrmlive.login.LoginPage;

public class TC2 extends Baseclass
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
	@BeforeClass (dependsOnMethods = "setup")
	public void loginToApp() throws IOException
	{
		lp.enterCredentials();
		lp.clickloginbutton();
	}

	@Test (priority = 1)
	public void verifyWidget()
	{
		String[] s= {"Quick Access","Buzz Latest Posts","My Actions","Latest Documents","Latest News","Employees on Leave Today","Time At Work","Headcount by Location", "COVID-19 Report"};
		 String widgetList;
		for(int i=0; i<s.length;i++)
		{
			String textValue=s[i];
			widgetList = driver.findElement(By.xpath("//*[@class='widget-header']//*[text()='"+textValue+"']")).getText();	
			Reporter.log(widgetList, true);
			Assert.assertEquals(widgetList,s[i]);
		}
	}

	@AfterClass
	public void tearDown()
	{
		driver.quit();
	}
}
