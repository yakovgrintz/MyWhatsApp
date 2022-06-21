import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import javax.swing.*;
import java.util.LinkedList;
import java.util.List;

public class MySelenium {
    ChromeDriver driver;
    public final String WHATSAPP_URL_HOME_PAGE = "https://web.whatsapp.com/";

    public MySelenium() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("user-data-dir=C:\\Users\\שלמה\\AppData\\Local\\Google\\Chrome\\User Data\\Profile 1");
        //options.addArguments("user-data-dir=C:/Users/User/AppData/Local/Google/Chrome/User Data");
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
                //System.out.println(checkStatus());
                Thread.sleep(10 * 1000);
                getAnswerMessage();
            } catch (Exception e) {
                temp.setCanToSend(false);
                System.out.println("yakov");
            }

        }


    }

    public void sendToList(ListOfConatants list) {
        for (int i = 0; i < list.size(); i++) {
            PhoneNumberIL temp = list.getConants(i);
            try {
                driver.get(temp.getUrlToSend());
                Thread.sleep(10 * 1000);
                driver.findElement(By.className("_3J6wB")).findElement(By.className("_20C5O")).click();
                temp.setCanToSend(false);
            } catch (Exception e) {


                try {
                    if (temp.getPathToImage() != null) {
                        WebElement d = driver.findElement(By.className("_1un-p")).findElement(By.className("_26lC3"));
                        d.click();
                        driver.findElement(By.className("_1HnQz")).findElement(By.cssSelector("li:nth-child(1) > button > input[type=file]")).sendKeys(temp.getPathToImage());
                        Thread.sleep(3 * 1000);
                        driver.findElement(By.className("_1VmmK")).findElement(By.className("_13NKt")).sendKeys(temp.getMessage() + Keys.ENTER);
                        continue;
                    }
                    driver.findElement(By.className("_1LbR4")).findElement(By.className("_13NKt")).sendKeys(temp.getMessage() + Keys.ENTER);
                    Thread.sleep(1000);
                    temp.setSent(true);
                    Thread.sleep(10 * 1000);
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
            Thread.sleep(5000);
            List<WebElement> list = driver.findElements(By.className("_22Msk"));
            WebElement test = list.get(list.size() - 1);
            return test;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public String getAnswerMessage() {
        System.out.println("jj");
        WebElement answer = getLastOfMessage();
        System.out.println("f");
        String whoAnswer = answer.findElement(By.cssSelector("span")).getAttribute("aria-label");
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

    public void setDriver() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("user-data-dir=C:/Users/User/AppData/Local/Google/Chrome/User Data");
        this.driver = new ChromeDriver(options);
    }

    private String checkAndSetAnswer(PhoneNumberIL temp) {
        String text = null;
        try {
            WebElement element = getLastOfMessage();
            text = element.findElement(By.className("i0jNr")).getText();
            if (!text.equals(temp.getMessage())) {
                temp.setAnswer(text);
                temp.setGetAnswer(true);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return text;
    }


    public void checkAnswerAndStatus(ListOfConatants list) {
        if (list == null) {
            throw new RuntimeException("the list is null");
        }
        for (int i = 0; i < list.size(); i++) {
            try {
                PhoneNumberIL temp = list.getConants(i);
                if (!temp.isSent()) {
                    continue;
                }
                if (temp.isGetAnswer()){
                    continue;
                }
                this.driver.get(temp.getUrlToSend());
                Thread.sleep(5000);
                checkAndSetStatus(temp);
                checkAndSetAnswer(temp);
                Thread.sleep(5000);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

        }
    }
}