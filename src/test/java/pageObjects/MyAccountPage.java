package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccountPage extends BasePage{
    //Constructor
    public MyAccountPage(WebDriver driver)
    {
        super(driver);
    }
    //Page Locators
    @FindBy(xpath = "//h2[text() = 'My Account']")
    WebElement myAccountPageHeading;

    @FindBy(linkText = "Logout")
    WebElement logoutLink;

    //Action Methods
    public boolean isMyAccountPageHeadingExists()
    {
        try
        {
            return (myAccountPageHeading.isDisplayed());
        }
        catch (Exception e)
        {
            return false;
        }
    }

    public void clickLogoutLink()
    {
        logoutLink.click();
    }

}
