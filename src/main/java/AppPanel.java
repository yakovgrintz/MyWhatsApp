import javax.swing.*;
import java.awt.*;

public class AppPanel extends JPanel implements MyApp {
    public AppPanel(){
        this.setBounds(0,0,WIDTH_OF_WINDOW,HEIGHT_OF_WINDOW);
        this.setBackground(Color.GREEN);
        this.setVisible(true);
        JMenu menu = new JMenu();
        JMenuItem addContant = new JMenuItem("Add Contant");
        menu.add(addContant);
        menu.setMenuLocation(0,400);
        menu.setSize(100,500);
        //this.add(menu);
    }
}
