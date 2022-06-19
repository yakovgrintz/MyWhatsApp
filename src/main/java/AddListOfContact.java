import javax.swing.*;
import java.awt.*;

public class AddListOfContact extends JFrame {
    public AddListOfContact(ListOfConatants list){
        this.setSize(300, 400);
        this.setResizable(false);
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        JLabel pleaseEnterPhoneNumber = new JLabel("Please enter the comma-separated phone numbers");
        pleaseEnterPhoneNumber.setBounds(20, 10, 150, 20);
        pleaseEnterPhoneNumber.setBackground(Color.white);
        JTextField textArea = new JTextField();
        textArea.setBackground(Color.white);
        textArea.setBounds(0,pleaseEnterPhoneNumber.getY()+pleaseEnterPhoneNumber.getHeight(),this.getWidth(),300);



        JButton addToList = new JButton("add to list");
        addToList.addActionListener((event) -> {
            try {
                String[] numbers =textArea.getText().split(",");
                this.add(new FromTextToContact(numbers,list));
            }catch (Exception e){
                JOptionPane.showMessageDialog(new JFrame(),
                        e.getMessage(),
                        "error",
                        JOptionPane.PLAIN_MESSAGE);
            }



        });
        addToList.setBounds(50, textArea.getY()+ textArea.getHeight(), 200, 50);
        this.add(pleaseEnterPhoneNumber);
        this.add(textArea);
        this.add(addToList);
        this.setVisible(true);

    }

    public static void main(String[] args) {
        AddListOfContact n = new AddListOfContact(new ListOfConatants());
    }
}
