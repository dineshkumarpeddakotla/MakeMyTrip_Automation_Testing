package com.makemytripapplication.pages;

import com.makemytripapplication.base.BaseClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class HomePage extends BaseClass {

    @FindBy(xpath = "//*[@id='root']/div/div[2]/div/div[1]/div[2]/div[1]/div[3]")
    WebElement departure;
    @FindBy(xpath = "//*[@id='root']/div/div[2]/div/div[1]/div[2]/div[1]/div[4]")
    WebElement returnDate;
    @FindBy(xpath = "//div[@class='DayPicker-Months']/div[1]/div[1]/div")
    WebElement dayPickerMonth;
    @FindBy(xpath = "//span[@aria-label='Next Month']")
    WebElement nextMonthBut;
    @FindBy(xpath = "//div[@class='DayPicker-Months']/div[1]/div[3]/div/div[@class='DayPicker-Day']/div/p[1]")
    List<WebElement> dayPickerBody1;
    @FindBy(xpath = "//label[@for='departure']/p[1]")
    WebElement departureDate;
    @FindBy(xpath = "//label[@for='return']/p[1]")
    WebElement returnDateValue;

    public HomePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    String flag;

    public String selectDepartureDate(String date) {
        departure.click();
        selectDate(date);

        return departureDate.getText();
    }

    public String returnDate(String date) {
        returnDate.click();
        selectDate(date);

        return returnDateValue.getText();
    }

    private void selectDate(String date) {
        System.out.println(date);
        String[] dateFormat = date.split("/");
        String day = dateFormat[0];
        String month = dateFormat[1];
        String year = dateFormat[2];
        flag = "false";
        while (flag.equals("false")) {
            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
            if (dayPickerMonth.getText().equals(month+ " " +year)) {
                for (WebElement day1 : dayPickerBody1) {
                    if (day1.getText().equals(day)) {
                        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
                        day1.click();
                        flag = "true";
                        break;
                    }
                }
            } else {
                nextMonthBut.click();
            }
        }
    }
}
