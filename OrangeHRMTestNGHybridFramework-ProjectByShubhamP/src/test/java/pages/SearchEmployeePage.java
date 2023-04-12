package pages;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utility.PageObjectRepo;
import utility.ReusableActions;

public class SearchEmployeePage extends PageObjectRepo {

	WebDriver driver;
	ReusableActions actions = new ReusableActions();

	public SearchEmployeePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//a[normalize-space()='Employee List']")
	WebElement empListTab;

	@FindBy(xpath = "//form[@class='oxd-form']/child::div//input[contains(@placeholder,'Type for hints')]")
	WebElement empNameTextField;

	@FindBy(xpath = "(//input[contains(@class,'oxd-input')])[2]")
	WebElement empIdTextField;

	@FindBy(xpath = "//button[@type='submit']")
	WebElement searchButton;
	@FindBy(xpath = "//button[@type='reset']")
	WebElement resetButton;

	@FindBy(xpath = "/html[1]/body[1]/div[1]/div[1]/div[2]/div[2]/div[1]/div[2]/div[3]/div[1]/div[2]/div[1]/div[1]/div[3]/div[1]")
	List<WebElement> empRecord;
	//div[@class='oxd-table-card']//div[3]
//	/html[1]/body[1]/div[1]/div[1]/div[2]/div[2]/div[1]/div[2]/div[3]/div[1]/div[2]/div[1]/div[1]/div[3]/div[1]
	
	@FindBy(xpath = "//div[@class='oxd-table-card']//div[3]")
	WebElement empNameRecord;
	// div[@class='oxd-table-body']//div[@role='row']/div[4][@role='cell']
	// div[@class="oxd-table-body"]//div[@role="row"]/div[@role="cell"]

	@FindBy(xpath = "//span[normalize-space()='(1) Record Found']")
	WebElement empRecordFoundMsg;

	By employeeRecord = By.xpath("//div[@class='oxd-table-card']//div[3]");

	@FindBy(xpath = "//i[contains(@class,'bi-trash')]")
	WebElement deleteEmpButton;
	@FindBy(xpath = "//i[contains(@class,'bi-pencil-fill')]")
	WebElement editEmpButton;

	@FindBy(xpath = "//button[normalize-space()='Add']")
	WebElement addEmployeeButton;

	@FindBy(xpath = "//button[normalize-space()='Yes, Delete']")
	WebElement deleteConfirmationButton;

	@FindBy(xpath = "//div[@id='oxd-toaster_1']/child::div//p[text()='Successfully Deleted']")
	WebElement deleteConfirmationMessage;

	@FindBy(xpath = "//span[normalize-space()='No Records Found']")
	WebElement noRecordMessage;

	@FindBy(xpath = "//p[@class='oxd-text oxd-text--p oxd-text--toast-message oxd-toast-content-text']")
	WebElement noRecordMsgPopup;

	public void checkBrokenLinkInSearchEmpPage() {
		actions.brokenLinks(driver);
	}

	public void clickOnEmpListTab() {
		empListTab.click();
	}

	public void enterEmployeeName(String empName) {
		wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOf(empNameTextField)).sendKeys(empName);
	}

	public void enterEmployeeId(String empId) {
		actions.fluentWait(driver, empIdTextField, 10);
		empIdTextField.sendKeys(empId);
	}

	public void clickOnSearchButton() {
		searchButton.click();
	}

	public void clickOnResetButton() {
		resetButton.click();
	}

	public boolean isEmpFound(String empName) {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		boolean found = false;
		actions.fluentWait(driver, empRecordFoundMsg, 10);
		try {
			for (WebElement employeeData : empRecord) {
				actions.fluentWait(driver, employeeData, 25);
				if (employeeData.getText().trim().contains(empName) || empRecordFoundMsg.isDisplayed()) {
					found = true;
					break;
				}
			}
		} catch (StaleElementReferenceException e) {
			empRecord = driver.findElements(employeeRecord);
			actions.fluentWait(driver, empRecord, 20);
			for (WebElement employeeData : empRecord) {
				actions.fluentWait(driver, employeeData, 25);
				if (employeeData.getText().trim().contains(empName) || empRecordFoundMsg.isDisplayed()) {
					found = true;
					break;
				}
			}
		}

		return found;
	}

	public boolean isEmployeeFound(String empName) {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		boolean found = false;
		actions.fluentWait(driver, empRecordFoundMsg, 15);
		actions.fluentWait(driver, empNameRecord, 15);
		if (empNameRecord.getText().trim().contains(empName) || empRecordFoundMsg.isDisplayed()) {
			found = true;
		}
		return found;
	}

	public boolean isNoRecordFoundDisplayed() {
		boolean displayed = false;
		actions.fluentWait(driver, noRecordMsgPopup, 20);
		if (noRecordMessage.isDisplayed() || noRecordMsgPopup.isDisplayed()) {
			displayed = true;
		}
		return displayed;
	}

	public void clickOnDeleteEmpButton() {
		actions.fluentWait(deleteEmpButton, driver, 15);
		deleteEmpButton.click();

	}

	public boolean isEmpDeleted() {

		boolean deleted = false;
		actions.fluentWait(deleteConfirmationButton, driver, 15);
		deleteConfirmationButton.click();
		actions.fluentWait(driver, deleteConfirmationMessage, 15);
		if (deleteConfirmationMessage.getText().contains("Successfully Deleted")) {
			deleted = true;
		}
		return deleted;
	}

	public AddEmployeePage clickOnEditEmpButton() {
		actions.fluentWait(editEmpButton, driver, 15);
		editEmpButton.click();
		return new AddEmployeePage(driver);

	}

	public EmployeeDetailsPage clickEditEmpButton() {
		actions.fluentWait(editEmpButton, driver, 15);
		editEmpButton.click();
		return new EmployeeDetailsPage(driver);

	}

	public AddEmployeePage clickOnAddEmployeeButton() {

		actions.fluentWait(addEmployeeButton, driver, 15);
		addEmployeeButton.click();
		return new AddEmployeePage(driver);

	}

}
