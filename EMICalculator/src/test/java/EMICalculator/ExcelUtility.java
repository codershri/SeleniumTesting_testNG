package EMICalculator;

import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.util.List;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;

public class ExcelUtility {
    // Path to the Excel file
    private String excelFilePath;

    // Constructor to initialize the Excel file path
    public ExcelUtility(String excelFilePath) {
        this.excelFilePath = excelFilePath;
    }

    // Method to write EMI data to the Excel file
    public void writeEMIData(List<WebElement> rows) throws IOException {
        // Create a new workbook and sheet
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Yearly Breakup");

        // Create header row and set column names
        XSSFRow header = sheet.createRow(0);
        header.createCell(0).setCellValue("Year");
        header.createCell(1).setCellValue("Principal (A)");
        header.createCell(2).setCellValue("Interest (B)");
        header.createCell(3).setCellValue("Taxes, Home Insurance & Maintenance (C)");
        header.createCell(4).setCellValue("Total Payment (A + B + C)");
        header.createCell(5).setCellValue("Balance");
        header.createCell(6).setCellValue("Loan Paid To Date");

        // Iterate over EMI data and write to the sheet
        int rowNum = 1;
        for (WebElement row : rows) {
            XSSFRow excelRow = sheet.createRow(rowNum++);
            List<WebElement> cells = row.findElements(By.tagName("td"));
            int cellNum = 0;
            for (WebElement cell : cells) {
                Cell excelCell = excelRow.createCell(cellNum++);
                excelCell.setCellValue(cell.getText());
            }
        }

        // Write the workbook to the file
        FileOutputStream fileOut = new FileOutputStream(excelFilePath);
        workbook.write(fileOut);
        workbook.close();
        fileOut.close();
    }

}
