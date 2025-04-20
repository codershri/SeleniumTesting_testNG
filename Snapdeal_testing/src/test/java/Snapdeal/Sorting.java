package Snapdeal;

import java.io.*;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
//import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Sorting extends SelectSearch{
	
	public void Sort() throws Exception
	{
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		
		// Locate the minimum value input field and enter value
        WebElement sm_min = driver.findElement(By.xpath("//*[@name='fromVal']"));
        sm_min.clear();
        sm_min.sendKeys("700");
        captureScreenshot("Minimum Value");

        // Locate the maximum value input field and enter value
        WebElement sm_max = driver.findElement(By.xpath("//*[@name='toVal']"));
        sm_max.clear();
        sm_max.sendKeys("1400");
        captureScreenshot("Maximum Value");

        // Locate and click the GO button
        WebElement goBtn = driver.findElement(By.xpath("//div[contains(@class, 'price-go-arrow')]"));
        goBtn.click();

//      Locate the relevance dropdown and select "Popularity"
        WebElement relevance = driver.findElement(By.xpath("//div[@class=\"sort-selected\"]"));
        wait.until(ExpectedConditions.elementToBeClickable(relevance));
        relevance.click();
        captureScreenshot("Filtering");

//		Locate and select the popularity
        WebElement pop = driver.findElement(By.xpath("//*[@data-index=\"1\" and normalize-space()='Popularity']"));
        wait.until(ExpectedConditions.elementToBeClickable(pop));
        pop.click();
        captureScreenshot("Filtering Popularity");
	}
}
