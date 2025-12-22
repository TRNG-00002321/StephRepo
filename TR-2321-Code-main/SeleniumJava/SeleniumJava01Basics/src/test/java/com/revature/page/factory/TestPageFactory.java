package com.revature.page.factory;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Demo: Page Factory Pattern
 *
 * 1. @FindBy annotations declare elements
 * 2. PageFactory.initElements() initializes them
 * 3. @CacheLookup caches element for reuse
 * 4. Cleaner syntax than traditional POM
 */
@DisplayName("Page Factory Demo")
public class TestPageFactory {
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
    // TESTS USING PAGE FACTORY
    // ==========================================================

    @Test
    @DisplayName("Valid login using Page Factory")
    void validLogin_pageFactory() {
        LoginPageFactory loginPage = new LoginPageFactory(driver);

        SecurePageFactory securePage = loginPage
                .enterUsername("tomsmith")
                .enterPassword("SuperSecretPassword!")
                .clickLogin();

        assertTrue(securePage.isSecureAreaDisplayed());
        assertTrue(securePage.getFlashMessage().contains("logged into"));
    }

    @Test
    @DisplayName("Page Factory vs Traditional POM")
    void pageFactoryVsTraditional() {
        /*
         * Traditional POM:
         * private By username = By.id("username");
         * driver.findElement(username).sendKeys(text);
         *
         * Page Factory:
         * @FindBy(id = "username")
         * private WebElement username;
         * username.sendKeys(text);  // Direct use!
         */

        LoginPageFactory loginPage = new LoginPageFactory(driver);

        // Elements used directly without findElement calls
        loginPage.enterUsername("tomsmith")
                .enterPassword("SuperSecretPassword!");

        System.out.println("Page Factory is cleaner!");
    }

    @Test
    @DisplayName("Using @CacheLookup benefit")
    void cacheLookupBenefit() {
        /*
         * @CacheLookup caches element after first lookup
         * Good for static elements that don't change
         * Bad for dynamic elements (can cause StaleElementReferenceException)
         */

        LoginPageFactory loginPage = new LoginPageFactory(driver);

        // loginButton is cached after first use
        // Subsequent accesses don't re-query DOM
        loginPage.enterUsername("tomsmith")
                .enterPassword("SuperSecretPassword!")
                .clickLogin();

        System.out.println("@CacheLookup improves performance for static elements");
    }

    @Test
    @DisplayName("Finding multiple elements")
    void findingMultipleElements() {
        LoginPageFactory loginPage = new LoginPageFactory(driver);

        int inputCount = loginPage.getInputCount();
        System.out.println("Input fields found: " + inputCount);

        assertTrue(inputCount >= 2); // username and password
    }

    @Test
    @DisplayName("Complete login flow with Page Factory")
    void completeLoginFlow() {
        LoginPageFactory loginPage = new LoginPageFactory(driver);

        // Verify page loaded
        assertTrue(loginPage.getSubheader().contains("log"));

        // Login
        SecurePageFactory securePage = loginPage.loginAs("tomsmith", "SuperSecretPassword!");

        // Verify secure area
        assertEquals("Secure Area", securePage.getHeading());

        // Logout
        LoginPageFactory loginAgain = securePage.clickLogout();

        // Verify back on login page
        assertTrue(loginAgain.getSubheader().contains("log"));

        System.out.println("Complete flow using Page Factory!");
    }

}
