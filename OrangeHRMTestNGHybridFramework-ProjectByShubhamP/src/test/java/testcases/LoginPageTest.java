package testcases;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.NoSuchElementException;
import org.testng.Assert;
import org.testng.annotations.Test;

import base.TestBase;
import pages.LoginPage;
import utility.DataProviderClass;


public class LoginPageTest extends TestBase {

	@Test(priority = 5)
	public void verifyLoginWithValidCredendial() {

		loginPage = new LoginPage(driver);

		loginPage.enterUsername(userName);
		log.info("----------User entered username------------");

		loginPage.enterPassword(password);
		log.info("----------User entered password------------");

		dashboardPage = loginPage.clickLoginButton();
		log.info("----------User Clicked Login Button--------");

		String dashboardLabel = dashboardPage.getText();
		if (dashboardText.equals(dashboardLabel) && dashboardPage.isUserNameDisplayed() == true) {
			Assert.assertTrue(true);
			log.info("---------verifyLoginWithValidCredendial is Passed---------");
		} else {
			log.info("********verifyLoginWithValidCredendial is Failed**********");
			captureScreenshot(driver, "verifyLoginWithValidCredendial");
			Assert.assertTrue(false, "Actual Text Doesn't match with Expected Text");

		}
		dashboardPage.profileDropDown();
		dashboardPage.logoutLink();

	}

	@Test(priority = 1, dataProvider = "LoginTestDataSet", dataProviderClass = DataProviderClass.class, invocationCount = 1)
	public void verifyLoginWithInvalidCredendial(String userName, String password) {

		loginPage = new LoginPage(driver);
		loginPage.enterUsername(userName);
		log.info("----------User entered username------------");

		loginPage.enterPassword(password);
		log.info("----------User entered password------------");

		loginPage.clickLoginButton();
		log.info("----------User Clicked Login Button--------");

		String actualText = loginPage.getText();
		Assert.assertEquals(actualText, invalidLoginText);
		log.info("---------verifyLoginWithInValidCredendial is Passed--------");
	}

	@Test(priority = 4, description = "LoginWithUpdatedPassword")
	public void verifyLoginWithUpdatedPassword() {

		loginPage = new LoginPage(driver);
		loginPage.enterUsername(userName);
		loginPage.enterPassword(password);
		dashboardPage = loginPage.clickLoginButton();

		dashboardPage.profileDropDown();
		updatePasswordPage = dashboardPage.changePasswordLink();

		updatePasswordPage.enterCurrentPassword(password);
		
		updatePasswordPage.setNewPassword("Shubham456@");
		log.info("---------Setting new Password---------");
		updatePasswordPage.enterConfirmPassword("Shubham456@");
		log.info("---------Entering new Password---------");
		updatePasswordPage.clickOnSaveButton();
		boolean popup = updatePasswordPage.isDisplayed();
		Assert.assertTrue(popup);
		log.info("---------Password is updated---------");
		dashboardPage.profileDropDown();
		dashboardPage.logoutLink();
		log.info("---------Clicked on logout---------");
		driver.get(url);
		loginPage.enterUsername(userName);
		loginPage.enterPassword("Shubham123@");
		log.info("---------Entered updated password---------");
		loginPage.clickLoginButton();
		log.info("-------clicked on Login-------");

		try {
			if (dashboardPage.getText().equals(dashboardText)) {
				log.info("--------verifyLoginWithUpdatedPassword is Passed-----");
				Assert.assertTrue(true);
			}
		} catch (NoSuchElementException e) {
			log.info("*********verifyLoginWithUpdatedPassword is Failed********");
			captureScreenshot(driver, "verifyLoginWithUpdatedPassword");
			Assert.assertTrue(false, "unable to login with updated password");
		}
		dashboardPage.profileDropDown();
		dashboardPage.logoutLink();
	}

	@Test(priority = 2)
	public void verifyApplicationLogo() {

		loginPage = new LoginPage(driver);

		if (loginPage.isLogoDisplayed() == true) {
			log.info("--------Logo is Displayed on the LoginPage---------");
			log.info("--------verifyApplicationLogo is Passed------------");

			Assert.assertTrue(true);
		} else {
			log.info("--------verifyApplicationLogo is failed---------");
			Assert.assertTrue(false, "logo is not present on the login Page");
		}
	}

	@Test(priority = 3)
	public void verifyManadatoryFieldMessage() {
		loginPage = new LoginPage(driver);
		loginPage.clickLoginButton();
		String expectedText = "Required";
		if (loginPage.isRequiredTextDispalyed(expectedText) == true) {
			log.info("--------verifyManadatoryFieldMessage is Passed---------");

			Assert.assertTrue(true);
		} else {
			log.info("--------verifyManadatoryFieldMessage is Failed---------");

			Assert.assertTrue(false, "text reqiued is not displayed ");
		}
	}

}
