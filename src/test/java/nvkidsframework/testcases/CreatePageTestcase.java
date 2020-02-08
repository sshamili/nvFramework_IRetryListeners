package nvkidsframework.testcases;



import org.testng.annotations.Test;

import nv.leafbot.pages.LoginPage;
import nv.selenium.api.base.SeleniumBase;

public class CreatePageTestcase extends SeleniumBase{
	
	
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
		@Test(testName="TC_001", description="create leaf for demo sales manager")
		public void createLeaf_SalesManager() throws InterruptedException {
			new LoginPage(driver, test)
			.enterUserName("DemoSalesManager")
			.enterPassword("crmsfa")
			.clickLogin()
			.clickLogout();		
		}
		
		@Test(testName="TC_002", description="create leaf for demo CSR")
		public void createLeaf_DemoCSR() throws InterruptedException {
			new LoginPage(driver, test)
			.enterUserName("Demo1CSR")
			.enterPassword("crmsfa")
			.clickLogInForFailer()
			.verifyErrorMsg("User not found");
		}

}
