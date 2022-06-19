import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.List;

public class Test {
    public static void main(String[] args) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver","C:\\Users\\שלמה\\Desktop\\תואר יעקב\\chromedriver.exe");

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
        }

    }
}
