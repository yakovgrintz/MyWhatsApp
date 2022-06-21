import javax.swing.*;
import java.awt.*;

public class AddListOfContact extends MyAddWindow {
    public final int REDUCTION=38, NUM_OF_ITEMS=3+1,START_X = 0, START_Y = 0, WIDTH = 150, HEIGHT =(this.getHeight()-REDUCTION)/NUM_OF_ITEMS;

    public AddListOfContact(ListOfConatants list) {
        super();
        JLabel pleaseEnterListOfPhoneNumber = new JLabel("<html>Please Enter List Of Phone Number" +
                "<br> Separated by a comma<html>");
        pleaseEnterListOfPhoneNumber.setBounds(START_X, START_Y, WIDTH_OF_ADD_WINDOW, HEIGHT);
        this.add(pleaseEnterListOfPhoneNumber);
        JTextArea numbersArea = new JTextArea();
        numbersArea.setBounds(START_X, pleaseEnterListOfPhoneNumber.getY() + HEIGHT, WIDTH_OF_ADD_WINDOW, HEIGHT * 2);
        numbersArea.setBackground(Color.white);
        numbersArea.setLineWrap(true);
        this.add(numbersArea);
        JButton addToList = new JButton("Add To List");
        addToList.setBounds(START_X, numbersArea.getY() + numbersArea.getHeight(), WIDTH_OF_ADD_WINDOW, HEIGHT);
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

    public static void main(String[] args) {
        AddListOfContact y = new AddListOfContact(new ListOfConatants());
    }


}