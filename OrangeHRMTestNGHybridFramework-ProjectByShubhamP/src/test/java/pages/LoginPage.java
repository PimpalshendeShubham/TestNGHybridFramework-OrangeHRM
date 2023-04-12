package pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utility.PageObjectRepo;
import utility.ReusableActions;

public class LoginPage extends PageObjectRepo {

	WebDriver driver;
	ReusableActions actions = new ReusableActions();

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	@FindBy(name = "username")
	WebElement username;
	@FindBy(name = "password")
	WebElement password;
	@FindBy(xpath = "//button[@type='submit']")
	WebElement loginButton;
	@FindBy(xpath = "//p[text()='Invalid credentials']")
	WebElement validationText;
	@FindBy(xpath = "//img[@alt='company-branding']")
	WebElement logo;
	@FindBy(xpath = "//div[@class='oxd-form-row']//span")
	WebElement requiredText1;

	@FindBy(xpath = "(//div[@class='oxd-form-row']//span)[2]")
	WebElement requiredText2;

	public boolean isBrokenLinkPresentOnLoginPage() {
		boolean brokenLink = false;
		// actions.fluentWait(driver, loginButton, 0);
		if (actions.brokenLinks(driver) == true) {
			brokenLink = true;
		}
		return brokenLink;
	}

	public void enterUsername(String username) {

		actions.fluentWait(driver, this.username, 20);
		this.username.sendKeys(username);
	}

	public void enterPassword(String password) {
		this.password.sendKeys(password);
	}

	public DashboardPage clickLoginButton() {
		actions.fluentWait(driver, loginButton, 15);
		loginButton.sendKeys(Keys.ENTER);
		dashboardPage = new DashboardPage(driver);
		return dashboardPage;
	}

	public String getText() {
		actions.fluentWait(driver, validationText, 15);
		return validationText.getText();

	}

	public boolean isLogoDisplayed() {
		boolean displayed = false;
		actions.fluentWait(driver, logo, 15);
		if (logo.isDisplayed()) {
			displayed = true;
		}
		return displayed;
	}

	public boolean isRequiredTextDispalyed(String text) {
		boolean displayed = false;
		if (requiredText1.getText().equals(text) && requiredText2.getText().equals(text)) {
			displayed = true;
		}
		return displayed;
	}
}
