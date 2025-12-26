package com.revature.cucumber.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SecurePage extends BasePage {

    // Locators using @FindBy
    @FindBy(id = "username")
    private WebElement usernameField;

    @FindBy(id = "password")
    private WebElement passwordField;

    @FindBy(css = "button[type='submit']")
    private WebElement loginButton;

    // Locator for flash message
    private By flashMessage = By.id("flash");


    public SecurePage(WebDriver driver) {
        super(driver);
    }

    public void navigateToLogin() {
        navigateTo("/login");
    }

    public void enterUsername(String username) {
        // TODO: Implement
        usernameField.clear();
        usernameField.sendKeys(username);
    }

    public void enterPassword(String password) {
        // TODO: Implement
        passwordField.clear();
        passwordField.sendKeys(password);
    }

    public void clickLogin() {
        // TODO: Implement
        loginButton.click();
    }

    public void login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLogin();
    }

    public String getFlashMessage() {
        return getText(flashMessage);
    }

    public boolean isFlashMessageDisplayed() {
        return isDisplayed(flashMessage);
    }

    public boolean isOnLoginPage() {
        return getCurrentUrl().contains("/login");
    }
}
