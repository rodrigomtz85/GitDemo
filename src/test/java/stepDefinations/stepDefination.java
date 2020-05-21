package stepDefinations;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import pageObjects.LandingPage;
import pageObjects.LoginPage;
import pageObjects.PortalHomePage;
import resources.Base;

import java.io.IOException;

public class stepDefination extends Base {

     @Given("Initialize the browser with chrome")
    public void initialize_the_browser_with_chrome() throws IOException {

        driver = initializeDriver();

    }

    @Then("^Navigate to \"([^\"]*)\" site$")
    public void navigate_to_site(String string) {
        driver.get(string);
    }

    @Given("Click on Login link in home page to land on Secure sing in Page")
    public void click_on_Login_link_in_home_page_to_land_on_Secure_sing_in_Page() {
        LandingPage lp = new LandingPage(driver);
        if(lp.getPopUpSize()>0)
            lp.getPopUp().click();

        lp.getLogin();
    }

    @When("^User enters (.+) and (.+) and logs in$")
    public void user_enters_and_and_logs_in(String string, String string2) throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.getEmail().sendKeys(string);
        loginPage.getPassword().sendKeys(string2);
        Thread.sleep(3000);
        loginPage.getSubmit().click();
    }

    @Then("Verify that user is succesfully logged in")
    public void verify_that_user_is_succesfully_logged_in() {
        PortalHomePage p = new PortalHomePage(driver);
        Assert.assertTrue(p.getSearchBox().isDisplayed());
    }

    @And("^close browsers$")
    public void close_browsers() throws Throwable {
        driver.quit();
    }

}