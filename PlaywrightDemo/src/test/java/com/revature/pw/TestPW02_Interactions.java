package com.revature.pw;


import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.regex.Pattern;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

@DisplayName("Playwright Element Locate and Interactions")
public class TestPW02_Interactions extends BaseTest
{
    @DisplayName("Playwright Interactions")
    @Test
    public void demoWaits()
    {
        navigateTo("/dynamic_loading/1");


        page.locator("#start button").click();

        String result = page.locator("#finish").textContent();

        System.out.println("Result: " + result);
    }

    @DisplayName("Playwright Locators")
    @Test
    public void demoLocators()
    {
        navigateTo("/login");

        Locator byID = page.locator("#username");

        Locator byText = page.locator("text=login");

        Locator byRole = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Login"));

        Locator byPlaceHolder = page.getByPlaceholder("User name");

        Locator byLabel = page.getByLabel("Username");

    }

    @DisplayName("Element Interactions")
    @Test
    public void demoInteractions()
    {
        navigateTo("/login");

        page.locator("#username").fill("tomsmith");
        page.locator("#password").fill("SuperSecretPassword!");
        page.locator("button[type=submit]").click();

        assertThat(page).hasURL(Pattern.compile(".*secure"));
        assertThat(page.locator(".flash")).containsText("secure area");

    }


}
