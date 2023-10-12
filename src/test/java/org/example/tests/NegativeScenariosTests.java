package org.example.tests;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.example.page_objects.CartPage;
import org.example.page_objects.ProductsPage;
import org.example.test_components.BaseTest;
import org.example.test_components.Retry;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;


public class NegativeScenariosTests extends BaseTest {

    @Test(groups = {"negative"},retryAnalyzer = Retry.class)
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
