package com.makemytripapplication.pages;

import com.makemytripapplication.base.BaseClass;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Registration extends BaseClass {

    @FindBy(id = "firstName")
    WebElement firstName;
    @FindBy(id = "password")
    WebElement password;
    @FindBy(xpath = "//span[contains(text(),'Save and Continue')]")
    WebElement saveAndContinue;
    @FindBy(xpath = "//p[contains(text(),'Skip')]")
    WebElement skip;
    @FindBy(name = "verifyOTPText")
    WebElement otpTextBox;
    @FindBy(xpath = "//a[contains(text(),'Resend OTP')]")
    WebElement resendOtp;
    @FindBy(xpath = "//button[@class='capText font16']")
    WebElement verifyAndCreateAccount;

}
