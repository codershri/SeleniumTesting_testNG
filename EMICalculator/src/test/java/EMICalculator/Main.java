package EMICalculator;

import org.openqa.selenium.WebDriver; 
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import org.testng.annotations.Parameters;

import java.io.IOException;
import java.util.List;
import org.openqa.selenium.WebElement;

public class Main extends AutomationReport{
    WebDriver driver;
    String baseUrl="https://emicalculator.net/";
    EMICalculatorPage emiCalculatorPage;
    ExcelUtility excelUtility;
    ScreenShots ss = new ScreenShots();
    
    @BeforeClass
    @Parameters("browser")
    public void driverSetup(String br) {
    	switch (br.toLowerCase()) {
        case "chrome":
            driver = new ChromeDriver();
            break;
        case "edge":
            driver = new EdgeDriver();
            break;
    	}
        driver.manage().window().maximize();
        driver.get(baseUrl);
        emiCalculatorPage = new EMICalculatorPage(driver);
        Assert.assertTrue(true);
        ss.takeScreenshot("HomePage", driver);
    }

    @Test(priority = 1)
    public void calculateEMI() {
        emiCalculatorPage.clickCarLoanTab();
        emiCalculatorPage.enterLoanAmount();
        emiCalculatorPage.enterLoanInterest();
        emiCalculatorPage.enterLoanTenure();
        String pri = emiCalculatorPage.getPrincipal();
        System.out.println("Monthly Principal Amount: " + pri);
        String interest = emiCalculatorPage.getInterest();
        System.out.println("Monthly Interest Amount: "+interest);
        Assert.assertNotNull(pri);
        Assert.assertNotNull(interest);
        ss.takeScreenshot("CarLoanEMI Calculation", driver);
    }
    @Test(priority=2)
    public void HomeCalculator() throws IOException {

        emiCalculatorPage.HomeLoanCalculator();
        excelUtility = new ExcelUtility("./output.xlsx");
        List<WebElement> rows = emiCalculatorPage.getYearlyBreakupRows();
        Assert.assertNotNull(rows);
        excelUtility.writeEMIData(rows);
        ss.takeScreenshot("HomeLoanEMI Calculation", driver);
    }
    
    @Test(priority=3)
    public void LoanEMICalc(){
   		emiCalculatorPage.LoanEmiCalculator();
   		emiCalculatorPage.LoanAmount();
   		emiCalculatorPage.LoanInterest();
    	emiCalculatorPage.LoanTenture();
    	emiCalculatorPage.LoanFees();
    	ss.takeScreenshot("EMI Calculator", driver);
    }

    
    @Test(priority=4)
    public void LoanAmountCalc()  {
   		emiCalculatorPage.LoanAmountCalculator();
   		emiCalculatorPage.LoanEMI();
   		emiCalculatorPage.LoanInterest();
    	emiCalculatorPage.LoanTenture();
    	emiCalculatorPage.LoanFees();
    	ss.takeScreenshot("Loan Amount Calculator", driver);
    }
    
    @Test(priority=5)
	public void LoanTentureCalc()  {
		emiCalculatorPage.LoanTentureCalculator();
		emiCalculatorPage.LoanAmount();
		emiCalculatorPage.LoanEMITenture();
		emiCalculatorPage.LoanInterest();
		emiCalculatorPage.LoanFees();
		ss.takeScreenshot("Loan Tenture Calc", driver);
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
    
}
