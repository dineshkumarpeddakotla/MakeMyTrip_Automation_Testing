package com.makemytripapplication.pages;

import com.makemytripapplication.base.BaseClass;
import com.makemytripapplication.utility.JavaScript;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class Flights extends BaseClass {

    @FindBy(xpath = "//div[@class='paneView'][1]/div[2]/div/label/div/div[2]/div[2]/p")
    List<WebElement> onWardFlights;
    @FindBy(xpath = "//div[@class='paneView'][1]/div[2]/div/label/div/div[2]/div[2]/p")
    List<WebElement> returnFlights;
    @FindBy(xpath = "//div[@class='filtersOuter simplev2'][1]/div/div/div/label[1]")
    WebElement onWardNonStop;
    @FindBy(xpath = "//div[@class='filtersOuter simplev2'][1]/div/div/div/label[2]")
    WebElement onWardOneStop;
    @FindBy(xpath = "//div[@class='filtersOuter simplev2'][2]/div/div/div/label[1]")
    WebElement returnNonStop;
    @FindBy(xpath = "//div[@class='filtersOuter simplev2'][2]/div/div/div/label[2]")
    WebElement returnOneStop;
    @FindBy(xpath = "//div[@class='paneView'][1]/div[2]/div/label/div/div[2]/div[2]/span/span")
    List<WebElement> onWardRadioButton;
    @FindBy(xpath = "//div[@class='paneView'][1]/div[2]/div/label/div/div[2]/div[2]/span/span")
    List<WebElement> returnRadioButton;

    public Flights(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void selectFilter() throws InterruptedException {
        onWardNonStop.click();
        Thread.sleep(1000);
        onWardOneStop.click();
        Thread.sleep(1000);
        returnNonStop.click();
        Thread.sleep(1000);
        returnOneStop.click();
    }

    public void onWardFlights() {
        onWardFlights.forEach(JavaScript::scrollIntoView);
        System.out.println(onWardFlights.size());
    }

    public void returnFlights() {
        System.out.println(returnFlights.size());
    }
}
