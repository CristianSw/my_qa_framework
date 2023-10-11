package org.example.tests;

import org.example.page_objects.CartPage;
import org.example.page_objects.ProductsPage;
import org.example.test_components.BaseTest;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class NegativeScenariosTests extends BaseTest {

    @Test(groups = {"negative"})
    public void loginFailTest() {
        String expectedErrorMessage = "Incorrect email or password.";
        loginPage.loginToApp("somewronglogin@example.com", "somewrongpasswd");
        String actualErrorMessage = loginPage.getErrorMessage();
        Assert.assertEquals(actualErrorMessage ,expectedErrorMessage);

    }
    @Test
    public void productNotMatchValidation(){
        String wrongProductName = "zara coat 33";
        String productName = "zara coat 3";
        String login = "cristian.dolinta@example.com";
        String passwd = "TestPasswd1234";

        ProductsPage productsPage = loginPage.loginToApp(login, passwd);
        WebElement product = productsPage.getProductByName(productName);
        productsPage.addProductToCart(product);

        CartPage cartPage = productsPage.goToCartPage();
        boolean isPresent = cartPage.isElementPresent(wrongProductName);
        Assert.assertFalse(isPresent);
    }
}
