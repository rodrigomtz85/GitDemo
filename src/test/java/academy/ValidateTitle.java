package academy;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pageObjects.LandingPage;
import resources.Base;


import java.io.IOException;



public class ValidateTitle extends Base{

    WebDriver driver;
    public static Logger log = LogManager.getLogger(Base.class.getName());

    @BeforeTest
    public void initialize() throws IOException {
        this.driver = initializeDriver();
        log.info("Driver is initialized");

        this.driver.get(prop.getProperty("url"));
        log.info("Navigated to Home page");
    }

    @Test
    public void basePageNavigation() throws IOException {
        LandingPage lp = new LandingPage(this.driver);
        //Compare the text form the bBrowser with actual text .- Error...
        Assert.assertEquals(lp.getTitle().getText(), "Featured Courses123");

    }
    @AfterTest
    public void teardown(){
        this.driver.close();
    }

}
