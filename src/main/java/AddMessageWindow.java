import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.util.concurrent.atomic.AtomicReference;

public class AddMessageWindow extends MyAddWindow {
    public final String PERSONAL_MESSAGE_NAME = "<Name>";
    public final int REDUCTION=38, NUM_OF_ITEMS=5,START_X = 0, START_Y = 0, WIDTH = this.getWidth(), HEIGHT =(this.getHeight()-REDUCTION)/NUM_OF_ITEMS ;


    public AddMessageWindow(ListOfConatants list) {
        super();
        JTextField fieldMessage = new JTextField();
        JLabel title = new JLabel("Please Enter Your Message");
        title.setBounds(START_X, START_Y, WIDTH, HEIGHT);
        this.add(title);
        fieldMessage.setBounds(START_X, title.getY() + HEIGHT, WIDTH, HEIGHT);
        fieldMessage.setBackground(Color.white);
        this.add(fieldMessage);
        AtomicReference<String> pathToImage = new AtomicReference<String>(null);
        JButton setMessage = new JButton("Set Message");
        setMessage.addActionListener((event) -> {

            try {
                setMessage(list, fieldMessage.getText(),pathToImage.get());
                dispose();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(new JFrame(),
                        e.getMessage(),
                        "error",
                        JOptionPane.PLAIN_MESSAGE);
            }

        });
        setMessage.setBounds(START_X, fieldMessage.getY() + HEIGHT, WIDTH, HEIGHT);
        this.add(setMessage);
        JButton addNameToMessage= new JButton("Add Name To Message");
        addNameToMessage.setBounds(START_X, setMessage.getY()+ HEIGHT,WIDTH,HEIGHT);
        addNameToMessage.addActionListener((event) -> {
                fieldMessage.setText(fieldMessage.getText()+PERSONAL_MESSAGE_NAME);

        });
        AddImageButton addImage = new AddImageButton(START_X, addNameToMessage.getY()+ HEIGHT, WIDTH, HEIGHT,pathToImage);
        
        this.add(addNameToMessage);
        this.add(addImage);
        this.setVisible(true);

    }


    private void setMessage(ListOfConatants list, String message,String pathToImage) {
        if (message == null || message.equals("")) {
            throw new RuntimeException("please enter your message in textField");
        } else {
            for (int i = 0; i < list.size(); i++) {
                PhoneNumberIL temp = list.getConants(i);
                if (temp.getMessage() == ""||temp.getMessage()==null) {

                    String tempStr = message.replaceAll(PERSONAL_MESSAGE_NAME, temp.getName());
                    temp.setMessage(tempStr);
                    temp.setPathToImage(pathToImage);
                }
            }
        }
    }

}
