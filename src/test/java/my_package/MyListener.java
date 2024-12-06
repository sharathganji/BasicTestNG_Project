package my_package;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class MyListener implements ITestListener {

    public void onTestStart(ITestResult result) {
        System.out.println("onTestStart- Test execution started");
    }

    public void onTestSuccess(ITestResult result) {
        System.out.println("onTestSuccess-Test method ran successfully");
    }

    public void onTestFailure(ITestResult result) {
        System.out.println("onTestFailure-Test method failed");
    }

    public void onTestSkipped(ITestResult result) {
        System.out.println("onTestSkipped-Test method skipped");
    }

    public void onStart(ITestContext context) {
        System.out.println("onStart-Test method stated exexution");
    }

    public void onFinish(ITestContext context) {
        System.out.println("onFinish-all test method executed, finished");
    }
}
