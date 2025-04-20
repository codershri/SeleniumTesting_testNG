package Snapdeal;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class SelectSearch {
    static WebDriver driver;
    static String baseUrl = "https://www.snapdeal.com/";

    public void Search() throws Exception {
        // Set the path to the ChromeDriver executable
//        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");

        // Chrome driver instance creation
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        
        // Navigation to the link
        driver.get(baseUrl);

        // Locate the search box and enter text
        WebElement searchBox = driver.findElement(By.id("inputValEnter"));
        String text = "Bluetooth headphone";
        searchBox.sendKeys(text);
        captureScreenshot("Bluetooth headphone");

        // Locate and click the search button
        WebElement searchBtn = driver.findElement(By.className("searchTextSpan"));
        searchBtn.click();        
    }
    
    public static void captureScreenshot(String fileName) throws IOException {
    	String filePath1 = "//ScreenShots//";
        // Convert WebDriver object to TakesScreenshot
        TakesScreenshot screenshot = (TakesScreenshot) driver;

        // Call getScreenshotAs method to create an image file
        File srcFile = screenshot.getScreenshotAs(OutputType.FILE);
        File destFile = new File(System.getProperty("user.dir")+filePath1+fileName+".png");
        FileUtils.copyFile(srcFile, destFile);
        System.out.println("Screenshot saved: "+destFile.getAbsolutePath());

        // Move image file to the desired location
    }
}