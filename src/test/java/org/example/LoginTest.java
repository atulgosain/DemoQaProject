package org.example;

import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import utils.CSVReaderUtil;

import java.util.List;
import java.util.stream.Stream;


/**
 * Unit test for simple App.
 */
public class LoginTest
{
    static WebDriver driver;
static String testDataFilePath = "src/test/resources/credentialsTestData.csv";

public static Stream<String[]> loginDataProvider(){
    List<String[]> data = CSVReaderUtil.getTestData(testDataFilePath);
    return data.stream();

}

@BeforeAll
static void setup(){
    System.out.println("Initializing WebDriver...");

    ChromeOptions options = new ChromeOptions();

    // Disable Chrome password manager pop-ups
    options.addArguments("--disable-save-password-bubble");
    options.addArguments("--disable-popup-blocking");

    driver = new ChromeDriver();
    driver.manage().window().maximize();
    driver.get("https://www.saucedemo.com/");
}
@Test
    public void dummyTest() {
        System.out.println("Dummy test to ensure JUnit 5 is detected by Maven.");
    }

@ParameterizedTest
 @MethodSource("loginDataProvider")
   void testLogin(String userName, String password, String expectedResult){

    //   driver = new ChromeDriver();
    //    driver.manage().window().maximize();
     //   driver.get("https://www.saucedemo.com/");


     WebElement userNameField = driver.findElement(By.id("user-name"));
     WebElement passwordField = driver.findElement(By.id("password"));
     WebElement loginButton = driver.findElement(By.id("login-button"));

   //  WebElement errorMsg = driver.findElement(By.cssSelector(".error-message-container"));


     //Login with Test Data and validate the response
    userNameField.clear();
    userNameField.sendKeys(userName);
    passwordField.clear();
     passwordField.sendKeys(password);
     loginButton.click();

     boolean loginSuccess = driver.getCurrentUrl().contains("inventory.html");

     if (expectedResult.equals("Success")) {
         assertTrue(loginSuccess, "Login should succeed for: " + userName);
         WebElement burgerMenuBtn = driver.findElement(By.id("react-burger-menu-btn"));
         burgerMenuBtn.click();
         WebElement logoutMenuLink = driver.findElement(By.id("logout_sidebar_link"))   ;
         if( logoutMenuLink.isDisplayed()){
          logoutMenuLink.click();
         }


     } else {
        WebElement errorLoginMsg = driver.findElement(By.cssSelector(".error-message-container"));
         System.out.println("Error Message"+errorLoginMsg.getText());
         assertTrue(errorLoginMsg.isDisplayed(), "Error message should be displayed for: " + userName);
         System.out.println(errorLoginMsg.getText());

     }


 }

    @AfterAll
    static void closeBrowser() {
        System.out.println("Attempting to close the browser...");
        if (driver != null) {
            driver.quit();
            System.out.println("Browser closed successfully.");
        } else {
            System.out.println("Driver is null, cannot close browser.");
        }
    }



}
