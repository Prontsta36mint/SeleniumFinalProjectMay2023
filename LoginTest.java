package Oraangehrm;

import EnhancedFramework.Setup;
import org.bouncycastle.i18n.LocalizedException;
import org.openqa.selenium.By;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static java.sql.DriverManager.getDriver;


public class LoginTest extends Setup {

    private AssertJUnit Assert;

    private LocalizedException loginPage;
    private Log4JLogger log;

    @Test
    public void validCredential() {
        String expectedTitle = "Orange hrm";
        String actualTitle = driver.getTitle();
        Assert.assertEquals(expectedTitle, actualTitle);
        /* enter username,password,and click on login button */
        driver.findElement(By.id("user-name")).sendKeys("Admin");
        System.out.println("enter username success");
        driver.findElement(By.id("password")).sendKeys("admin123");
        System.out.println("enter password success");
        driver.findElement(By.id("login-button")).click();
        System.out.println("click on login button Success");

        // check user is logged in
        String expectedHomePageHeader = "Products";
        String actualHomePageHeader = driver.findElement(By.xpath("//span[contains(text(),'products')]")).getText();
        Assert.assertEquals(expectedHomePageHeader, actualHomePageHeader);
        System.out.println("user logged in success");

    }

    @Test
    public void invalidUsername(String s, String admin) {
        String expectedTitle = "Orange hrm";
        String actualTitle = driver.getTitle();
        Assert.assertEquals(expectedTitle, actualTitle);
        //enter username, password, and click on login button
        driver.findElement(By.id("user-name")).sendKeys("invalid_username");
        System.out.println("enter username success");
        driver.findElement(By.id("password")).sendKeys("password");
        System.out.println("enter password success");
        driver.findElement(By.id("login-button")).click();
        System.out.println("click on login button Success");

        //validate the error message
        String expectedError = "Epic sadface: Username and password do not match any user in the service";
        String actualError = String.valueOf(getClass());
        actualError = String.valueOf(loginPage.getErrorMessage());
        Assert.assertEquals(expectedError, actualError);
        try {
            Assert.wait(Long.parseLong("validate error success"));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


    }

    @Test
    //@Test
    public <HomePage> void missingPassword() {
        LoginPage loginPage = new LoginPage(getDriver());
        HomePage homePage = new HomePage(getDriver());
        String expectedTitle = "Orange hrm";
        String actualTitle = getCurrentTitle();
        Assert.assertEquals(expectedTitle, actualTitle);
        //enter  username, password, and click on login button
        invalidUsername("#user-name", "Admin");
        log.info("enter username success");
        invalidUsername("#password", "");
        log.info("enter password success");
        clickOn("#login-button");
        log.info("click on login button Success");
        loginPage.enterUsername(validUsername);
        loginPage.enterPassword("");
        loginPage.clickOnLoginBtn();
}

    //validate the error message
    String expectedError = "Epic sadface: Password is required";
    /**
     *
     */
    Class<?> actualError;

    {
        actualError = getElementText("//body/div[@id='root']/div[1]/div[2]/div[1]/div[1]/div[1]/form[1]/div[3]/h3[1]");
    }

    String actualError= loginPage.getErrorMessage();
        Assert.assertEquals(expectedError, actualError);
        log.info("validate error success");
}




