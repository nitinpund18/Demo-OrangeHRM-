package com.orangehrmlive.tc;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
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


public class TcTable6 extends Baseclass
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
	public void verifyFileUpload() throws AWTException, InterruptedException
	{
		hp.clickEmpMgt();
		emp.clickFirstRowinTable();
		emp.clickAddBtn();
		emp.clickBrowseBtn(driver);
		String actFilePath=System.getProperty("user.dir")+"\\attachment\\SampleWord.docx";
		StringSelection ss=new StringSelection(actFilePath);
	    Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);;
		Thread.sleep(500);
		Robot r = new Robot();
	      r.keyPress(KeyEvent.VK_ENTER);
	      r.keyRelease(KeyEvent.VK_ENTER);
	      r.keyPress(KeyEvent.VK_CONTROL);
	      r.keyPress(KeyEvent.VK_V);
	      r.keyRelease(KeyEvent.VK_CONTROL);
	      r.keyRelease(KeyEvent.VK_V);
	      r.keyPress(KeyEvent.VK_ENTER);
	      r.keyRelease(KeyEvent.VK_ENTER);
		emp.clickUploadSaveBtn(driver);
		String actFileName=emp.getFileName();
		if(!(actFilePath.contains(actFileName))) {
		}
	}
	@Test(priority=2)
	public void verifyFileUploaderName()
	{
		String actName = emp.getFileUploaderName();
		String expNameAsUser=hp.getProfileOwnerName();
		if(!(expNameAsUser.contains(actName))) {
			Assert.fail();
		}
	}
//	@AfterClass
//	public void tearDown() throws InterruptedException
//	{
//		Thread.sleep(2000);
//		driver.quit();
//	}
}

