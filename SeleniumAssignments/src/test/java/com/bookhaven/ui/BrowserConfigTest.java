package com.bookhaven.ui;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.junit.jupiter.api.Assertions.*;

class BrowserConfigTest {

    private WebDriver driver;

    @AfterEach
    void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    @DisplayName("Chrome with basic options")
    void testChromeBasicOptions() {
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();

        // Common useful options
        options.addArguments("--start-maximized");      // Start maximized
        options.addArguments("--disable-extensions");   // Disable extensions
        options.addArguments("--disable-popup-blocking"); // Allow popups
        options.addArguments("--disable-infobars");     // Disable info bars

        driver = new ChromeDriver(options);
        driver.get("https://example.com");

        assertTrue(driver.getTitle().contains("Example"));
    }

    @Test
    @DisplayName("Chrome headless mode")
    void testChromeHeadless() {
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new");  // New headless mode (Chrome 109+)
        options.addArguments("--window-size=1920,1080");  // Set window size
        options.addArguments("--disable-gpu");    // Recommended for headless

        driver = new ChromeDriver(options);
        driver.get("https://example.com");

        // Test runs without visible browser!
        assertEquals("Example Domain", driver.getTitle());

        // Verify we can still interact
        assertNotNull(driver.getPageSource());
    }

    @Test
    @DisplayName("Chrome with custom user agent")
    void testChromeCustomUserAgent() {
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.addArguments(
                "--user-agent=Mozilla/5.0 (iPhone; CPU iPhone OS 14_0 like Mac OS X)"
        );

        driver = new ChromeDriver(options);
        driver.get("https://www.whatismybrowser.com/detect/what-is-my-user-agent");

        // Site will detect mobile user agent
    }

    @Test
    @DisplayName("Chrome with download directory")
    void testChromeDownloadDirectory() {
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();

        java.util.Map<String, Object> prefs = new java.util.HashMap<>();
        prefs.put("download.default_directory", "C:/Downloads/test");
        prefs.put("download.prompt_for_download", false);
        prefs.put("download.directory_upgrade", true);

        options.setExperimentalOption("prefs", prefs);

        driver = new ChromeDriver(options);
        // Downloads will go to specified directory
    }

    @Test
    @DisplayName("Chrome incognito mode")
    void testChromeIncognito() {
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");

        driver = new ChromeDriver(options);
        driver.get("https://example.com");

        // Browser runs in incognito mode
        assertTrue(driver.getTitle().contains("Example"));
    }
}