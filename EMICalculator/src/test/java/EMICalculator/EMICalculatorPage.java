package EMICalculator;

import java.util.List;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class EMICalculatorPage {
    WebDriver driver;

    @FindBy(id = "car-loan")
    WebElement carLoanTab;

    @FindBy(id = "loanamount")
    WebElement loanAmount;

    @FindBy(id = "loaninterest")
    WebElement loanInterest;

    @FindBy(id = "loanterm")
    WebElement loanTenure;
    
    @FindBy(id = "year2025")
    WebElement ele;

    @FindBy(xpath = "//*[@id=\"monthyear2025\"]/td/div/table/tbody/tr[1]/td[2]")
    WebElement pricipal;

    @FindBy(xpath = "//*[@id=\"monthyear2025\"]/td/div/table/tbody/tr[1]/td[3]")
    WebElement interest;

    @FindBy(id = "menu-item-dropdown-2696")
    WebElement MenuButton;
    
    @FindBy(xpath ="//a[@title='Home Loan EMI Calculator']")
    WebElement homeLoan;
    
    @FindBy(xpath = "//table[@class='noextras']/tbody/tr")
    List<WebElement> yearlyBreakupRows;

    @FindBy(xpath="//a[@title='Loan Calculator']")
    WebElement loanCalc;

    @FindBy(xpath="//label[normalize-space()='Yr']")
    WebElement loanYears;
    
    @FindBy(xpath="//label[normalize-space()='Mo']")
    WebElement loanMonths;
    
    @FindBy(id="loanfees")
    WebElement loanFees;

    @FindBy(id="loanamountslider")
    WebElement loanAmountSlider;
    
    @FindBy(id="loaninterestslider")
    WebElement interestSlider;
    
    @FindBy(id = "loantermslider")
    WebElement tenureSlider;
    
    @FindBy(id = "loanfeesslider")
    WebElement FeeSlider;
    
    @FindBy(id="loanemislider")
    WebElement EmiSlider;
    
    @FindBy(xpath="(//span[@class='input-group-text'][contains(text(),'₹')])[1]")
    WebElement rup;
    
    @FindBy(xpath="//span[normalize-space()='%']")
    WebElement perc;

    @FindBy(xpath="(//span[@class='input-group-text'][contains(text(),'₹')])[3]")
    WebElement charge;
    
    @FindBy(id="loan-amount-calc")
    WebElement loanAmountCalc;
    
    @FindBy(id="loanemi")
    WebElement loanEmi;
    
    @FindBy(xpath="(//div[@class='input-group-append'])[2]")
    WebElement ruemi;
    
    @FindBy(id="loan-tenure-calc")
    WebElement loanTentureCalc;
    public EMICalculatorPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void clickCarLoanTab() {
        carLoanTab.click();
    }

    public void enterLoanAmount() {
        loanAmount.clear();
        loanAmount.sendKeys("1500000");
        
    }

    public void enterLoanInterest(){
        loanInterest.clear();
        loanInterest.sendKeys(".5");
    }

    public void enterLoanTenure(){
        loanTenure.clear();
        loanYears.click();
        ele.click();
    }

    public String getPrincipal() {
        return pricipal.getText();
        
    }
    
    public String getInterest() {
    	return interest.getText();
    }
    
    public void HomeLoanCalculator(){
    	MenuButton.click();
    	homeLoan.click();
    	driver.navigate().back();
    	MenuButton.click();
    	homeLoan.click();
    }

    public List<WebElement> getYearlyBreakupRows() {
        return yearlyBreakupRows;
    }
    
    public void LoanEmiCalculator() {
    	MenuButton.click();
    	loanCalc.click();
    }
    
    public void LoanAmountCalculator() {
    	loanAmountCalc.click();
    }
    
	public void LoanTentureCalculator() {
    	loanTentureCalc.click();
    }
    
    @SuppressWarnings("deprecation")
	public void LoanAmount(){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].value='';", loanAmount);
        loanAmount.sendKeys("2500000");
        rup.click();
        Assert.assertEquals(loanAmount.getAttribute("value"), "25,00,000");
        validateSliderMovement(loanAmountSlider, loanAmount);
    }

    @SuppressWarnings("deprecation")
	public void LoanInterest() {
        loanInterest.clear();
        loanInterest.sendKeys("9.5");
        perc.click();
        Assert.assertEquals(loanInterest.getAttribute("value"), "9.5"); 
        validateSlider(interestSlider, loanInterest);
    }
    
	@SuppressWarnings("deprecation")
	public void LoanTenture() {
    	JavascriptExecutor js = (JavascriptExecutor) driver;
    	js.executeScript("arguments[0].value='';", loanTenure);
        loanTenure.sendKeys(Keys.CONTROL + "a");
        loanTenure.sendKeys(Keys.DELETE);
        loanYears.click();
        loanTenure.sendKeys("25");
        loanYears.click();
        Assert.assertEquals(loanTenure.getAttribute("value"), "25");
        loanMonths.click();
        Assert.assertEquals(loanTenure.getAttribute("value"), "300");  
        validateSliderMovement(tenureSlider, loanTenure);
    }
	
    @SuppressWarnings("deprecation")
	public void LoanFees() {
    	 loanFees.clear();
         loanFees.sendKeys("20000");
         charge.click();
         Assert.assertEquals(loanFees.getAttribute("value"), "20,000");
         validateSliderMovement(FeeSlider, loanFees);
    }
    
    @SuppressWarnings("deprecation")
	public void LoanEMI() {
    	JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].value='';", loanEmi);
        loanEmi.sendKeys("50000");
        ruemi.click();
        Assert.assertEquals(loanEmi.getAttribute("value"), "50,000.00");
        validateSliderMovement(EmiSlider, loanEmi);
    }
    
    @SuppressWarnings("deprecation")
	public void LoanEMITenture() {
    	JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].value='';", loanEmi);
        loanEmi.sendKeys("60000");
        ruemi.click();
        Assert.assertEquals(loanEmi.getAttribute("value"), "59,967.27");
        validateSliderMovement(EmiSlider, loanEmi);
    }
    
    @SuppressWarnings("deprecation")
	public void validateSliderMovement(WebElement slider, WebElement valueElement) {
		String initialValue = valueElement.getAttribute("value");
        Actions actions = new Actions(driver);
        actions.clickAndHold(slider).moveByOffset(-150, 0).release().perform();
		String newValue = valueElement.getAttribute("value");
        Assert.assertNotEquals(initialValue, newValue, "Slider value did not change.");
    }
    @SuppressWarnings("deprecation")
	public void validateSlider(WebElement slider, WebElement valueElement) {
		String initialValue = valueElement.getAttribute("value");
        Actions actions = new Actions(driver);
        actions.clickAndHold(slider).moveByOffset(40, 0).release().perform();
		String newValue = valueElement.getAttribute("value");
        Assert.assertNotEquals(initialValue, newValue, "Slider value did not change.");
    }
}