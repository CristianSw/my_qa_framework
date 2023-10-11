package org.example.page_objects;

import org.example.abstract_components.AbstractComponents;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class OrderPage extends AbstractComponents {
    WebDriver driver;
    public OrderPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(css = "tr td:nth-child(3)")
    List<WebElement> orders;

    public boolean verifyOrderIsDisplayed(String productName){
        return orders.stream().anyMatch( o -> o.getText().equalsIgnoreCase(productName));
    }

   }
