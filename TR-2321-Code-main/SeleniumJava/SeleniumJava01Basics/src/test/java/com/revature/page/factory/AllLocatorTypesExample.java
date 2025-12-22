package com.revature.page.factory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

public class AllLocatorTypesExample {
    @FindBy(id = "elementId")
    private WebElement byId;

    @FindBy(name = "elementName")
    private WebElement byName;

    @FindBy(className = "elementClass")
    private WebElement byClassName;

    @FindBy(css = "#id .class tag")
    private WebElement byCssSelector;

    @FindBy(xpath = "//div[@id='test']")
    private WebElement byXpath;

    @FindBy(linkText = "Click Here")
    private WebElement byLinkText;

    @FindBy(partialLinkText = "Click")
    private WebElement byPartialLinkText;

    @FindBy(tagName = "input")
    private java.util.List<WebElement> byTagName;

    // Using @FindBys (AND condition)
    @FindBys({
            @FindBy(className = "form"),
            @FindBy(tagName = "input")
    })
    private WebElement byChainedCondition;

    // Using @FindAll (OR condition)
    @FindAll({
            @FindBy(id = "username"),
            @FindBy(id = "password")
    })
    private java.util.List<WebElement> byOrCondition;

    public AllLocatorTypesExample(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

}
