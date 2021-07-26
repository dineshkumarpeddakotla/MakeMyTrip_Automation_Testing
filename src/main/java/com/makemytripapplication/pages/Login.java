package com.makemytripapplication.pages;

import com.makemytripapplication.base.BaseClass;
import com.makemytripapplication.utility.Log;
import com.makemytripapplication.utility.MailUtil;
import com.makemytripapplication.utility.ReadMobileOtp;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.concurrent.TimeUnit;

public class Login extends BaseClass {

    @FindBy(xpath = "//ul[@class='userSection pushRight']/li[3]/div/p")
    WebElement login;
    @FindBy(xpath = "//*[@id='SW']/div[1]/div[1]/ul/li[3]/div[3]")
    WebElement popUpInfo;
    @FindBy(id = "username")
    WebElement emailOrMobileNumber;
    @FindBy(xpath = "//p[@class='makeFlex hrtlCenter flagCountryCode']")
    WebElement dropDownButton;
    @FindBy(xpath = "//input[@id='enterCountry']")
    WebElement countrySearchBar;
    @FindBy(xpath = "//span[@class='makeFlex perfectCenter appendLeft10']")
    WebElement searchButton;
    @FindBy(xpath = "//div[@class='cntrycode__list']/div[1]")
    WebElement selectCountry;
    @FindBy(xpath = "//span[contains(text(),'Continue')]")
    WebElement continueButton;
    @FindBy(id = "password")
    WebElement password;
    @FindBy(xpath = "//a[contains(text(),'or Login via OTP')]")
    WebElement loginWithOTP;
    @FindBy(xpath = "//a[contains(text(),'Reset Password')]")
    WebElement resetPassword;
    @FindBy(xpath = "//span[contains(text(),'Google')]")
    WebElement googleButton;
    @FindBy(xpath = "//label[contains(text(),'Login with Phone/Email')]")
    WebElement loginPhone;
    @FindBy(id = "otp")
    WebElement otpTextBox;
    @FindBy(xpath = "//p[contains(text(),'Please enter password')]")
    WebElement passwordEnterMessage;
    @FindBy(xpath = "//span[contains(text(),'Either Username or Password is incorrect.')]")
    WebElement passwordErrorMsg;
    @FindBy(xpath = "//button[@class='capText font16']")
    WebElement loginButton;

    public Login(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public String loginToApplication(String type, String emailOrMobile, String countryName, String otpOrPassword,
                                     String pass) throws InterruptedException {

        Log.debug("click on Login Button");
        selectLogin();
//        login.click();
//        loginPhone.click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        Log.debug("enter email or mobile number");
        enterEmailOrMobileNumber(type,emailOrMobile,countryName);
        Log.debug("click on continue button");
        continueButton.click();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        if (otpOrPassword.equals("otp")) {
            String otp;
            if (type.equals("email")) {
                loginWithOTP.click();
                Thread.sleep(2000);
                otp = MailUtil.readMailOtp("OTP to login to your MakeMyTrip account");
                otpTextBox.sendKeys(otp);
            } else if (type.equals("mobile number")) {
                Thread.sleep(2000);
                otp = ReadMobileOtp.readOTP();
                otpTextBox.sendKeys(otp);
            }
        } else {
            Log.debug("enter password");
            password.sendKeys(pass);
        }
        Log.debug("click on Login button");
        loginButton.click();
        Thread.sleep(3000);

        return login.getText();
    }

    private void enterEmailOrMobileNumber(String type, String emailOrMobile, String countryName) {
        if (type.equals("email")) {
            Log.debug("enter email :" +emailOrMobile);
            emailOrMobileNumber.sendKeys(emailOrMobile);
        } else if (type.equals("mobile number")) {
            Log.debug("enter mobile number"+emailOrMobile);
            emailOrMobileNumber.sendKeys(emailOrMobile);
            Log.debug("click on drop down button");
            dropDownButton.click();
            Log.debug("search for countryName: " +countryName);
            countrySearchBar.sendKeys(countryName);
            Log.debug("click on country");
            selectCountry.click();
        }
    }

    private void selectLogin() {
        if (popUpInfo.isDisplayed()) {
            loginPhone.click();
        } else {
            login.click();
        }
    }
}
