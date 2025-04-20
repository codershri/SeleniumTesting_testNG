package EMICalculator;

import org.testng.ITestContext;  
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class AutomationReport implements ITestListener {
    // Reporter for generating HTML reports
    public ExtentSparkReporter htmlreporter;
    // ExtentReports object to manage the report
    public ExtentReports extent;
    // ExtentTest object to log test details
    public ExtentTest test;

    // Method called when all tests are finished
    public void onFinish(ITestContext context) {
        // Flush the report to write all details to the file
        extent.flush();
    }

    // Method called when the test suite starts
    public void onStart(ITestContext context) {
        // Initialize the HTML reporter with the file path
        htmlreporter = new ExtentSparkReporter(System.getProperty("user.dir") + "\\Reports\\EMICalculator-Report.html");
        // Initialize the ExtentReports object
        extent = new ExtentReports();
        // Attach the HTML reporter to the ExtentReports object
        extent.attachReporter(htmlreporter);
        
        // Configure the HTML report
        htmlreporter.config().setDocumentTitle("EMI Calculation");
        htmlreporter.config().setReportName("Automation Report");
        htmlreporter.config().setTheme(Theme.DARK);
        
        // Set system information for the report
        extent.setSystemInfo("Computer Name", "Local Host");
        extent.setSystemInfo("Environment", "QA");
        extent.setSystemInfo("Tester Name", "Ponduru Devayani");
        extent.setSystemInfo("OS", "Windows10");
        extent.setSystemInfo("Browser", "Edge");
    }

    // Method called when a test case passes
    public void onTestSuccess(ITestResult result) {
        // Create a test entry in the report
        test = extent.createTest(result.getName());
        // Log the test case as passed
        test.log(Status.PASS, "Test case passed: " + result.getName());
        // Add additional information
        test.info("Test Completed");
    }

    // Method called when a test case fails
    public void onTestFailure(ITestResult result) {
        // Create a test entry in the report
        test = extent.createTest(result.getName());
        // Log the test case as failed and include the throwable
        test.log(Status.FAIL, "Test case failed: " + result.getThrowable());
        // Add additional information
        test.info("Test interrupted");
    }

    // Method called when a test case is skipped
    public void onTestSkipped(ITestResult result) {
        // Create a test entry in the report
        test = extent.createTest(result.getName());
        // Log the test case as skipped
        test.log(Status.SKIP, "Test case skipped: " + result.getName());
        // Add additional information
        test.info("Test skipped");
    }
}