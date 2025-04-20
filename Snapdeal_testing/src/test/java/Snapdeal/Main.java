package Snapdeal;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import java.io.File;

public class Main {
    ExtentSparkReporter sparkReporter;
    ExtentReports extent;
    ExtentTest test1, test2, test3;

    @BeforeClass
    public void setUp() {
        // Create the Reports directory if it doesn't exist
        File reportsDir = new File(System.getProperty("user.dir") + "/Reports");
        if (!reportsDir.exists()) {
            reportsDir.mkdirs();
        }

        // Set up ExtentReports
        sparkReporter = new ExtentSparkReporter(System.getProperty("user.dir") + "/Reports/TestReport.html");
        sparkReporter.config().setEncoding("utf-8");
        sparkReporter.config().setDocumentTitle("Automation Test Report");
        sparkReporter.config().setReportName("SnapDeal Automation Report");
        sparkReporter.config().setTheme(Theme.STANDARD);

        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);
        extent.setSystemInfo("Tester", "Shrirang");
        extent.setSystemInfo("Environment", "Test");
    }

    @Test
    public void testAutomationFlow1() {
        try {
            test1 = extent.createTest("Automation Test 1", "Testing Navigation and Search button");
            PrintData pd = new PrintData();
            pd.Search();
            test1.pass("Entered the data in search bar",
                    MediaEntityBuilder.createScreenCaptureFromPath(System.getProperty("user.dir") + "/ScreenShots/Bluetooth headphone.png").build());
        } catch (Exception e) {
            test1.fail("Test failed: " + e.getMessage());
        }
    }

    @Test
    public void testAutomationFlow2() {
        try {
            test2 = extent.createTest("Automation Test 2", "Testing Filtering of Data");
            PrintData pd = new PrintData();
            pd.Sort();
            test2.pass("Filtering criteria entered",
                    MediaEntityBuilder.createScreenCaptureFromPath(System.getProperty("user.dir") + "/ScreenShots/Filtering.png").build());
            test2.pass("Filtering criteria entered",
                    MediaEntityBuilder.createScreenCaptureFromPath(System.getProperty("user.dir") + "/ScreenShots/Filtering Popularity.png").build());
        } catch (Exception e) {
            test2.fail("Test failed: " + e.getMessage());
        }
    }

    @Test
    public void testAutomationFlow3() {
        try {
            test3 = extent.createTest("Automation Test 3", "Testing the entire SnapDeal Search Automation Flow");
            PrintData pd = new PrintData();
            pd.Print();
            test3.pass("Results are displayed",
                    MediaEntityBuilder.createScreenCaptureFromPath(System.getProperty("user.dir") + "/ScreenShots/Results Page.png").build());
        } catch (Exception e) {
            test3.fail("Test failed: " + e.getMessage());
        }
    }

    @AfterClass
    public void tearDown() {
        extent.flush();
    }
}