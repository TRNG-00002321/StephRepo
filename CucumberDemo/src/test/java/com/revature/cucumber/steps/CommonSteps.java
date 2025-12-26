package com.revature.cucumber.steps;

import io.cucumber.java.en.*;
import static org.junit.jupiter.api.Assertions.*;

import com.revature.cucumber.context.TestContext;
import com.revature.cucumber.pages.LoginPage;
import com.revature.cucumber.pages.SecurePage;

public class CommonSteps {

    private TestContext context;

    public CommonSteps() {
        context = TestContext.getInstance();
    }

    @Given("the user is on the {string} page")
    public void navigateToPage(String pageName) {
        context.getDriver().get(
                "https://the-internet.herokuapp.com/" + pageName);
    }

    @Then("the page title should be {string}")
    public void verifyPageTitle(String expectedTitle) {
        assertEquals(expectedTitle, context.getDriver().getTitle());
    }
}