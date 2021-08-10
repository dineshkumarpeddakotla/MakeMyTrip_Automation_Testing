/*
 *Purpose : Class is implemented to execute the methods before and after test
 *               @BeforeTest is used to execute the method before execute actual test
 *               @AfterTest is used to execute the method after execute actual test
 *               @Parameter is used to provide the value for browser name
 *               @BeforeClass is used to execute the method before class
 *               @AfterSuite is to execute the method after suite
 *
 * @author Dinesh Kumar Peddakotla
 * @version 1.0
 * @since 15-07-2021
 */
package com.makemytripapplication.base;

import com.makemytripapplication.constants.IConstants;
import com.makemytripapplication.utility.CrossBrowser;
import com.makemytripapplication.utility.Log;
import com.makemytripapplication.utility.MailUtil;
import io.qameta.allure.Description;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Connection;
import java.sql.Statement;

public class BaseClass {
    //driver variable is declared
    public static WebDriver driver;
    public static Connection connection;
    public static Statement statement;
    /** declared string variable */
    public static String sqlQuery;

    @BeforeClass
    @Description("check the internet connection before running the class")
    public void checkConnection() {
        URL url;
        URLConnection connection;
        try {
            url = new URL("http://www.google.com");
            connection = url.openConnection();
            connection.connect();
            Log.info("Internet is connected");
        } catch (IOException e) {
            e.printStackTrace();
            Log.error("Internet is not connected");
        }
    }

    /**
     * setUp method is used to open browser and navigate to url
     * browser is opened based on parameter value
     */
    @Description("setUp method is used to open browser and navigate to url before test")
    @Parameters("browserName")
    @BeforeTest//execute before test
    public void setUp(@Optional("chrome") String browserName) throws InterruptedException {

        Log.info("select browser");
        driver = CrossBrowser.selectDriver(browserName);
        Log.info("navigate to url :" + IConstants.URL);
        driver.get(IConstants.URL);
        driver.manage().window().maximize();
       Thread.sleep(5000);
    }

    //tearDown method closes all connections
    @AfterTest//execute after test
    @Description("tearDown method closes all connections and browser")
    public void tearDown() {
        driver.quit();
    }

    @AfterSuite
    @Description("send reports to mail after the suite")
    public void sendReports() {
        MailUtil.sendMail();
        Log.info("send reports to email");
    }
}
