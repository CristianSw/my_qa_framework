package org.example.page_objects;

import org.example.abstract_components.AbstractComponents;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ProductsPage extends AbstractComponents {
    WebDriver driver;

    public ProductsPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    //PageFactory
    @FindBy(css = ".mb-3")
    List<WebElement> products;

    By productsBy = By.cssSelector(".mb-3");
    By productByName = By.cssSelector("b");
    By addToCart = By.cssSelector(".card-body button:last-of-type");
    By toastMessage = By.cssSelector("#toast-container");
    By anymatingBy = By.cssSelector("ng-animating");

    public List<WebElement> getProductsList() {
        waitForElementToAppear(productsBy);
        return products;
    }

    public WebElement getProductByName(String productName) {
        return products.stream()
                .filter(p -> p.findElement(productByName).getText().equalsIgnoreCase(productName))
                .findFirst()
                .orElse(null);
    }

    public void addProductToCart(WebElement product){
        product.findElement(addToCart).click();
        waitForElementToAppear(toastMessage);
        waitForElementToDisappear(anymatingBy);
    }
}
