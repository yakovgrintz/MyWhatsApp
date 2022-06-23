import javax.swing.*;

public class MyAddWindow extends JFrame implements MyApp {
    public MyAddWindow(){
        this.setSize(WIDTH_OF_ADD_WINDOW, HEIGHT_OF_ADD_WINDOW);
        this.setResizable(false);
        this.setLayout(null);
        this.setLocationRelativeTo(null);
    }
    public MyAddWindow(int width,int height){
        this.setSize(width, height);
        this.setResizable(false);
        this.setLayout(null);
        this.setLocationRelativeTo(null);
    }
}
