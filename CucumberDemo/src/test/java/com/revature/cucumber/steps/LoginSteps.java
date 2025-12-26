package com.revature.cucumber.steps;



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

    private WebDriver driver;
    private static final String LOGIN_URL =
            "https://the-internet.herokuapp.com/login";

    // ---------- Hooks ----------

    @Before
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    // ---------- Background Steps ----------

    @Given("the application is running")
    public void the_application_is_running() {
        driver.get(LOGIN_URL);
        assertTrue(driver.getTitle().contains("The Internet"));

    }

    @And("the test database is seeded with users")
    public void the_test_database_is_seeded_with_users() {
        // No-op for demo application
        // Exists for readability and future extensibility
        System.out.println("the test database is seeded with users");
    }

    @And("the test database is already seeded with users")
    public void the_test_database_is_already_seeded_with_users() {
        System.out.println("the test database is already seeded with users");
    }

    // ---------- Scenario Steps ----------

    @Given("the user is on the login page")
    public void the_user_is_on_the_login_page() {
        driver.get(LOGIN_URL);
    }

    @When("the user enters username {string}")
    public void the_user_enters_username(String username) {
        driver.findElement(By.id("username")).clear();
        driver.findElement(By.id("username")).sendKeys(username);
    }

    @And("the user enters password {string}")
    public void the_user_enters_password(String password) {
        driver.findElement(By.id("password")).clear();
        driver.findElement(By.id("password")).sendKeys(password);
    }

    @And("the user clicks the login button")
    public void the_user_clicks_the_login_button() {
        driver.findElement(By.cssSelector("button[type='submit']")).click();
    }

    @Then("the user should be redirected to the secure area")
    public void the_user_should_be_redirected_to_the_secure_area() {
        assertTrue(driver.getCurrentUrl().contains("/secure"));
    }

    @Then("the the page should display a message containing {string}")
    public void the_the_page_should_display_a_message_containing(String expectedSubstring) {
        String flashMessage = driver.findElement(By.id("flash")).getText();
        assertTrue(flashMessage.contains(expectedSubstring));
    }

    // ---------- Outcome Validation ----------

    @Then("the {string} should be displayed")
    public void the_expected_result_should_be_displayed(String expectedResult) {
        String flashMessage =
                driver.findElement(By.id("flash")).getText();

        switch (expectedResult.toLowerCase()) {
            case "success message":
                assertTrue(
                        flashMessage.contains("You logged into a secure area!")
                );
                break;

            case "error message":
                assertTrue(
                        flashMessage.contains("Your username is invalid")
                                || flashMessage.contains("Your password is invalid")
                );
                break;

            default:
                throw new IllegalArgumentException(
                        "Unknown expected_result: " + expectedResult
                );
        }
    }

    @And("the user should be on the {string} page")
    public void the_user_should_be_on_the_expected_page(String expectedPage) {

        switch (expectedPage.toLowerCase()) {
            case "secure":
                assertTrue(driver.getCurrentUrl().contains("/secure"));
                break;

            case "login":
                assertTrue(driver.getCurrentUrl().contains("/login"));
                break;

            default:
                throw new IllegalArgumentException(
                        "Unknown expected_page: " + expectedPage
                );
        }
    }
}