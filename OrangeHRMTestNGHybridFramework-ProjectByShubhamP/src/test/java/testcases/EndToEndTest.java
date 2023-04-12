package testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.TestBase;
import pages.LoginPage;
import utility.DataProviderClass;

public class EndToEndTest extends TestBase {

	@BeforeMethod(enabled = true)
	public void verifyLogin() {
		loginPage = new LoginPage(driver);
		loginPage.enterUsername(userName);
		loginPage.enterPassword(password);
		dashboardPage = loginPage.clickLoginButton();
		String dashboardLabel = dashboardPage.getText();
		Assert.assertEquals(dashboardLabel, dashboardText);

	}

	@Test(priority = 1, dataProvider = "EndToEndDataSet1", dataProviderClass = DataProviderClass.class)
	public void verifyEndToEndBasicFlow(String firstName, String lastName, String empId) {

		searchEmployeePage = dashboardPage.clickOnPimLink();
		addEmployeePage = searchEmployeePage.clickOnAddEmployeeButton();
       
		addEmployeePage.enterEmpFirstName(firstName);
		addEmployeePage.enterEmpLastName(lastName);
		addEmployeePage.enterEmpId(empId);
		addEmployeePage.clickOnSaveButton();
		if (addEmployeePage.isEmployeeAdded(firstName + " " + lastName) == true) {
			Assert.assertTrue(true, "Employee is added into the system");

			searchEmployeePage.clickOnEmpListTab();
			searchEmployeePage.enterEmployeeName(firstName + " " + lastName);
			searchEmployeePage.clickOnSearchButton();
			if (searchEmployeePage.isEmployeeFound(firstName + " " + lastName) == true) {
				Assert.assertTrue(true);
				searchEmployeePage.clickOnDeleteEmpButton();

				if (searchEmployeePage.isEmpDeleted() == true) {
					Assert.assertTrue(true, "Employee deleted sucessfully");
				} else {
					Assert.assertTrue(false);
				}
			} else {
				captureScreenshot(driver, "verifyEndToEndBasicFlow");
				Assert.assertTrue(false, "Employee not found!");
			}
		} else {
			captureScreenshot(driver, "verifyEndToEndBasicFlow");
			Assert.assertTrue(false, "Employee not added!");
		}

	}

	@Test(priority = 3)
	public void verifyActiveLinks() {
		loginPage = new LoginPage(driver);
		try {
			if (loginPage.isBrokenLinkPresentOnLoginPage() == true) {

				log.info("Broken Link found on the LoginPage");
				captureScreenshot(driver, "verifyActiveLinks");
				Assert.assertTrue(false, "Broken link/links are Present");
			} else {
				log.info("Only Active links are Found");
				Assert.assertTrue(true, "Only Active links are Present");
			}
		} catch (java.lang.AssertionError e) {
			e.printStackTrace();
		}

		finally {
			loginPage.enterUsername(userName);
			loginPage.enterPassword(password);
			dashboardPage = loginPage.clickLoginButton();
		}
		if (dashboardPage.isBrokenLinkOnDashboardPage() == true) {

			log.info("Broken Link found on the DashboardPage");
			captureScreenshot(driver, "verifyActiveLinks");
			Assert.assertTrue(false, "Broken link/links are Present");
		} else {
			log.info("Only Active Links are found on the DashboardPage");
			Assert.assertTrue(true, "Only Active links are Present");
		}
		dashboardPage.profileDropDown();
		dashboardPage.logoutLink();
	}

	@Test(priority = 2, dataProvider = "EndToEndAlternateFlowDataSet", dataProviderClass = DataProviderClass.class)
	public void verifyEndToEndAlternateFlow(String firstName, String lastName, String empId, String updateFirstName,
			String updateLastName) {
		searchEmployeePage = dashboardPage.clickOnPimLink();
		addEmployeePage = searchEmployeePage.clickOnAddEmployeeButton();

		addEmployeePage.enterEmpFirstName(firstName);
		addEmployeePage.enterEmpLastName(lastName);
		addEmployeePage.enterEmpId(empId);
		addEmployeePage.clickOnSaveButton();
		if (addEmployeePage.isEmployeeAdded(firstName + " " + lastName) == true) {
			Assert.assertTrue(true, "Employee is added into the system");

			searchEmployeePage.clickOnEmpListTab();
			searchEmployeePage.enterEmployeeName(firstName + " " + lastName);
			searchEmployeePage.clickOnSearchButton();
			if (searchEmployeePage.isEmployeeFound(firstName + " " + lastName) == true) {
				Assert.assertTrue(true);

				empDeatilsPage = searchEmployeePage.clickEditEmpButton();
				log.info("Clickeddd**************************************");

				if (empDeatilsPage.isUpdatedEmpNameDisplayed(updateFirstName, updateLastName) == true) {
					log.info("Displayed************************************");
					Assert.assertTrue(true);
				} else {
					log.info("Employee name is not updated...............");
					captureScreenshot(driver, "verifyEndToEndAlternateFlow");
					Assert.assertTrue(false);
				}
			}
		} else {
			Assert.assertTrue(false, "Employee is not added!");
		}
	}

	@AfterMethod(enabled = true)
	public void clickOnLogout() {
		dashboardPage.profileDropDown();
		dashboardPage.logoutLink();

	}
	/*
	 * @Test(dataProvider = "EndToEndDataSet2", dataProviderClass =
	 * DataSupplier.class) public void verifyEndToEndAlternateFlow(String empName,
	 * String empId, String email, String contactNo) { searchEmployeePage =
	 * dashboardPage.clickOnPimLink();
	 * 
	 * searchEmployeePage.enterEmployeeName(empName);
	 * searchEmployeePage.clickOnSearchButton(); if
	 * (searchEmployeePage.isEmployeeFound(empName) == true) {
	 * Assert.assertTrue(true); empDeatilsPage =
	 * searchEmployeePage.clickEditEmpButton();
	 * empDeatilsPage.clickOnContactDetails();
	 * driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	 * empDeatilsPage.setWorkContactNo(contactNo);
	 * empDeatilsPage.setWorkEmail(email); empDeatilsPage.clickOnSaveButton();
	 * log.info("clicked on save************************************"); if
	 * (empDeatilsPage.isContactDetailsSet(contactNo, email) == true) {
	 * Assert.assertTrue(true, "Contact Details is set Successfully"); } else {
	 * Assert.assertTrue(false, "Contact Details is not set"); } } else {
	 * Assert.assertTrue(false); log.info("not Found"); } }
	 */
}
