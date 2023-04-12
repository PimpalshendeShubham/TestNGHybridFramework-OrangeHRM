package utility;

import java.io.File;
import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;

public class Listener extends TestListenerAdapter {

	public ExtentHtmlReporter htmlReporter;
	public ExtentReports extent;
	public ExtentTest logger;

	public void onStart(ITestContext testContext) {
		htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "/test-output/OHRMExtentReport.html");// specify
																														// location
																														// of
																														// the
																														// report
		htmlReporter.loadXMLConfig(System.getProperty("user.dir") + "/extent-config.xml");

		extent = new ExtentReports();

		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("Host name", "OrangeHRMProject");
		extent.setSystemInfo("Environemnt", "Windows 10");
		extent.setSystemInfo("user", "Shubham Pimpalshende");

		htmlReporter.config().setDocumentTitle("OrangeHRM Report"); // Tile of report
		htmlReporter.config().setReportName("Test Automation Report"); // name of the report
		htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP); // location of the chart
		htmlReporter.config().setTheme(com.aventstack.extentreports.reporter.configuration.Theme.STANDARD);

	}

	public void onTestSuccess(ITestResult result) {
		logger = extent.createTest(result.getName()); // create new entry in the report
		// send the passed information to the report with GREEN color highlighted
		logger.log(Status.PASS, MarkupHelper.createLabel(result.getName(), ExtentColor.GREEN));
	}

	public void onTestFailure(ITestResult result) {

		logger = extent.createTest(result.getName()); // create new entry in the report
		logger.log(Status.FAIL, MarkupHelper.createLabel(result.getName(), ExtentColor.RED));
		logger.log(Status.FAIL,
				MarkupHelper.createLabel("Name of the failed test case is: " + result.getName(), ExtentColor.RED));

		String screenShotPath = System.getProperty("user.dir") + "\\Screenshots\\" + result.getName() + ".png";

		File screenShotFile = new File(screenShotPath);

		if (screenShotFile.exists()) {
			try {
				logger.fail("Captured Screenshot is: " + logger.addScreenCaptureFromPath(screenShotPath));
			} catch (IOException e) {
				e.printStackTrace();
			}

		}

	}

	public void onTestSkipped(ITestResult result) {
		logger = extent.createTest(result.getName()); // create new entry in the report
		logger.log(Status.SKIP, MarkupHelper.createLabel(result.getName(), ExtentColor.ORANGE));
	}

	public void onFinish(ITestContext testContext) {
		extent.flush();

		// Convert HTML report to PDF

	}

}