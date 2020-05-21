package resources;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class Base {

    public WebDriver driver;
    public Properties prop;

    public WebDriver initializeDriver() throws IOException {

        prop = new Properties();
        FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "\\src\\main\\resources\\data.properties");
        prop.load(fis);

        //propiedades desde Maven
        //String browser = System.getProperty("browser");

        String browser = prop.getProperty("browser");

        if (browser.contains("chrome")) {
            System.setProperty("webdriver.chrome.driver", "C:\\Selenium\\Drivers\\Chrome_v81\\chromedriver.exe");
            ChromeOptions cOptions = new ChromeOptions();

            if (browser.contains("headless")) {
                cOptions.addArguments("headless");
            }
            this.driver = new ChromeDriver(cOptions);


        } else if (browser == "firefox") {
            System.setProperty("webdriver.gecko.driver", "C:\\Selenium\\Drivers\\Chrome_v81\\chromedriver.exe");
            this.driver = new FirefoxDriver();
        } else if (browser == "IE") {
            System.setProperty("webdriver.internetExplorer.driver", "C:\\Selenium\\Drivers\\Chrome_v81\\chromedriver.exe");
            this.driver = new InternetExplorerDriver();
        }
        this.driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        return this.driver;
    }

    public String getScreenShotPath(String testCaseName, WebDriver driver) throws IOException {

        TakesScreenshot screenshot = (TakesScreenshot) driver;
        File source = screenshot.getScreenshotAs(OutputType.FILE);
        String destinationFile = System.getProperty("user.dir") + "\\reports\\" + testCaseName + ".png";

        //Para que funcione esté método hay que importar la librería de apache common io (Maven)
        FileUtils.copyFile(source, new File(destinationFile));

        return destinationFile;
    }
}
