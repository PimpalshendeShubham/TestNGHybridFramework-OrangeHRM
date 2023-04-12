package testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.TestBase;
import pages.LoginPage;
import utility.DataProviderClass;

public class SearchEmployeePageTest extends TestBase {

	@BeforeMethod(enabled = true)
	public void userLogin() {
		loginPage = new LoginPage(driver);
		loginPage.enterUsername(userName);
		loginPage.enterPassword(password);
		dashboardPage = loginPage.clickLoginButton();

	}

	@Test(priority = 1, dataProvider = "EmpSearchTestDataSet1", dataProviderClass = DataProviderClass.class)
	public void verifyValidEmployeeSearch(String empName, String empId) {

		searchEmployeePage = dashboardPage.clickOnPimLink();

		searchEmployeePage.enterEmployeeName(empName);
		searchEmployeePage.clickOnSearchButton();
		if (searchEmployeePage.isEmployeeFound(empName) == true) {
			Assert.assertTrue(true);
		}

		else {
			log.info("*********verifyValidEmployeeSearch is Failed**********");
			captureScreenshot(driver, "verifyValidEmployeeSearch");
			Assert.assertTrue(false);
		}
	}

	@Test(priority = 3, dataProvider = "EmpSearchTestDataSet2", dataProviderClass = DataProviderClass.class)
	public void verifyInvalidEmployeeSearch(String empName, String empId) {

		searchEmployeePage = dashboardPage.clickOnPimLink();

		searchEmployeePage.enterEmployeeName(empName);
		searchEmployeePage.clickOnSearchButton();
		if (searchEmployeePage.isNoRecordFoundDisplayed() == true) {
			Assert.assertTrue(true);
		}

		else {                  
			Assert.assertTrue(false);
		}                        
	}

	@Test(priority = 2, dataProvider = "EmpSearchAndDeleteTestDataSet", dataProviderClass = DataProviderClass.class)
	public void verifyDeleteEmployee(String empName, String empId) {
		searchEmployeePage = dashboardPage.clickOnPimLink();
		searchEmployeePage.enterEmployeeName(empName);
		searchEmployeePage.clickOnSearchButton();
		searchEmployeePage.clickOnDeleteEmpButton();

		if (searchEmployeePage.isEmpDeleted() == true) {
			Assert.assertTrue(true, "Employee deleted sucessfully");
		} else {
			captureScreenshot(driver, "verifyDeleteEmployee");
			Assert.assertTrue(false);
		}
	}
                                                                                            
	@Test(priority = 1, dataProvider = "EmpSearchDataSet", dataProviderClass = DataProviderClass.class, enabled = false)
	public void verifyValidEmpSearch(String empName, String empId) {

		searchEmployeePage = dashboardPage.clickOnPimLink();

		searchEmployeePage.enterEmployeeName(empName);
		searchEmployeePage.clickOnSearchButton();
		if (searchEmployeePage.isEmployeeFound(empName) == true) {
			Assert.assertTrue(true);
		}

		else {
			Assert.assertTrue(false);
		}
	}

	@AfterMethod(enabled = true)
	public void userLogout() {
		dashboardPage.profileDropDown();
		dashboardPage.logoutLink();

	}
}
