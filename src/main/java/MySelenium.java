import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class MySelenium {
    ChromeDriver driver;
    public final String WHATSAPP_URL_HOME_PAGE = "https://web.whatsapp.com/";

    public MySelenium(ListOfConatants listOfConatants, String message) {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("user-data-dir=C:/Users/User/AppData/Local/Google/Chrome/User Data");
        this.driver = new ChromeDriver(options);
        driver.get(WHATSAPP_URL_HOME_PAGE);

        //connectToWhatsApp();
        for (int i = 0; i < listOfConatants.size(); i++) {
            PhoneNumberIL temp = listOfConatants.getConants(i);
            try {
                driver.get(temp.getUrlToSend());
                Thread.sleep(15 * 1000);
                driver.findElement(By.cssSelector("#main > footer > div._2BU3P.tm2tP.copyable-area > div > span:nth-child(2) > div > div._2lMWa > div.p3_M1 > div > div._13NKt.copyable-text.selectable-text")).sendKeys(message + Keys.ENTER);
                System.out.println(checkStatus());
            } catch (Exception e) {
                temp.setCanToSend(false);
                System.out.println("yakov");
            }

        }


    }

    public void connectToWhatsApp() {
        do {
            driver.get(WHATSAPP_URL_HOME_PAGE);
            try {
                Thread.sleep(30 * 1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        while (!checkConnectToWhatsApp());


    }

    private boolean checkConnectToWhatsApp() {
        try {
            this.driver.findElement(By.className("_3aF8K"));
            return false;
        } catch (Exception e) {
            return true;
        }
    }

    protected boolean checkUrlWhatsappLegall(String url) {
        if (!checkConnectToWhatsApp()) {
            throw new RuntimeException("We can not perform the test");
        }
        driver.get(url);

        try {
            driver.findElement(By.className("_3J6wB"));
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    public ChromeDriver getDriver() {
        return driver;
    }
    public int checkStatus(){
        WebElement status = this.driver.findElement(By.cssSelector("#main > div._2gzeB > div > div._33LGR > div._3K4-L > div:nth-child(24) > div > div.Nm1g1._22AX6 > div._22Msk > div._1beEj > div > div > span"));
        String statusAttribute= status.getAttribute("aria-label");
        System.out.println(statusAttribute);
        if (statusAttribute==" נקראה "){
            return 1;
        }
        if (statusAttribute==" נמסרה "){
            return 2;
        }
        if (statusAttribute==" נשלחה "){
            return 3;
        }
        return -1;
    }
}
