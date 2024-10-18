package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;


public class DataProviders {

    private final String path = "./testData/Opencart_LoginData.xlsx"; // File path

    // DataProvider 1
    @DataProvider(name = "LoginData")
    public Object[][] getLoginData() throws IOException {
        ExcelUtility xlUtil = new ExcelUtility(path); // Create an instance of ExcelUtility

        int totalRows = xlUtil.getRowCount("Sheet1");
        int totalCols = xlUtil.getCellCount("Sheet1", 1);

        Object[][] logindata = new Object[totalRows][totalCols]; // Create a 2D array for login data

        for (int i = 1; i <= totalRows; i++) { // Start from 1 to skip the header
            for (int j = 0; j < totalCols; j++) {
                logindata[i - 1][j] = xlUtil.getCellData("Sheet1", i, j); // Read data from Excel
            }
        }
        return logindata; // Return the 2D array
    }


}


