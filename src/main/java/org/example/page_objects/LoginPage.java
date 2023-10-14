package org.example.page_objects;

import org.example.abstract_components.AbstractComponents;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;

public class LoginPage extends AbstractComponents {
    WebDriver driver;

    public LoginPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    //PageFactory
    @FindBy(id = "userEmail")
    WebElement getUserLogin;

    @FindBy(id = "userPassword")
    WebElement getUserPasswd;

    @FindBy(id = "login")
    WebElement loginBtn;
    @FindBy(css = "[class*='flyInOut']")
    WebElement loginError;


    public ProductsPage loginToApp(String login, String passwd){
        getUserLogin.sendKeys(login);
        getUserPasswd.sendKeys(passwd);
        loginBtn.click();
        ProductsPage productsPage = new ProductsPage(driver);
        return productsPage;
    }
    public void goToPage() throws IOException {
        driver.get("https://rahulshettyacademy.com/client");
    }

    public String getErrorMessage(){
        waitForWebElementToAppear(loginError);
        return loginError.getText();
    }

}
