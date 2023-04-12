package testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.TestBase;
import pages.LoginPage;
import utility.DataProviderClass;

public class CustomFieldConfigurationPageTest extends TestBase {

	int customFieldCount = 1;

	@BeforeMethod(enabled = true)
	public void userLogin() {
		loginPage = new LoginPage(driver);
		loginPage.enterUsername(userName);
		loginPage.enterPassword(password);
		dashboardPage = loginPage.clickLoginButton();

	}

	@Test(priority = 1, dataProvider = "CustomFieldTestDataSet1", dataProviderClass = DataProviderClass.class)
	public void verifyAddCustomField(String customFieldName, String category) {

		customFieldPage = dashboardPage.clickPimLink();
		customFieldPage.navigateToCustomFieldPage();
		customFieldPage.clickOnAddButton();
		customFieldPage.enterFieldName(customFieldName);
		customFieldPage.clickAndSelectScreenDropdownItem(category);
		customFieldPage.clickAndSelectTypeDropdownItem();
		customFieldPage.clickOnSaveButton();

		if (customFieldPage.isCustomFieldAdded() == true) {
			Assert.assertTrue(true);
			customFieldCount += 1;
		} else {
			Assert.assertTrue(false);
		}

	}

	@Test(priority = 2)
	public void verifyCustomRecordMessage() {
		customFieldPage = dashboardPage.clickPimLink();

		customFieldPage.navigateToCustomFieldPage();
		String fieldCount = String.valueOf(customFieldCount);
		if (customFieldPage.getCustomFieldRecordMsg().contains(fieldCount)) {
			Assert.assertTrue(true);
		} else {
			captureScreenshot(driver, "verifyCustomRecordMessage");
			Assert.assertTrue(false);
		}

	}

	@Test(priority = 3, dataProvider = "CustomFieldTestDataSet2", dataProviderClass = DataProviderClass.class)
	public void verifyErrorMessageForField(String existCustomFieldName, String validationText, String requiredText) {
		customFieldPage = dashboardPage.clickPimLink();
		customFieldPage.navigateToCustomFieldPage();
		customFieldPage.clickOnAddButton();
		customFieldPage.enterFieldName(existCustomFieldName);

		if (customFieldPage.isCustomFieldAlreadyExist(validationText) == true) {
			Assert.assertTrue(true);
		} else {
			Assert.assertTrue(false, "errorText is not displyaed");
		}
		customFieldPage.clickOnSaveButton();
		if (customFieldPage.isRequiredTextDisplayed(requiredText) == true) {
			Assert.assertTrue(true);
		} else {
			captureScreenshot(driver, "verifyErrorMessageForField");
			Assert.assertTrue(false, "requiredText is not displyaed");
		}

	}

	@AfterMethod(enabled = true)
	public void clickOnLogout() {
		dashboardPage.profileDropDown();
		dashboardPage.logoutLink();

	}
}
