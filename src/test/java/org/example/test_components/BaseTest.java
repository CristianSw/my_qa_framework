package org.example.test_components;

import org.example.page_objects.LoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class BaseTest {
    public WebDriver driver;
    public  LoginPage loginPage;

    public WebDriver initTest() throws IOException {
        //Properties class
        Properties properties = new Properties();
        FileInputStream fileInputStream = new FileInputStream(System.getProperty("user.dir") + "//src//main//java//org//example//ressources//GlobalData.properties");
        properties.load(fileInputStream);
        String browserWanted = properties.getProperty("browser");


        if (browserWanted.equalsIgnoreCase("firefox")) {
            driver = new FirefoxDriver();
        } else if (browserWanted.equalsIgnoreCase("chrome")) {
            driver = new ChromeDriver();

        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        return driver;
    }

    @BeforeMethod(alwaysRun = true)
    public LoginPage launchApplication() throws IOException {
        driver = initTest();
        loginPage = new LoginPage(driver);
        loginPage.goToPage();
        return loginPage;
    }

    @AfterMethod(alwaysRun = true)
    public void closeDriver(){
        driver.close();

    }
}

