package pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import utility.ReusableActions;

public class CustomFieldConfigurationPage {

	WebDriver driver;
	ReusableActions actions = new ReusableActions();

	public CustomFieldConfigurationPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//button[normalize-space()='Add']")
	WebElement addButton;

	@FindBy(xpath = "//h6[normalize-space()='Custom Fields']")
	WebElement customFieldText;

	@FindBy(xpath = "//span[@class='oxd-text oxd-text--span']")
	WebElement recordFoundMessage;

	@FindBy(xpath = "//div[@class='orangehrm-card-container']/child::form[@class='oxd-form']//input[@class='oxd-input oxd-input--active']")
	WebElement fieldName;

	@FindBy(xpath = "//span[normalize-space()='Already exists']")
	WebElement validationText;

	@FindBy(xpath = "(//div[contains(text(),'Select')])[1]")
	WebElement screenDropdown;
	@FindBy(xpath = "//span[normalize-space()='Required']")
	WebElement reqiredText1;

	@FindBy(xpath = "//div[@role='listbox']//div[@role='option']")
	List<WebElement> dropdownList;

	@FindBy(xpath = "(//span[normalize-space()='Required'])[2]")
	WebElement reqiredText2;

	@FindBy(xpath = "//span[normalize-space()='Text or Number']")
	WebElement typeDropdownItem;

	@FindBy(xpath = "(//div[@class='orangehrm-card-container']/child::form[@class='oxd-form']//div[@class='oxd-select-text--after'])[2]") // (//div[contains(text(),'Select')])[2]
	WebElement typeDropdown;

	@FindBy(xpath = "//button[@type='submit']")
	WebElement saveButton;

	@FindBy(xpath = "//button[normalize-space()='Cancel']")
	WebElement cancelButton;

	@FindBy(xpath = "//div[@class='oxd-table-body']//div[@class='oxd-table-card']")
	List<WebElement> customFieldList;

	public void checkBrokenLinkInCustomFieldPage() {
		actions.brokenLinks(driver);
	}

	public void navigateToCustomFieldPage() {
		driver.navigate().to("https://opensource-demo.orangehrmlive.com/web/index.php/pim/listCustomFields");
	}

	public void clickOnAddButton() {
		actions = new ReusableActions();
		actions.fluentWait(driver, addButton, 15);
		addButton.click();
	}

	public void isCustomFieldTextDisplayed() {
		customFieldText.isDisplayed();
	}

	public String getCustomFieldRecordMsg() {
		actions.fluentWait(driver, recordFoundMessage, 10);
		return recordFoundMessage.getText();
	}

	public void enterFieldName(String fldName) {
		actions.fluentWait(driver, fieldName, 15);
		fieldName.sendKeys(fldName);
	}

	public void clickAndSelectScreenDropdownItem(String option) {
		screenDropdown.click();
		actions.fluentWait(driver, dropdownList, 15);
		for (WebElement lisItem : dropdownList) {
			if (lisItem.getText().equals(option)) {
				lisItem.click();
				break;
			}
		}
	}

	public void clickAndSelectTypeDropdownItem() {
		actions.fluentWait(driver, typeDropdown, 15);
		typeDropdown.click();
		typeDropdownItem.click();
	}

	public void clickOnSaveButton() {
		saveButton.click();
	}

	public boolean isCustomFieldAdded() {
		boolean added = false;
		actions.fluentWait(driver, customFieldList, 15);
		if (customFieldList.size() != 1 && customFieldList.size() != 0) {
			added = true;
		}
		return added;
	}

	public void getCancelButton() {
		cancelButton.click();
	}

	public boolean isCustomFieldAlreadyExist(String text) {
		boolean exist = false;
		actions.fluentWait(driver, validationText, 10);
		if (validationText.getText().equals(text)) {
			exist = true;
		}
		return exist;
	}

	public boolean isRequiredTextDisplayed(String text) {
		boolean dispalyed = false;
		if (reqiredText1.getText().equals(text) && reqiredText2.getText().equals(text)) {
			dispalyed = true;
		}

		else {
			dispalyed = false;
		}
		return dispalyed;
	}
}
