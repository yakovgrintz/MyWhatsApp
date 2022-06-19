import javax.swing.*;
import java.awt.*;

public class AddMessageWindow extends JFrame {
    public final String PERSONAL_MESSAGE_NAME = " \\ NAME \\ ";
    public AddMessageWindow(ListOfConatants list) {
        this.setSize(300, 400);
        this.setResizable(false);
        this.setLayout(null);
        this.setLocationRelativeTo(null);

        JTextField fieldMessage = new JTextField();
        JLabel title = new JLabel("Please Enter Your Message");
        title.setBounds(25, 25, 250, 50);
        this.add(title);
        fieldMessage.setBounds(title.getX(), title.getY() + title.getHeight(), title.getWidth(), title.getHeight() * 2);
        fieldMessage.setBackground(Color.white);
        this.add(fieldMessage);
        JButton setMessage = new JButton("Set Message");
        setMessage.addActionListener((event) -> {

            try {
                setMessage(list, fieldMessage.getText());
            } catch (Exception e) {
                JOptionPane.showMessageDialog(new JFrame(),
                        e.getMessage(),
                        "error",
                        JOptionPane.PLAIN_MESSAGE);
            }

        });
        setMessage.setBounds(title.getX(), fieldMessage.getY() + fieldMessage.getHeight(), title.getWidth(), title.getHeight());
        this.add(setMessage);
        JCheckBox addNameToMessage= new JCheckBox("Add Name To Message");
        addNameToMessage.setBounds(setMessage.getX(), setMessage.getY()+ setMessage.getHeight(),setMessage.getWidth(),title.getHeight());
        addNameToMessage.addItemListener((event) -> {
            if (addNameToMessage.isSelected()) {
                fieldMessage.setText(fieldMessage.getText()+PERSONAL_MESSAGE_NAME);
            }
        });
        this.add(addNameToMessage);
        this.setVisible(true);

    }

    public static void main(String[] args) {
        AddMessageWindow test = new AddMessageWindow(new ListOfConatants());
    }

    private void setMessage(ListOfConatants list, String message) {
        if (message == null || message.equals("")) {
            throw new RuntimeException("please enter your message in textField");
        } else {
            for (int i = 0; i < list.size(); i++) {
                PhoneNumberIL temp = list.getConants(i);
                if (temp.getMessage() == ""||temp.getMessage()==null) {
                    String tempStr = message.replaceAll(PERSONAL_MESSAGE_NAME, temp.getName());
                    System.out.println(tempStr);
                    temp.setMessage(tempStr);
                }
            }
        }
    }

}
