package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.LoginPage;
import pageObjects.HomePage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC002_LoginTest extends BaseClass {


    @Test(groups = {"Sanity", "Master"})
    public void verify_account_login(){

        try {

            logger.info("*** Started LoginTest! ***");

            //HomePage
            HomePage hp = new HomePage(driver);
            hp.clickMyAccountLink();
            hp.clickLoginLink();
            logger.info("Clicked on Login link");

            //LoginPage
            LoginPage loginPage = new LoginPage(driver);
            logger.info("Inputted user email");
            loginPage.setEmail(prop.getProperty("email"));
            logger.info("Inputted user password");
            loginPage.setPassword(prop.getProperty("password"));
            logger.info("Clicked on Login button");
            loginPage.clickLoginButton();

            //MyAccountPage
            MyAccountPage myAccountPage = new MyAccountPage(driver);
            boolean targetPage = myAccountPage.isMyAccountPageHeadingExists();
            //Assert.assertTrue(targetPage);
            Assert.assertEquals(targetPage, true, "Login failed!");
        }
        catch (Exception e)
        {
            Assert.fail();
        }
        logger.info("*** Completed TC002_LoginTest execution! *** ");


    }



}
