package pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utility.ReusableActions;

public class AddEmployeePage extends ReusableActions {

	WebDriver driver;
	ReusableActions actions = new ReusableActions();

	public AddEmployeePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(name = "firstName")
	WebElement firstNameTextField;

	@FindBy(name = "lastName")
	WebElement lastNameTextField;

	@FindBy(xpath = "//div[@class='oxd-input-group oxd-input-field-bottom-space']//div//input[@class='oxd-input oxd-input--active']")
	WebElement empIdTextField;

	@FindBy(xpath = "//button[@type='submit']")
	WebElement saveButton;

	@FindBy(xpath = "//h6[text()='abc Haq']")
	WebElement employeeNameText;

	@FindBy(xpath = "(//input[@class='oxd-input oxd-input--active'])[3]")
	WebElement employeeIdTextBox;

	@FindBy(xpath = "//div[@class='orangehrm-horizontal-padding orangehrm-vertical-padding']//button[@type='submit'][normalize-space()='Save']")
	WebElement saveFormButton;

	public void checkBrokenLinkInAddEmpPage() {
		actions.brokenLinks(driver);
	}

	public void enterEmpFirstName(String firstname) {
		actions.fluentWait(driver, firstNameTextField, 15);
		firstNameTextField.sendKeys(firstname);

	}

	public void enterEmpLastName(String lastname) {
		lastNameTextField.sendKeys(lastname);
	}

	public void enterEmpId(String id) {
		actions.fluentWait(driver, empIdTextField, 15);
		empIdTextField.sendKeys(id);

	}

	public void clickOnSaveButton() {
		actions.fluentWait(saveButton, driver, 20);
		try {
			saveButton.click();
		} catch (ElementClickInterceptedException e) {
			driver.findElement(By.xpath("//button[@type='submit']")).click();
		}
	}

	public boolean isEmployeeAdded(String empName) {
		boolean added = false;
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		WebElement employeeNameText = driver.findElement(By.xpath("//h6[text()='" + empName + "']"));

		actions.fluentWait(driver, employeeNameText, 20);
		if (employeeNameText.isDisplayed()) {
			added = true;
		}
		return added;
	}

	public void setEmpId(String empId) {
		actions.fluentWait(driver, employeeIdTextBox, 10);

		employeeIdTextBox.sendKeys(empId);
		saveFormButton.click();
	}

	public boolean isEmpIdSet(String empId) {
		boolean isReset = false;
		actions.fluentWait(driver, employeeIdTextBox, 10);
		if (employeeIdTextBox.getAttribute("value").equals(empId)) {
			isReset = true;
		}
		return isReset;
	}
}
