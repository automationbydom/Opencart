package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage{

    //Constructor
    public LoginPage(WebDriver driver)
    {
        super(driver);
    }
    // Login Page Locators
    @FindBy(xpath = "//a[normalize-space()='Login']")
    WebElement login_link;
    @FindBy(xpath = "//input[@id='input-email']")
    WebElement txt_email;
    @FindBy(xpath = "//input[@id='input-password']")
    WebElement txt_password;
    @FindBy(xpath = "//input[@value='Login']")
    WebElement btn_login;



    //Action Methods
    public void setEmail(String email)
    {
        txt_email.clear();
        txt_email.sendKeys(email);
    }

    public void setPassword(String password)
    {
        txt_password.clear();
        txt_password.sendKeys(password);
    }

    public void clickLoginButton()
    {
        btn_login.click();
    }



}
