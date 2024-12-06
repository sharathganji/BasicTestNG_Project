package my_package;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class MyExtentReportManager implements ITestListener {
    ExtentSparkReporter sparkReporter; //UI
    ExtentReports extentReports; //common information
    ExtentTest extentTest; //create tests, update test case status

    public void onStart(ITestContext context){
        sparkReporter = new ExtentSparkReporter(System.getProperty("user.dir")+"/Reports/myExtentReports.html");
        sparkReporter.config().setDocumentTitle("EXTENT_DOCUMENT");
        sparkReporter.config().setReportName("EXTENT_REPORT_NAME");
        sparkReporter.config().setTheme(Theme.STANDARD);

        extentReports = new ExtentReports();
        extentReports.attachReporter(sparkReporter);
        extentReports.setSystemInfo("Tester", "Sharath");
        extentReports.setSystemInfo("Environment", "QA");
        extentReports.setSystemInfo("OS", "Windows-10");
        extentReports.setSystemInfo("Browser", "Chrome");
    }

    public void onTestSuccess(ITestResult result) {
        extentTest = extentReports.createTest(result.getName());
        extentTest.log(Status.PASS, "Test Case is passed: "+result.getName());
    }
    public void onTestFailure(ITestResult result) {
        extentTest = extentReports.createTest(result.getName());
        extentTest.log(Status.FAIL, "Test Case is failed: "+result.getName());
        extentTest.log(Status.FAIL, "Test Case is failed: "+result.getThrowable());
    }
    public void onTestSkipped(ITestResult result) {
        extentTest = extentReports.createTest(result.getName());
        extentTest.log(Status.SKIP, "test case is skipped: "+result.getName());
    }
    public void onFinish(ITestContext context) {
        extentReports.flush();
    }
}
