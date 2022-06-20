import javax.swing.*;
import java.awt.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class EditName extends JPanel implements MyApp {
    public EditName(ListOfConatants list, int index) {
        this.setBounds(0, 0, WIDTH_OF_ADD_WINDOW, HEIGHT_OF_ADD_WINDOW);
        this.setLayout(null);
        PhoneNumberIL temp = list.getConants(index);
        JLabel number = new JLabel("<html> This number :<br>" + temp.getPhoneNumber() + "<br>Please Enter Name");
        number.setBounds(0, 0, this.getWidth(), this.getHeight() / 4);
        JTextField name = new JTextField();
        name.setBounds(number.getX(), number.getY() + number.getHeight(), number.getWidth(), number.getHeight());
        name.setBackground(Color.white);
        JButton set = new JButton("Set");
        set.setBounds(name.getX(), name.getY() + name.getHeight(), name.getWidth() / 2, name.getHeight());
        JButton delete = new JButton("delete");
        delete.setBounds(set.getX() + set.getWidth(), set.getY(), set.getWidth(), set.getHeight());
        this.add(number);
        this.add(name);
        this.add(set);
        this.add(delete);
        set.addActionListener((event) -> {

            String newName = name.getText();
            if (newName == null) {
                newName = "";
            }
            temp.setName(newName);
        });

        delete.addActionListener((enent) -> {
            list.remove(temp);
        });
    }
}



