import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

public class SeleniumMain {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver","C:\\Users\\User\\Desktop\\files to project\\chromedriver102.exe");
        //System.setProperty("webdriver.chrome.driver","C:\\Users\\שלמה\\Desktop\\תואר יעקב\\chromedriver.exe");

       //System.setProperty("webdriver.chrome.driver","C:\\Users\\שלמה\\Desktop\\תואר יעקב\\chromedriver.exe");
        //ListOfConatants list = new ListOfConatants();
        //list.addPhoneNumberIL(new PhoneNumberIL("0586010301","yakov"));
        //MySelenium test=new MySelenium(list,"hubuiebvuobseoib");
        WindowApp windowApp=new WindowApp();
    }
}
