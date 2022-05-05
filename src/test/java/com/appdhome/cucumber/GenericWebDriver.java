package com.appdhome.cucumber;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class GenericWebDriver {
    public static WebDriver webDriver;
    public static WebDriver getWebDriver(){
        if (webDriver == null){
            String pathDriver = System.getProperty("user.dir") + "\\driver\\chromedriver.exe";
            System.setProperty("webdriver.chrome.driver", pathDriver);
            webDriver = new ChromeDriver();
        }
        return webDriver;
    }
}