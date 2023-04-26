package com.orangehrmlive.base;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import com.orangehrmlive.util.Utility;

public class Baseclass {
	public WebDriver driver;
	public void browserlaunch() throws InterruptedException, IOException
	{
	String key="webdriver.chrome.driver";
	String value=System.getProperty("user.dir")+"C:\\Users\\Shree\\eclipse-workspace\\SeleniumNitin\\driver\\chromedriver_win32 (4)\\chromedriver.exe";
//	String url=Utility.getPropertyData("url");
	String url="https://pramodm-trials77.orangehrmlive.com/auth/login";
	System.setProperty(key, value);
	driver=new ChromeDriver();
	driver.manage().window().maximize();
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
	driver.get(url);
	
	}
}
