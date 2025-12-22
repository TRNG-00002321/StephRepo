package com.revature.sel;


import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Find Elements Demo")
public class Test02FindElements {
    private WebDriver driver;
    private static final String BASE_URL = "https://the-internet.herokuapp.com";

    @BeforeAll
    static void setupClass() {
        WebDriverManager.chromedriver().setup();
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
    // SECTION 1: All By Strategies
    // ==========================================================

    @Test
    @DisplayName("By.id - Fastest and most reliable")
    void byId() {
        driver.get(BASE_URL + "/login");

        WebElement username = driver.findElement(By.id("username"));
        WebElement password = driver.findElement(By.id("password"));

        assertTrue(username.isDisplayed());
        assertTrue(password.isDisplayed());
        System.out.println("By.id - Best choice when ID exists!");
    }

    @Test
    @DisplayName("By.name - Good for form elements")
    void byName() {
        driver.get(BASE_URL + "/login");

        WebElement username = driver.findElement(By.name("username"));
        assertTrue(username.isDisplayed());
        System.out.println("By.name - Useful for forms");
    }

    @Test
    @DisplayName("By.className - Match single class")
    void byClassName() {
        driver.get(BASE_URL + "/login");

        WebElement button = driver.findElement(By.className("radius"));
        assertTrue(button.getText().contains("Login"));
        System.out.println("By.className - Note: only one class at a time");
    }

    @Test
    @DisplayName("By.tagName - Find by HTML tag")
    void byTagName() {
        driver.get(BASE_URL + "/login");

        List<WebElement> inputs = driver.findElements(By.tagName("input"));
        System.out.println("Found " + inputs.size() + " input elements");
        assertTrue(inputs.size() >= 2);
    }

    @Test
    @DisplayName("By.linkText - Exact link text")
    void byLinkText() {
        driver.get(BASE_URL);

        WebElement link = driver.findElement(By.linkText("Form Authentication"));
        link.click();
        assertTrue(driver.getCurrentUrl().contains("login"));
        System.out.println("By.linkText - Exact match required");
    }

    @Test
    @DisplayName("By.partialLinkText - Partial link text")
    void byPartialLinkText() {
        driver.get(BASE_URL);

        WebElement link = driver.findElement(By.partialLinkText("Form Auth"));
        link.click();
        assertTrue(driver.getCurrentUrl().contains("login"));
        System.out.println("By.partialLinkText - Flexible matching");
    }

    @Test
    @DisplayName("By.cssSelector - Powerful CSS selectors")
    void byCssSelector() {
        driver.get(BASE_URL + "/login");

        // By ID
        WebElement byId = driver.findElement(By.cssSelector("#username"));

        // By class
        WebElement byClass = driver.findElement(By.cssSelector(".radius"));

        // By attribute
        WebElement byAttr = driver.findElement(By.cssSelector("input[name='password']"));

        // Combined
        WebElement combined = driver.findElement(By.cssSelector("form#login button.radius"));

        System.out.println("CSS Selectors are powerful and fast!");
    }

    @Test
    @DisplayName("By.xpath - Most flexible")
    void byXpath() {
        driver.get(BASE_URL + "/login");

        // Basic
        WebElement basic = driver.findElement(By.xpath("//input[@id='username']"));

        // With text
        WebElement withText = driver.findElement(By.xpath("//button[text()='Login']"));

        // Contains
        WebElement contains = driver.findElement(By.xpath("//button[contains(@class,'radius')]"));

        System.out.println("XPath is most flexible but can be slower");
    }

    // ==========================================================
    // SECTION 2: findElement vs findElements
    // ==========================================================

    @Test
    @DisplayName("findElement - Returns single element")
    void findElement_single() {
        driver.get(BASE_URL + "/login");

        // Returns first matching element
        WebElement element = driver.findElement(By.tagName("input"));
        assertNotNull(element);

        // Throws NoSuchElementException if not found
        assertThrows(NoSuchElementException.class, () -> {
            driver.findElement(By.id("nonexistent"));
        });
    }

    @Test
    @DisplayName("findElements - Returns list")
    void findElements_list() {
        driver.get(BASE_URL + "/login");

        // Returns list of all matching elements
        List<WebElement> inputs = driver.findElements(By.tagName("input"));
        System.out.println("Found " + inputs.size() + " inputs");

        // Returns empty list if not found (no exception!)
        List<WebElement> nonexistent = driver.findElements(By.id("nonexistent"));
        assertEquals(0, nonexistent.size());
        System.out.println("Empty list for no matches - no exception!");
    }

    @Test
    @DisplayName("Iterating over multiple elements")
    void iteratingElements() {
        driver.get(BASE_URL + "/checkboxes");

        List<WebElement> checkboxes = driver.findElements(By.xpath("//input[@type='checkbox']"));

        for (int i = 0; i < checkboxes.size(); i++) {
            WebElement checkbox = checkboxes.get(i);
            System.out.println("Checkbox " + (i+1) + " selected: " + checkbox.isSelected());
        }
    }

    // ==========================================================
    // SECTION 3: Locator Selection Guide
    // ==========================================================

    @Test
    @DisplayName("Locator selection best practices")
    void locatorSelectionGuide() {
        driver.get(BASE_URL + "/login");

        /*
         * LOCATOR PRIORITY (Best to Most Flexible):
         *
         * 1. ID - Fast, unique, stable
         *    By.id("username")
         *
         * 2. Name - Good for forms
         *    By.name("password")
         *
         * 3. CSS Selector - Powerful, fast
         *    By.cssSelector("#login .submit")
         *
         * 4. XPath - Most flexible, can match text
         *    By.xpath("//button[text()='Login']")
         *
         * 5. Link Text - Only for <a> elements
         *    By.linkText("Forgot Password?")
         *
         * AVOID:
         * - Index-only selectors (//div[3])
         * - Absolute XPath (/html/body/div/...)
         * - Dynamically generated IDs
         */

        // Best: ID
        WebElement byId = driver.findElement(By.id("username"));

        // Good: CSS
        WebElement byCss = driver.findElement(By.cssSelector("#username"));

        // Flexible: XPath with text
        WebElement byXpath = driver.findElement(By.xpath("//button[contains(text(),'Login')]"));

        System.out.println("Use the simplest locator that works!");
    }
}
