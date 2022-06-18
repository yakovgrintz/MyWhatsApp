import javax.swing.*;
import java.awt.*;

public class WelcomePanel extends JPanel implements MyApp {
    //private MySelenium myDriver;

    public WelcomePanel(){
        this.setBounds(0,0,WIDTH_OF_WINDOW,HEIGHT_OF_WINDOW);
        this.setBackground(Color.CYAN);
        this.setVisible(true);
        JButton button = new JButton("connect to whatsapp");
        button.setBounds(500,250,100,100);
        button.addActionListener((event)->{
            //this.myDriver= new MySelenium();
            DRIVER.connectToWhatsApp();
            //this.myDriver.connectToWhatsApp();
            System.out.println("hh");

            this.setVisible(false);
            //this.add(new AppPanel(myDriver));
        });
        this.add(button);
    }


}
