package org.example.tests;

import org.example.data.DataReader;
import org.example.page_objects.*;
import org.example.test_components.BaseTest;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class StandalonePageFactoryTest extends BaseTest {

    @Test(dataProvider = "getData", groups = "purchase")
    public void submitOrder(HashMap<String, String> input) throws IOException {
        /*Enter user credentials*/
        ProductsPage productsPage = loginPage.loginToApp(input.get("login"), input.get("passwd"));
        WebElement product = productsPage.getProductByName(input.get("productName"));
        productsPage.addProductToCart(product);

        CartPage cartPage = productsPage.goToCartPage();
        boolean isPresent = cartPage.isElementPresent(input.get("productName"));
        Assert.assertTrue(isPresent);
        CheckoutPage checkoutPage = cartPage.clickOnCheckoutBtn();

        checkoutPage.selectCountry("mol");
        OrderFinishedPage orderFinishedPage = checkoutPage.placeOrder();

        boolean isSameText = orderFinishedPage.checkSuccessMessage(input.get("expectedMessage"));
        Assert.assertTrue(isSameText);
    }


    @Test(dependsOnMethods = {"submitOrder"})
    public void validateOrderHistoryTest() {
        String login = "cristian.dolinta@example.com";
        String passwd = "TestPasswd1234";
        String productName = "zara coat 3";
        ProductsPage productsPage = loginPage.loginToApp(login, passwd);
        OrderPage orderPage = productsPage.goToOrderPage();
        boolean isOrderPresent = orderPage.verifyOrderIsDisplayed(productName);
        Assert.assertTrue(isOrderPresent);
    }

    @DataProvider
    public Object[][] getData() throws IOException {
        DataReader dataReader = new DataReader();
        List<HashMap<String, String>> jsonData = dataReader.getJSONData(System.getProperty("user.dir") + "//src//test//java//org//example//data//data.json");
        return new Object[][]{{jsonData.get(0)}, {jsonData.get(1)}};
    }

//    @DataProvider
//    public Object[][] getData(){
//        String login = "cristian.dolinta@example.com";
//        String passwd = "TestPasswd1234";
//        String productName1 = "zara coat 3";
//        String productName2 = "ADIDAS ORIGINAL";
//        String expectedMessage = "Thankyou for the order.";
//
//        HashMap<String,String> map = new HashMap<>();
//        map.put("login",login);
//        map.put("passwd",passwd);
//        map.put("productName",productName1);
//        map.put("expectedMessage",expectedMessage);
//
//        HashMap<String,String> map1 = new HashMap<>();
//        map1.put("login",login);
//        map1.put("passwd",passwd);
//        map1.put("productName",productName2);
//        map1.put("expectedMessage",expectedMessage);
//        return new Object[][] {{map},{map1}};
//    }

//    @DataProvider
//    public Object[][] getData(){
//        String login = "cristian.dolinta@example.com";
//        String passwd = "TestPasswd1234";
//        String productName1 = "zara coat 3";
//        String productName2 = "ADIDAS ORIGINAL";
//        String expectedMessage = "Thankyou for the order.";
//        return new Object[][] {{login,passwd,productName1,expectedMessage},{login,passwd,productName2,expectedMessage}};
//    }
}
