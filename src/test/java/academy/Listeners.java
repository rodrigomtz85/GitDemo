package academy;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import resources.Base;
import resources.ExtentReporterNG;

import java.io.IOException;

public class Listeners extends Base implements ITestListener {

    ExtentTest test;
    ExtentReports extent =  ExtentReporterNG.getReportObject();
    ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();

    @Override
    public void onTestStart(ITestResult result) {

        this.test = this.extent.createTest(result.getMethod().getMethodName());
        extentTest.set(this.test);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        this.extentTest.get().log(Status.PASS,"Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {

        this.extentTest.get().fail(result.getThrowable());

        WebDriver driver = null;
        String testCaseName = result.getMethod().getMethodName();
        try {
            driver = (WebDriver) result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
        } catch (IllegalAccessException e) {
            e.printStackTrace(System.out);
        } catch (NoSuchFieldException e) {
            e.printStackTrace(System.out);
        }
        try {
            this.extentTest.get().addScreenCaptureFromPath(getScreenShotPath(testCaseName, driver),result.getMethod().getMethodName());

        } catch (IOException e) {
            e.printStackTrace(System.out);
        }

    }

    @Override
    public void onTestSkipped(ITestResult result) {

    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

    }

    @Override
    public void onTestFailedWithTimeout(ITestResult result) {

    }

    @Override
    public void onStart(ITestContext context) {

    }

    @Override
    public void onFinish(ITestContext context) {
        this.extent.flush();
    }
}
