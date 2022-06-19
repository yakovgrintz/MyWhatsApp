import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class AppPanel extends JPanel implements MyApp {
    private ListOfConatants listOfConatants;
    private JTable table;
    private Object[][] rowData;
    private JScrollPane tp;
    public final int WIDTH_OF_BUTTON = 150, X_VAL_OF_BUTTON = 850, HEIGHT_OF_BUTTON = 100;
    public final String[] COLUMN_NAMES = {"Name", "Phone Number", "Meesage", "Status", "Sent With WhatsApp","Answer"};

    public AppPanel() {
        //this.setVisible(false);

        this.listOfConatants = new ListOfConatants();
        this.setBounds(0, 0, WIDTH_OF_WINDOW, HEIGHT_OF_WINDOW);
        this.setBackground(Color.GREEN);
        this.setLayout(null);

        JButton addContant = new JButton("Add Contant");
        addContant.addActionListener((event) -> {
            new AddContactWindow(this.listOfConatants);
        });
        addContant.setBounds(X_VAL_OF_BUTTON, 0, WIDTH_OF_BUTTON, HEIGHT_OF_BUTTON);
        this.add(addContant);
        JButton sendToList = new JButton("Send To List");
        sendToList.setBounds(X_VAL_OF_BUTTON, addContant.getY() + HEIGHT_OF_BUTTON, WIDTH_OF_BUTTON, HEIGHT_OF_BUTTON);
        sendToList.addActionListener((event) -> {
            DRIVER.sendToList(listOfConatants);
            updateData();

        });
        JButton updateTable = new JButton("Update Table");
        updateTable.setBounds(X_VAL_OF_BUTTON, sendToList.getY() + HEIGHT_OF_BUTTON, WIDTH_OF_BUTTON, HEIGHT_OF_BUTTON);
        updateTable.addActionListener((event) -> {
            updateTable();
        });
        DefaultTableModel model = new DefaultTableModel(COLUMN_NAMES, 0);
        table = new JTable(model);
        table.setName("data table");
        table.setBackground(Color.white);
        tp = new JScrollPane(table);
        tp.setSize(WIDTH_OF_WINDOW-WIDTH_OF_BUTTON, HEIGHT_OF_WINDOW);
        tp.setVisible(true);
        this.add(tp);
        this.add(addContant);
        this.add(updateTable);
        this.add(sendToList);
        JButton setMessage = new JButton("Set Message");
        setMessage.setBounds(X_VAL_OF_BUTTON, updateTable.getY() + HEIGHT_OF_BUTTON, WIDTH_OF_BUTTON, HEIGHT_OF_BUTTON);
        setMessage.addActionListener((event) -> {
            new AddMessageWindow(this.listOfConatants);
        });
        this.add(setMessage);
        this.setVisible(true);

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

    }


    private void updateTable() {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
        for (int i = 0; i < this.listOfConatants.size(); i++) {
            PhoneNumberIL temp = this.listOfConatants.getConants(i);
            Object[] newRow = {temp.getName(), temp.getPhoneNumber(), temp.getMessage(), temp.getStatus(), temp.isSent(),temp.getAnswer()};
            model.addRow(newRow);
        }
        repaint();
    }
    private void updateData(){
        new Thread(()->{
           while (true){
               try {
                   DRIVER.checkAnswerAndStatus(this.listOfConatants);
                   Thread.sleep(10*1000);
               } catch (InterruptedException e) {
                   throw new RuntimeException(e);
               }
           }
        }).start();
    }
}
