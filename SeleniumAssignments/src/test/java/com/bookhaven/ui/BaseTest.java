package com.bookhaven.ui;

import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {

    protected WebDriver driver;

    @BeforeEach
    void setUp() {
        // Set driver path (adjust to your path)
        WebDriverManager.chromedriver().setup();

        // Initialize driver
        driver = new ChromeDriver();

        // Maximize window
        driver.manage().window().maximize();
    }

    @AfterEach
    void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}