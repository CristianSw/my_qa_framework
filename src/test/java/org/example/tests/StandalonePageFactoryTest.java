package org.example.tests;

import org.example.page_objects.*;
import org.example.test_components.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

public class StandalonePageFactoryTest extends BaseTest {

    @Test
    public void submitOrder() throws IOException {
        String login = "cristian.dolinta@example.com";
        String passwd = "TestPasswd1234";
        String productName = "zara coat 3";
        String expectedMessage = "Thankyou for the order.";

        /*Enter user credentials*/
        ProductsPage productsPage = loginPage.loginToApp(login, passwd);
        WebElement product = productsPage.getProductByName(productName);
        productsPage.addProductToCart(product);

        CartPage cartPage = productsPage.goToCartPage();
        boolean isPresent = cartPage.isElementPresent(productName);
        Assert.assertTrue(isPresent);
        CheckoutPage checkoutPage = cartPage.clickOnCheckoutBtn();

        checkoutPage.selectCountry("mol");
        OrderFinishedPage orderFinishedPage = checkoutPage.placeOrder();

        boolean isSameText = orderFinishedPage.checkSuccessMessage(expectedMessage);
        Assert.assertTrue(isSameText);
    }


    @Test(dependsOnMethods = {"submitOrder"})
    public void validateOrderHistoryTest(){
        String login = "cristian.dolinta@example.com";
        String passwd = "TestPasswd1234";
        String productName = "zara coat 3";
        ProductsPage productsPage = loginPage.loginToApp(login, passwd);
        OrderPage orderPage = productsPage.goToOrderPage();
        boolean isOrderPresent = orderPage.verifyOrderIsDisplayed(productName);
        Assert.assertTrue(isOrderPresent);
    }
}
