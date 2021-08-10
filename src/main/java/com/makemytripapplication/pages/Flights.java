package com.makemytripapplication.pages;

import com.makemytripapplication.base.BaseClass;
import com.makemytripapplication.utility.JavaScript;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class Flights extends BaseClass {

    @FindBy(xpath = "//div[@class='paneView'][1]/div[2]/div/div")
    List<WebElement> onWardFlights;
    @FindBy(xpath = "//div[@class='paneView'][2]/div[2]/div/div")
    List<WebElement> returnFlights;
    @FindBy(xpath = "//div[@class='filtersOuter simplev2'][1]/div/div/div/label[1]/div/span[1]")
    WebElement onWardNonStop;
    @FindBy(xpath = "//div[@class='filtersOuter simplev2'][1]/div/div/div/label[2]/div/span[1]")
    WebElement onWardOneStop;
    @FindBy(xpath = "//div[@class='filtersOuter simplev2'][2]/div/div/div/label[1]/div/span[1]")
    WebElement returnNonStop;
    @FindBy(xpath = "//div[@class='filtersOuter simplev2'][2]/div/div/div/label[2]/div/span[1]")
    WebElement returnOneStop;
    @FindBy(xpath = "//div[@class='paneView'][1]/div[2]/div/div/label/div/div[2]/div[2]/p")
    List<WebElement> departureFlightPrices;
    @FindBy(xpath = "//div[@class='paneView'][2]/div[2]/div/div/label/div/div[2]/div[2]/p")
    List<WebElement> returnFlightsPrice;
    @FindBy(xpath = "//div[@class='stickyFlightDtl appendRight15'][1]/div/span")
    WebElement departurePrice;
    @FindBy(xpath = "//div[@class='stickyFlightDtl appendRight15'][2]/div/span")
    WebElement returnPrice;
    @FindBy(xpath = "//div[@class='textRight appendRight10']/p[1]/span")
    WebElement totalFare;

    public Flights(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void selectFilter() throws InterruptedException {
        onWardNonStop.click();
        Thread.sleep(2000);
        onWardOneStop.click();
        Thread.sleep(3000);
        returnNonStop.click();
        Thread.sleep(1500);
        returnOneStop.click();
        Thread.sleep(1500);
    }

    public void onWardFlights() throws InterruptedException {
        Thread.sleep(1500);
        System.out.println("total no of onwards flights : " + onWardFlights.size());
//        onWardFlights.forEach(element -> System.out.println(element.getText()));
    }

    public void returnFlights() {
        System.out.println("total no of return flights" + returnFlights.size());
//        returnFlights.forEach(element -> System.out.println(element.getText()));
    }

    public void selectFlight() throws InterruptedException {
        JavaScript.scrollIntoView(onWardFlights.get(9));
        Thread.sleep(3000);
        onWardFlights.get(9).click();
        Thread.sleep(2000);
        returnFlights.get(9).click();
        Thread.sleep(2000);
    }

    public boolean checkPrices() {
        System.out.println(departurePrice.getText() + "   " + departureFlightPrices.get(9).getText());
        System.out.println(returnPrice.getText() + "   " + returnFlightsPrice.get(9).getText());

        boolean checkDepPrice = departureFlightPrices.get(10).getText().equals(departurePrice.getText());
        boolean checkRetPrice = returnFlightsPrice.get(10).getText().equals(returnPrice.getText());
        System.out.println(checkDepPrice + " " + checkRetPrice);

        return checkDepPrice && checkRetPrice;
    }

    public boolean checkTotalFare() {
        int depPrice = price(departurePrice.getText());
        int retPrice = price(returnPrice.getText());
        int totalPrice = price(totalFare.getText());
        System.out.println(depPrice + " " + retPrice + " " + totalPrice);

        return depPrice + retPrice == totalPrice;
    }

    private int price(String priceText) {
        String priceValue = priceText.replaceAll("[^(0-9)]+", "");
        return Integer.parseInt(priceValue);
    }
}
