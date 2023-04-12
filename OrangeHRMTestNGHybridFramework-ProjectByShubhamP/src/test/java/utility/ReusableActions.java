package utility;

import java.net.HttpURLConnection;
import java.net.URL;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

public class ReusableActions {

	WebDriver driver;
	WebElement element;

	public void fluentWait(WebDriver driver, WebElement element, int timeOut) {
		Wait<WebDriver> wait = null;
		try {
			wait = new FluentWait<WebDriver>((WebDriver) driver).withTimeout(Duration.ofSeconds(20))
					.pollingEvery(Duration.ofSeconds(2)).ignoring(Exception.class);
			wait.until(ExpectedConditions.visibilityOf(element));
		} catch (Exception e) {
		}
	}

	public void fluentWait(WebDriver driver, List<WebElement> element, int timeOut) {

		Wait<WebDriver> wait = null;
		try {
			wait = new FluentWait<WebDriver>((WebDriver) driver).withTimeout(Duration.ofSeconds(20))
					.pollingEvery(Duration.ofSeconds(2)).ignoring(Exception.class);
			wait.until(ExpectedConditions.visibilityOfAllElements(element));
		} catch (Exception e) {
		}
	}

	public void fluentWait(WebElement element, WebDriver driver, int timeOut) {
		Wait<WebDriver> wait = null;
		try {
			wait = new FluentWait<WebDriver>((WebDriver) driver).withTimeout(Duration.ofSeconds(20))
					.pollingEvery(Duration.ofSeconds(2)).ignoring(Exception.class);
			wait.until(ExpectedConditions.elementToBeClickable(element));

		} catch (Exception e) {
		}
	}

	boolean brokenLinkPresent;

	public boolean brokenLinks(WebDriver driver) {
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		List<WebElement> links = driver.findElements(By.tagName("a"));
		System.out.println("Total Links Present in this Page are: " + links.size());

		List<String> urlList = new ArrayList<String>();

		for (WebElement link : links) {
			String url = link.getAttribute("href");
			urlList.add(url);
		}
		urlList.parallelStream().forEach(link -> {
			try {
				URL url = new URL(link);
				HttpURLConnection httpUrlConnection = (HttpURLConnection) url.openConnection();
				httpUrlConnection.connect();

				if (httpUrlConnection.getResponseCode() >= 400) {
					System.err.println(link + " ----->" + httpUrlConnection.getResponseMessage() + " is a Broken link");
					brokenLinkPresent = true;
				} else {
					System.out.println(link + " ----->" + httpUrlConnection.getResponseMessage());
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

		});
		return brokenLinkPresent;
	}
}
