import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import javax.swing.*;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class MySelenium implements MyApp{
    ChromeDriver driver;
    public final String WHATSAPP_URL_HOME_PAGE = "https://web.whatsapp.com/";

    public MySelenium() {
        ChromeOptions options = new ChromeOptions();
        //options.addArguments("user-data-dir=C:\\Users\\שלמה\\AppData\\Local\\Google\\Chrome\\User Data\\Profile 1");
        //options.addArguments("user-data-dir=C:/Users/User/AppData/Local/Google/Chrome/User Data");
        this.driver = new ChromeDriver(options);
    }

    public void sendToList(ListOfConatants list) {
        for (int i = 0; i < list.size(); i++) {
            PhoneNumberIL temp = list.getConants(i);
            try {
                driver.get(temp.getUrlToSend());
                Thread.sleep(5 * MS);
                driver.findElement(By.className("_3J6wB")).findElement(By.className("_20C5O")).click();
                temp.setCanToSend(false);
            } catch (Exception e) {
                try {
                    if (temp.getPathToImage() != null) {
                        WebElement d = driver.findElement(By.className("_1un-p")).findElement(By.className("_26lC3"));
                        d.click();
                        driver.findElement(By.className("_1HnQz")).findElement(By.cssSelector("li:nth-child(1) > button > input[type=file]")).sendKeys(temp.getPathToImage());
                        Thread.sleep(5 * MS);
                        driver.findElement(By.className("_1VmmK")).findElement(By.className("_13NKt")).sendKeys(temp.getMessage() + Keys.ENTER);
                        temp.setAllToSent();
                        continue;
                    }
                    driver.findElement(By.className("_1LbR4")).findElement(By.className("_13NKt")).sendKeys(temp.getMessage() + Keys.ENTER);
                    temp.setAllToSent();
                    Thread.sleep(1 * MS);
                } catch (Exception e2) {
                    JOptionPane.showMessageDialog(new JFrame(),
                            e2.getMessage(),
                            "error",
                            JOptionPane.PLAIN_MESSAGE);
                }
            }

        }
    }

    public void connectToWhatsApp() {
        driver.get(WHATSAPP_URL_HOME_PAGE);
        do {

            try {
                Thread.sleep(10 * MS);
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
            try {
                this.driver.findElement(By.id("app"));
                return true;
            }catch (Exception e2){
                System.exit(0);
                return false;
            }
        }
    }




    public void checkAndSetStatus(PhoneNumberIL temp) {
        String status = "";
        try {
            WebElement tempElement = getLastOfMessage();
            try {
                status = getLastOfMessage().findElement(By.cssSelector("div._1beEj > div > div > span")).getAttribute("aria-label");
                temp.setStatus(status);
            } catch (Exception e) {
                System.out.println("h");
            }
        } catch (Exception e) {
            return;
        }
    }

    public WebElement getLastOfMessage() {
        try {
            Thread.sleep(5*MS
            );
            List<WebElement> list = driver.findElements(By.className("_22Msk"));
            WebElement test = list.get(list.size() - 1);
            return test;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }


    private String checkAndSetAnswer(PhoneNumberIL temp) {
        String text = null;
        try {
            WebElement element = getLastOfMessage();
            text = element.findElement(By.className("i0jNr")).getText();
            if (!text.equals(temp.getMessage())) {
                temp.setAnswer(text);
                temp.setGetAnswer(true);
                temp.setStatus("נקראה");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return text;
    }


    public void checkAnswerAndStatus(ListOfConatants list, AtomicBoolean run) {
        if (list == null) {
            throw new RuntimeException("the list is null");
        }
        for (int i = 0; i < list.size(); i++) {

            try {
                PhoneNumberIL temp = list.getConants(i);
                if (!temp.isSent()) {
                    continue;
                }
                if (!temp.isNeedCheck()) {
                    continue;
                }
                driver.get(temp.getUrlToSend());
                Thread.sleep(5*MS);
                checkAndSetStatus(temp);
                checkAndSetAnswer(temp);
                Thread.sleep(MS);
                if (temp.isGetAnswer()|!temp.isCanToSend()){
                    temp.setNeedCheck(false);
                }
                continue;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

        }

    }






}