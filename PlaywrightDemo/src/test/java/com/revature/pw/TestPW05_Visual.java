package com.revature.pw;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.LoadState;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.nio.file.Paths;

@DisplayName("Visual Testing Demo")
public class TestPW05_Visual
{
    @DisplayName("Visual Testing")
    @Test
    public void testVisuals()
    {
        try(Playwright pw = Playwright.create())
        {
            Browser browser = pw.chromium().launch();
            Page page = browser.newPage();

            page.navigate("https://the-internet.herokuapp.com/login");

            //Wait for page to load
            page.waitForLoadState(LoadState.NETWORKIDLE);

//            Path baselinePath = Paths.get("target/visual-tests/baseline/login.png");
//            page.screenshot(
//                    new Page.ScreenshotOptions()
//                            .setPath(baselinePath)
//            );

            Path currentPath = Paths.get("target/visual-tests/current/login.png");

            page.screenshot(new Page.ScreenshotOptions()
                    .setPath(currentPath)
            );



        }
    }
}
