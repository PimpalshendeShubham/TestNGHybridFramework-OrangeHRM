package base;

import java.io.File;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import io.github.bonigarcia.wdm.WebDriverManager;
import utility.PageObjectRepo;
import utility.ReadConfiguration;

public class TestBase extends PageObjectRepo {

	ReadConfiguration readConfig = new ReadConfiguration();

	public String url = readConfig.getBaseUrl();
	public String browserName = readConfig.getBrowser();
	public String userName = readConfig.getUsername();
	public String password = readConfig.getPassword();
	public String dashboardText = readConfig.getDashboardText();
	public String invalidLoginText = readConfig.getInvalidLoginText();

	public static WebDriver driver;
	public static Logger log;

	@BeforeTest
	public void setup() {

		switch (browserName.toLowerCase()) {
		case "chrome":
//			WebDriverManager.chromedriver().setup();
			System.setProperty("webdriver.chrome.driver",
					"C:\\Users\\ASUS\\Eclipse-WorkspaceEduBridge\\OrangeHRMProject\\src\\test\\resources\\Drivers\\chromedriver.exe");
			ChromeOptions options = new ChromeOptions();
			options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
			driver = new ChromeDriver(options);
			driver.manage().deleteAllCookies();

			log = Logger.getLogger("OrangeHRMProject");
			PropertyConfigurator.configure("logfile.properties");

			driver.manage().window().maximize();
			driver.get(url);
			log.info("-------Opening Apllication Url---------");

			break;

		case "firefox":
			System.setProperty("webdriver.gecko.driver",
					"C:\\Users\\ASUS\\Eclipse-WorkspaceEduBridge\\OrangeHRMProject\\src\\test\\resources\\Drivers\\geckodriver.exe");
			FirefoxOptions firefoxOptions = new FirefoxOptions();
			// firefoxOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);
			firefoxOptions.setHeadless(true);
			driver = new FirefoxDriver(firefoxOptions);

			log = Logger.getLogger("OrangeHRMProject");
			PropertyConfigurator.configure("logfile.properties");

			driver.get(url);
			log.info("-------Opening Apllication Url---------");

			driver.manage().window().maximize();
			log.info("Login Page is loading");
//			WebDriverManager.firefoxdriver().setup();
//			driver = new FirefoxDriver();
			break;

		case "edge":
			System.setProperty("webdriver.edge.driver",
					"C:\\Users\\ASUS\\Eclipse-WorkspaceEduBridge\\OrangeHRMProject\\src\\test\\resources\\Drivers\\msedgedriver.exe");

			// EdgeOptions options=new EdgeOptions();
			driver = new EdgeDriver();

			log = Logger.getLogger("OrangeHRMProject");
			PropertyConfigurator.configure("logfile.properties");

			driver.get(url);
			log.info("-------Opening Apllication Url---------");
			driver.manage().window().maximize();

			break;
		default:
			driver = null;
			break;

		}

	}

	@AfterTest
	public void tearDown() {
		driver.close();
		driver.quit();
	}

	public void captureScreenshot(WebDriver driver, String testMethodName) {

		TakesScreenshot screenshot = ((TakesScreenshot) driver);

		File src = screenshot.getScreenshotAs(OutputType.FILE);

		File dest = new File(System.getProperty("user.dir") + "//Screenshots//" + testMethodName + ".png");

		// step3: copy image file to destination
		try {
			FileUtils.copyFile(src, dest);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public String generatePassword()
	{
		return(RandomStringUtils.randomAscii(10));
	}

}
