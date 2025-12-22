package com.bookhaven.ui;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import static org.junit.jupiter.api.Assertions.*;

class FirstSeleniumTest extends BaseTest {

    @Test
    @DisplayName("Navigate to Google and verify title")
    void testNavigateToGoogle() {
        // Navigate to URL
        driver.get("https://www.google.com");

        // Get page title
        String title = driver.getTitle();

        // Verify title
        assertTrue(title.contains("Google"), "Page title should contain 'Google'");
    }

    @Test
    @DisplayName("Navigate to Example.com and verify content")
    void testNavigateToExample() {
        // Navigate
        driver.get("https://example.com");

        // Get title and URL
        String title = driver.getTitle();
        String currentUrl = driver.getCurrentUrl();

        // Assertions
        assertEquals("Example Domain", title);
        assertTrue(currentUrl.contains("example.com"));

        // Find element and verify text
        WebElement heading = driver.findElement(By.tagName("h1"));
        assertEquals("Example Domain", heading.getText());
    }

    @Test
    @DisplayName("Navigate to Youtube.com")
    void testNavigateToYoutube() {
        // Navigate
        driver.get("https://www.youtube.com");

        // Get title and URL
        String currentUrl = driver.getCurrentUrl();

        // Assertions
        assertTrue(currentUrl.contains("youtube.com"));

    }

    @Test
    @DisplayName("Navigate to practice site and find elements")
    void testFindElements() {
        // Navigate to a practice site
        driver.get("https://the-internet.herokuapp.com/");

        // Find heading
        WebElement heading = driver.findElement(By.tagName("h1"));
        assertEquals("Welcome to the-internet", heading.getText());

        // Find link by link text
        WebElement formAuthLink = driver.findElement(By.linkText("Form Authentication"));
        assertTrue(formAuthLink.isDisplayed());

        // Get page source
        String pageSource = driver.getPageSource();
        assertTrue(pageSource.contains("Available Examples"));
    }

    @Test
    @DisplayName("Fill and submit login form")
    void testLoginForm() {
        // Navigate to login page
        driver.get("https://the-internet.herokuapp.com/login");

        // Find username field and enter text
        WebElement usernameField = driver.findElement(By.id("username"));
        usernameField.sendKeys("tomsmith");

        // Find password field and enter text
        WebElement passwordField = driver.findElement(By.id("password"));
        passwordField.sendKeys("SuperSecretPassword!");

        // Find and click submit button
        WebElement loginButton = driver.findElement(By.cssSelector("button[type='submit']"));
        loginButton.click();

        // Verify success message
        WebElement flashMessage = driver.findElement(By.id("flash"));
        assertTrue(flashMessage.getText().contains("You logged into a secure area!"));
    }

    @Test
    @DisplayName("Test invalid login")
    void testInvalidLogin() {
        driver.get("https://the-internet.herokuapp.com/login");

        // Enter invalid credentials
        driver.findElement(By.id("username")).sendKeys("invalid");
        driver.findElement(By.id("password")).sendKeys("invalid");
        driver.findElement(By.cssSelector("button[type='submit']")).click();

        // Verify error message
        WebElement flashMessage = driver.findElement(By.id("flash"));
        assertTrue(flashMessage.getText().contains("Your username is invalid!"));
    }

    @Test
    @DisplayName("Test form clearing")
    void testFormClearing() {
        driver.get("https://the-internet.herokuapp.com/login");

        WebElement usernameField = driver.findElement(By.id("username"));

        // Enter text
        usernameField.sendKeys("some text");
        assertEquals("some text", usernameField.getAttribute("value"));

        // Clear field
        usernameField.clear();
        assertEquals("", usernameField.getAttribute("value"));

        // Enter new text
        usernameField.sendKeys("new text");
        assertEquals("new text", usernameField.getAttribute("value"));
    }

    @Test
    @DisplayName("Logout after successful login")
    void testLogout() {
        driver.get("https://the-internet.herokuapp.com/login");

        driver.findElement(By.id("username")).sendKeys("tomsmith");
        driver.findElement(By.id("password")).sendKeys("SuperSecretPassword!");
        driver.findElement(By.cssSelector("button[type='submit']")).click();

        WebElement logoutButton = driver.findElement(By.cssSelector("a.button.secondary.radius"));
        logoutButton.click();

        WebElement flashMessage = driver.findElement(By.id("flash"));
        assertTrue(flashMessage.getText().contains("You logged out of the secure area!"));

        assertTrue(driver.getCurrentUrl().contains("/login"));
    }

    @Test
    @DisplayName("Verify placeholder text in login input fields")
    void testLoginPlaceholders() {
        driver.get("https://the-internet.herokuapp.com/login");

        WebElement usernameField = driver.findElement(By.id("username"));
        WebElement passwordField = driver.findElement(By.id("password"));

        // Verify placeholder text (placeholder text is empty by default)

        assertEquals("", usernameField.getAttribute("placeholder"));
        assertEquals("", passwordField.getAttribute("placeholder"));
    }

    @Test
    @DisplayName("Login using Enter key instead of clicking the button")
    void testLoginWithEnterKey() {
        driver.get("https://the-internet.herokuapp.com/login");

        driver.findElement(By.id("username")).sendKeys("tomsmith");
        driver.findElement(By.id("password")).sendKeys("SuperSecretPassword!" + Keys.ENTER);

        WebElement flashMessage = driver.findElement(By.id("flash"));
        assertTrue(flashMessage.getText().contains("You logged into a secure area!"));
    }

    @Test
    @DisplayName("Test link clicking and navigation")
    void testLinkClicking() {
        driver.get("https://the-internet.herokuapp.com/");

        // Click a link
        driver.findElement(By.linkText("Checkboxes")).click();

        // Verify navigation
        assertTrue(driver.getCurrentUrl().contains("checkboxes"));

        // Go back
        driver.navigate().back();

        // Verify we're back
        assertTrue(driver.getCurrentUrl().equals("https://the-internet.herokuapp.com/"));
    }

    @Test
    @DisplayName("Test checkbox interactions")
    void testCheckboxes() {
        driver.get("https://the-internet.herokuapp.com/checkboxes");

        // Find checkboxes
        java.util.List<WebElement> checkboxes = driver.findElements(By.cssSelector("input[type='checkbox']"));

        assertEquals(2, checkboxes.size(), "Should find 2 checkboxes");

        // Check states
        WebElement checkbox1 = checkboxes.get(0);
        WebElement checkbox2 = checkboxes.get(1);

        // First checkbox - initially unchecked
        assertFalse(checkbox1.isSelected());

        // Click to check
        checkbox1.click();
        assertTrue(checkbox1.isSelected());

        // Click to uncheck
        checkbox1.click();
        assertFalse(checkbox1.isSelected());
    }

    @Test
    @DisplayName("Test getting element attributes")
    void testGetAttributes() {
        driver.get("https://the-internet.herokuapp.com/login");

        WebElement usernameField = driver.findElement(By.id("username"));

        // Get various attributes
        String id = usernameField.getAttribute("id");
        String type = usernameField.getAttribute("type");
        String name = usernameField.getAttribute("name");

        assertEquals("username", id);
        assertEquals("text", type);
        assertEquals("username", name);

        // Check if element is enabled and displayed
        assertTrue(usernameField.isEnabled());
        assertTrue(usernameField.isDisplayed());
    }

}