package com.revature.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("First Selenium Tests")
class FirstSeleniumTest {

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
    public void testBasic() throws InterruptedException{

        //Navigate to website
        driver.get("https://www.selenium.dev/");
        Thread.sleep(1000);

        String title = driver.getTitle();
        System.out.println("Title: "+title);


        //Assert
        assertTrue(title.contains("Selenium"));

    }

    @Test
    public void testBasic2() throws InterruptedException{

        //Navigate to website
        driver.get("https://www.selenium.dev/documentation");
        Thread.sleep(1000);

        String title = driver.getTitle();
        System.out.println("Title: "+title);


        //Assert
        assertTrue(title.contains("Documentation"));

    }

    @AfterEach
    public void teardown(){
        if(driver!=null)
            driver.quit();
        //only closes one window while quit closes all windows.
        //driver.close();
    }
}