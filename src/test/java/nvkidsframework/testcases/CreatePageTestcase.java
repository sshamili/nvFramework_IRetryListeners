package nvkidsframework.testcases;



import java.io.IOException;

import org.apache.http.auth.UsernamePasswordCredentials;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import nv.leafbot.pages.LoginPage;
//import nv.selenium.api.base.SeleniumBase;
import nv.selenium.utils.CommonLocators;
import nv.selenium.utils.DataLibrary;


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
	
	@DataProvider(name = "fetchData")
	public Object[][] fetchData() throws IOException {
		return DataLibrary.readExcelData("CreateLeaf_data");
	}	
	
		@Test(dataProvider="fetchData",testName="TC_001", description="create leaf for demo sales manager")
		public void createLeaf_SalesManager(String username, String password) throws InterruptedException {
			new LoginPage(driver, test)
			.enterUserName(username)
			.enterPassword(password)
			.clickLogin()
			.clickLogout();		
		}
		
		@Test(dataProvider="fetchData",testName="TC_002", description="create leaf for demo CSR")
		public void createLeaf_DemoCSR(String username, String password) throws InterruptedException {
			new LoginPage(driver, test)
			.enterUserName(username)
			.enterPassword(password)
			.clickLogInForFailer()
			.verifyErrorMsg("User not found");
		}

}
