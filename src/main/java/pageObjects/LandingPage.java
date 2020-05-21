package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class LandingPage {

    public WebDriver driver;

    private By signin = By.cssSelector("a[href*='sign_in']");
    //By title = By.xpath("//h2[text()='Featured Courses']");
    private By navBar = By.cssSelector("div[role='navigation']");
    @FindBy(xpath = "//h2[text()='Featured Courses']")
    private WebElement title;
    private By popUp = By.xpath("//button[text()='NO THANKS']");

    public LandingPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public LoginPage getLogin() {

        LoginPage loginPage = new LoginPage(this.driver);

        this.driver.findElement(signin).click();

        return loginPage;


    }

    public WebElement getTitle() {
        return this.title;
    }

    public WebElement navigationBar() {
        return this.driver.findElement(navBar);
    }

    public int getPopUpSize() {
        return this.driver.findElements(popUp).size();
    }
    public WebElement getPopUp() {
        return this.driver.findElement(popUp);
    }

}
