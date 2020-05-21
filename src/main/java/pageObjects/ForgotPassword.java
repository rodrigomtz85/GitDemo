package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ForgotPassword {

    WebDriver driver = null;

    By email = By.cssSelector("input[id='user_email']");
    By sendMeInstructions = By.name("commit");

    public ForgotPassword(WebDriver driver){
        this.driver = driver;
    }

    public WebElement getEmail(){
        return this.driver.findElement(email);
    }
    public WebElement getSendMeInstructions(){
        return this.driver.findElement(sendMeInstructions);
    }


}
