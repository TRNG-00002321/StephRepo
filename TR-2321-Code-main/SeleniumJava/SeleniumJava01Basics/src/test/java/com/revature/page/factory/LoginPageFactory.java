package com.revature.page.factory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPageFactory {
    private WebDriver driver;

    // @FindBy declares element locators
    @FindBy(id = "username")
    private WebElement usernameField;

    @FindBy(id = "password")
    private WebElement passwordField;

    @FindBy(xpath = "//button[@type='submit']")
    @CacheLookup  // Cache element after first find
    private WebElement loginButton;

    @FindBy(id = "flash")
    private WebElement flashMessage;

    @FindBy(css = ".subheader")
    private WebElement subheader;

    // Multiple element find
    @FindBy(tagName = "input")
    private java.util.List<WebElement> allInputs;

    // Constructor must call initElements
    public LoginPageFactory(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Action methods use elements directly
    public LoginPageFactory enterUsername(String username) {
        usernameField.clear();
        usernameField.sendKeys(username);
        return this;
    }

    public LoginPageFactory enterPassword(String password) {
        passwordField.clear();
        passwordField.sendKeys(password);
        return this;
    }

    public SecurePageFactory clickLogin() {
        loginButton.click();
        return new SecurePageFactory(driver);
    }

    public String getFlashMessage() {
        return flashMessage.getText();
    }

    public String getSubheader() {
        return subheader.getText();
    }

    public int getInputCount() {
        return allInputs.size();
    }

    public SecurePageFactory loginAs(String username, String password) {
        return enterUsername(username)
                .enterPassword(password)
                .clickLogin();
    }

}
