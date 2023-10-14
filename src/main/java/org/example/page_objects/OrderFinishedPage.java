package org.example.page_objects;

import org.example.abstract_components.AbstractComponents;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrderFinishedPage extends AbstractComponents {
    WebDriver driver;

    public OrderFinishedPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }
    @FindBy(xpath = "//h1[@class='hero-primary']")
    WebElement message;

    By messageBy = By.xpath("//h1[@class='hero-primary']");

    public boolean checkSuccessMessage(String expectedMessage){
        waitForElementToAppear(messageBy);
        String successMessage = message.getText();
        return successMessage.equalsIgnoreCase(expectedMessage);
    }
}
