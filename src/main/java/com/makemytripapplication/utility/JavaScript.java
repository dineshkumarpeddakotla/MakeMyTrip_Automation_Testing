package com.makemytripapplication.utility;

import com.makemytripapplication.base.BaseClass;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

public class JavaScript extends BaseClass {
    static JavascriptExecutor js = (JavascriptExecutor) driver;

    /**
     * scrollIntoView method is used to scroll the web page until to find desired web element
     * @param element web element
     */
    public static void scrollIntoView(WebElement element) {
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public static void scrollBottomOfThePage() {
        js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
    }
}
