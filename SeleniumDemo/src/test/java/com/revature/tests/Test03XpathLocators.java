package com.revature.tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("XPath Locators Demo")
public class Test03XpathLocators {

    private WebDriver driver;

    // Base URL for practice site
    private static final String BASE_URL = "https://the-internet.herokuapp.com";

    @BeforeEach
    void setUp() {
        // Note: In real setup, use WebDriverManager or set property
        driver = new ChromeDriver();
        driver.manage().window().minimize();
    }

    @AfterEach
    void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    // ==========================================================
    // SECTION 1: Absolute vs Relative XPath
    // ==========================================================

    @Test
    @DisplayName("Absolute XPath - starts from root")
    void absoluteXpath_startsFromRoot() {
        /*
         * Absolute XPath starts with single slash (/)
         * Starts from document root
         *
         * PROBLEMS:
         * - Very brittle - breaks if DOM structure changes
         * - Hard to read and maintain
         * - AVOID in production code!
         */

        driver.get(BASE_URL);

        // Absolute XPath - BAD PRACTICE (but shows the concept)
        // Example: /html/body/div[2]/div/h1
        // This would break if any parent element changes

        // Let's find the heading using absolute path
        try {
            WebElement heading = driver.findElement(
                    By.xpath("/html/body/div[2]/div/h1"));
            System.out.println("Found heading: " + heading.getText());
        } catch (NoSuchElementException e) {
            System.out.println("Absolute XPath is brittle - element not found");
        }
    }

    @Test
    @DisplayName("Relative XPath - starts with //")
    void relativeXpath_startsAnywhere() {
        /*
         * Relative XPath starts with double slash (//)
         * Searches entire document for matching elements
         *
         * MUCH BETTER:
         * - More maintainable
         * - Survives DOM restructuring
         * - Preferred in production
         */

        driver.get(BASE_URL);

        // Relative XPath - GOOD PRACTICE
        WebElement heading = driver.findElement(
                By.xpath("//h1[@class='heading']"));

        System.out.println("Heading text: " + heading.getText());
        assertNotNull(heading.getText());
    }

    // ==========================================================
    // SECTION 2: XPath with Attributes
    // ==========================================================

    @Test
    @DisplayName("XPath by ID attribute")
    void xpathById_findElement() {
        driver.get(BASE_URL + "/login");

        // Find by id attribute
        WebElement usernameInput = driver.findElement(
                By.xpath("//input[@id='username']"));

        assertTrue(usernameInput.isDisplayed());
        System.out.println("Found username input by id");
    }

    @Test
    @DisplayName("XPath by name attribute")
    void xpathByName_findElement() {
        driver.get(BASE_URL + "/login");

        // Find by name attribute
        WebElement passwordInput = driver.findElement(
                By.xpath("//input[@name='password']"));

        assertTrue(passwordInput.isDisplayed());
        System.out.println("Found password input by name");
    }

    @Test
    @DisplayName("XPath by multiple attributes")
    void xpathByMultipleAttributes_moreSpecific() {
        driver.get(BASE_URL + "/login");

        /*
          * Combine multiple attributes for more specific matching
         */

        WebElement loginButton = driver.findElement(
                By.xpath("//button[@type='submit' and @class='radius']"));

        assertTrue(loginButton.isDisplayed());
        System.out.println("Found login button: " + loginButton.getText());
    }

    // ==========================================================
    // SECTION 3: XPath Functions
    // ==========================================================

    @Test
    @DisplayName("contains() - Partial text match")
    void xpathContains_partialMatch() {
        /*
        * contains() matches if attribute/text CONTAINS the value
         * Great for dynamic IDs or partial class names
         */

        driver.get(BASE_URL);

        // Find link containing "Form" in text
        WebElement formLink = driver.findElement(
                By.xpath("//a[contains(text(), 'Form')]"));

        System.out.println("Found link: " + formLink.getText());
        assertTrue(formLink.getText().contains("Form"));

        // Find element with class containing 'heading'
        WebElement heading = driver.findElement(
                By.xpath("//*[contains(@class, 'heading')]"));

        System.out.println("Heading: " + heading.getText());
    }

    @Test
    @DisplayName("starts-with() - Prefix matching")
    void xpathStartsWith_prefixMatch() {
        /*
         * starts-with() matches beginning of attribute value
         * Useful for dynamic IDs with consistent prefixes
         */

        driver.get(BASE_URL + "/dynamic_loading");

        // Find link starting with "Example"
        List<WebElement> exampleLinks = driver.findElements(
                By.xpath("//a[starts-with(text(), 'Example')]"));

        System.out.println("Found " + exampleLinks.size() + " Example links:");
        for (WebElement link : exampleLinks) {
            System.out.println("  - " + link.getText());
        }

        assertTrue(exampleLinks.size() > 0);
    }

    @Test
    @DisplayName("text() - Exact text content")
    void xpathText_exactMatch() {
        /*
         * text() matches the exact text content of an element
         * Case-sensitive!
         */

        driver.get(BASE_URL);

        // Find link with exact text
        WebElement checkboxLink = driver.findElement(
                By.xpath("//a[text()='Checkboxes']"));

        System.out.println("Found: " + checkboxLink.getText());
        assertEquals("Checkboxes", checkboxLink.getText());
    }

    @Test
    @DisplayName("normalize-space() - Handle whitespace")
    void xpathNormalizeSpace_handleWhitespace() {
        /*
         * normalize-space() strips leading/trailing whitespace
         * and collapses multiple spaces into one
         *
         * Useful when text has inconsistent spacing
         */

        driver.get(BASE_URL);

        // This handles text with extra whitespace
        WebElement element = driver.findElement(
                By.xpath("//a[normalize-space(text())='Checkboxes']"));

        assertNotNull(element);
        System.out.println("Found element despite whitespace variations");
    }

    // ==========================================================
    // SECTION 4: XPath Axes
    // ==========================================================

    @Test
    @DisplayName("parent axis - Navigate up")
    void xpathParent_navigateUp() {
        /*
         * parent:: moves up one level in DOM
         * Useful when you can find child but need parent
         */

        driver.get(BASE_URL + "/tables");

        // Find a cell, then get its parent row
        WebElement cell = driver.findElement(
                By.xpath("//td[text()='jsmith@gmail.com']"));

        WebElement parentRow = cell.findElement(
                By.xpath("./parent::tr"));

        System.out.println("Parent row text: " + parentRow.getText());
        assertTrue(parentRow.getText().contains("Smith"));
    }

    @Test
    @DisplayName("child axis - Navigate down")
    void xpathChild_navigateDown() {
        /*
         * child:: selects direct children
         * Default axis, so child::div is same as just div
         */

        driver.get(BASE_URL);

        // Find all direct child links of the content div
        List<WebElement> links = driver.findElements(
                By.xpath("//div[@id='content']//a"));

        System.out.println("Found " + links.size() + " links in content area");
        assertTrue(links.size() > 0);
    }

    @Test
    @DisplayName("following-sibling axis - Next siblings")
    void xpathFollowingSibling_nextElements() {
        /*
         * following-sibling:: selects siblings after current element
         * Useful for table columns or list items
         */

        driver.get(BASE_URL + "/tables");

        // Find "Due" header, then get following sibling headers
        WebElement dueHeader = driver.findElement(
                By.xpath("//th[normalize-space(.)='Due']"));

        List<WebElement> nextHeaders = dueHeader.findElements(
                By.xpath("./following-sibling::th"));

        System.out.println("Headers after 'Due':");
        for (WebElement header : nextHeaders) {
            System.out.println("  - " + header.getText());
        }
    }

    @Test
    @DisplayName("preceding-sibling axis - Previous siblings")
    void xpathPrecedingSibling_previousElements() {
        /*
         * preceding-sibling:: selects siblings before current element
         */

        driver.get(BASE_URL + "/tables");

        // Find "Action" header, then get preceding sibling headers
        WebElement actionHeader = driver.findElement(
                By.xpath("//th[normalize-space(.)='Action']"));

        List<WebElement> prevHeaders = actionHeader.findElements(
                By.xpath("./preceding-sibling::th"));

        System.out.println("Headers before 'Action':");
        for (WebElement header : prevHeaders) {
            System.out.println("  - " + header.getText());
        }
    }

    // ==========================================================
    // SECTION 5: Complex XPath Expressions
    // ==========================================================

    @Test
    @DisplayName("Combining conditions with and/or")
    void xpathAndOr_combineConditions() {
        driver.get(BASE_URL + "/login");

        // Using 'and'
        WebElement button = driver.findElement(
                By.xpath("//button[@type='submit' and contains(@class, 'radius')]"));

        System.out.println("Button found: " + button.getText());

        // Using 'or'
        List<WebElement> inputs = driver.findElements(
                By.xpath("//input[@id='username' or @id='password']"));

        System.out.println("Found " + inputs.size() + " inputs");
        assertEquals(2, inputs.size());
    }

    @Test
    @DisplayName("Indexed selection with [n]")
    void xpathIndex_selectByPosition() {
        /*
         * XPath indexes start at 1, not 0!
         * Use [1] for first, [last()] for last
         */

        driver.get(BASE_URL);

        // Get first link
        WebElement firstLink = driver.findElement(
                By.xpath("(//a)[1]"));
        System.out.println("First link: " + firstLink.getText());

        // Get last link
        WebElement lastLink = driver.findElement(
                By.xpath("(//a)[last()]"));
        System.out.println("Last link: " + lastLink.getText());

        // Get specific link by position
        WebElement thirdLink = driver.findElement(
                By.xpath("(//div[@id='content']//a)[3]"));
        System.out.println("Third link: " + thirdLink.getText());
    }

    @Test
    @DisplayName("XPath with not() function")
    void xpathNot_excludeElements() {
        /*
         * not() excludes elements matching a condition
         */

        driver.get(BASE_URL + "/checkboxes");

        // Find checkbox that is NOT checked
        List<WebElement> unchecked = driver.findElements(
                By.xpath("//input[@type='checkbox' and not(@checked)]"));

        System.out.println("Unchecked checkboxes: " + unchecked.size());

        // Find checkbox that IS checked
        List<WebElement> checked = driver.findElements(
                By.xpath("//input[@type='checkbox' and @checked]"));

        System.out.println("Checked checkboxes: " + checked.size());
    }

    // ==========================================================
    // SECTION 6: XPath Best Practices
    // ==========================================================

    @Test
    @DisplayName("XPath best practices demonstration")
    void xpathBestPractices_examples() {
        driver.get(BASE_URL + "/login");

        /*
         * Show good vs bad XPath examples
         */

        // BAD: Too brittle, depends on DOM structure
        // //html/body/div[2]/div/div/form/div[1]/input

        // GOOD: Uses meaningful attributes
        WebElement good1 = driver.findElement(
                By.xpath("//input[@id='username']"));

        // GOOD: Short and specific
        WebElement good2 = driver.findElement(
                By.xpath("//button[@type='submit']"));

        // GOOD: Uses text when appropriate
        WebElement good3 = driver.findElement(
                By.xpath("//h2[normalize-space(.)='Login Page']/following::form[1]"));

        // BAD: Index-based (fragile)
        // //form/div[1]/input

        // GOOD
        WebElement good4 = driver.findElement(
                By.xpath("//div[@id='content']//form"));

        System.out.println("All good XPath examples found successfully!");
    }

}
