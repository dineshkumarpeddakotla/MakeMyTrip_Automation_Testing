package com.makemytripapplication.utility;

import com.makemytripapplication.base.BaseClass;
import org.openqa.selenium.WebElement;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class BrokenLinks extends BaseClass {

    public static void checkLinks(List<WebElement> link, List<WebElement> img) {
        link.addAll(img);

        List<String> activeLinks = new ArrayList<>();

        for (WebElement element : link) {
            if (element.getAttribute("href") != null && (!element.getAttribute("href").contains("javascript"))) {
                activeLinks.add(element.getAttribute("href"));
            }
        }
        activeLinks.parallelStream().forEach(BrokenLinks::checkBrokenLinks);
    }

    public static void checkBrokenLinks(String urlLink) {
        try {
            URL url = new URL(urlLink);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setConnectTimeout(5000);
            httpURLConnection.connect();
            if (httpURLConnection.getResponseCode() >= 400) {
                System.out.println(urlLink+" ------> "+httpURLConnection.getResponseMessage()+" is a broken link");
            } else {
                System.out.println(urlLink+"------->"+httpURLConnection.getResponseMessage());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
