package utility;

import org.openqa.selenium.support.ui.WebDriverWait;

import pages.AddEmployeePage;
import pages.CustomFieldConfigurationPage;
import pages.DashboardPage;
import pages.EmployeeDetailsPage;
import pages.LoginPage;
import pages.SearchEmployeePage;
import pages.UpdatePasswordPage;

public class PageObjectRepo {

	public LoginPage loginPage;
	public DashboardPage dashboardPage;
	public UpdatePasswordPage updatePasswordPage;
	public CustomFieldConfigurationPage customFieldPage;
	public SearchEmployeePage searchEmployeePage;
	public AddEmployeePage addEmployeePage;
	public EmployeeDetailsPage empDeatilsPage;
	public ReusableActions actions;
	public WebDriverWait wait;

}
