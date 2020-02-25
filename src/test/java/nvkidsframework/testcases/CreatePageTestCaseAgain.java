package nvkidsframework.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import nv.leafbot.pages.LoginPage;
//import nv.selenium.api.base.SeleniumBase;
import nv.selenium.utils.CommonLocators;

public class CreatePageTestCaseAgain extends CommonLocators{
	
	
//	public void setValues() {
//		testCaseName = "Login and LoginOut";
//		testDescription = "Login testCase using DemoSalesManager UserName and LogOut";
//		nodes = "Leads";
//		authors = "Gopinath";
//		category = "Smoke";
//		dataSheetName = "TC001";
//		dataSheetName1 = "TC009";
//	
//}
	
//	@DataProvider(name = "fetchData")
//	public Object[][] fetchData() throws IOException {
//		return DataLibrary.readExcelData("CreateLeaf_data");
//	}
	
	@Test(testName="TC_003", description="Again create leaf for demo sales manager")
	public void createLeaf_SalesManager() throws InterruptedException {
		WebDriver driver = threadloc.get();
		new LoginPage(driver, test)
		.enterUserName("democsr2")
		.enterPassword("crmsfa")
		.clickLogin()
		.clickLogout();		
	}
	
	@Test(testName="TC_004", description=" Again create leaf for demo CSR")
	public void createLeaf_DemoCSR() throws InterruptedException {
		WebDriver driver = threadloc.get();
		new LoginPage(driver, test)
		.enterUserName("demmmmo")
		.enterPassword("crmsfa")
		.clickLogInForFailer()
		.verifyErrorMsg("User not found");
	}

}
