package com.revature.sel;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Browser Options Demo")
public class Test08Options {
    private WebDriver driver;

    @BeforeAll
    static void setupClass() {
        WebDriverManager.chromedriver().setup();
    }

    @AfterEach
    void tearDown() {
        if (driver != null) driver.quit();
    }

    // ==========================================================
    // SECTION 1: Headless Mode
    // ==========================================================

    @Test
    @DisplayName("Chrome headless mode")
    void chromeHeadless() {
        /*
         * Headless = browser without visible UI
         * Perfect for CI/CD pipelines
         * Faster execution, fewer resources
         */

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new");  // New headless mode


        driver = new ChromeDriver(options);
        driver.get("https://www.selenium.dev");

        System.out.println("Title in headless: " + driver.getTitle());
        assertTrue(driver.getTitle().contains("Selenium"));
    }

    @Test
    @DisplayName("Firefox headless mode")
    void firefoxHeadless() {
        WebDriverManager.firefoxdriver().setup();

        FirefoxOptions options = new FirefoxOptions();
        options.addArguments("-headless");

        driver = new FirefoxDriver(options);
        driver.get("https://www.selenium.dev");

        assertTrue(driver.getTitle().contains("Selenium"));
    }

    // ==========================================================
    // SECTION 2: Custom Arguments
    // ==========================================================

    @Test
    @DisplayName("Window size arguments")
    void windowSizeArgs() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--window-size=1920,1080");
        options.addArguments("--start-maximized");

        driver = new ChromeDriver(options);
        driver.get("https://www.selenium.dev");

        System.out.println("Window size: " + driver.manage().window().getSize());
    }

    @Test
    @DisplayName("Disable extensions and infobars")
    void disableExtensions() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-extensions");
        options.addArguments("--disable-infobars");
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--disable-notifications");

        driver = new ChromeDriver(options);
        driver.get("https://www.selenium.dev");

        System.out.println("Extensions disabled");
    }

    @Test
    @DisplayName("Performance options")
    void performanceOptions() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-gpu");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-browser-side-navigation");

        driver = new ChromeDriver(options);
        driver.get("https://www.selenium.dev");

        System.out.println("Performance optimized");
    }

    // ==========================================================
    // SECTION 3: Capabilities
    // ==========================================================

    @Test
    @DisplayName("Accept insecure certificates")
    void acceptInsecureCerts() {
        ChromeOptions options = new ChromeOptions();
        options.setAcceptInsecureCerts(true);

        driver = new ChromeDriver(options);
        driver.get("https://www.selenium.dev");

        System.out.println("Insecure certs accepted");
    }

    @Test
    @DisplayName("Set page load strategy")
    void pageLoadStrategy() {
        /*
         * Page load strategies:
         * - NORMAL: Wait for full page load (default)
         * - EAGER: Wait for DOMContentLoaded
         * - NONE: Return immediately
         */

        ChromeOptions options = new ChromeOptions();
        options.setPageLoadStrategy(org.openqa.selenium.PageLoadStrategy.EAGER);

        driver = new ChromeDriver(options);
        driver.get("https://www.selenium.dev");

        System.out.println("EAGER page load strategy");
    }

    // ==========================================================
    // SECTION 4: Complete CI/CD Configuration
    // ==========================================================

    @Test
    @DisplayName("Complete CI/CD configuration")
    void cicdConfiguration() {
        /*
         * INSTRUCTOR NOTE:
         * Complete setup for CI/CD environments
         */

        ChromeOptions options = new ChromeOptions();

        // Headless for CI/CD
        options.addArguments("--headless=new");

        // Stability options
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-gpu");

        // Window settings
        options.addArguments("--window-size=1920,1080");

        // Clean session
        options.addArguments("--incognito");

        // Accept all certificates
        options.setAcceptInsecureCerts(true);

        driver = new ChromeDriver(options);
        driver.get("https://www.selenium.dev");

        System.out.println("CI/CD ready configuration!");
        assertTrue(driver.getTitle().contains("Selenium"));
    }
}
