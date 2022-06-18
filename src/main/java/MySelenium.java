import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.LinkedList;
import java.util.List;

public class MySelenium {
    ChromeDriver driver;
    public final String WHATSAPP_URL_HOME_PAGE = "https://web.whatsapp.com/";
    public MySelenium(){
        ChromeOptions options = new ChromeOptions();
        options.addArguments("user-data-dir=C:/Users/User/AppData/Local/Google/Chrome/User Data");
        this.driver = new ChromeDriver(options);
    }

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
                driver.findElement(By.className("_1LbR4")).findElement(By.className("_13NKt")).sendKeys(message + Keys.ENTER);
                //driver.findElement(By.cssSelector("#main > footer > div._2BU3P.tm2tP.copyable-area > div > span:nth-child(2) > div > div._2lMWa > div.p3_M1 > div > div._13NKt.copyable-text.selectable-text")).sendKeys(message + Keys.ENTER);
                Thread.sleep(1000);
                System.out.println(checkStatus());
                Thread.sleep(10 * 1000);
                getAnswerMessage();
            } catch (Exception e) {
                temp.setCanToSend(false);
                System.out.println("yakov");
            }

        }


    }
    public void sendToList(ListOfConatants list,String message){
        for (int i = 0; i < list.size(); i++) {
            PhoneNumberIL temp = list.getConants(i);
            try {
                driver.get(temp.getUrlToSend());
                Thread.sleep(5 * 1000);
                driver.findElement(By.className("_1LbR4")).findElement(By.className("_13NKt")).sendKeys(message + Keys.ENTER);
                //driver.findElement(By.cssSelector("#main > footer > div._2BU3P.tm2tP.copyable-area > div > span:nth-child(2) > div > div._2lMWa > div.p3_M1 > div > div._13NKt.copyable-text.selectable-text")).sendKeys(message + Keys.ENTER);
                Thread.sleep(1000);
                temp.setMessage(message);
                temp.setSent(true);

                System.out.println(checkStatus());
                Thread.sleep(10 * 1000);
                //getAnswerMessage();
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

    public int checkStatus() {
        WebElement status = getLastOfMessage().findElement(By.cssSelector("div._1beEj > div > div > span"));
        String statusAttribute = status.getAttribute("aria-label");
        switch (statusAttribute) {
            case " נשלחה " -> {
                return 3;
            }
            case " נמסרה " -> {
                return 2;
            }
            case " נקראה " -> {
                return 1;
            }
        }
        return -1;
    }

    public WebElement getLastOfMessage() {
        try {
            List<WebElement> listOfMessageInChat = this.driver.findElements(By.className("Nm1g1"));
            WebElement lastMessage = listOfMessageInChat.get(listOfMessageInChat.size() - 1);
            return lastMessage;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public String getAnswerMessage() {
        System.out.println("jj");
        WebElement answer = getLastOfMessage();
        System.out.println("f");
        String whoAnswer = answer.findElement(By.cssSelector("//span")).getAttribute("aria-label");
        System.out.println("hjklfvkf");
        switch (whoAnswer) {
            case "את/ה:" -> {
                System.out.println("hhhh");
                return "The recipient did not respond";
            }
            default -> {
                String textOfMessage = answer.findElement(By.cssSelector("div._22Msk > div.copyable-text > div._1Gy50 > span.i0jNr.selectable-text.copyable-text > span")).getText();
                System.out.println(textOfMessage);
                return textOfMessage;
            }
        }

    }
    public void setDriver(){
        ChromeOptions options = new ChromeOptions();
        options.addArguments("user-data-dir=C:/Users/User/AppData/Local/Google/Chrome/User Data");
        this.driver = new ChromeDriver(options);
    }
}