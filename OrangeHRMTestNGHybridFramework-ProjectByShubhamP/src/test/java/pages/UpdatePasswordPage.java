package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utility.ReusableActions;

public class UpdatePasswordPage {
	WebDriver driver;
	ReusableActions actions = new ReusableActions();

	public UpdatePasswordPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//h6[text()='Update Password']")
	WebElement updatePasswordLabel;
	@FindBy(xpath = "(//input[@class='oxd-input oxd-input--active'])[2]")
	WebElement currentPasswordField;
	@FindBy(xpath = "//div[@class='oxd-grid-item oxd-grid-item--gutters user-password-cell']//div[@class='oxd-input-group oxd-input-field-bottom-space']//div//input[@type='password']")
	WebElement newPasswordField;
	@FindBy(xpath = "//*[@id='app']/div[1]/div[2]/div[2]/div/div/form/div[2]/div/div[2]/div/div[2]/input")
	WebElement confirmPasswordField;
	@FindBy(xpath = "//button[@type='submit']")
	WebElement saveButton;
	@FindBy(xpath = "//div[@class='oxd-form-actions']//button[@type='button']")
	WebElement cancelButton;
	@FindBy(xpath = "//div[@id='oxd-toaster_1']")
	WebElement savedMsgPopup;

	public void checkBrokenLinkUpdatePasswordPage() {
		actions.brokenLinks(driver);
	}

	public void enterCurrentPassword(String currentPassword) {
		actions.fluentWait(driver, currentPasswordField, 20);
		currentPasswordField.sendKeys(currentPassword);
	}

	public void setNewPassword(String newPassword) {
		actions.fluentWait(newPasswordField, driver, 20);
		newPasswordField.click();
		newPasswordField.sendKeys(newPassword);
	}

	public void enterConfirmPassword(String confirmPassword) {
		actions.fluentWait(confirmPasswordField, driver, 15);
		confirmPasswordField.click();
		confirmPasswordField.sendKeys(confirmPassword);
	}

	public void clickOnSaveButton() {
		actions.fluentWait(saveButton, driver, 15);
		saveButton.click();
	}

	public boolean isDisplayed() {
		actions.fluentWait(driver, savedMsgPopup, 20);
		return savedMsgPopup.isDisplayed();

	}

}
