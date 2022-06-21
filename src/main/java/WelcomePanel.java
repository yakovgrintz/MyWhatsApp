import javax.swing.*;
import java.awt.*;

public class WelcomePanel extends JPanel implements MyApp {

    public WelcomePanel(){
        this.setBounds(0,0,WIDTH_OF_WINDOW,HEIGHT_OF_WINDOW);
        this.setBackground(Color.CYAN);
        this.setLayout(null);
        JButton button = new JButton("connect to whatsapp");
        button.setBounds(500,250,100,100);
        button.addActionListener((event)->{
            DRIVER.connectToWhatsApp();
            this.setVisible(false);
        });
        this.add(button);
        this.setVisible(true);

    }


}
