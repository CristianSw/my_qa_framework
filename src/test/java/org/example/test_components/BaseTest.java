package org.example.test_components;

import org.apache.commons.io.FileUtils;
import org.example.page_objects.LoginPage;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class BaseTest {
    public WebDriver driver;
    public LoginPage loginPage;

    public WebDriver initTest() throws IOException {
        //Properties class
        Properties properties = new Properties();
        FileInputStream fileInputStream = new FileInputStream(System.getProperty("user.dir") + "//src//main//java//org//example//ressources//GlobalData.properties");
        properties.load(fileInputStream);
        String browserWanted = System.getProperty("browser") != null ? System.getProperty("browser") : properties.getProperty("browser");
        ChromeOptions chromeOptions = new ChromeOptions();
        FirefoxOptions firefoxOptions = new FirefoxOptions();

        if (browserWanted.equalsIgnoreCase("firefox")) {
            // Add the headless option for Firefox
            firefoxOptions.addArguments("--headless");
            driver = new FirefoxDriver(firefoxOptions);
        } else if (browserWanted.equalsIgnoreCase("chrome")) {
//            System.setProperty("webdriver.chrome.driver","//usr//bin//chromedriver");
            // Add the headless option for Chrome
            chromeOptions.addArguments("--headless");
            driver = new ChromeDriver(chromeOptions);
        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        return driver;
    }

    public String getScreenshotOfAllPage(String testCaseName, WebDriver driver) throws IOException {
        TakesScreenshot screenshot = (TakesScreenshot) driver;
        File screenshotFile = screenshot.getScreenshotAs(OutputType.FILE);
        File dest = new File(System.getProperty("user.dir") + "//reports//" + testCaseName + ".png");
        FileUtils.copyFile(screenshotFile, dest);
        return System.getProperty("user.dir") + "//reports//" + testCaseName + ".png";
    }

    public String getScreenshotOfElement(WebElement element, String testCaseName) throws IOException {
        File screenshotFile = element.getScreenshotAs(OutputType.FILE);
        File dest = new File(System.getProperty("user.dir") + "//reports//" + testCaseName + ".png");
        FileUtils.copyFile(screenshotFile, dest);
        return System.getProperty("user.dir") + "//reports//" + testCaseName + ".png";
    }

    @BeforeMethod(alwaysRun = true)
    public LoginPage launchApplication() throws IOException {
        driver = initTest();
        loginPage = new LoginPage(driver);
        loginPage.goToPage();
        return loginPage;
    }

    @AfterMethod(alwaysRun = true)
    public void closeDriver() {
        driver.close();

    }
}




