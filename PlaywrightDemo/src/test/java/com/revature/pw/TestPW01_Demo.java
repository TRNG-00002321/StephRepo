package com.revature.pw;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.regex.Pattern;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

@DisplayName("Playwright Demo")
public class TestPW01_Demo
{
    @DisplayName("Basic PW Test")
    @Test
    public void basicTest() {

        //Playwright.create() initializes the Playwright library

        try(Playwright pw = Playwright.create()) {

            //Launch the browser
            Browser browser = pw.chromium().launch(
                    new BrowserType.LaunchOptions()
                            .setHeadless(false) //default is true
                            .setSlowMo(500) //slow down the browser
            );

            //Create a new page (tab)
            Page page = browser.newPage();

            //navigate to a URL
            page.navigate("https://playwright.dev");

            //get and print title
            String title = page.title();
            System.out.println("Title: " + title);
            System.out.println("URL: " + page.url());

            //auto-waiting locator
            page.locator("text=Get Started").click();

            //auto-retrying assertion
            assertThat(page).hasURL(Pattern.compile(".*intro"));

            System.out.println("Nav to: " + page.url());

        }

    }
}
