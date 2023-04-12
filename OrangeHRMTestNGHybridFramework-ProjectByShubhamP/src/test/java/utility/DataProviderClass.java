package utility;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviderClass {

	@DataProvider(name = "LoginTestDataSet")
	Object readLoginData() throws IOException {
		String location = "C:\\Users\\ASUS\\Eclipse-WorkspaceEduBridge\\OrangeHRMProject\\TestDataResource\\OHRMEmployeeData.xlsx";
		ExcelUtility read = new ExcelUtility(location);
		int rows = read.getRowCount("LoginTestData");
		//System.out.println(rows);
		int cols = read.getCellCount("LoginTestData", rows);
		//System.out.println(cols);

		Object[][] data = new Object[rows][cols];

		for (int i = 1; i <= rows; i++) {
			for (int j = 0; j < cols; j++) {
				data[i - 1][j] = read.getCellData("LoginTestData", i, j);
			}

		}

		return data;
	}

	@DataProvider(name = "EmpSearchTestDataSet1", indices = { 1 })
	Object readValidSearchData() throws IOException {
		String location = "C:\\Users\\ASUS\\Eclipse-WorkspaceEduBridge\\OrangeHRMProject\\TestDataResource\\OHRMEmployeeData.xlsx";
		ExcelUtility read = new ExcelUtility(location);
		int rows = read.getRowCount("EmployeeSearchTestData");
		//System.out.println(rows);
		int cols = read.getCellCount("EmployeeSearchTestData", rows);
		//System.out.println(cols);

		Object[][] data = new Object[rows][cols];

		for (int i = 1; i <= rows; i++) {
			for (int j = 0; j < cols; j++) {
				data[i - 1][j] = read.getCellData("EmployeeSearchTestData", i, j);
			}

		}

		return data;
	}

	@DataProvider(name = "EmpSearchTestDataSet2", indices = { 4, 5 })
	Object readInvalidSearchData() throws IOException {
		String location = "C:\\Users\\ASUS\\Eclipse-WorkspaceEduBridge\\OrangeHRMProject\\TestDataResource\\OHRMEmployeeData.xlsx";
		ExcelUtility read = new ExcelUtility(location);
		int rows = read.getRowCount("EmployeeSearchTestData");
		//System.out.println(rows);
		int cols = read.getCellCount("EmployeeSearchTestData", rows);
		//System.out.println(cols);

		Object[][] data = new Object[rows][cols];

		for (int i = 1; i <= rows; i++) {
			for (int j = 0; j < cols; j++) {
				data[i - 1][j] = read.getCellData("EmployeeSearchTestData", i, j);
			}

		}

		return data;
	}

	@DataProvider(name = "EmpSearchAndDeleteTestDataSet", indices = { 1 })
	Object readSearchDeleteData() throws IOException {
		String location = "C:\\Users\\ASUS\\Eclipse-WorkspaceEduBridge\\OrangeHRMProject\\TestDataResource\\OHRMEmployeeData.xlsx";
		ExcelUtility read = new ExcelUtility(location);
		int rows = read.getRowCount("EmployeeSearchTestData");
		//System.out.println(rows);
		int cols = read.getCellCount("EmployeeSearchTestData", rows);
		//System.out.println(cols);

		Object[][] data = new Object[rows][cols];

		for (int i = 1; i <= rows; i++) {
			for (int j = 0; j < cols; j++) {
				data[i - 1][j] = read.getCellData("EmployeeSearchTestData", i, j);
			}

		}

		return data;
	}

	@DataProvider(name = "EmpAddTestDataSet", indices = { 0, 1, 2})
	Object readAddEmpData() throws IOException {
		String location = "C:\\Users\\ASUS\\Eclipse-WorkspaceEduBridge\\OrangeHRMProject\\TestDataResource\\OHRMEmployeeData.xlsx";
		ExcelUtility read = new ExcelUtility(location);
		int rows = read.getRowCount("AddEmpTestData");
		//System.out.println(rows);
		int cols = read.getCellCount("AddEmpTestData", rows);
		//System.out.println(cols);

		Object[][] data = new Object[rows][cols];

		for (int i = 1; i <= rows; i++) {
			for (int j = 0; j < cols; j++) {
				data[i - 1][j] = read.getCellData("AddEmpTestData", i, j);
			}

		}

		return data;
	}

	@DataProvider(name = "CustomFieldTestDataSet1", indices = 1)
	Object readCustomFieldData() throws IOException {
		String location = "C:\\Users\\ASUS\\Eclipse-WorkspaceEduBridge\\OrangeHRMProject\\TestDataResource\\OHRMEmployeeData.xlsx";
		ExcelUtility read = new ExcelUtility(location);
		int rows = read.getRowCount("CustomFieldTestData");
		//System.out.println(rows);
		int cols = read.getCellCount("CustomFieldTestData", rows);
		//System.out.println(cols);

		Object[][] data = new Object[rows][cols];

		for (int i = 1; i <= rows; i++) {
			for (int j = 0; j < cols; j++) {
				data[i - 1][j] = read.getCellData("CustomFieldTestData", i, j);
			}

		}

		return data;
	}

	@DataProvider(name = "CustomFieldTestDataSet2")
	Object readCfData() {
		String[][] data = { { "Parent Contact Details", "Already exists", "Required" } };
		return data;

	}

	@DataProvider(name = "EmpSearchDataSet")
	Object readEmpSearchData() {
		String[][] data = { { "Paul Collings", "0024" } };
		return data;
	}

	@DataProvider(name = "EndToEndDataSet1", indices = { 0, 1 })
	Object readEndToEndTestData1() throws IOException {
		String location = "C:\\Users\\ASUS\\Eclipse-WorkspaceEduBridge\\OrangeHRMProject\\TestDataResource\\OHRMEmployeeData.xlsx";
		ExcelUtility read = new ExcelUtility(location);
		int rows = read.getRowCount("EndToEndTestData1");
		//System.out.println(rows);
		int cols = read.getCellCount("EndToEndTestData1", rows);
		//System.out.println(cols);

		Object[][] data = new Object[rows][cols];

		for (int i = 1; i <= rows; i++) {
			for (int j = 0; j < cols; j++) {
				data[i - 1][j] = read.getCellData("EndToEndTestData1", i, j);
			}

		}

		return data;
	}

	@DataProvider(name = "EndToEndAlternateFlowDataSet")
	Object readEToEAlternamteFlowDataData() {
		String[][] data = { { "Sir", "Max", "188", "Glenn", "Well" } };
		return data;
	}

	@DataProvider(name = "EndToEndDataSet2", indices = { 1, 2 })
	Object readEndToEndTestData2() throws IOException {
		String location = "C:\\Users\\ASUS\\Eclipse-WorkspaceEduBridge\\OrangeHRMProject\\TestDataResource\\OHRMEmployeeData.xlsx";
		ExcelUtility read = new ExcelUtility(location);
		int rows = read.getRowCount("EndToEndTestData2");
		//System.out.println(rows);
		int cols = read.getCellCount("EndToEndTestData2", rows);
		//System.out.println(cols);

		Object[][] data = new Object[rows][cols];
		for (int i = 1; i <= rows; i++) {
			for (int j = 0; j < cols; j++) {
				data[i - 1][j] = read.getCellData("EndToEndTestData2", i, j);
			}

		}

		return data;
	}
}
