import javax.swing.*;
import java.awt.*;

public class AddListOfContact extends JFrame implements MyApp {
    public AddListOfContact(ListOfConatants list) {
        this.setSize(WIDTH_OF_ADD_WINDOW, HEIGHT_OF_ADD_WINDOW);
        this.setResizable(false);
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        JLabel pleaseEnterListOfPhoneNumber = new JLabel("<html>Please Enter List Of Phone Number" +
                "<br> Separated by a comma<html>");
        pleaseEnterListOfPhoneNumber.setBounds(0, 0, WIDTH_OF_ADD_WINDOW, 50);
        this.add(pleaseEnterListOfPhoneNumber);
        JTextArea numbersArea = new JTextArea();
        numbersArea.setBounds(pleaseEnterListOfPhoneNumber.getX(), pleaseEnterListOfPhoneNumber.getY() + pleaseEnterListOfPhoneNumber.getHeight(), WIDTH_OF_ADD_WINDOW, pleaseEnterListOfPhoneNumber.getHeight() * 4);
        numbersArea.setBackground(Color.white);
        this.add(numbersArea);
        JButton addToList = new JButton("Add To List");
        addToList.setBounds(numbersArea.getX(), numbersArea.getY() + numbersArea.getHeight(), WIDTH_OF_ADD_WINDOW, pleaseEnterListOfPhoneNumber.getHeight());
        addToList.addActionListener((event)->{
            String listOfNumbers = numbersArea.getText();
            createContant(list,listOfNumbers);
            numbersArea.setText("");
        });
        this.add(addToList);
        this.setVisible(true);
    }
    private void createContant(ListOfConatants list,String str){
        String[] numbers = str.split(",");
        String name = "";
        PhoneNumberIL temp;
        for (String phoneNumber:numbers
             ) {
            try {
                temp = new PhoneNumberIL(phoneNumber, name);

                list.addPhoneNumberIL(temp);
            } catch (Exception e) {
                new FixNumber(list,phoneNumber);
            }

        }
        dispose();
    }


}