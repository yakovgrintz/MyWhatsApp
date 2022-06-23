import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.concurrent.atomic.AtomicReference;

public class AddContactWindow extends MyAddWindow {
    public final int REDUCTION = 38, NUM_OF_ITEMS = 8, START_X = 20, START_Y = 10, WIDTH = 150, HEIGHT = (this.getHeight() - REDUCTION) / NUM_OF_ITEMS;

    public AddContactWindow(ListOfConatants list) {
        super();
        JLabel pleaseEnterName = new JLabel("please enter name");
        JLabel pleaseEnterPhoneNumber = new JLabel("please enter phone number");
        pleaseEnterName.setBounds(START_X, START_Y, WIDTH, HEIGHT);
        pleaseEnterName.setBackground(Color.white);
        JTextField field1 = new JTextField();
        field1.setBounds(START_X, pleaseEnterName.getY() + HEIGHT, WIDTH, HEIGHT);
        field1.setBackground(Color.white);
        pleaseEnterPhoneNumber.setBounds(START_X, field1.getY() + HEIGHT, WIDTH, HEIGHT);
        pleaseEnterPhoneNumber.setBackground(Color.white);
        JTextField field2 = new JTextField();
        field2.setBounds(START_X, pleaseEnterPhoneNumber.getY() + HEIGHT, WIDTH, HEIGHT);
        field2.setBackground(Color.white);
        AtomicReference<String> pathToImage = new AtomicReference<String>(null);
        this.add(pleaseEnterName);
        this.add(pleaseEnterPhoneNumber);
        this.add(field1);
        this.add(field2);
        JCheckBox sendPrivatelyMessage = new JCheckBox("Send Specially Message");
        sendPrivatelyMessage.setBounds(START_X, field2.getY() + HEIGHT, WIDTH, HEIGHT);
        JTextField field3 = new JTextField();
        field3.setBounds(START_X, sendPrivatelyMessage.getY() + HEIGHT, WIDTH, HEIGHT);
        field3.setBackground(Color.white);
        field3.setVisible(false);
        this.add(field3);
        JButton addToList = new JButton("add to list");
        addToList.setBounds(START_X, field3.getY() + HEIGHT, WIDTH, HEIGHT);
        AddImageButton addImageButton = new AddImageButton(START_X, addToList.getY() + HEIGHT, WIDTH, HEIGHT, pathToImage);

        addToList.addActionListener((event) -> {
            String name = field1.getText();
            String phoneNumber = field2.getText();
            PhoneNumberIL temp = null;
            try {
                temp = new PhoneNumberIL(phoneNumber, name);
                if (sendPrivatelyMessage.isSelected()) {
                    temp.setMessage(field3.getText());
                    if (new File(pathToImage.toString()).exists()) {
                        temp.setPathToImage(pathToImage.toString());
                    }
                    sendPrivatelyMessage.setSelected(false);
                    pathToImage.set(null);
                }
                list.addPhoneNumberIL(temp);
            } catch (Exception e) {
                sendPrivatelyMessage.setSelected(false);
                clearFields(field1, field2, field3);
                JOptionPane.showMessageDialog(new JFrame(),
                        e.getMessage(),
                        "error",
                        JOptionPane.PLAIN_MESSAGE);
            }

            clearFields(field1, field2, field3);
        });

        addImageButton.setVisible(false);
        sendPrivatelyMessage.addItemListener((event) -> {
            if (sendPrivatelyMessage.isSelected()) {
                field3.setVisible(true);
                addImageButton.setVisible(true);
            } else if (!sendPrivatelyMessage.isSelected()) {
                field3.setVisible(false);
                addImageButton.setVisible(false);

            }
        });
        this.add(sendPrivatelyMessage);
        this.add(addImageButton);


        this.add(addToList);
        this.setVisible(true);

    }


    public static void main(String[] args) {
        AddContactWindow addContactWindow = new AddContactWindow(new ListOfConatants());
    }

    private void clearFields(JTextField field1, JTextField field2, JTextField field3) {
        field1.setText("");
        field2.setText("");
        field3.setText("");

    }
}
