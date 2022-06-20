
import javax.swing.*;
import java.awt.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class EditNames extends MyAddWindow implements MyApp {
    public EditNames(ListOfConatants list) {
        super();
        JLabel number = new JLabel();
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
        this.setVisible(true);PhoneNumberIL temp;
        for (AtomicInteger i = new AtomicInteger(); i.get() < list.size(); i.getAndIncrement()) {
            temp = list.getConants(i.get());
            if (temp.getName()!=""){
                continue;
            }else{
            AtomicBoolean b = new AtomicBoolean(true);
            number.setText("<html> This number :<br>" + temp.getPhoneNumber() + "<br>Please Enter Name");
            PhoneNumberIL temp2 = list.getConants(i.get());
            set.addActionListener((event) -> {
                String newName = name.getText();
                if (newName == null) {
                    newName = "";
                }
                //list.getConants(i.get())
                       temp2 .setName(newName);
                name.setText("");
                b.set(false);
            });

            delete.addActionListener((enent) -> {
                list.remove(temp2);
                i.getAndDecrement();
                name.setText("");
                b.set(false);
            });
            while (b.get()){

            }}

        }
        dispose();


    }

    public static void main(String[] args) {
        ListOfConatants l = new ListOfConatants();
        l.addPhoneNumberIL(new PhoneNumberIL("0505151524", ""));
        l.addPhoneNumberIL(new PhoneNumberIL("0586010301", ""));
        l.addPhoneNumberIL(new PhoneNumberIL("0586010301",""));
        EditNames n = new EditNames(l);
        try {
            System.out.println(l.getConants(0).getName());
            System.out.println(l.getConants(1).getName());
            System.out.println(l.getConants(2).getName());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }

   /* private PhoneNumberIL getNextUnnamed(ListOfConatants list, AtomicInteger index) {
        PhoneNumberIL temp = null;
        while (index.get()!=list.size()){
            PhoneNumberIL temp2 = list.getConants(index.get());
            if (list.getConants(index.get()).getName()!= "" | list.getConants(index.get()).getName() != null) {
                index.getAndIncrement();
                continue;
            } else {
                //index.getAndIncrement();
                temp = list.getConants(index.get());
                break;
            }
        }
        return temp;
        for (int i = 0; i < list.size(); i++) {
            PhoneNumberIL temp = list.getConants(i);
            if (temp.getMessage() == ""||temp.getMessage()==null) {

                String tempStr = message.replaceAll(PERSONAL_MESSAGE_NAME, temp.getName());
                //System.out.println(tempStr);
                temp.setMessage(tempStr);
            }
    }

    private void setWindow(ListOfConatants list, AtomicInteger index, JLabel number) {
        PhoneNumberIL temp = getNextUnnamed(list, index);
        if (temp == null) {
            System.out.println("g");dispose();
        } else {

        }
    }*/
}
