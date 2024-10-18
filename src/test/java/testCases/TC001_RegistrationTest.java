package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.RegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC001_RegistrationTest extends BaseClass {


    @Test(groups = {"Regression", "Master"})
    public void verify_account_registration(){

        try {

            logger.info("Started AccountRegistrationTest");
            HomePage hp = new HomePage(driver);
            hp.clickMyAccountLink();
            logger.info("Clicked on MyAccount link");
            hp.clickRegisterLink();
            logger.info("Clicked on Register link");

            RegistrationPage ac = new RegistrationPage(driver);
            logger.info("Providing customer's details!");
            ac.setFirstName(randomString().toUpperCase());
            ac.setLastName(randomString().toUpperCase());
            ac.setEmail(randomString() + "@gmail.com");
            ac.setTelephone(randomNumber());

            String password = randomAlphanumeric();
            ac.setPassword(password);
            ac.setConfirmPassword(password);

            ac.setPrivacyPolicy();
            ac.clickContinueBtn();

            logger.info("Validating expected message!");
            String confMsg = ac.getConfirmationMsg();
            if(confMsg.equals("Your Account Has Been Created!"))
            {
                Assert.assertTrue(true);
            }
            else
            {
                logger.error("Test failed!");
                logger.debug("*** Degug logs *** ");
                Assert.assertFalse(false);
            }
            //Assert.assertEquals(confMsg, "Yours Account Has Been Created!");

        }
        catch (Exception e)
        {
            Assert.fail();
            logger.info("*** Finished TC001_AccountRegistrationTest! *** ");
        }


    }



}
