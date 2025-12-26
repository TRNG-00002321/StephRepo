package com.revature.cucumber.steps;

import io.cucumber.java.en.*;
import static org.junit.jupiter.api.Assertions.*;

import com.revature.cucumber.context.TestContext;
import com.revature.cucumber.pages.LoginPage;
import com.revature.cucumber.pages.SecurePage;

public class LoginSteps {

    private TestContext context;
    private LoginPage loginPage;
    private SecurePage securePage;

    public LoginSteps() {
        context = TestContext.getInstance();
    }

    private LoginPage getLoginPage() {
        return context.getLoginPage();
    }

    private SecurePage getSecurePage() {
        return context.getSecurePage();
    }

    @Given("the user is on the login page")
    public void theUserIsOnTheLoginPage() {
        getLoginPage().navigateToLogin();
    }

    @When("the user enters username {string}")
    public void theUserEntersUsername(String username) {
        getLoginPage().enterUsername(username);
        context.setCurrentUser(username);
    }

    @When("the user enters password {string}")
    public void theUserEntersPassword(String password) {
        getLoginPage().enterPassword(password);
    }

    @When("the user clicks the login button")
    public void theUserClicksTheLoginButton() {
        getLoginPage().clickLogin();
    }

    @When("the user logs in with username {string} and password {string}")
    public void theUserLogsIn(String username, String password) {
        getLoginPage().login(username, password);
        context.setCurrentUser(username);
    }

    @Then("the user should be redirected to the secure area")
    public void theUserShouldBeRedirectedToSecureArea() {
        assertTrue(context.getDriver().getCurrentUrl().contains("/secure"),
                "User was not redirected to secure area");
    }

    @Then("the user should see a success message containing {string}")
    public void theUserShouldSeeSuccessMessage(String expectedMessage) {
        String actualMessage = getLoginPage().getFlashMessage();
        assertTrue(actualMessage.contains(expectedMessage),
                "Expected message containing '" + expectedMessage + "' but got '" + actualMessage + "'");
    }

    @Then("the user should remain on the login page")
    public void theUserShouldRemainOnLoginPage() {
        assertTrue(getLoginPage().isOnLoginPage(),
                "User should remain on login page");
    }

    @Then("the user should see an error message containing {string}")
    public void theUserShouldSeeErrorMessage(String expectedMessage) {
        String actualMessage = getLoginPage().getFlashMessage();
        assertTrue(actualMessage.contains(expectedMessage),
                "Expected error message containing '" + expectedMessage + "'");
    }

    @Then("the login should be {string}")
    public void theLoginShouldBe(String expectedResult) {
        if (expectedResult.equalsIgnoreCase("success")) {
            assertFalse(getLoginPage().isOnLoginPage());
        } else {
            assertTrue(getLoginPage().isOnLoginPage());
        }
    }
}