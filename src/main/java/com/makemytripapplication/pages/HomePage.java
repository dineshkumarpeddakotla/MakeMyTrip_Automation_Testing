package com.makemytripapplication.pages;

import com.makemytripapplication.base.BaseClass;
import com.makemytripapplication.utility.BrokenLinks;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
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
    @FindBy(xpath = "//a[@class='primaryBtn font24 latoBold widgetSearchBtn ']")
    WebElement search;
    @FindBy(xpath = "//span[contains(text(),'From')]")
    WebElement fromCity;
    @FindBy(xpath = "//input[@placeholder='From']")
    WebElement searchFromCity;
    @FindBy(xpath = "//input[@placeholder='To']")
    WebElement searchToCity;
    @FindBy(xpath = "//div[@class='calc60']/p[1]")
    List<WebElement> selectCity;
    @FindBy(xpath = "//span[contains(text(),'To')]")
    WebElement toCity;
    @FindBy(tagName = "a")
    List<WebElement> linksList;
    @FindBy(tagName = "img")
    List<WebElement> imgList;
    @FindBy(xpath = "//ul[@class='specialFare']/li[3]")
    WebElement studentsFare;
    @FindBy(xpath = "//ul[@class='specialFare']/li[3]/div/p[2]")
    WebElement studentsFareToolTip;

    String flag;

    public HomePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void selectFromCity() throws InterruptedException {
        fromCity.click();
        searchFromCity.sendKeys("Mumbai");
        Thread.sleep(1000);
        setSelectCity("Mumbai");
    }

    public void selectTOCity() throws InterruptedException {
        toCity.click();
        searchToCity.sendKeys("Bengaluru");
        Thread.sleep(1000);
        setSelectCity("Bengaluru");
    }

    public void clickSearch() {
        search.click();
    }

    public String selectDepartureDate(String date) {
        departure.click();
        System.out.println(date);
        String[] dateFormat = date.split("/");
        String day = dateFormat[0];
        String month = dateFormat[1];
        String year = dateFormat[2];
        String monthYear = month + " " + year;
        selectDate(day, monthYear);

        return departureDate.getText();
    }

    public String returnDate(String date) {
        returnDate.click();
        String[] dateFormat = date.split("/");
        String day = dateFormat[0];
        String month = dateFormat[1];
        String year = dateFormat[2];
        String monthYear = month + "" + year;
        selectDate(day, monthYear);
        return returnDateValue.getText();
    }

    private void selectDate(String day, String monthYear) {
        flag = "false";
        while (flag.equals("false")) {
            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
            System.out.println(dayPickerMonth.getText());
            if (dayPickerMonth.getText().equals(monthYear)) {
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

    public void checkBrokenLinks() {
        BrokenLinks.checkLinks(linksList, imgList);
    }

    public String getToolTip() throws InterruptedException {
        Actions actions = new Actions(driver);
        actions.moveToElement(studentsFare).perform();
        Thread.sleep(500);
        
        return studentsFareToolTip.getText();
    }

    private void setSelectCity(String city) {
        for (WebElement cityElement: selectCity) {
            String[] cityArray = cityElement.getText().split(",");
            if (cityArray[0].equals(city)) {
                cityElement.click();
                break;
            }
        }
    }
}
