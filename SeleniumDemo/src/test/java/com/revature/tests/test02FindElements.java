package com.revature.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("First Selenium Tests")
class test02FindElements {

    private String BASE_URL = "https://the-internet.herokuapp.com/";
    private WebDriver driver;

    @BeforeEach
    public void setup(){
        //Set up your WebDriverManager
        WebDriverManager.chromedriver().setup();

        //init your WebDriver
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }



    @Test
    @DisplayName("Test by Id")
    public void testById() throws InterruptedException{
        driver.get(BASE_URL+"/login");
        WebElement userName = driver.findElement(By.id("username"));
        WebElement pass = driver.findElement(By.id("password"));

        assertTrue(userName.isDisplayed());
        assertTrue(pass.isDisplayed());

    }

    @Test
    @DisplayName("Test by Name")
    public void testByName() throws InterruptedException{
        driver.get(BASE_URL+"/login");
        WebElement userName = driver.findElement(By.name("username"));
        WebElement pass = driver.findElement(By.name("password"));

        assertTrue(userName.isDisplayed());
        assertTrue(pass.isDisplayed());

    }

    @Test
    @DisplayName("Test by TagName")
    public void testByTagName() throws InterruptedException{
        driver.get(BASE_URL+"/login");
        List<WebElement> elements = driver.findElements(By.tagName("input"));
        //WebElement pass = driver.findElements(By.tagName("password"));

        assertTrue(elements.get(0).isDisplayed());
        assertTrue(elements.get(1).isDisplayed());

    }

    @Test
    @DisplayName("Test Title inside Button")
    public void testByNameForButton() throws InterruptedException{
        driver.get(BASE_URL+"/login");
        WebElement element = driver.findElement(By.tagName("button"));
        //WebElement pass = driver.findElements(By.tagName("password"));

        String title = element.getText();

        assertTrue(title.contains("Login"));

    }

    @Test
    @DisplayName("Find Header in HTML using xPath")
    public void testForHeaderUsingXPath(){
        driver.get(BASE_URL);
        WebElement element = driver.findElement(By.xpath("//h2")); //"html/body/div[1]/div/h2" absolute path

        String title = element.getText();
        assertTrue(title.contains("Available Examples"));
    }

    @Test
    @DisplayName("Complete login form interaction")
    void completeForm_loginFlow() {


        driver.get(BASE_URL + "/login");

        // Find elements
        WebElement userName = driver.findElement(By.id("username"));
        WebElement pass = driver.findElement(By.id("password"));
        WebElement btn = driver.findElement(By.tagName("button"));



        // Verify elements are displayed and enabled
        assertTrue(userName.isDisplayed());
        assertTrue(userName.isEnabled());
        assertTrue(pass.isDisplayed());
        assertTrue(pass.isEnabled());

        // Clear and enter credentials
        //username=tomsmith
        //password=SuperSecretPassword!

        userName.clear();
        pass.clear();

        userName.sendKeys("tomsmith");
        pass.sendKeys("SuperSecretPassword!");

                // Verify input values
        assertEquals("tomsmith", userName.getAttribute("value"));
        assertEquals("SuperSecretPassword!", pass.getAttribute("value"));

                // Click login
        btn.click();

                // Verify success (check for success message or URL)
        WebElement flash = driver.findElement(By.id("flash"));
        String flashText = flash.getText();

        assertTrue(flashText.contains("You logged into a secure area!") ||
                driver.getCurrentUrl().contains("secure"));
    }



    @AfterEach
    public void teardown(){
        if(driver!=null)
            driver.quit();
        //only closes one window while quit closes all windows.
        //driver.close();
    }
}