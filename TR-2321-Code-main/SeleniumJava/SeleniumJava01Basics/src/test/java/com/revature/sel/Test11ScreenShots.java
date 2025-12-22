package com.revature.sel;


import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Demo: Screenshot Capture in Selenium
 *
 * 1. TakesScreenshot interface for full page screenshots
 * 2. Selenium 4 element screenshots
 * 3. Organize screenshots with timestamps
 * 4. Screenshot on failure pattern
 */

@DisplayName("Screenshots Demo")
public class Test11ScreenShots {

    private WebDriver driver;
    private static final String BASE_URL = "https://the-internet.herokuapp.com";
    private static final String SCREENSHOT_DIR = "target/screenshots";

    @BeforeAll
    static void setupClass() {
        WebDriverManager.chromedriver().setup();

        // Create screenshot directory
        try {
            Files.createDirectories(Paths.get(SCREENSHOT_DIR));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @BeforeEach
    void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @AfterEach
    void tearDown() {
        if (driver != null) driver.quit();
    }

    // ==========================================================
    // SECTION 1: Full Page Screenshots
    // ==========================================================

    @Test
    @DisplayName("Take full page screenshot")
    void fullPageScreenshot() throws IOException {
        driver.get(BASE_URL + "/login");

        // Cast driver to TakesScreenshot
        TakesScreenshot screenshot = (TakesScreenshot) driver;

        // Take screenshot as file
        File srcFile = screenshot.getScreenshotAs(OutputType.FILE);

        // Define destination
        String filename = "login_page_" + getTimestamp() + ".png";
        Path destination = Paths.get(SCREENSHOT_DIR, filename);

        // Copy file
        Files.copy(srcFile.toPath(), destination);

        System.out.println("Screenshot saved: " + destination);
        assertTrue(Files.exists(destination));
    }

    @Test
    @DisplayName("Screenshot as bytes")
    void screenshotAsBytes() {
        driver.get(BASE_URL + "/login");

        TakesScreenshot screenshot = (TakesScreenshot) driver;

        // Get as byte array (useful for attachments, reports)
        byte[] imageBytes = screenshot.getScreenshotAs(OutputType.BYTES);

        System.out.println("Screenshot size: " + imageBytes.length + " bytes");
        assertTrue(imageBytes.length > 0);
    }

    @Test
    @DisplayName("Screenshot as Base64")
    void screenshotAsBase64() {
        driver.get(BASE_URL + "/login");

        TakesScreenshot screenshot = (TakesScreenshot) driver;

        // Get as Base64 (useful for embedding in HTML reports)
        String base64Image = screenshot.getScreenshotAs(OutputType.BASE64);

        System.out.println("Base64 length: " + base64Image.length());
        assertFalse(base64Image.isEmpty());
    }

    // ==========================================================
    // SECTION 2: Element Screenshots (Selenium 4)
    // ==========================================================

    @Test
    @DisplayName("Element screenshot")
    void elementScreenshot() throws IOException {
        driver.get(BASE_URL + "/login");

        // Find specific element
        WebElement loginForm = driver.findElement(By.id("login"));

        // Take screenshot of just this element
        File srcFile = loginForm.getScreenshotAs(OutputType.FILE);

        String filename = "login_form_" + getTimestamp() + ".png";
        Path destination = Paths.get(SCREENSHOT_DIR, filename);
        Files.copy(srcFile.toPath(), destination);

        System.out.println("Element screenshot saved: " + destination);
        assertTrue(Files.exists(destination));
    }

    @Test
    @DisplayName("Button element screenshot")
    void buttonScreenshot() throws IOException {
        driver.get(BASE_URL + "/login");

        WebElement button = driver.findElement(By.xpath("//button[@type='submit']"));

        File srcFile = button.getScreenshotAs(OutputType.FILE);

        String filename = "login_button_" + getTimestamp() + ".png";
        Path destination = Paths.get(SCREENSHOT_DIR, filename);
        Files.copy(srcFile.toPath(), destination);

        System.out.println("Button screenshot saved: " + destination);
    }

    // ==========================================================
    // SECTION 3: Screenshot Utility Methods
    // ==========================================================

    /**
     * Utility method for taking screenshots
     */
    private void takeScreenshot(String name) {
        try {
            TakesScreenshot screenshot = (TakesScreenshot) driver;
            File srcFile = screenshot.getScreenshotAs(OutputType.FILE);

            String filename = name + "_" + getTimestamp() + ".png";
            Path destination = Paths.get(SCREENSHOT_DIR, filename);

            Files.copy(srcFile.toPath(), destination);
            System.out.println("Screenshot: " + destination);
        } catch (IOException e) {
            System.err.println("Failed to take screenshot: " + e.getMessage());
        }
    }

    private String getTimestamp() {
        return LocalDateTime.now().format(
                DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
    }

    @Test
    @DisplayName("Using screenshot utility")
    void usingScreenshotUtility() {
        driver.get(BASE_URL + "/login");
        takeScreenshot("step1_login_page");

        driver.findElement(By.id("username")).sendKeys("tomsmith");
        driver.findElement(By.id("password")).sendKeys("SuperSecretPassword!");
        takeScreenshot("step2_credentials_entered");

        driver.findElement(By.xpath("//button[@type='submit']")).click();
        takeScreenshot("step3_after_login");
    }

    // ==========================================================
    // SECTION 4: Screenshot on Failure Pattern
    // ==========================================================

    @Test
    @DisplayName("Screenshot on failure demonstration")
    void screenshotOnFailure() {
        driver.get(BASE_URL + "/login");

        try {
            // Simulate a failing assertion
            driver.findElement(By.id("username")).sendKeys("invalid");
            driver.findElement(By.id("password")).sendKeys("invalid");
            driver.findElement(By.xpath("//button[@type='submit']")).click();

            // This might fail
            WebElement success = driver.findElement(By.className("success"));
            assertTrue(success.isDisplayed());

        } catch (Exception e) {
            // Take screenshot on failure
            takeScreenshot("FAILURE_login_test");
            System.out.println("Test failed - screenshot captured");
            // Re-throw or handle
        }
    }

    // ==========================================================
    // SECTION 5: JUnit Extension for Auto Screenshots
    // ==========================================================

    /*
     * Show this as a separate file concept:
     *
     * public class ScreenshotExtension implements TestWatcher {
     *     @Override
     *     public void testFailed(ExtensionContext context, Throwable cause) {
     *         // Get driver from test instance
     *         // Take screenshot
     *         // Attach to report
     *     }
     * }
     *
     * Usage:
     * @ExtendWith(ScreenshotExtension.class)
     * class MyTests { ... }
     */

    @Test
    @DisplayName("Best practices summary")
    void bestPracticesSummary() {
        driver.get(BASE_URL);

        /*
         * SCREENSHOT BEST PRACTICES:
         *
         * 1. Take screenshots on failure automatically
         * 2. Use timestamps in filenames
         * 3. Organize in dated folders
         * 4. Don't screenshot everything (disk space)
         * 5. Include element screenshots for UI bugs
         * 6. Integrate with test reports (Allure, ExtentReports)
         */

        takeScreenshot("best_practices_demo");
        System.out.println("Screenshots are essential for debugging failures!");
    }

}
