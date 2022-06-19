import javax.swing.*;

public class WindowApp extends JFrame implements MyApp {
    private MySelenium mySeleniumDriver;
    private WelcomePanel welcomePanel;
    private AppPanel appPanel;
    public WindowApp(){
        this.setSize(WIDTH_OF_WINDOW,HEIGHT_OF_WINDOW);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.welcomePanel=new WelcomePanel();
        this.add(welcomePanel);
        while (!this.welcomePanel.isVisible()){
            try {
                Thread.sleep(31*1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        this.appPanel=new AppPanel();
        this.add(this.appPanel);

        this.setVisible(true);

    }

}
