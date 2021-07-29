package com.makemytripapplication;

import com.makemytripapplication.base.BaseClass;

import com.makemytripapplication.pages.Flights;
import com.makemytripapplication.pages.HomePage;
import com.makemytripapplication.pages.Login;
import com.makemytripapplication.utility.DataProvider;

import com.makemytripapplication.utility.listeners.TestListener;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

@Listeners(TestListener.class)
@Feature("login to application")
@Epic("user is able login to application")
public class TestMakeMyTrip extends BaseClass {

    @Test(dataProvider = "LoginDetails",dataProviderClass = DataProvider.class)
    @Description("verify user is able to login with email otp")
    @Feature("login to application with otp")
    @Story("as a end user, i want to login to application with email otp")
    public void loginToApplication_WithEmail_Otp(String type, String email, String country, String otp,String password) throws InterruptedException {
        Login login = new Login(driver);
        String actualText = login.loginToApplication(type,email, country,otp,password);
        String notExpectedText = "Login or Create Account";

        Assert.assertNotEquals(actualText, notExpectedText);
    }

    @Test(dataProvider = "LoginDetails",dataProviderClass = DataProvider.class,priority = 1)
    @Description("verify user is able to login with mobile otp")
    @Feature("login to application with otp")
    @Story("as a end user, i want to login to application with mobile otp")
    public void loginToApplication_WithMobileNumber_Otp(String type, String mobileNumber, String country, String otp, String password) throws InterruptedException {
        Login login = new Login(driver);
        String actualText = login.loginToApplication(type,mobileNumber,country,otp,password);
        String notExpectedText = "Login or Create Account";

        Assert.assertNotEquals(actualText, notExpectedText);
    }

    @Test(dataProvider = "LoginDetails",dataProviderClass = DataProvider.class,priority = 2)
    @Description("verify user is able to login with password")
    @Feature("login to application with password")
    @Story("as a end user, i want to login to application with password")
    public void loginToApplication_With_Password(String email, String password) throws InterruptedException {
        Login login = new Login(driver);

        String actualText = login.loginToApplication("email", email, "India",
                "password",password);
        driver.manage().deleteAllCookies();
        String notExpectedText = "Login or Create Account";

        Assert.assertNotEquals(actualText, notExpectedText);
    }

    @Test(dataProvider = "LoginDetails",dataProviderClass = DataProvider.class)
    @Description("select departure and return date for travel")
    @Feature("calender feature")
    @Story("user is able select departure and return date for searching")
    public void selectDeparture_And_ReturnDate(String departureDate, String returnDate,
                                               String expectedDepDate, String expectedRetDate) throws InterruptedException {
        HomePage homePage = new HomePage(driver);
        Flights flights = new Flights(driver);

        homePage.selectFromCity();
        homePage.selectTOCity();
        String actualDepartureDate = homePage.selectDepartureDate(departureDate);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        String actualReturnDate = homePage.returnDate(returnDate);
        Thread.sleep(3000);
        homePage.clickSearch();
        driver.manage().timeouts().pageLoadTimeout(20,TimeUnit.SECONDS);
        flights.onWardFlights();
        flights.returnFlights();

        Assert.assertEquals(actualDepartureDate, expectedDepDate);
        Assert.assertEquals(actualReturnDate, expectedRetDate);
    }

    @Test
    public void checkBrokenLinks_WebPage() {
        HomePage homePage = new HomePage(driver);
        homePage.checkBrokenLinks();
    }

    @Test
    public void checkToolTip() throws InterruptedException {
        HomePage homePage = new HomePage(driver);
        String actualText = homePage.getToolTip();
        System.out.println("tooltip "+actualText);
        String expectedToolTip = "Student Fare\n" + "Only students above 12 years of age are eligible for " +
                                 "special fares and/or additional baggage allowances. Carrying valid student " +
                                 "ID cards and student visas (where applicable) is mandatory, else the " +
                                 "passenger may be denied boarding or asked to pay for extra baggage.";

        Assert.assertEquals(actualText, expectedToolTip);
    }
}
