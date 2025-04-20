package EMICalculator;

import java.io.File;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class ScreenShots {
    // Method to take a screenshot and save it to a specified file
    public void takeScreenshot(String fileName, WebDriver driver) {
        // Cast the WebDriver instance to TakesScreenshot
        TakesScreenshot sc = (TakesScreenshot) driver;
        
        // Capture the screenshot as a file
        File screenshot = sc.getScreenshotAs(OutputType.FILE);
        
        // Define the destination file path and name
        File destination = new File(System.getProperty("user.dir") + "\\Screenshots\\" + fileName + ".png");
        
        // Rename the screenshot file to the destination file
        screenshot.renameTo(destination);
    }
}