package PageObjectModel;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import Util.BaseClass;

public class LoginPages extends BaseClass {


	// Constructor of the page and Pagefactory:

	public LoginPages(WebDriver driver) {
	
		this.driver=driver;
		//PageFactory.initElements(driver, this);
	}

	// Locators of Login page:
	
	//@FindBy(how = How.XPATH, using = "//a[@class='profile-menu-button ng-star-inserted']") 
	 //private WebElement profileButton;

	private By profileButton = By.xpath("//a[@class='profile-menu-button ng-star-inserted']");
	private By rippleLink = By.xpath("//a[@class='ripplelink']");
	private By emailId = By.xpath("//input[@type='email']");
	private By next = By.xpath("//input[@type='submit']");
	private By usePassLink = By.xpath("//a[text()='Use your password instead']");
	private By authentication = By.id("FormsAuthentication");
	private By password = By.id("passwordInput");
	private By signIn = By.id("submitButton");
	private By token = By.xpath("//span[text()='Sign in with your phone or token device']");
	private By textOfLandingPage = By.xpath("//div[text()='Microsoft Cloud Accelerator Program']");

	// page actions: features(behavior) of the page the form of methods:

	public String getLoginPageTitle() {
		return driver.getTitle();
	}

	public void clicksProfileButton() {
		driver.findElement(profileButton).click();
		driver.findElement(rippleLink).click();
	}

	public void enterUserName(String username) {
		driver.findElement(emailId).sendKeys(username);
		driver.findElement(next).click();
	}

	public void clicksTheAuthenButton() {

		if (driver.findElement(usePassLink).isDisplayed()) {
			driver.findElement(usePassLink).click();
			driver.findElement(authentication).click();
		} else {
			driver.findElement(authentication).click();
		}
	}

	public void enterPassword(String pwd) {
		//Boolean numberAndPassword = driver.findElement(usePassLink).isDisplayed();
		//System.out.println("number and Password Authentication : "+ numberAndPassword );
		if (driver.findElement(authentication).isDisplayed()) {
			driver.findElement(authentication).click();
		} else if(driver.findElement(usePassLink).isDisplayed()){
			
			driver.findElement(usePassLink).click();
			driver.findElement(authentication).click();
		}
		/* driver.findElement(authentication).click();
		clicksTheAuthenButton();
		driver.findElement(password).sendKeys(pwd);
		*/
		
		driver.findElement(password).sendKeys(pwd);
		driver.findElement(signIn).click();
	}

	public void clicksPhoneTokenAuthetication() {
		driver.findElement(token).click();
	}
	public String getTextOfLandingPage() {
	return driver.findElement(textOfLandingPage).getText();
		
	}
}
