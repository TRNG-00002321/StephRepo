package com.revature.cucumber.hooks;

import io.cucumber.java.*;
import com.revature.cucumber.context.TestContext;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class Hooks {

    private TestContext context;

    public Hooks() {
        context = TestContext.getInstance();
    }

    @Before
    public void setUp(Scenario scenario) {
        System.out.println("Starting: " + scenario.getName());

        boolean headless = scenario.getSourceTagNames().contains("@headless");
        context.initializeDriver(headless);
        context.reset();
    }

    @After
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed() && context.getDriver() != null) {
            byte[] screenshot = ((TakesScreenshot) context.getDriver())
                    .getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", "Failure Screenshot");
        }

        context.quitDriver();
        System.out.println("Finished: " + scenario.getName() +
                " - " + scenario.getStatus());
    }
}