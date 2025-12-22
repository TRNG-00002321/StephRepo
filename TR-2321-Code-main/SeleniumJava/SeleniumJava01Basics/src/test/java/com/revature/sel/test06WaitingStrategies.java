package com.revature.sel;

import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.FluentWait;

import static org.junit.jupiter.api.Assertions.*;

import java.time.Duration;
import java.util.function.Function;

@DisplayName("Waiting Strategies Demo")
public class test06WaitingStrategies {
    private WebDriver driver;
    private static final String BASE_URL = "https://the-internet.herokuapp.com";

    @BeforeEach
    void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @AfterEach
    void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    // ==========================================================
    // SECTION 1: Why We Need Waits
    // ==========================================================

    @Test
    @DisplayName("Without wait - NoSuchElementException")
    @Disabled("Demonstrates failure - enable to see")
    void withoutWait_fails() {
        /*
         * This test will fail because element isn't there immediately
         */

        driver.get(BASE_URL + "/dynamic_loading/1");

        // Click start button
        driver.findElement(By.xpath("//button[text()='Start']")).click();

        // Try to get text immediately - FAILS!
        WebElement result = driver.findElement(By.id("finish"));
        System.out.println("Result: " + result.getText());
    }

    // ==========================================================
    // SECTION 2: Implicit Waits
    // ==========================================================

    @Test
    @DisplayName("Implicit wait - Global timeout")
    void implicitWait_globalTimeout() {
        /*
         * Implicit wait applies to ALL findElement calls
         * Set once, applies everywhere
         * Pros: Simple, one-time setup
         * Cons: Applies to everything, can slow down tests
         */

        // Set implicit wait for all elements
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.get(BASE_URL + "/dynamic_loading/1");

        // Click start
        driver.findElement(By.xpath("//button[text()='Start']")).click();

        // This will wait up to 10 seconds for element to appear
        WebElement result = driver.findElement(By.id("finish"));
        String text = result.getText();

        System.out.println("Result: " + text);
        assertTrue(text.contains("Hello World"));

        // Reset implicit wait (optional, but good practice)
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
    }

    // ==========================================================
    // SECTION 3: Explicit Waits
    // ==========================================================

    @Test
    @DisplayName("Explicit wait - WebDriverWait")
    void explicitWait_webDriverWait() {
        /*
         * Explicit waits are targeted to specific elements
         * Use ExpectedConditions for common scenarios
         * PREFERRED approach for most situations
         */

        driver.get(BASE_URL + "/dynamic_loading/1");

        // Click start
        driver.findElement(By.xpath("//button[text()='Start']")).click();

        // Create explicit wait
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Wait for element to be visible
        WebElement result = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("finish")));

        String text = result.getText();
        System.out.println("Result: " + text);
        assertTrue(text.contains("Hello World"));
    }

    @Test
    @DisplayName("ExpectedConditions - Various conditions")
    void expectedConditions_catalog() {
        /*
         * ExpectedConditions has many useful conditions
         */

        driver.get(BASE_URL + "/dynamic_loading/2");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        // visibilityOfElementLocated - waits until element is visible
        WebElement startBtn = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//button[text()='Start']")));

        // elementToBeClickable - waits until element can be clicked
        wait.until(ExpectedConditions.elementToBeClickable(startBtn));
        startBtn.click();

        // presenceOfElementLocated - element exists in DOM
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("finish")));

        // visibilityOfElementLocated - element is visible
        WebElement result = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("finish")));

        System.out.println("Result: " + result.getText());
    }

    @Test
    @DisplayName("Common ExpectedConditions examples")
    void commonExpectedConditions_examples() {
        driver.get(BASE_URL + "/checkboxes");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Wait for title to contain text
        wait.until(ExpectedConditions.titleContains("Checkboxes"));
        System.out.println("Title check passed");

        // Wait for URL to contain text
        wait.until(ExpectedConditions.urlContains("checkboxes"));
        System.out.println("URL check passed");

        // Wait for element to be present
        WebElement checkbox = wait.until(
                ExpectedConditions.presenceOfElementLocated(
                        By.xpath("//input[@type='checkbox']")));
        System.out.println("Element present");

        // Wait for element to be clickable
        wait.until(ExpectedConditions.elementToBeClickable(checkbox));
        System.out.println("Element clickable");

        checkbox.click();

        // Wait for element to be selected
        wait.until(ExpectedConditions.elementToBeSelected(checkbox));
        System.out.println("Element selected");
    }

    // ==========================================================
    // SECTION 4: Fluent Waits
    // ==========================================================

    @Test
    @DisplayName("Fluent wait - Custom polling")
    void fluentWait_customPolling() {
        /*
         * FluentWait allows:
         * - Custom timeout
         * - Custom polling interval
         * - Ignoring specific exceptions
         */

        driver.get(BASE_URL + "/dynamic_loading/1");
        driver.findElement(By.xpath("//button[text()='Start']")).click();

        // Create fluent wait with custom settings
        Wait<WebDriver> fluentWait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(30))
                .pollingEvery(Duration.ofMillis(500))  // Check every 500ms
                .ignoring(NoSuchElementException.class)
                .withMessage("Waiting for result element");

        WebElement result = fluentWait.until(driver -> {
            WebElement element = driver.findElement(By.id("finish"));
            return element.isDisplayed() ? element : null;
        });

        System.out.println("Result: " + result.getText());
    }

    // ==========================================================
    // SECTION 5: Custom Wait Conditions
    // ==========================================================

    @Test
    @DisplayName("Custom wait condition")
    void customWaitCondition() {
        /*
         * Create custom conditions when built-in ones don't suffice
         */

        driver.get(BASE_URL + "/dynamic_loading/1");
        driver.findElement(By.xpath("//button[text()='Start']")).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        // Custom condition: wait until element has specific text
        WebElement result = wait.until(new Function<WebDriver, WebElement>() {
            @Override
            public WebElement apply(WebDriver d) {
                try {
                    WebElement elem = d.findElement(By.id("finish"));
                    if (elem.getText().contains("Hello")) {
                        return elem;
                    }
                } catch (NoSuchElementException e) {
                    // Element not yet present
                }
                return null;
            }
        });

        System.out.println("Custom wait succeeded: " + result.getText());
    }

    @Test
    @DisplayName("Lambda-based custom condition")
    void lambdaCustomCondition() {
        driver.get(BASE_URL + "/dynamic_loading/1");
        driver.findElement(By.xpath("//button[text()='Start']")).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        // Lambda version - cleaner syntax
        WebElement result = wait.until(d -> {
            try {
                WebElement elem = d.findElement(By.id("finish"));
                return elem.isDisplayed() ? elem : null;
            } catch (NoSuchElementException e) {
                return null;
            }
        });

        assertTrue(result.getText().contains("Hello World"));
    }

    // ==========================================================
    // SECTION 6: Wait Best Practices
    // ==========================================================

    @Test
    @DisplayName("Wait best practices demonstration")
    void waitBestPractices() {
        /*
         *
         * DO:
         * - Use explicit waits for specific conditions
         * - Set reasonable timeout values
         * - Wait for the right condition
         *
         * DON'T:
         * - Use Thread.sleep()
         * - Mix implicit and explicit waits
         * - Wait longer than necessary
         */

        driver.get(BASE_URL + "/dynamic_loading/1");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // GOOD: Wait for clickable before clicking
        WebElement startBtn = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("//button[text()='Start']")));
        startBtn.click();

        // GOOD: Wait for visibility when reading text
        WebElement result = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("finish")));

        // GOOD: Use descriptive assertion messages
        assertTrue(result.getText().contains("Hello"),
                "Expected 'Hello' in result text");

        System.out.println("Best practices followed!");
    }

    @Test
    @DisplayName("What NOT to do - Thread.sleep")
    @Disabled("Demonstrates bad practice")
    void badPractice_threadSleep() throws InterruptedException {
        /*
         * NEVER use Thread.sleep() in production tests!
         *
         * Problems:
         * - Wastes time if element appears early
         * - Still fails if element takes longer
         * - Not reliable across different machines/networks
         */

        driver.get(BASE_URL + "/dynamic_loading/1");
        driver.findElement(By.xpath("//button[text()='Start']")).click();

        // BAD: Hard-coded sleep
        Thread.sleep(5000);  // Always waits 5 seconds!

        WebElement result = driver.findElement(By.id("finish"));
        System.out.println(result.getText());
    }

    // ==========================================================
    // SECTION 7: Wait for Invisibility
    // ==========================================================

    @Test
    @DisplayName("Wait for element to disappear")
    void waitForInvisibility() {
        /*
         * Sometimes we need to wait for something to DISAPPEAR
         * Like loading spinners or overlays
         */

        driver.get(BASE_URL + "/dynamic_loading/1");
        driver.findElement(By.xpath("//button[text()='Start']")).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        // Wait for loading indicator to disappear
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loading")));

        // Now the result should be visible
        WebElement result = driver.findElement(By.id("finish"));
        System.out.println("Loading finished. Result: " + result.getText());
    }

    // ==========================================================
    // SECTION 8: Multiple Conditions
    // ==========================================================

    @Test
    @DisplayName("Wait for multiple conditions")
    void waitForMultipleConditions() {
        driver.get(BASE_URL + "/login");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Wait for multiple elements to be present
        wait.until(ExpectedConditions.and(
                ExpectedConditions.presenceOfElementLocated(By.id("username")),
                ExpectedConditions.presenceOfElementLocated(By.id("password")),
                ExpectedConditions.elementToBeClickable(By.xpath("//button[@type='submit']"))
        ));

        System.out.println("All form elements ready");

        // Wait for either of two conditions
        // wait.until(ExpectedConditions.or(
        //     ExpectedConditions.presenceOfElementLocated(By.id("success")),
        //     ExpectedConditions.presenceOfElementLocated(By.id("error"))
        // ));
    }

}
