package nv.selenium.api.base;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import org.testng.log4testng.Logger;

import nv.selenium.api.design.Browser;
import nv.selenium.api.design.Element;
import nv.selenium.utils.Reporter;

public class SeleniumBase extends Reporter implements Browser, Element{

	public Logger log = Logger.getLogger(SeleniumBase.class);
//	public Properties prop = new Properties();
	public static WebDriverWait wait;
	public SoftAssert softAssert;
	//public ExtentReports extent;
	//ExtentTest logger;
	public String hubIPAddress;
	@BeforeClass
	public  void beforeclass() throws FileNotFoundException, IOException{
//		prop.load(new FileInputStream("./Resources/log4j.properties"));
//		PropertyConfigurator.configure(prop);
		author =System.getProperty("user.name");
		
	}
	
	@BeforeMethod
	public  synchronized void configureAndLaunch(Method m) throws IOException {
		
		Test testClass=m.getAnnotation(Test.class);
//		testcaseName =m.getName();
		testcaseName = testClass.testName();
		testcaseDec =testClass.description();
		String[] group = new String[testClass.groups().length];
		
		for(int i=0;i<testClass.groups().length;i++) {
			group[i]=testClass.groups()[i];
			category =category+group[i]+", ";
		
		}
		//String Browser="chrome";
		String URL ="http://leaftaps.com/opentaps";
		String Browser="chrome";
		if(category!="")
			category=category.substring(0, category.length()-2);
			else
				category="";
		RemoteWebDriver driver = startApp(Browser,URL);
		
		report(driver);
	}
		
		
		
	@Override
	public void clearAndType(WebElement ele, String data) {
		try {
			ele.clear();
			ele.sendKeys(data);
			reportStep("The Data :"+data+" entered Successfully", "pass");
		} catch (ElementNotInteractableException e) {
			reportStep("The Element "+ele+" is not Interactable", "fail");
			throw new RuntimeException();
		}

	}
	
	@Override
	public boolean verifyPartialText(WebElement ele, String expectedText) {
		try {
			if(ele.getText().contains(expectedText)) {
				reportStep("The expected text contains the actual "+expectedText,"pass");
				return true;
			}else {
				reportStep("The expected text doesn't contain the actual "+expectedText,"fail");
			}
		} catch (WebDriverException e) {
			System.out.println("Unknown exception occured while verifying the Text");
		} 
		return false;
	}
		
	
	@Override
	public WebElement locateElement(String locatorType, String value) {
		try {
			RemoteWebDriver driver = (RemoteWebDriver) threadloc.get();
			switch(locatorType.toLowerCase()) {
			case "id": return driver.findElementById(value);
			case "name": return driver.findElementByName(value);
			case "class": return driver.findElementByClassName(value);
			case "link": return driver.findElementByLinkText(value);
			case "xpath": return driver.findElementByXPath(value);
			}
		} catch (NoSuchElementException e) {
			reportStep("The Element with locator:"+locatorType+" Not Found with value: "+value, "fail");
			throw new RuntimeException();
		}catch (Exception e) {
			reportStep("The Element with locator:"+locatorType+" Not Found with value: "+value, "fail");
		}
		return null;
	}
		
	@AfterMethod
	public void closeBrowser() {
		WebDriver driver = threadloc.get();
		driver.quit();
		//driver1.remove();
	}
	
	@Override
	public void click(WebElement ele) {
		String text="";
		try {
			WebDriver driver = threadloc.get();
			wait = new WebDriverWait(driver, 10);
			wait.until(ExpectedConditions.elementToBeClickable(ele));
			text = ele.getText();
			ele.click();
			reportStep("The Element "+text+" clicked", "pass"); 
		} catch (StaleElementReferenceException e) {
			reportStep("The Element "+text+" could not be clicked", "fail");
			throw new RuntimeException();
		} 
	}
		
		
	
	@Override
	public long takeSnap() {
		long number = (long) Math.floor(Math.random() * 900000000L) + 10000000L; 
		try {
			WebDriver driver = threadloc.get();
			FileUtils.copyFile(((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE) , new File("./reports/images/"+number+".jpg"));
		} catch (WebDriverException e) {
			System.out.println("The browser has been closed.");
		} catch (IOException e) {
			System.out.println("The snapshot could not be taken");
		}
		return number;
	}
	
	
	@Override
	public   synchronized RemoteWebDriver startApp(String browser, String url) {
		RemoteWebDriver driver=null;
		try {
			if(browser.equalsIgnoreCase("chrome")) {
				System.setProperty("webdriver.chrome.driver",
						"./drivers/chromedriver.exe");
				driver = new ChromeDriver();
			} else if(browser.equalsIgnoreCase("firefox")) {
				System.setProperty("webdriver.gecko.driver",
						"./drivers/geckodriver.exe");
				driver = new FirefoxDriver();
			} else if(browser.equalsIgnoreCase("ie")) {
				System.setProperty("webdriver.ie.driver",
						"./drivers/IEDriverServer.exe");
				driver = new InternetExplorerDriver();
			}
			threadloc.set(driver);
			driver = (RemoteWebDriver) threadloc.get();
			driver.navigate().to(url);
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			//return driver;
		} catch (Exception e) {
		System.err.println("The browser could not be launched");
		} 
	finally{
		takeSnap();

}
		return driver;
}
}
