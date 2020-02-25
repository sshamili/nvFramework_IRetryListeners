package nvkidsframework.testcases;



import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import nv.leafbot.pages.LoginPage;
//import nv.selenium.api.base.SeleniumBase;
import nv.selenium.utils.CommonLocators;


public class CreatePageTestcase extends CommonLocators{
	
	
//		public void setValues() {
//			testCaseName = "Login and LoginOut";
//			testDescription = "Login testCase using DemoSalesManager UserName and LogOut";
//			nodes = "Leads";
//			authors = "Gopinath";
//			category = "Smoke";
//			dataSheetName = "TC001";
//			dataSheetName1 = "TC009";
//		
//	}
	
	/*
	 * @DataProvider(name = "fetchData") public Object[][] fetchData() throws
	 * IOException { return DataLibrary.readExcelData("CreateLeaf_data"); }
	 */	
	
		@Test(testName="TC_001", description="create leaf for demo sales manager")
		public void createLeaf_SalesManager() throws InterruptedException {
			WebDriver driver = threadloc.get();
			System.out.println("Executing Democsr");
			new LoginPage(driver, test)
			.enterUserName("democsr")
			.enterPassword("crmsfa")
			.clickLogin()
			.clickLogout();	
			System.out.println("completed demo csr");
		}
		
		@Test(testName="TC_002",  description="create leaf for demo CSR")
		public void createLeaf_DemoCSR() throws InterruptedException {
			System.out.println("Executing DemoSalesmanager");
			WebDriver driver = threadloc.get();
			new LoginPage(driver, test)
			.enterUserName("demosalemanager")
			.enterPassword("crmsfa")
			.clickLogInForFailer()
			.verifyErrorMsg("User not found");
			System.out.println("completed demo sales manager");
		}

}
