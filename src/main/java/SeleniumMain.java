import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

public class SeleniumMain {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver","./chromedriver102.exe");

        WindowApp windowApp=new WindowApp();
    }
}
