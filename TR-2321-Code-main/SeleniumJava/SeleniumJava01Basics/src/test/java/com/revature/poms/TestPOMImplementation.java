package com.revature.poms;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("POM Test")
public class TestPOMImplementation {
    private WebDriver driver;
    private static final String BASE_URL = "https://the-internet.herokuapp.com";
    // ==========================================================
    // TEST SETUP
    // ==========================================================

    @BeforeAll
    static void setupClass() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(BASE_URL + "/login");
    }

    @AfterEach
    void tearDown() {
        if (driver != null) driver.quit();
    }

    // ==========================================================
    // TESTS USING PAGE OBJECTS
    // ==========================================================

    @Test
    @DisplayName("Valid login using POM")
    void validLogin_usingPOM() {
        LoginPage loginPage = new LoginPage(driver);

        // Fluent style usage
        SecurePage securePage = loginPage
                .enterUsername("tomsmith")
                .enterPassword("SuperSecretPassword!")
                .clickLogin();

        assertTrue(securePage.isSecureAreaDisplayed());
        assertTrue(securePage.getFlashMessage().contains("logged into"));
    }

    @Test
    @DisplayName("Valid login using compound method")
    void validLogin_compoundMethod() {
        LoginPage loginPage = new LoginPage(driver);

        // Single method call
        SecurePage securePage = loginPage.loginAs("tomsmith", "SuperSecretPassword!");

        assertTrue(securePage.isSecureAreaDisplayed());
    }

    @Test
    @DisplayName("Invalid login shows error")
    void invalidLogin_showsError() {
        LoginPage loginPage = new LoginPage(driver);

        loginPage
                .enterUsername("invalid")
                .enterPassword("invalid")
                .clickLoginExpectingError();

        String errorMessage = loginPage.getErrorMessage();
        assertTrue(errorMessage.contains("invalid"));
    }

    @Test
    @DisplayName("Login and logout flow")
    void loginLogout_flow() {
        LoginPage loginPage = new LoginPage(driver);

        // Login
        SecurePage securePage = loginPage.loginAs("tomsmith", "SuperSecretPassword!");
        assertTrue(securePage.isSecureAreaDisplayed());

        // Logout
        LoginPage loginPageAfterLogout = securePage.clickLogout();
        assertTrue(loginPageAfterLogout.isUsernameFieldDisplayed());
    }

    @Test
    @DisplayName("Demonstrate POM benefits")
    void demonstratePOMBenefits() {
        /*
         * Notice how clean the test is:
         * - No locators visible
         * - No WebDriver calls
         * - Intent is clear
         *
         * If locator changes, update ONE place (page object)
         */

        LoginPage loginPage = new LoginPage(driver);

        // Test reads like documentation
        SecurePage securePage = loginPage.loginAs("tomsmith", "SuperSecretPassword!");

        assertEquals("Secure Area", securePage.getHeading());
        assertTrue(securePage.getFlashMessage().contains("secure area"));

        System.out.println("POM makes tests maintainable and readable!");
    }
}
