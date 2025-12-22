package com.revature.page.factory;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SecurePageFactory {
    private WebDriver driver;

    @FindBy(id = "flash")
    private WebElement flashMessage;

    @FindBy(css = "a[href*='logout']")
    @CacheLookup
    private WebElement logoutButton;

    @FindBy(tagName = "h2")
    private WebElement heading;

    public SecurePageFactory(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String getFlashMessage() {
        return flashMessage.getText();
    }

    public String getHeading() {
        return heading.getText();
    }

    public boolean isSecureAreaDisplayed() {
        try {
            return heading.getText().contains("Secure Area");
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public LoginPageFactory clickLogout() {
        logoutButton.click();
        return new LoginPageFactory(driver);
    }

}
