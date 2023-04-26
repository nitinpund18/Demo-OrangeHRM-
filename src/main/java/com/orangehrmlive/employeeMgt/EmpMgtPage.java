package com.orangehrmlive.employeeMgt;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

public class EmpMgtPage 
{
	@FindBy (xpath="//*[@id='topbar']/ul[1]/li/div") private WebElement empMgtPageHeader;
	@FindBy (xpath="(//a[@class='top-level-menu-item'])[1]") private WebElement myInfoHeader;
	@FindBy (xpath="//form[@name='personal.personalDetailsForm']//div/input") private List<WebElement> perInfo;
	@FindBy (xpath="//*[text()='Salary ']") private WebElement salaryHeader;
	@FindBy (xpath="(//*[@class='summary-card-column summary-card-right'])[2]") private WebElement payCTC;
	@FindBy (xpath="//*[@id=\"widget.id\"]/span/span/quick-access-widget/div/div/div[1]/span[2]") private WebElement quickAccessWidget;
	@FindBy (xpath="//*[@id='widget.id']/span/span/my-actions-widget/div/div/div[1]/span[2]") private WebElement myActionWidget;
	@FindBy (xpath="(//*[@class='widget-header-text'])[1]") private WebElement firstWidget;
	@FindBy (xpath="//*[@id='employeeListTable']/tbody/tr[1]/td[3]") private WebElement firstNameInTable;
	@FindBy (xpath="//*[@id='employeeListTable']/tbody/tr/td[3]") private List<WebElement> tableRow;
	@FindBy (xpath="//*[@id='employeeListTable']/tbody/tr[1]/td[1]/following-sibling::td") private List<WebElement> tableColumn;	
	@FindBy (xpath="//*[text()='Middle Name']/preceding-sibling::input") private WebElement middleName;
	@FindBy (xpath="(//*[text()='Save'])[1]") private WebElement perInfoSavebtn;
	@FindBy (xpath="//*[text()='Successfully Updated']") private WebElement updateStatusMsg;
	@FindBy (xpath="//*[@id='top-ribbon-menu']/div/div[2]/a") private WebElement backArrowBtn;
	@FindBy (xpath="//*[@title='Next Page']") private WebElement nextPageBtn;
	@FindBy (xpath="//*[text()='Add']") private WebElement addBtn;
	@FindBy (xpath="//*[text()='Browse']") private WebElement browseBtn;
	@FindBy (xpath="(//*[text()='Save'])[3]") private WebElement UploadSaveBtn;
	@FindBy (xpath="//*[@class='break-word']") private WebElement fileNameText;
	@FindBy (xpath="(//*[@src='template']/span)[6]") private WebElement actFileUploaderName;

	
	public EmpMgtPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	public void clickMyInfo()
	{
		myInfoHeader.click();
	}
	public void personalInfo()
	{
		for(WebElement temp:perInfo)
		{
		Assert.assertNotNull(temp.getText());		
		}
	}
	public void clickSalary()
	{
		salaryHeader.click();
	}
	public double getPayableCTC(WebDriver driver)
	{
		WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOf(payCTC));
		String temp= payCTC.getText().replace("$", "").replace(",", "");
		return Double.parseDouble(temp);
	}
	public String getEmpMgtempMgtPageHeader()
	{
		String pageHeaderText = empMgtPageHeader.getText();
		return pageHeaderText;
	}
	public String getTitleOfQuickAccessWidget()
	{
		String quickAccessWidgetTitle = quickAccessWidget.getText();
		return quickAccessWidgetTitle;
	}
	public void moveWidget(WebDriver driver)
	{
		Actions act = new Actions(driver); 
		act.clickAndHold(quickAccessWidget).release(myActionWidget).build().perform();
	}
	public String getTitleOfFirstWidget()
	{
		String firstWidgetTitle = firstWidget.getText();
		return firstWidgetTitle;
	}
	public void clickFirstRowinTable()
	{
		firstNameInTable.click();
	}
	public void updateMiddleName(String newMiddleName)
	{
		middleName.clear();
		middleName.sendKeys(newMiddleName);
		perInfoSavebtn.click();
	}
	public String getUpdateMsgStatus()
	{
		return updateStatusMsg.getText();
	}
	public String getFirstNameInTable(WebDriver driver)
	{
		return firstNameInTable.getText();
	}
	public void clickBackArrowBtn()
	{
		backArrowBtn.click();
	}
	public boolean isNextPageAvailable() 
	{
		System.out.println(nextPageBtn.isDisplayed());
		return nextPageBtn.isDisplayed();
	}
	public WebElement navigateToNextPage() 
	{
		return nextPageBtn;
	}
	public ArrayList<ArrayList<String>> tableData(WebDriver driver)
	{ 
		ArrayList<ArrayList<String>> TData = new  ArrayList<>();
		ArrayList<String> rowDataHeader= new  ArrayList<>();
		for(int k=1; k<=tableColumn.size();k++)	//TableHeader
		{
			String headerCellText = driver.findElement(By.xpath("//*[@id='employeeListTable']//th[1]/following-sibling::th["+k+"]")).getText();
			rowDataHeader.add(headerCellText);
		}
		TData.add(rowDataHeader);
		try{
		    while(true) {
		    	for(int i=1; i<=tableRow.size(); i++) //tableBody
				{
				   ArrayList<String> rowData= new  ArrayList<>();
					for(int j=1; j<=tableColumn.size(); j++)
					{	
					String cellText = driver.findElement(By.xpath("//*[@id='employeeListTable']/tbody/tr["+i+"]/td["+j+"]/following-sibling::td[1]")).getText();
					rowData.add(cellText);
					}
					TData.add(rowData);
				}
		   new FluentWait<WebDriver>(driver)
		        .withTimeout(Duration.ofSeconds(5)) 
		        .pollingEvery(Duration.ofSeconds(1))
		        .ignoring(NoSuchElementException.class)
		        .until(ExpectedConditions.visibilityOf(nextPageBtn))
		        .click();
		    }
		} catch (Exception ignored){ }
		return TData;
	}
	public void clickAddBtn() 
	{
		addBtn.click();
	}
	public void clickBrowseBtn(WebDriver driver)
	{
		Actions act= new Actions(driver);
		act.click(browseBtn).perform();
	}
	public void clickUploadSaveBtn(WebDriver driver)
	{
		Actions act= new Actions(driver);
		act.click(UploadSaveBtn).perform();
	}
	public String getFileName()
	{
		return fileNameText.getText();
	}
	public String getFileUploaderName()
	{
		return actFileUploaderName.getText();
	}
}
