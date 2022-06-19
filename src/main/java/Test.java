import com.google.common.collect.Table;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


import javax.swing.*;
import javax.swing.table.TableModel;
import java.io.FileOutputStream;
import java.util.List;

public class Test {
    public static void main(String[] args) throws Exception {
        /*System.setProperty("webdriver.chrome.driver","C:\\Users\\שלמה\\Desktop\\תואר יעקב\\chromedriver.exe");

        ChromeOptions options = new ChromeOptions();
        options.addArguments("user-data-dir=C:\\Users\\שלמה\\AppData\\Local\\Google\\Chrome\\User Data\\Profile 1");
        ChromeDriver driver = new ChromeDriver(options);
        driver.get("https://web.whatsapp.com/send?phone=972586010301");
        for (int i = 0; i < 10; i++) {
            Thread.sleep(5000);
            List<WebElement> list = driver.findElements(By.className("_22Msk"));
            WebElement test = list.get(list.size() - 1);
            try {
                String status = test.findElement(By.cssSelector("div._1beEj > div > div > span")).getAttribute("aria-label");
                System.out.println(status);
            }catch (Exception e){
//            String s1=test.findElement(By.cssSelector("div.copyable-text > div > span.i0jNr.selectable-text.copyable-text > span")).getText();
                String s1= test.findElement(By.className("i0jNr")).getText();
                System.out.println("S1:"+s1);
            }
            String s1=test.findElement(By.cssSelector("div.copyable-text > div > span.i0jNr.selectable-text.copyable-text > span")).getText();
            System.out.println("S1:"+s1);
            Thread.sleep(1000);
        }*/
        Object columnNames[] = {"Name", "Amount", "Factor"};
        Object rowData[][] = {
                {"Bob", 12.0, 3.0},
                {"Alice", 34.0, 2.5},
                {"Jack", 56.0, 2.0},
                {"John", 78.0, 1.5}
        };
        JTable table = new JTable(rowData, columnNames);

        exportToExcel(table, "./Excel.xlsx");

    }
    static void exportToExcel(JTable table, String filePath) throws Exception {
        TableModel model = table.getModel();
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet();
        Row row;
        Cell cell;

        // write the column headers
        row = sheet.createRow(0);
        for (int c = 0; c < model.getColumnCount(); c++) {
            cell = row.createCell(c);
            cell.setCellValue(model.getColumnName(c));
        }

        // write the data rows
        for (int r = 0; r < model.getRowCount(); r++) {
            row = sheet.createRow(r+1);
            for (int c = 0; c < model.getColumnCount(); c++) {
                cell = row.createCell(c);
                Object value = model.getValueAt(r, c);
                if (value instanceof String) {
                    cell.setCellValue((String)value);
                } else if (value instanceof Double) {
                    cell.setCellValue((Double)value);
                }
            }
        }

        FileOutputStream out = new FileOutputStream(filePath);
        workbook.write(out);
        out.close();
        workbook.close();

    }

}
