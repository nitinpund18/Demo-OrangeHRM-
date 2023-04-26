package com.orangehrmlive.tc;

import static org.testng.Assert.assertNotEquals;

import java.io.IOException;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.orangehrmlive.base.Baseclass;
import com.orangehrmlive.login.HomePage;
import com.orangehrmlive.login.LoginPage;

public class TC3 extends Baseclass
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
	public void verifyProfileDisplay()
	{
		Assert.assertTrue(hp.userProfile());
	}
	@Test (priority = 2)
	public void verifyUserProfileOptions()
	{
		hp.navigateToUserProfile(driver);
		hp.clickSetting();
		int actOptionCount = hp.userProfileOptions();
		int expOptionCount =2;
		Assert.assertEquals(actOptionCount,expOptionCount );
	}
	@Test (priority =3)
	public void verifyEmployeeCount()
	{
		hp.clickAbout();
		int act = hp.employeeCount();
		Assert.assertNotEquals(act, 0);
	}
	@Test (priority = 4)
	public void verifyCompanyDetails()
	{
		for( WebElement temp:hp.companyDetails())
		{
			Assert.assertNotNull(temp.getText());
		}
	}
	@Test (priority =5)
	public void popupClose()
	{
		hp.closePopUp();
	}
	@AfterClass
	public void tearDown()
	{
		driver.quit();
	}
}
