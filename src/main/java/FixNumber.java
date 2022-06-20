import javax.swing.*;

public class FixNumber extends JFrame implements MyApp {
    public FixNumber(ListOfConatants list,String phoneNumber){
        this.setSize(WIDTH_OF_ADD_WINDOW/2, HEIGHT_OF_ADD_WINDOW/2);
        this.setResizable(false);
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        JLabel pleaseFixNumber = new JLabel("Please Fix Number");
        pleaseFixNumber.setBounds(0, 0, this.getWidth(), 50);
        this.add(pleaseFixNumber);
        JTextField number = new JTextField(phoneNumber);
        number.setBounds(pleaseFixNumber.getX(),pleaseFixNumber.getY()+pleaseFixNumber.getHeight(),this.getWidth(),pleaseFixNumber.getHeight());
        this.add(number);
        JButton fix = new JButton("Fix");
        fix.setBounds(number.getX(),number.getY()+number.getHeight(),this.getWidth()/2,number.getHeight());
        fix.addActionListener((event)->{
            PhoneNumberIL temp;
            String name = "";
            try {
                String newNumber=number.getText();
                temp = new PhoneNumberIL(newNumber, name);
                list.addPhoneNumberIL(temp);
                this.dispose();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(new JFrame(),
                        e.getMessage(),
                        "error",
                        JOptionPane.PLAIN_MESSAGE);
            }
        });
        JButton delete = new JButton("delete");
        delete.setBounds(fix.getX()+fix.getWidth(),fix.getY(), fix.getWidth(), fix.getHeight());
        delete.addActionListener((enent)->{
            dispose();
        });
        this.add(delete);
        this.add(fix);
        this.setVisible(true);
    }
}
