package nv.leafbot.pages;



import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import com.aventstack.extentreports.ExtentTest;

import cucumber.api.java.en.Given;
//import nv.selenium.api.base.SeleniumBase;
import nv.selenium.utils.CommonLocators;

public class LoginPage extends CommonLocators{
	
	public LoginPage(RemoteWebDriver driver, ExtentTest test){
		this.driver = driver;
		this.test = test;
		PageFactory.initElements(driver,this);
	}
	@CacheLookup
	@FindBy(how=How.ID,using="username")
	private WebElement eleUserName;	
	
	@FindBy(how=How.ID,using="password")
	private WebElement elePassword;	
	
	@FindBy(how=How.CLASS_NAME,using="decorativeSubmit")
	private WebElement eleLogin;
	
	@FindBy(how=How.ID, using = "errorDiv")
	private WebElement eleVerifyErrMsg;
	
	
	@Given("Enter the username as (.*)")
	public LoginPage enterUserName(String data) throws InterruptedException {	
		//WebElement eleUsername = locateElement("id", "username");
		Thread.sleep(5000);
		clearAndType(eleUserName, data);
		return this;
	}	

	@Given("Enter the Password as (.*)")
	public LoginPage enterPassword(String data) {
		clearAndType(elePassword, data);
		return this;
	}	
	
	@Given("Click on the Login")
	public LoginPage clickLogin() {
		click(eleLogin);
		return this;		
	}
	
	@FindBy(how=How.CLASS_NAME,using="decorativeSubmit")
	private WebElement eleLogOut;
	
	@Given("Click on the Logout")
	public LoginPage clickLogout() {
		click(eleLogOut);
		return this;

	}
	public LoginPage clickLogInForFailer() {
		click(eleLogin);
		return this;		
	}
	
	@Given("Verify the error message (.*)")
	public LoginPage verifyErrorMsg(String data) {
		verifyPartialText(eleVerifyErrMsg, data);
		return this;
	}
	
	
}
