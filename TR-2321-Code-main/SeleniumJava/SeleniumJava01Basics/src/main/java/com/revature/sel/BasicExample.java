package com.revature.sel;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class BasicExample {
    public static void main(String[] args) {
        // Automatic driver setup - one line!
        WebDriverManager.chromedriver().setup();

        // Create driver as normal
        WebDriver driver = new ChromeDriver();

        try {
            driver.get("https://www.google.com/");
            System.out.println("Title: " + driver.getTitle());
        } finally {
            driver.quit();
        }
    }
}
