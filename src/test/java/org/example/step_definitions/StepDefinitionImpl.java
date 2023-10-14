package org.example.step_definitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.page_objects.*;
import org.example.test_components.BaseTest;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.io.IOException;

public class StepDefinitionImpl extends BaseTest {

    public LoginPage loginPage;
    public ProductsPage productsPage;
    public CartPage cartPage;
    public CheckoutPage checkoutPage;
    public OrderFinishedPage orderFinishedPage;

    @Given("I landed on Ecommerce page")
    public void I_landed_on_Ecommerce_page() throws IOException {
        loginPage = launchApplication();
    }

    @Given("^Login with username (.+) and password (.+)$")
    public void Login_with_username_and_password(String username, String passwd) {
        productsPage = loginPage.loginToApp(username, passwd);
    }

    //Add a product <productName> to cart
    @When("^Add a product (.+) to cart$")
    public void Add_a_product_to_cart(String productName) {
        WebElement product = productsPage.getProductByName(productName);
        productsPage.addProductToCart(product);
    }

    @And("^Checkout (.+)$")
    public void Checkout_productName(String productName) {
        cartPage = productsPage.goToCartPage();
        boolean isPresent = cartPage.isElementPresent(productName);
        Assert.assertTrue(isPresent);
    }

    @And("Submit the order")
    public void Submit_order() {
        checkoutPage = cartPage.clickOnCheckoutBtn();
        checkoutPage.selectCountry("mol");
    }

    @Then("^(.+) is displayed on confirmation page$")
    public void Message_displayed_on_confirmation_page(String expectedMessage) {
        orderFinishedPage = checkoutPage.placeOrder();
        boolean isSameText = orderFinishedPage.checkSuccessMessage(expectedMessage);
        Assert.assertTrue(isSameText);
        driver.close();
    }

   @Then("^(.+) is displayed$")
    public void Login_error_message_displayed(String expectedErrorMessage){
       String actualErrorMessage = loginPage.getErrorMessage();
       Assert.assertEquals(actualErrorMessage ,expectedErrorMessage);
       driver.close();
   }


}
