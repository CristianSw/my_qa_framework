package org.example.page_objects;

import org.example.abstract_components.AbstractComponents;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CartPage extends AbstractComponents {
    WebDriver driver;

    public CartPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(css = "[routerlink*='cart']")
    WebElement cart;
    @FindBy(css = ".cartSection h3")
    List<WebElement> cartProducts;

    @FindBy(css = ".totalRow button")
    WebElement checkoutBtn;


    public List<WebElement> getCartProducts(){
        return cartProducts;
    }

    public boolean isElementPresent(String productName){
        return cartProducts.stream().anyMatch(p -> p.getText().equalsIgnoreCase(productName));
    }

    public CheckoutPage clickOnCheckoutBtn(){
        checkoutBtn.click();
        CheckoutPage checkoutPage = new CheckoutPage(driver);
        return checkoutPage;
    }
}
