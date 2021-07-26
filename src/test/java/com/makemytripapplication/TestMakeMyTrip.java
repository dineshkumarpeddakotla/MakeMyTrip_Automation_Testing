package com.makemytripapplication;

import com.makemytripapplication.base.BaseClass;
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

    @Test(dataProvider = "LoginDetails",dataProviderClass = DataProvider.class,dependsOnMethods = "loginToApplication_WithEmail_Otp")
    @Description("select departure and return date for travel")
    @Feature("calender feature")
    @Story("user is able select departure and return date for searching")
    public void selectDeparture_And_ReturnDate(String departureDate, String returnDate,
                                               String expectedDepDate, String expectedRetDate) {
        HomePage homePage = new HomePage(driver);

        String actualDepartureDate = homePage.selectDepartureDate(departureDate);
        String actualReturnDate = homePage.returnDate(returnDate);

        Assert.assertEquals(actualDepartureDate, expectedDepDate);
        Assert.assertEquals(actualReturnDate, expectedRetDate);
    }
}
