package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utility.PageObjectRepo;
import utility.ReusableActions;

public class DashboardPage extends PageObjectRepo {

	WebDriver driver;
	ReusableActions actions = new ReusableActions();

	public DashboardPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//h6[@class='oxd-text oxd-text--h6 oxd-topbar-header-breadcrumb-module']")
	WebElement dashboardLabel;
	@FindBy(xpath = "//i[@class='oxd-icon bi-caret-down-fill oxd-userdropdown-icon']")
	WebElement dropDownButton;
	@FindBy(xpath = "//a[text()='Change Password']")
	WebElement changePasswordLink;
	@FindBy(xpath = "//a[contains(text(),'Logout')]")
	WebElement logoutLink;
	@FindBy(xpath = "//p[@class='oxd-userdropdown-name']")
	WebElement userName;

	@FindBy(xpath = "//a[normalize-space()='PIM']")
	WebElement pimLink;

	public boolean isBrokenLinkOnDashboardPage() {
		boolean brokenLink = false;
		// actions.fluentWait(driver, loginButton, 0);
		if (actions.brokenLinks(driver) == true) {
			brokenLink = true;
		}
		return brokenLink;
	}

	public String getText() {
		actions = new ReusableActions();
		actions.fluentWait(driver, dashboardLabel, 10);
		return dashboardLabel.getText();

	}

	public boolean isUserNameDisplayed() {
		boolean displayed = false;
		if (userName.isDisplayed()) {
			displayed = true;
		}
		return displayed;
	}

	public void profileDropDown() {
		actions.fluentWait(driver, dropDownButton, 15);
		dropDownButton.click();

	}

	public UpdatePasswordPage changePasswordLink() {
		actions.fluentWait(changePasswordLink, driver, 10);
		changePasswordLink.click();
		return new UpdatePasswordPage(driver);
	}

	public void logoutLink() {
		logoutLink.click();
	}

	public SearchEmployeePage clickOnPimLink() {
		actions.fluentWait(driver, pimLink, 10);
		pimLink.click();
		return new SearchEmployeePage(driver);
	}

	public CustomFieldConfigurationPage clickPimLink() {
		actions.fluentWait(driver, pimLink, 10);
		pimLink.click();
		return new CustomFieldConfigurationPage(driver);
	}

}