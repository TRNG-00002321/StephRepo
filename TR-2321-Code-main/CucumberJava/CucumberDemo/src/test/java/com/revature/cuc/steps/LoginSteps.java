package com.revature.cuc.steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertTrue;


public class LoginSteps {

//    private WebDriver driver;
//    private final String BASE_URL = "https://the-internet.herokuapp.com/login";
//
//    // ---------- Hooks ----------
//
//    @Before
//    public void setUp() {
//        driver = new ChromeDriver();
//        driver.manage().window().maximize();
//    }
//
//    @After
//    public void tearDown() {
//        if (driver != null) {
//            driver.quit();
//        }
//    }
//
//    // ---------- Background Steps ----------
//
//    @Given("the application is running")
//    public void the_application_is_running() {
//        // Simple health check by opening the login page
//        driver.get(BASE_URL);
//        assertTrue(driver.getTitle().contains("The Internet"));
//    }
//
//    @And("the test database is seeded with users")
//    public void the_test_database_is_seeded_with_users() {
//        // No action required for this demo app
//        // Step exists for readability and future extensibility
//    }
//
//    // ---------- Scenario Steps ----------
//
//    @Given("the user is on the login page")
//    public void the_user_is_on_the_login_page() {
//        driver.get(BASE_URL);
//    }
//
//    @When("the user enters username {string}")
//    public void the_user_enters_username(String username) {
//        driver.findElement(By.id("username")).sendKeys(username);
//    }
//
//    @And("the user enters password {string}")
//    public void the_user_enters_password(String password) {
//        driver.findElement(By.id("password")).sendKeys(password);
//    }
//
//    @And("the user clicks the login button")
//    public void the_user_clicks_the_login_button() {
//        driver.findElement(By.cssSelector("button[type='submit']")).click();
//    }
//
//    @Then("the user should be redirected to the secure area")
//    public void the_user_should_be_redirected_to_the_secure_area() {
//        assertTrue(driver.getCurrentUrl().contains("/secure"));
//    }
//
//    @And("the page should display message containing {string}")
//    public void the_page_should_display_message_containing(String expectedMessage) {
//        String actualMessage = driver.findElement(By.id("flash")).getText();
//        assertTrue(actualMessage.contains(expectedMessage));
//    }
}
