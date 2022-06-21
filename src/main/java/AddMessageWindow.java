import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.util.concurrent.atomic.AtomicReference;

public class AddMessageWindow extends JFrame {
    public final String PERSONAL_MESSAGE_NAME = "<Name>";

    public AddMessageWindow(ListOfConatants list) {
        this.setSize(300, 400);
        this.setResizable(false);
        this.setLayout(null);
        this.setLocationRelativeTo(null);

        JTextField fieldMessage = new JTextField();
        JLabel title = new JLabel("Please Enter Your Message");
        title.setBounds(25, 25, 250, 50);
        this.add(title);
        fieldMessage.setBounds(title.getX(), title.getY() + title.getHeight(), title.getWidth(), title.getHeight() * 2);
        fieldMessage.setBackground(Color.white);
        this.add(fieldMessage);
        AtomicReference<String> pathToImage = new AtomicReference<String>(null);
        JButton setMessage = new JButton("Set Message");
        setMessage.addActionListener((event) -> {

            try {
                setMessage(list, fieldMessage.getText(),pathToImage.get());
            } catch (Exception e) {
                JOptionPane.showMessageDialog(new JFrame(),
                        e.getMessage(),
                        "error",
                        JOptionPane.PLAIN_MESSAGE);
            }

        });
        setMessage.setBounds(title.getX(), fieldMessage.getY() + fieldMessage.getHeight(), title.getWidth(), title.getHeight());
        this.add(setMessage);
        JButton addNameToMessage= new JButton("Add Name To Message");
        addNameToMessage.setBounds(setMessage.getX(), setMessage.getY()+ setMessage.getHeight(),setMessage.getWidth(),title.getHeight());
        addNameToMessage.addActionListener((event) -> {
                fieldMessage.setText(fieldMessage.getText()+PERSONAL_MESSAGE_NAME);

        });
        JButton addImage = new JButton("Add Image");
        addImage.setBounds(addNameToMessage.getX(), addNameToMessage.getY()+ addNameToMessage.getHeight(), addNameToMessage.getWidth(), addNameToMessage.getHeight());
        addImage.addActionListener((event)->{
            JFileChooser j = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());

            // invoke the showsOpenDialog function to show the save dialog
            int r = j.showOpenDialog(null);

            // if the user selects a file
            if (r == JFileChooser.APPROVE_OPTION)

            {
                // set the label to the path of the selected file
                pathToImage.set(j.getSelectedFile().getAbsolutePath());
                System.out.println(pathToImage.get());
                System.out.println(pathToImage.toString());
            }


        });
        this.add(addNameToMessage);
        this.add(addImage);
        this.setVisible(true);

    }

    public static void main(String[] args) {
        AddMessageWindow test = new AddMessageWindow(new ListOfConatants());
    }

    private void setMessage(ListOfConatants list, String message,String pathToImage) {
        if (message == null || message.equals("")) {
            throw new RuntimeException("please enter your message in textField");
        } else {
            for (int i = 0; i < list.size(); i++) {
                PhoneNumberIL temp = list.getConants(i);
                if (temp.getMessage() == ""||temp.getMessage()==null) {

                    String tempStr = message.replaceAll(PERSONAL_MESSAGE_NAME, temp.getName());
                    //System.out.println(tempStr);
                    temp.setMessage(tempStr);
                    temp.setPathToImage(pathToImage);
                }
            }
        }
    }

}
