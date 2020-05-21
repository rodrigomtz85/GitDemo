package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage {

    public WebDriver driver;

    public LoginPage(WebDriver driver){
        this.driver = driver;
    }

    By email = By.cssSelector("input[id='user_email']");
    By password = By.cssSelector("input[id='user_password']");
    By submit = By.name("commit");
    By forgotPass = By.cssSelector("a[href*='password/new']");

    public WebElement getEmail(){
        return this.driver.findElement(email);
    }

    public WebElement getPassword(){
        return this.driver.findElement(password);
    }

    public WebElement getSubmit(){
        return this.driver.findElement(submit);
    }

    public ForgotPassword setForgotPass(){

        this.driver.findElement(forgotPass).click();
        ForgotPassword fp = new ForgotPassword(driver);
        return fp;

    }
}
