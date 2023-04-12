package pages;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utility.ReusableActions;

public class EmployeeDetailsPage extends ReusableActions {

	WebDriver driver;
	ReusableActions actions = new ReusableActions();

	public EmployeeDetailsPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "/html[1]/body[1]/div[1]/div[1]/div[2]/div[2]/div[1]/div[1]/div[1]/div[2]/div[1]/form[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[2]/input[1]")
	WebElement employeeFirstName;
	// input[@name='firstName']
	@FindBy(xpath = "/html[1]/body[1]/div[1]/div[1]/div[2]/div[2]/div[1]/div[1]/div[1]/div[2]/div[1]/form[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[3]/div[2]/input[1]")
	WebElement employeeLastName;
	// input[@name='lastName']

	@FindBy(xpath = "//div[@class='orangehrm-horizontal-padding orangehrm-vertical-padding']//button[@type='submit'][normalize-space()='Save']")
	WebElement saveButton;

	@FindBy(xpath = "//div[@class='orangehrm-edit-employee-name']")
	WebElement employeeNameText;

	@FindBy(xpath = "//a[normalize-space()='Contact Details']")
	WebElement contactDetailsLink;

	@FindBy(xpath = "(//input[@class='oxd-input oxd-input--active'])[9]")
	WebElement employeeWorkContactNo;

	@FindBy(xpath = "(//input[@class='oxd-input oxd-input--active'])[10]")
	WebElement employeeWorkEmail;

	@FindBy(xpath = "/html[1]/body[1]/div[1]/div[1]/div[2]/div[2]/div[1]/div[1]/div[1]/div[2]/div[1]/form[1]/div[5]/button[1]")
	WebElement saveBtn;

	@FindBy(xpath = "//div[@class='oxd-toast oxd-toast--success oxd-toast-container--toast']")
	WebElement updateValidationMessage;

	public boolean isUpdatedEmpNameDisplayed(String fname, String lname) {
		boolean updated = false;
		JavascriptExecutor js = (JavascriptExecutor) driver;
		actions.fluentWait(driver, employeeNameText, 10);
		// String oldNameText = employeeNameText.getText();

		actions.fluentWait(employeeFirstName, driver, 10);
		Actions act = new Actions(driver);
		act.moveToElement(employeeFirstName, 702, 270);
		employeeFirstName.clear();
		employeeFirstName.sendKeys(fname);
		actions.fluentWait(driver, employeeLastName, 10);
		employeeFirstName.click();
		employeeLastName.clear();
		employeeLastName.sendKeys(lname);
		actions.fluentWait(saveButton, driver, 15);
		// act.moveToElement(saveButton,1405, 693).click();
		saveButton.click();
//		try {
//			Thread.sleep(4000);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
		String fName = employeeFirstName.getAttribute("value");
		String lName = employeeLastName.getAttribute("value");
		js.executeScript("window.scrollTo(0, 0)");
		actions.fluentWait(driver, employeeNameText, 15);
		String NameText = employeeNameText.getText();

		if ((NameText).equals(fName + " " + lName)) {
			updated = true;
		}
		return updated;
	}

	public void clickOnContactDetails() {
		actions.fluentWait(contactDetailsLink, driver, 10);
		contactDetailsLink.click();
	}

	public void setWorkEmail(String email) {
		// actions.fluentWait(driver, employeeWorkEmail, 10);
		// driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Actions act = new Actions(driver);
		act.sendKeys(Keys.TAB).sendKeys(email).perform();

	}

	public void setWorkContactNo(String contactNo) {
		Actions act = new Actions(driver);
		actions.fluentWait(driver, employeeWorkContactNo, 10);
		act.moveToElement(employeeWorkContactNo).sendKeys(contactNo).perform();
	}

	public void clickOnSaveButton() {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JavascriptExecutor js = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		js.executeScript("arguments[0].click()", saveButton);

//		Actions act=new Actions(driver);
//		act.moveToElement(saveButton, 1355, 691).click();
		// act.moveToElement(saveButton).click().perform();
		// saveButton.click();
	}

	public boolean isContactDetailsSet(String empId, String contactNo) {
		boolean set = false;
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		actions.fluentWait(driver, contactDetailsLink, 15);
		if (employeeWorkContactNo.getAttribute("value").equals(contactNo)
				&& employeeWorkEmail.getAttribute("value").equals(empId)) {
			set = true;
		}
//		actions.fluentWait(driver, updateValidationMessage, 10);
//		boolean result=updateValidationMessage.isDisplayed();
//		if(result)
//		{
//			set=true;
//		}
		return set;
	}

}