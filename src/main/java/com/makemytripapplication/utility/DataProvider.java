/*
 *Purpose : Class is implemented to provide multiple data for test cases from excel sheet
 *                @DataProvider is provides the data to test cases
 *
 * @author Dinesh Kumar Peddakotla
 * @version 1.0
 * @since 15-07-2021
 */

package com.makemytripapplication.utility;

import com.makemytripapplication.constants.IConstants;
import com.makemytripapplication.database.DataBase_Operations;

import java.lang.reflect.Method;

public class DataProvider {

    /**
     * getDataFromProvider is used get the from excel sheet
     * @return data from excel sheet
     */
    @org.testng.annotations.DataProvider(name = "LoginDetails")
    public static Object[][] getDataFromProvider(Method method) {
        switch (method.getName()) {
            case "loginToApplication_WithEmail_Otp":
                ExcelUtil excelUtil = new ExcelUtil(IConstants.EXCEL_FILE_PATH, "loginWithEmail");

                return excelUtil.readData();
            case "loginToApplication_WithMobileNumber_Otp":
                JsonUtil jsonUtil = new JsonUtil();

                return jsonUtil.readJson(IConstants.JSON_FILE_PATH,5);
            case "loginToApplication_With_Password":
                DataBase_Operations dataBase = new DataBase_Operations();
                return dataBase.retrieveData();
            case "selectDeparture_And_ReturnDate":
                ExcelUtil excelUtil1 = new ExcelUtil(IConstants.EXCEL_FILE_PATH, "travelDates");
                return excelUtil1.readData();
        }
     return null;
    }
}
