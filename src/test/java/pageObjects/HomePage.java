package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage{

    //Constructor
    public HomePage (WebDriver driver){
        super(driver);
    }
    //Locators
    @FindBy(linkText = "Register")
    WebElement link_register;
    @FindBy(xpath = "//a[@title='My Account']")
    WebElement my_account;
    @FindBy(linkText = "Login")
    WebElement link_login;

    //Action Methods

    public void clickMyAccountLink(){
        my_account.click();

    }
    public void clickRegisterLink(){
        link_register.click();
    }

    public void clickLoginLink(){
        link_login.click();
    }





}
