package utility;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ReadConfiguration {

	Properties properties;

	public ReadConfiguration() {

		try {
			FileReader fileReader = new FileReader(
					"C:\\Users\\ASUS\\Eclipse-WorkspaceEduBridge\\OrangeHRMProject\\src\\test\\resources\\Configuration\\config.properties");
			properties = new Properties();
			properties.load(fileReader);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public String getBaseUrl() {
		String url = properties.getProperty("baseUrl");

		if (url != null)
			return url;
		else
			throw new RuntimeException("Url not specified in config file.");
	}

	public String getBrowser() {
		String browser = properties.getProperty("browser");

		if (browser != null)
			return browser;
		else
			throw new RuntimeException("browser not specified in config file.");
	}

	public String getUsername() {
		String username = properties.getProperty("username");
		if (username != null)
			return username;
		else
			throw new RuntimeException("email not specified in config file.");

	}

	public String getPassword() {
		String password = properties.getProperty("password");
		if (password != null)
			return password;
		else
			throw new RuntimeException("password not specified in config file.");

	}

	public String getDashboardText() {
		String dashboardText = properties.getProperty("dashboardValidationText");
		if (dashboardText != null)
			return dashboardText;
		else
			throw new RuntimeException("dashboardText not specified in config file.");

	}

	public String getInvalidLoginText() {
		String invalidLoginText = properties.getProperty("invalidLoginMessage");
		if (invalidLoginText != null)
			return invalidLoginText;
		else
			throw new RuntimeException("invalidLoginText not specified in config file.");

	}
}
