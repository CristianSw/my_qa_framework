package org.example.page_objects;

import org.example.abstract_components.AbstractComponents;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutPage extends AbstractComponents {

    WebDriver driver;
    Actions action;
    public CheckoutPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        action = new Actions(driver);
        PageFactory.initElements(driver,this);
    }


    @FindBy(xpath = "//input[@placeholder='Select Country']")
    WebElement selectCountry;
    @FindBy(xpath = "(//button[contains(@class,'ta-item')])[1]")
    WebElement country;
    @FindBy(xpath = "//a[contains(@class,'action__submit')]")
    WebElement placeOrderBtn;
    By selectCountryBy = By.xpath("//input[@placeholder='Select Country']");
    By selectCountryLocatorBY = By.cssSelector(".ta-results");


    public void selectCountry(String keys){
        waitForElementToAppear(selectCountryBy);
        action.sendKeys(selectCountry,"mol").build().perform();
        waitForElementToAppear(selectCountryLocatorBY);
        country.click();

    }

    public OrderFinishedPage placeOrder(){
        placeOrderBtn.click();
        return new OrderFinishedPage(driver);
    }



}
