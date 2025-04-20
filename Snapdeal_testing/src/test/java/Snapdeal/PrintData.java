package Snapdeal;

import java.time.Duration;
import java.util.Set;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PrintData extends Sorting {

    public void Print() throws Exception{
        String parentId = driver.getWindowHandle();
        captureScreenshot("Results page");
     System.out.println("--------------------------------------------------------------------");
        // Array of XPath values for the products
        String[] productXpaths = {
            "//*[@data-js-pos='0']",
            "//*[@data-js-pos='1']",
            "//*[@data-js-pos='2']",
            "//*[@data-js-pos='3']",
            "//*[@data-js-pos='4']"
        };

        // Loop through each product XPath
        for (String productXpath : productXpaths) {
            selectAndPrintProductDetails(productXpath, parentId);
        }
        driver.quit();
    }

    private void selectAndPrintProductDetails(String productXpath, String parentId) {
    	
    	WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
        // Select the product
        WebElement product = driver.findElement(By.xpath(productXpath));
        wait.until(ExpectedConditions.elementToBeClickable(product));
        product.click();

        // Get all window handles
        Set<String> childWindowHandles = driver.getWindowHandles();

        // Switch to the child window
        for (String child : childWindowHandles) {
            if (!child.equals(parentId)) {
                driver.switchTo().window(child);
                break;
            }
        }

        // Print product details
        String prodText = driver.findElement(By.xpath("//h1")).getText();
        System.out.println("Product Text : "+prodText);

        String prodPrice = driver.findElement(By.xpath("//*[@itemprop='price']")).getText();
        System.out.println("Price: "+prodPrice+"/-");

        // Close the child window
        driver.close();
        

        // Switch back to the parent window
        driver.switchTo().window(parentId);
    }
}