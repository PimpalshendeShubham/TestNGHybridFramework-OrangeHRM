package testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.TestBase;
import pages.LoginPage;
import utility.DataProviderClass;

public class AddEmployeePageTest extends TestBase {

	@BeforeMethod(enabled = true)
	public void verifyLogin() {
		loginPage = new LoginPage(driver);
		loginPage.enterUsername(userName);
		loginPage.enterPassword(password);
		dashboardPage = loginPage.clickLoginButton();

	}

	@Test(dataProvider = "EmpAddTestDataSet", dataProviderClass = DataProviderClass.class)
	public void verifyAddEmployee(String firstName, String lastName, String empId) {
		searchEmployeePage = dashboardPage.clickOnPimLink();

		addEmployeePage = searchEmployeePage.clickOnAddEmployeeButton();
		log.info("Clicked on Add...................................");
		addEmployeePage.enterEmpFirstName(firstName);
		addEmployeePage.enterEmpLastName(lastName);
		log.info("entered lastname...................................");
		addEmployeePage.enterEmpId(empId);
		log.info("entered ID...................................");
		addEmployeePage.clickOnSaveButton();

		log.info("Clicked on save...................................");
		if (addEmployeePage.isEmployeeAdded(firstName + " " + lastName) == true) {
			Assert.assertTrue(true, "Employee is added into the system");
			log.info("-------Employee Added with Name: " + firstName + "----------");
		} else {
			captureScreenshot(driver, "verifyAddEmployee");
			Assert.assertTrue(false, "Employee is not added!");
		}

	}

	@AfterMethod(enabled = true)
	public void clickOnLogout() {
		dashboardPage.profileDropDown();
		dashboardPage.logoutLink();

	}
}
