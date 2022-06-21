import com.google.common.collect.Table;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

public class Test {
    public static void main(String[] args) throws Exception {
        System.setProperty("webdriver.chrome.driver","C:\\Users\\שלמה\\Desktop\\תואר יעקב\\chromedriver.exe");

        ChromeOptions options = new ChromeOptions();
        options.addArguments("user-data-dir=C:\\Users\\שלמה\\AppData\\Local\\Google\\Chrome\\User Data\\Profile 1");
        ChromeDriver driver = new ChromeDriver(options);
        driver.get("https://web.whatsapp.com/send?phone=972580010301");
        Thread.sleep(10 * 1000);
        try {
            driver.findElement(By.className("_3J6wB")).findElement(By.className("_20C5O")).click();
            System.out.println("h");
        } catch (Exception e) {


        BufferedImage image = ImageIO.read(new File("C:\\Users\\שלמה\\Pictures\\Screenshots\\y.png"));

        WebElement d = driver.findElement(By.className("_1un-p")).findElement(By.className("_26lC3"));
        d.click();
        driver.findElement(By.className("_1HnQz")).findElement(By.cssSelector("li:nth-child(1) > button > input[type=file]")).sendKeys("C:\\Users\\שלמה\\Pictures\\Screenshots\\y.png");
        Thread.sleep(3*1000);
        driver.findElement(By.className("_1VmmK")).findElement(By.className("_13NKt")).sendKeys("" + Keys.ENTER);}

    }


}
