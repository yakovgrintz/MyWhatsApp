import javax.swing.*;

public class FixNumber extends MyAddWindow implements MyApp {
    public final int REDUCTION=38,NUM_OF_ITEMS=3,START_X = 0, START_Y = 0, WIDTH = this.getWidth(), HEIGHT =(this.getHeight()-REDUCTION)/NUM_OF_ITEMS ;

    public FixNumber(ListOfConatants list,String phoneNumber){
        super(WIDTH_OF_ADD_WINDOW/2,HEIGHT_OF_ADD_WINDOW/2);

        JLabel pleaseFixNumber = new JLabel("Please Fix Number");
        pleaseFixNumber.setBounds(START_X, START_Y, WIDTH, HEIGHT);
        this.add(pleaseFixNumber);

        JTextField number = new JTextField(phoneNumber);
        number.setBounds(START_X,pleaseFixNumber.getY()+HEIGHT,WIDTH,HEIGHT);
        this.add(number);

        JButton fix = new JButton("Fix");
        fix.setBounds(START_X,number.getY()+HEIGHT,WIDTH/2,HEIGHT);
        JButton delete = new JButton("delete");
        delete.setBounds(fix.getX()+fix.getWidth(),fix.getY(), fix.getWidth(), HEIGHT);

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

        delete.addActionListener((enent)->{
            dispose();
        });
        this.add(delete);
        this.add(fix);
        this.setVisible(true);
    }


}
