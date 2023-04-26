package com.orangehrmlive.recruitment;

import java.time.Duration;
import java.time.Period;
import java.util.List;

import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RecruitmentPage {
@FindBy (xpath="//*[text()='All Candidates']/preceding-sibling::span") private WebElement allCandidatesCount;
@FindBy (xpath="//*[text()='All Candidates']") private WebElement allCandidates;
@FindBy (xpath="//*[text()='Application Received']/preceding-sibling::span") private WebElement applicationReceivedCount;
@FindBy (xpath="//*[text()='Application Received']") private WebElement applicationReceived;
@FindBy (xpath="//*[text()='Shortlisted']/preceding-sibling::span") private WebElement shortlistedCount;
@FindBy (xpath="//*[text()='Shortlisted']") private WebElement shortlisted;
@FindBy (xpath="//*[text()='In Progress']/preceding-sibling::span") private WebElement inProgressCount;
@FindBy (xpath="//*[text()='In Progress']") private WebElement inProgress;
@FindBy (xpath="//*[text()='Job Offer']/preceding-sibling::span") private WebElement jobOfferCount;
@FindBy (xpath="//*[text()='Job Offer']") private WebElement jobOffer;
@FindBy (xpath="//*[text()='Hired']/preceding-sibling::span") private WebElement hiredCount;
@FindBy (xpath="//*[text()='Hired']") private WebElement hired;
@FindBy (xpath="//*[text()='Rejected']/preceding-sibling::span") private WebElement rejectedCount;
@FindBy (xpath="//*[text()='Rejected']") private WebElement rejected;
@FindBy (xpath="//*[@id='mount-vue-app']//h5") private WebElement tableHeaderTitle;
@FindBy (xpath="//div[@role='row']/div") private List<WebElement> tableCells;
@FindBy (xpath="//div[@class='oxd-table-body']//div[3]/div/a") private List<WebElement> candidateName;
@FindBy (xpath="//*[@class='oxd-icon oxd-icon--medium bi-chevron-right']") private WebElement nextPageBtn;
@FindBy (xpath="//button[contains(@class,'item--page')]") private List<WebElement> pageCount;
	public RecruitmentPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	public int getAllCandidatesCount()
	{
		return Integer.parseInt(allCandidatesCount.getText());
	}
	public int getApplicationReceivedCount()
	{
		return Integer.parseInt(applicationReceivedCount.getText());
	}
	public int getShortlistedCount()
	{
		return Integer.parseInt(shortlistedCount.getText());
	}
	public int getInProgressCount()
	{
		return Integer.parseInt(inProgressCount.getText());
	}
	public int getJobOfferCount()
	{
		return Integer.parseInt(jobOfferCount.getText());
	}
	public int getHiredCount()
	{
		return Integer.parseInt(hiredCount.getText());
	}
	public int getRejectedCount()
	{
		return Integer.parseInt(rejectedCount.getText());
	}
	public int getTableHeaderCount(WebDriver driver)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		try
		{
			wait.until(ExpectedConditions.visibilityOfAllElements(tableCells));
		}
		catch(StaleElementReferenceException e)
		{
			wait.until(ExpectedConditions.visibilityOfAllElements(tableCells));
		}
		
		return Integer.parseInt((tableHeaderTitle.getText()).replaceAll("[^0-9]",""));
	}
	public void clickAllCandidate()
	{
		allCandidates.click();
	}
	public void clickApplicationReceived()
	{
		applicationReceived.click();
	}
	public void clickShortlisted()
	{
		shortlisted.click();
	}
	public void clickInProgress()
	{
		inProgress.click();
	}
	public void clickJobOffer()
	{
		jobOffer.click();
	}
	public void clickHired()
	{
		hired.click();
	}
	public void clickRejected()
	{
		rejected.click();
	}
	public int getCountOfCandidateName()
	{
		return candidateName.size();	
	}
	public void naigateToNextPageOfTable(WebDriver driver) throws InterruptedException
	{
		Thread.sleep(2000);
		nextPageBtn.click();
	}
	public int getTotalPageCount() 
	{
		return pageCount.size();
	}
}
