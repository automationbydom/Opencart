package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RegistrationPage extends BasePage{

    //Constructor
    public RegistrationPage(WebDriver driver){
        super(driver);
    }

    //Locators
    @FindBy(id = "input-firstname") WebElement txt_firstName;
    @FindBy(id = "input-lastname") WebElement txt_lastName;
    @FindBy(id = "input-email") WebElement txt_email;
    @FindBy(id = "input-telephone") WebElement txt_telephone;
    @FindBy(id = "input-password") WebElement txt_pwd;
    @FindBy(id = "input-confirm") WebElement txt_confirmPwd;
    @FindBy(xpath = "//input[@value='Continue']") WebElement btn_continue;
    @FindBy(xpath = "//input[@name='agree']") WebElement checkBox;
    @FindBy(xpath = "//h1[normalize-space()='Your Account Has Been Created!']")
    WebElement msgConfirmation;



    //Action Methods


    public void setFirstName(String firstName) {
        txt_firstName.clear();
        txt_firstName.sendKeys(firstName);
    }

    public void setLastName(String lastName) {
        txt_lastName.clear();
        txt_lastName.sendKeys(lastName);
    }

    public void setEmail(String email) {
        txt_email.clear();
        txt_email.sendKeys(email);
    }

    public void setTelephone(String telephone) {
        txt_telephone.clear();
        txt_telephone.sendKeys(telephone);
    }

    public void setPassword(String pwd) {
        txt_pwd.clear();
        txt_pwd.sendKeys(pwd);
    }

    public void setConfirmPassword(String confirmPwd) {
        txt_confirmPwd.clear();
        txt_confirmPwd.sendKeys(confirmPwd);
    }

    public void setPrivacyPolicy(){
        checkBox.click();
    }

    public void clickContinueBtn() {
        btn_continue.click();
    }

    public String getConfirmationMsg(){
        try {
            return (msgConfirmation.getText());
        }catch (Exception e){
            return (e.getMessage());
        }

    }
}
