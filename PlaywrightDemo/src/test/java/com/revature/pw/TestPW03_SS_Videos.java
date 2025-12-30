package com.revature.pw;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.ScreenshotAnimations;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.nio.file.Paths;

@DisplayName("Playwright Screenshot and Video Demo")
public class TestPW03_SS_Videos
{
    @DisplayName("Screenshot Demo")
    @Test
    public void testScreenshots()
    {
        try(Playwright pw = Playwright.create()) {
            Browser browser = pw.chromium().launch();
            Page page = browser.newPage();

            page.navigate("https://playwright.dev/");

            page.screenshot(new Page.ScreenshotOptions()
                    .setPath(Paths.get("target/screenshots/basic.png"))
                    .setFullPage(true)
            );


        }
    }

    @DisplayName("Screenshot Locator Demo")
    @Test
    public void testScreenshotsLocators()
    {
        try(Playwright pw = Playwright.create()) {
            Browser browser = pw.chromium().launch();
            Page page = browser.newPage();

            page.navigate("https://playwright.dev/");

            // Screenshot of specific element
            page.locator("header").screenshot(new Locator.ScreenshotOptions()
                    .setPath(Paths.get("screenshots/header.png"))
            );

        }
    }


    @DisplayName("PW Video Recording")
    @Test
    public void testVideo()
    {
        try(Playwright pw = Playwright.create()) {
            Browser browser = pw.chromium().launch(
                    new BrowserType.LaunchOptions().setHeadless(true)
            );
            BrowserContext context = browser.newContext(new Browser.NewContextOptions()
                    .setRecordVideoDir(Paths.get("target/videos/"))
            );

            Page page = context.newPage();
            System.out.println("Recording Started");


            page.navigate("https://the-internet.herokuapp.com/");

            page.locator("text=Form Authentication").click();

            // Video is saved when context closes
            context.close();




        }
    }



}
