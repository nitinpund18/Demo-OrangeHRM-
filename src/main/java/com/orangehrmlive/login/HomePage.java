package com.orangehrmlive.login;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.Reporter;

public class HomePage 
{
	@FindBy (xpath ="//div[@id='topbar']//div") private WebElement empMgtHeader;
	@FindBy (xpath="//div[@class='image-container']/img") private WebElement profile;
	@FindBy (xpath="//i[@class='material-icons']") private WebElement settingIcon;
	@FindBy (xpath="//div[@class='picture']/div[2]/div/div/a") private List<WebElement> profileOptions;	
	@FindBy (xpath="//a[@id='aboutDisplayLink']") private WebElement about;
	@FindBy (xpath="//div[@class='row']/div[3]/p") private WebElement empCount;
	@FindBy (xpath="//div[@class='row']/div") private List<WebElement> aboutOptionList;
	@FindBy (xpath="//*[@id=\"companyInfo\"]/div/div[1]/p") private WebElement orgName;
	@FindBy (xpath="//a[@id='heartbeatSubmitBtn']") private WebElement okBtn;
//	@FindBy (xpath="//*[text()='"+textValue+"']") private WebElement widgetHeader;
	@FindBy (xpath="(//li[@id='left_menu_item_30']//span)[1]") private WebElement empMgtTab;
	@FindBy (xpath="(//*[@id='menu_item_81']//span)[1]") private WebElement hrAdmTab;
	@FindBy (xpath=("//div[@id='top-ribbon-menu']/div[3]/top-level-menu-item[2]")) private WebElement moreTab;
	@FindBy (xpath="//*[text()=' Qualifications ']") private WebElement qualificationTab;
	@FindBy (xpath="//a[text()='Skills']") private WebElement skillsSubtab;
	@FindBy (xpath="(//*[@id=\"menu_item_48\"])[1]") private WebElement leave;
	@FindBy (xpath="//*[@id='left_menu_item_131']/a[1]/span") private WebElement training;
	@FindBy (xpath="//*[@id=\"menu_item_171\"][1]/span") private WebElement performance;
	@FindBy (xpath="(//*[@id='menu_item_66']/span)[1]") private WebElement recruitment;
	@FindBy (xpath="//*[@id='side-menu-more']/span") private WebElement morebtn;
	@FindBy (xpath="//*[@id='ribbon-action-list']/li[2]/my-shortcut/div/a") private WebElement shareIcon;
	@FindBy (xpath="//*[@class='shortcut-panel-body']/div/div/div") private List<WebElement> shortcutItems;
	@FindBy (xpath="//*[@id=\"sidebar-profile-picture\"]/a") private WebElement profileOwnerName;
	public HomePage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
	public boolean employeeMgt()
	{
		boolean actEmpoMgtStatus = empMgtHeader.isDisplayed();
		return actEmpoMgtStatus;
	}
	public void navigateToUserProfile(WebDriver driver)
	{
		Actions act = new Actions(driver);
		act.moveToElement(profile).perform();
	}
	public boolean userProfile()
	{
		boolean actProfileDisplay = profile.isDisplayed();
		return actProfileDisplay;
	}
	public void clickSetting()
	{
		settingIcon.click();
	}
	public int userProfileOptions()
	{
		int actOptionCount = profileOptions.size();
		for(WebElement option:profileOptions)
		{
			Reporter.log(option.getText());
		}
		return actOptionCount;
	}
	public void clickAbout()
	{
		about.click();
	}
	public int employeeCount()
	{
		String[] temp = empCount.getText().split(" ");
		
		 int actEmpCount = Integer.parseInt(temp[1]);
		 return actEmpCount;
	}
	public List<WebElement> companyDetails()
	{
		return aboutOptionList;
	}
	public void closePopUp()
	{
		okBtn.click();
	}
	public void clickEmpMgt()
	{
		empMgtTab.click();
	}
	public void clickHrAdm()
	{
		hrAdmTab.click();
	}
	public void clickMoreTab()
	{
		moreTab.click();
	}
	public WebElement qalificationTab()
	{
		return qualificationTab;
	}
	public void clickSkillsSubtab()
	{
		skillsSubtab.click();
	}
	public String getOrgName()
	{
		String actOrgName = orgName.getText();
		return actOrgName;
	}
	public void clickLeaveOption()
	{
		leave.click();
	}
	public void clickTraining()
	{
		training.click();
	}
	public void clickMore()
	{
		morebtn.click();
	}
	public void clickPerformance()
	{
		performance.click();
	}
	public void clickRecruitment()
	{
		recruitment.click();
	}
	public void clickShareIcon()
	{
		shareIcon.click();
	}
	public int clickShortcutIcon()
	{
		int shortcutItemCount = shortcutItems.size();
		return shortcutItemCount;
	}
	public String getProfileOwnerName()
	{
		return profileOwnerName.getText();
	}
}
