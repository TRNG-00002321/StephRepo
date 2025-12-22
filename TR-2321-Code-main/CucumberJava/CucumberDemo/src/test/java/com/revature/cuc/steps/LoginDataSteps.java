package com.revature.cuc.steps;



import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertTrue;


public class LoginDataSteps {

//    private WebDriver driver;
//    private static final String LOGIN_URL = "https://the-internet.herokuapp.com/login";
//
//    // ---------- Hooks ----------
//
//
//
//    @Before
//    public void setUp() {
//        WebDriverManager.chromedriver().setup();
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
//        driver.get(LOGIN_URL);
//
//        assertTrue(driver.getTitle().contains("The Internet"));
//    }
//
//    @And("the test database is seeded with users")
//    public void the_test_database_is_seeded_with_users() {
//        // No-op for demo site
//        // Exists for readability and future extensibility
//    }
//
//    // ---------- Scenario Steps ----------
//
//    @Given("the user is on the login page")
//    public void the_user_is_on_the_login_page() {
//        driver.get(LOGIN_URL);
//    }
//
//    @When("the user enters username {string}")
//    public void the_user_enters_username(String username) {
//        driver.findElement(By.id("username")).clear();
//        driver.findElement(By.id("username")).sendKeys(username);
//    }
//
//    @And("the user enters password {string}")
//    public void the_user_enters_password(String password) {
//        driver.findElement(By.id("password")).clear();
//        driver.findElement(By.id("password")).sendKeys(password);
//    }
//
//    @And("the user clicks the login button")
//    public void the_user_clicks_the_login_button() {
//        driver.findElement(By.cssSelector("button[type='submit']")).click();
//    }
//
//    // ---------- Then Steps (Scenario Outline Driven) ----------
//
//    @Then("the {string} should be displayed")
//    public void the_expected_result_should_be_displayed(String expectedResult) {
//        String flashMessage = driver.findElement(By.id("flash")).getText();
//
//        if (expectedResult.equalsIgnoreCase("success message")) {
//            assertTrue(flashMessage.contains("You logged into a secure area!"));
//        } else if (expectedResult.equalsIgnoreCase("error message")) {
//            assertTrue(flashMessage.contains("Your username is invalid"));
//        } else {
//            throw new IllegalArgumentException("Unknown expected_result: " + expectedResult);
//        }
//    }
//
//    @And("the user should be on the {string} page")
//    public void the_user_should_be_on_the_expected_page(String expectedPage) {
//
//        if (expectedPage.equalsIgnoreCase("secure")) {
//            assertTrue(driver.getCurrentUrl().contains("/secure"));
//        } else if (expectedPage.equalsIgnoreCase("login")) {
//            assertTrue(driver.getCurrentUrl().contains("/login"));
//        } else {
//            throw new IllegalArgumentException("Unknown expected_page: " + expectedPage);
//        }
//    }
}

