package academy;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import pageObjects.ForgotPassword;
import pageObjects.LandingPage;
import pageObjects.LoginPage;
import resources.Base;

import java.io.IOException;

public class HomePage extends Base{

    WebDriver driver;
    public static Logger log = LogManager.getLogger(Base.class.getName());

    @BeforeTest
    public void initialize() throws IOException {
        this.driver = initializeDriver();
        log.info("Driver is initialized");
    }

    @Test(dataProvider = "getData")
    public void basePageNavigation(String email, String password,String text) throws IOException {

        this.driver.get(prop.getProperty("url"));
        LandingPage lp = new LandingPage(this.driver);
        LoginPage loginPage = lp.getLogin();
        loginPage.getEmail().sendKeys(email);
        loginPage.getPassword().sendKeys(password);
        ForgotPassword fp = loginPage.setForgotPass();
        fp.getEmail().sendKeys("XXX");
        fp.getSendMeInstructions().click();

        log.info("Succesfully validated Text Message");
        loginPage.getSubmit().click();

    }
    @DataProvider
    public Object [][] getData(){

        Object [][] data = new Object[2][3];
        //0th row
        data[0][0] = "myemail@mail.com";
        data[0][1] = "123456";
        data[0][2] = "Restricted User";

        //1st row
        data[1][0] = "myemail2@mail.com";
        data[1][1] = "654321";
        data[1][2] = "Public User";

        return  data;
    }
    @AfterTest
    public void teardown(){
        this.driver.close();
    }
}
