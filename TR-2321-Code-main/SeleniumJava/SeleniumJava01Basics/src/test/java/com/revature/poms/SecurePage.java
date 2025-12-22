package com.revature.poms;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SecurePage {
    private WebDriver driver;
    private WebDriverWait wait;

    private By flashMessage = By.id("flash");
    private By logoutButton = By.xpath("//a[contains(@href, 'logout')]");
    private By heading = By.tagName("h2");

    public SecurePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public String getFlashMessage() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(flashMessage));
        return driver.findElement(flashMessage).getText();
    }

    public String getHeading() {
        return driver.findElement(heading).getText();
    }

    public LoginPage clickLogout() {
        driver.findElement(logoutButton).click();
        return new LoginPage(driver);
    }

    public boolean isSecureAreaDisplayed() {
        try {
            return driver.findElement(heading).getText().contains("Secure Area");
        } catch (NoSuchElementException e) {
            return false;
        }
    }

}
