package com.revature.cucumber.hooks;

import io.cucumber.java.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;
import java.util.concurrent.atomic.AtomicInteger;

public class Hooks_old {

    private static WebDriver driver;

    // Flags set by tag-based hooks (must be set before WebDriver is created)
    private static boolean headless;
    private static boolean slow;

    // Lightweight "contexts" for conditional hooks (no external libs required)
    private static final ThreadLocal<Boolean> DATABASE_CONTEXT =
            ThreadLocal.withInitial(() -> false);
    private static final ThreadLocal<String> API_BASE_URL =
            ThreadLocal.withInitial(() -> null);
    private static final ThreadLocal<String> USER_CONTEXT =
            ThreadLocal.withInitial(() -> "anonymous");

    /**
     * Runs before scenarios tagged with @headless.
     * Configures headless browser mode.
     * This should run BEFORE the regular setup.
     */
    @Before(value = "@headless", order = 0)
    public void setUpHeadless() {
        System.out.println("Configuring headless mode...");
        headless = true;
    }

    /**
     * Runs before scenarios tagged with @slow.
     * Increases timeout values.
     *
     * We set a flag here and apply timeouts after the driver is created.
     */
    @Before(value = "@slow", order = 0)
    public void configureSlowTest() {
        System.out.println("Configuring slow-test timeouts...");
        slow = true;
    }

    /**
     * Runs only for scenarios tagged with @database.
     * Sets up database connection and test data.
     */
    @Before("@database")
    public void setUpDatabase(Scenario scenario) {
        System.out.println("Setting up database for: " + scenario.getName());

        // Minimal "working" implementation without requiring a real DB dependency.
        // If you later add JDBC/Testcontainers/etc., replace this with real setup.
        DATABASE_CONTEXT.set(true);

        // Example placeholders for real work:
        // 1. Connect to a test database
        // 2. Clear test data
        // 3. Insert required fixtures
        System.out.println("Database context enabled (stub).");
    }

    /**
     * Runs for scenarios tagged with @api.
     * Sets up API test configuration.
     */
    @Before("@api")
    public void setUpApi() {
        // Minimal setup: define a base URL in a ThreadLocal + System property
        // so any API client code can read it consistently.
        String baseUrl = System.getProperty("api.baseUrl");
        if (baseUrl == null || baseUrl.isBlank()) {
            baseUrl = System.getenv("API_BASE_URL");
        }
        if (baseUrl == null || baseUrl.isBlank()) {
            baseUrl = "http://localhost:8080";
        }

        API_BASE_URL.set(baseUrl);
        System.setProperty("api.baseUrl", baseUrl);

        // Example placeholders for real work:
        // 1. Configure base URL
        // 2. Set up authentication
        // 3. Initialize REST client
        System.out.println("Setting up API test configuration... baseUrl=" + baseUrl);
    }

    /**
     * Runs for scenarios tagged with both @login AND @admin.
     * Sets up admin user context.
     */
    @Before("@login and @admin")
    public void setUpAdminLogin() {
        USER_CONTEXT.set("admin");
        System.out.println("Setting up admin login context");
        // If you later add real login helpers, you can perform them here.
    }

    /**
     * Runs for scenarios tagged with @login but NOT @admin.
     * Sets up regular user context.
     */
    @Before("@login and not @admin")
    public void setUpRegularLogin() {
        USER_CONTEXT.set("user");
        System.out.println("Setting up regular user login context");
        // If you later add real login helpers, you can perform them here.
    }

    /**
     * Runs before each scenario.
     * Sets up the WebDriver.
     */
    @Before(order = 1)
    public void setUp(Scenario scenario) {
        System.out.println("Starting scenario: " + scenario.getName());

        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        // Common options you can keep:
        options.addArguments("--disable-gpu");
        options.addArguments("--remote-allow-origins=*");

        if (headless) {
            options.addArguments("--headless=new");
            options.addArguments("--window-size=1920,1080");
        }

        driver = new ChromeDriver(options);

        if (!headless) {
            driver.manage().window().maximize();
        }

        if (slow) {
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));
        } else {
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
            driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
        }
    }


    /**
     * Optional: Runs after each step.
     * Useful for debugging or capturing step-level screenshots.
     */
    @AfterStep
    public void afterStep(Scenario scenario) {
        // Optional: step-level logging can be noisy/slow
    }

    /**
     * Runs only for scenarios tagged with @database.
     * Cleans up database after test.
     */
    @After("@database")
    public void tearDownDatabase(Scenario scenario) {
        System.out.println("Cleaning up database after: " + scenario.getName());

        // Stub cleanup that "works" without an actual DB connection.
        // Replace with real cleanup when you introduce a DB layer.
        if (Boolean.TRUE.equals(DATABASE_CONTEXT.get())) {
            // 1. Delete test data
            // 2. Close connection
            DATABASE_CONTEXT.set(false);
            System.out.println("Database context cleared (stub).");
        }
    }

    /**
     * Runs after each scenario.
     * Cleans up WebDriver and captures screenshot on failure.
     */
    @After
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed()) {
            captureScreenshot(scenario);
        }

        if (driver != null) {
            driver.quit();
            driver = null;
        }

        // Reset per-scenario flags so they don't leak into the next scenario
        headless = false;
        slow = false;

        // Clear per-scenario ThreadLocals
        DATABASE_CONTEXT.remove();
        API_BASE_URL.remove();
        USER_CONTEXT.remove();

        System.out.println("Finished scenario: " + scenario.getName()
                + " - Status: " + scenario.getStatus());
    }

    /**
     * Captures screenshot and attaches to Cucumber report.
     */
    private void captureScreenshot(Scenario scenario) {
        if (driver == null) return;

        byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        scenario.attach(screenshot, "image/png", "Screenshot on failure");
    }

    /**
     * Provides access to WebDriver for step definitions.
     */
    public static WebDriver getDriver() {
        return driver;
    }
}
