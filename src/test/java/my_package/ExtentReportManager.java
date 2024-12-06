package my_package;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class ExtentReportManager implements ITestListener {

    ExtentSparkReporter sparkReporter;
    ExtentReports extentReports;
    ExtentTest extentTest;

    public void onStart(ITestContext context) {
        sparkReporter = new ExtentSparkReporter(System.getProperty("user.dir")+"/Reports/extentReports.html");
        sparkReporter.config().setDocumentTitle("Listener_Document");
        sparkReporter.config().setReportName("Listener_ReportName");
        sparkReporter.config().setTheme(Theme.DARK);

        extentReports = new ExtentReports();
        extentReports.attachReporter(sparkReporter);
        extentReports.setSystemInfo("Computer Name", "Localhost");
        extentReports.setSystemInfo("Tester", "Sharath");
        extentReports.setSystemInfo("Browser-Name", "Chrome");
        extentReports.setSystemInfo("OS", "Windows");
        extentReports.setSystemInfo("Env", "QA");
    }

    public void onTestSuccess(ITestResult result) {
        extentTest = extentReports.createTest(result.getName());
        extentTest.log(Status.PASS, "Test Case is Passed is: "+result.getName());
    }

    public void onTestFailure(ITestResult result) {
    extentTest = extentReports.createTest(result.getName());
    extentTest.log(Status.FAIL, "Test Case is Failed is: "+result.getName());
    extentTest.log(Status.FAIL, "Test Case is Failed is: "+result.getThrowable());
    }

    public void onTestSkipped(ITestResult result) {
        extentTest = extentReports.createTest(result.getName());
        extentTest.log(Status.SKIP, "Test case is skipped: "+result.getName());
    }

    public void onFinish(ITestContext context) {
        extentReports.flush();
    }
}
