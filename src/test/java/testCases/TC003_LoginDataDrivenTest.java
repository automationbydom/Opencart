package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

public class TC003_LoginDataDrivenTest extends BaseClass {

    // getting data provider from different class
    @Test(dataProvider = "LoginData", dataProviderClass = DataProviders.class, groups = "DataDriven")
    public void verify_loginDataDrivenTest(String email, String pwd, String exp) throws InterruptedException {
        logger.info("*** Starting TC003_LoginDataDrivenTest ***");


        try {

            //Home Page
            HomePage hp = new HomePage(driver);
            hp.clickMyAccountLink();
            hp.clickLoginLink();

            //Login Page
            LoginPage lp = new LoginPage(driver);
            lp.setEmail(email);
            lp.setPassword(pwd);
            lp.clickLoginButton();

            //My Account Page
            MyAccountPage myAcc = new MyAccountPage(driver);
            boolean targetPage = myAcc.isMyAccountPageHeadingExists();

            if (exp.equalsIgnoreCase("Valid")) {
                if (targetPage) {
                    myAcc.clickLogoutLink();
                    Assert.assertTrue(true);

                } else {
                    Assert.assertFalse(false);
                }
            }

            if (exp.equalsIgnoreCase("Invalid")) {
                if (targetPage) {
                    myAcc.clickLogoutLink();
                    Assert.fail();
                } else {
                    Assert.assertTrue(true);
                }
            }
        }catch(Exception e)
        {
            Assert.fail();
        }
        Thread.sleep(3000);
        logger.info("*** Finished LoginDataDrivenTest ***");



    }
}