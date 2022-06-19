import javax.swing.*;
import java.awt.*;
import java.util.concurrent.atomic.AtomicInteger;

public class FromTextToContact extends JPanel {
    public int WIDTH_OF_ITEM = 150, HEIGHT_OF_ITEM = 20, START_Y = 0, START_X = 20;

    public FromTextToContact(String[] numbers, ListOfConatants list) {
        if (numbers==null){
            throw new RuntimeException("you don't enter numbers");
        }
        this.setBounds(0, 0, 300, 400);
        this.setLayout(null);
        this.setBackground(Color.GRAY);
        AtomicInteger i = new AtomicInteger();
        i.set(0);

        JLabel pleaseEnterName = new JLabel("Please Enter Name To Number");
        JLabel pleaseEnterPhoneNumber = new JLabel("Phone Number :");
        pleaseEnterPhoneNumber.setBounds(START_X, START_Y, WIDTH_OF_ITEM, HEIGHT_OF_ITEM);
        pleaseEnterPhoneNumber.setBackground(Color.white);
        JTextField phoneField = new JTextField(numbers[i.get()]);
        phoneField.setBounds(START_X, pleaseEnterPhoneNumber.getY()+HEIGHT_OF_ITEM, WIDTH_OF_ITEM, HEIGHT_OF_ITEM);
        pleaseEnterName.setBounds(START_X, phoneField.getY()+HEIGHT_OF_ITEM, WIDTH_OF_ITEM, HEIGHT_OF_ITEM);
        pleaseEnterName.setBackground(Color.white);

        this.add(pleaseEnterName);
        this.add(pleaseEnterPhoneNumber);
        JTextField nameField = new JTextField();
        nameField.setBounds(START_X, pleaseEnterName.getY()+HEIGHT_OF_ITEM, WIDTH_OF_ITEM, HEIGHT_OF_ITEM);
        nameField.setBackground(Color.white);
        phoneField.setBackground(Color.white);
        this.add(nameField);
        this.add(phoneField);

        JCheckBox sendPrivatelyMessage = new JCheckBox("Send Specially Message");
        sendPrivatelyMessage.setBounds(START_X, nameField.getY()+HEIGHT_OF_ITEM, WIDTH_OF_ITEM, HEIGHT_OF_ITEM);
        JTextField field3 = new JTextField();
        field3.setBounds(START_X, sendPrivatelyMessage.getY()+HEIGHT_OF_ITEM, WIDTH_OF_ITEM, HEIGHT_OF_ITEM*2);
        field3.setBackground(Color.white);
        field3.setVisible(false);
        this.add(field3);
        sendPrivatelyMessage.addItemListener((event) -> {
            if (sendPrivatelyMessage.isSelected()) {
                field3.setVisible(true);
            } else if (!sendPrivatelyMessage.isSelected()) {
                field3.setVisible(false);
                field3.setText("");

            }
        });
        this.add(sendPrivatelyMessage);

        JButton addToList = new JButton("add to list");
        addToList.addActionListener((event) -> {
            String name = nameField.getText();
            String phoneNumber = phoneField.getText();
            PhoneNumberIL temp = null;
            try {
                temp = new PhoneNumberIL(phoneNumber, name);
                if (sendPrivatelyMessage.isSelected()) {
                    temp.setMessage(field3.getText());
                }
                sendPrivatelyMessage.setSelected(false);
                i.getAndIncrement();
                if (i.get()== numbers.length){
                    this.setVisible(false);
                }
                phoneField.setText(numbers[i.get()]);
            } catch (Exception e) {
                nameField.setText("");

                JOptionPane.showMessageDialog(new JFrame(),
                        e.getMessage(),
                        "error",
                        JOptionPane.PLAIN_MESSAGE);
            }
            list.addPhoneNumberIL(temp);
            nameField.setText("");
            phoneField.setText("");
        });
        addToList.setBounds(50, 245, 200, 50);
        this.add(addToList);
        this.setVisible(true);
    }


}
