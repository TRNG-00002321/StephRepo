package com.revature.sel;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("First Selenium WebDriver Tests")
public class Test01SeleniumFirst {
    private WebDriver driver;

    @BeforeEach
    void setUp() {
        /*
         * ChromeDriver requires:
         * 1. Chrome browser installed
         * 2. ChromeDriver executable (matching Chrome version)
         * 3. System property set OR ChromeDriver in PATH
         */

        // With WebDriverManager (recommended - uncomment when ready)
         WebDriverManager.chromedriver().setup();

        driver = new ChromeDriver();

        // Maximize browser window
        driver.manage().window().maximize();
    }

    @AfterEach
    void tearDown() {
        /*
         * ALWAYS close the driver!
         * - quit() closes all windows and ends the session
         * - close() only closes current window
         *
         * Forgetting to quit() leaves browser processes running
         */
        if (driver != null) {
            driver.quit();
        }
    }

    // ==========================================================
    // SECTION 1: Basic Navigation
    // ==========================================================

    @Test
    @DisplayName("Navigate to website and verify title")
    void navigateToWebsite_verifyTitle() {
        /*
         * The simplest Selenium test:
         * 1. Open a URL
         * 2. Get the page title
         * 3. Assert it matches expected
         */

        // Navigate to website
        driver.get("https://www.selenium.dev/");

        // Get page title
        String title = driver.getTitle();
        System.out.println("Page title: " + title);

        // Verify title
        assertTrue(title.contains("Selenium"),
                "Title should contain 'Selenium'");
    }

    @Test
    @DisplayName("Get current URL after navigation")
    void navigateToWebsite_verifyUrl() {
        /*
         * getCurrentUrl() returns the current page URL
         * Useful for verifying redirects or navigation
         */

        driver.get("https://www.selenium.dev/documentation/");

        String currentUrl = driver.getCurrentUrl();
        System.out.println("Current URL: " + currentUrl);

        assertTrue(currentUrl.contains("documentation"),
                "URL should contain 'documentation'");
    }

    @Test
    @DisplayName("Get page source")
    void getPageSource_containsExpectedContent() {
        /*
         * getPageSource() returns raw HTML
         * Useful for debugging or content verification
         */

        driver.get("https://www.selenium.dev/");

        String pageSource = driver.getPageSource();

        // Verify page contains expected content
        assertTrue(pageSource.contains("Selenium"),
                "Page source should contain 'Selenium'");

        // Print first 500 characters for demo
        System.out.println("Page source (first 500 chars):");
        System.out.println(pageSource.substring(0, Math.min(500, pageSource.length())));
    }

    // ==========================================================
    // SECTION 2: Basic WebDriver Properties
    // ==========================================================

    @Test
    @DisplayName("WebDriver window handle")
    void getWindowHandle_returnsUniqueId() {
        /*
         * Window handle is a unique ID for each browser window
         * Used for switching between multiple windows/tabs
         */

        driver.get("https://www.selenium.dev/");

        String windowHandle = driver.getWindowHandle();
        System.out.println("Window handle: " + windowHandle);

        assertNotNull(windowHandle, "Window handle should not be null");
        assertFalse(windowHandle.isEmpty(), "Window handle should not be empty");
    }

    // ==========================================================
    // SECTION 3: Test Lifecycle Demo
    // ==========================================================

    @Test
    @DisplayName("Multiple navigations in single test")
    void multipleNavigations_workCorrectly() {
        /*
         * Show that we can navigate to multiple pages in one test
         * Each test gets a fresh browser instance (@BeforeEach)
         */

        // First navigation
        driver.get("https://www.selenium.dev/");
        assertEquals("Selenium", driver.getTitle().split(" ")[0]);

        // Second navigation
        driver.get("https://www.google.com");
        assertTrue(driver.getTitle().toLowerCase().contains("google"));

        // Third navigation
        driver.get("https://www.selenium.dev/documentation/");
        assertTrue(driver.getCurrentUrl().contains("documentation"));
    }

    // ==========================================================
    // SECTION 4: Common Mistakes Demo
    // ==========================================================

    @Test
    @DisplayName("Demo: What happens without driver.quit()")
    @Disabled("Intentionally disabled - demonstrates bad practice")
    void withoutQuit_browserStaysOpen() {
        /*
         * If we don't call quit(), the browser stays open!
         * This leads to:
         * - Memory leaks
         * - Port exhaustion
         * - Zombie browser processes
         *
         * NEVER do this in real tests!
         */

        WebDriver tempDriver = new ChromeDriver();
        tempDriver.get("https://www.selenium.dev/");

        // Forgetting to close - BAD!
        // tempDriver.quit();
    }

    // ==========================================================
    // SECTION 5: Using Practice Website
    // ==========================================================

    @Test
    @DisplayName("Navigate to practice website - The Internet")
    void practiceWebsite_theInternet() {
        /*
         * The Internet (Heroku) is excellent for Selenium practice
         * It has examples of many UI patterns
         */

        driver.get("https://the-internet.herokuapp.com/");

        String title = driver.getTitle();
        System.out.println("Practice site title: " + title);

        assertTrue(title.contains("Internet"),
                "Should be on The Internet practice site");
    }

    @Test
    @DisplayName("Navigate to practice website - DemoQA")
    void practiceWebsite_demoQA() {
        /*
         * DemoQA has forms, widgets, and interactive elements
         */

        driver.get("https://demoqa.com/");

        String currentUrl = driver.getCurrentUrl();
        System.out.println("DemoQA URL: " + currentUrl);

        assertTrue(currentUrl.contains("demoqa"),
                "Should be on DemoQA practice site");
    }

}
