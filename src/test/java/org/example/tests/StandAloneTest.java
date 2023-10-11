package org.example.tests;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;

public class StandAloneTest {
    /*
     * Login Credentials:
     * login: cristian.dolinta@example.com
     * passwd: TestPasswd1234
     * */
    public static void main(String[] args) {
        WebDriver driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        Actions action = new Actions(driver);

        String login = "cristian.dolinta@example.com";
        String passwd = "TestPasswd1234";
        String productName = "zara coat 3";
        String expectedMessage = "Thankyou for the order.";


        driver.get("https://rahulshettyacademy.com/client");
        /*Enter user credentials*/
        driver.findElement(By.id("userEmail")).sendKeys(login);
        driver.findElement(By.id("userPassword")).sendKeys(passwd);
        /*login btn*/
        driver.findElement(By.id("login")).click();

        /*retrive all products web elements*/
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
        List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
        WebElement product = products.stream()
                .filter(p -> p.findElement(By.cssSelector("b")).getText().equalsIgnoreCase(productName))
                .findFirst()
                .orElse(null);
        assert product != null;
        product.findElement(By.cssSelector(".card-body button:last-of-type")).click();
        /*wait animation and mesage to be displayed*/
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("ng-animating")));



        /*click on cart */
        driver.findElement(By.cssSelector("[routerlink*='cart']")).click();

        /*retreive all elements from cart and check if they are valid*/
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".cartSection h3")));
        List<WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection h3"));
        boolean isPresent = cartProducts.stream().anyMatch(p -> p.getText().equalsIgnoreCase(productName));
        Assert.assertTrue(isPresent);

        driver.findElement(By.cssSelector(".totalRow button")).click();


        /*go to payment page and wait until element s are displayed*/
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Select Country']")));
        WebElement selectCountry = driver.findElement(By.xpath("//input[@placeholder='Select Country']"));
        action.sendKeys(selectCountry,"mol").build().perform();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
        driver.findElement(By.xpath("(//button[contains(@class,'ta-item')])[1]")).click();

        driver.findElement(By.xpath("//a[contains(@class,'action__submit')]")).click();


        /*go to checkout page and wait until page is loaded, check message*/
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[@class='hero-primary']")));
        String message = driver.findElement(By.xpath("//h1[@class='hero-primary']")).getText();
        boolean isSameText = message.equalsIgnoreCase(expectedMessage);
        Assert.assertTrue(isSameText);
    }

}
