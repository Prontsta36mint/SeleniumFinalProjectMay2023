package Oraangehrm;




import EnhancedFramework.Setup;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;



public class LogoutTest extends Setup {
    public class LogoutTest extends CommonAPI {
        Logger log = LogManager.getLogger(LogoutTest.class.getName());

        String validUsername = "standard_user";
        String validPassword = "secret_sauce";

        @Test
        public void logout() throws InterruptedException {
            LoginPage loginPage = new LoginPage(getDriver());
            HomePage homePage = new HomePage(getDriver());
            String expectedTitle = "Swag Labs";
            String actualTitle = getCurrentTitle();
            Assert.assertEquals(expectedTitle, actualTitle);
            //enter  username, password, and click on login button
            type("#user-name","standard_user");
            log.info("enter username success");
            type("#password","secret_sauce");
            log.info("enter password success");
            clickOn("#login-button");
            log.info("click on login button Success");
            loginPage.enterUsername(validUsername);
            loginPage.enterPassword(validPassword);
            loginPage.clickOnLoginBtn();

            //check user is logged in
            String expectedHomePageHeader = "Products";
            String actualHomePageHeader = getElementText("//span[contains(text(),'Products')]");
            String actualHomePageHeader = homePage.getHeaderText();
            Assert.assertEquals(expectedHomePageHeader, actualHomePageHeader);
            log.info("user logged in success");

            //click on hamburger menu
            clickOn("#react-burger-menu-btn");
            log.info("click on hamburger menu success");
            homePage.clickOnHamburgerMenu();
            Thread.sleep(1000);

            //hover hover & click on logout link
            hoverOver("#logout_sidebar_link");
            log.info("click on logout link success");
            homePage.hoverOverOnAndClickLogoutLink();

            //check user is landed to the login page
            boolean loginPageHeaderIsDisplayed = isVisible("//div[contains(text(),'Swag Labs')]");
            Assert.assertTrue(loginPageHeaderIsDisplayed);

            Assert.assertTrue(loginPage.checkPresenceOfLoginPageHeader());
            log.info("login page header is displayed");

            String expectedLoginPageHeaderText = "Swag Labs";
            String actualLoginPageHeaderText = getElementText("//div[contains(text(),'Swag Labs')]");
            String actualLoginPageHeaderText = loginPage.getLoginPageHeaderText();
            Assert.assertEquals(expectedLoginPageHeaderText, actualLoginPageHeaderText);
            log.info("login page header text validation success");
        }


