package com.revature.pw;

import com.revature.pw.BaseTest;
import org.junit.jupiter.api.*;

import java.util.regex.Pattern;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class LoginTest extends BaseTest {

    @Test
    void shouldLoginSuccessfully() {
        navigateTo("/login");

        page.locator("#username").fill("tomsmith");
        page.locator("#password").fill("SuperSecretPassword!");
        page.locator(".radius").click();

        assertThat(page).hasURL(Pattern.compile(".*secure"));
        assertThat(page.locator(".flash")).containsText("secure area");
    }

    @Test
    void shouldShowErrorForInvalidCredentials() {
        navigateTo("/login");

        page.locator("#username").fill("invalid");
        page.locator("#password").fill("wrong");
        page.locator(".radius").click();

        assertThat(page.locator(".flash")).isVisible();
        assertThat(page.locator(".flash")).containsText("invalid");
    }
}