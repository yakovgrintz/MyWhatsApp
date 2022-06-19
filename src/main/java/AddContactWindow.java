import javax.swing.*;
import java.awt.*;

public class AddContactWindow extends JFrame {
    public AddContactWindow(ListOfConatants list) {
        this.setSize(300, 400);
        this.setResizable(false);
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        JLabel pleaseEnterName = new JLabel("please enter name");
        JLabel pleaseEnterPhoneNumber = new JLabel("please enter phone number");
        pleaseEnterName.setBounds(20, 10, 150, 20);
        pleaseEnterName.setBackground(Color.white);
        pleaseEnterPhoneNumber.setBounds(20, 75, 150, 20);
        pleaseEnterPhoneNumber.setBackground(Color.white);

        this.add(pleaseEnterName);
        this.add(pleaseEnterPhoneNumber);
        JTextField field1 = new JTextField();
        JTextField field2 = new JTextField();
        field2.setBounds(50, 150, 150, 20);
        field1.setBounds(50, 50, 150, 20);
        field1.setBackground(Color.white);
        field2.setBackground(Color.white);
        this.add(field1);
        this.add(field2);
        JTextField field3 = new JTextField();
        field3.setBounds(50, 195, 150, 50);
        field3.setBackground(Color.white);
        field3.setVisible(false);
        this.add(field3);
        JCheckBox sendPrivatelyMessage = new JCheckBox("Send Specially Message");
        sendPrivatelyMessage.setBounds(10, 175, 290, 20);

        sendPrivatelyMessage.addItemListener((event) -> {
            if (sendPrivatelyMessage.isSelected()) {
                field3.setVisible(true);
            } else if (!sendPrivatelyMessage.isSelected()) {
                field3.setVisible(false);

            }
        });
        this.add(sendPrivatelyMessage);

        JButton addToList = new JButton("add to list");
        addToList.addActionListener((event) -> {
            String name = field1.getText();
            String phoneNumber = field2.getText();
            PhoneNumberIL temp = null;
            try {
                temp = new PhoneNumberIL(phoneNumber, name);
                if (sendPrivatelyMessage.isSelected()) {
                    temp.setMessage(field3.getText());
                }
            } catch (Exception e) {
                field1.setText("");
                field2.setText("");
                JOptionPane.showMessageDialog(new JFrame(),
                        e.getMessage(),
                        "error",
                        JOptionPane.PLAIN_MESSAGE);
            }
            list.addPhoneNumberIL(temp);
            field1.setText("");
            field2.setText("");
        });
        addToList.setBounds(50, 245, 200, 50);
        this.add(addToList);
        this.setVisible(true);

    }


    public static void main(String[] args) {
        AddContactWindow addContactWindow = new AddContactWindow(new ListOfConatants());
    }
}
