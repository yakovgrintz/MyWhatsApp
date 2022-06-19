import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class AppPanel extends JPanel implements MyApp {
    private ListOfConatants listOfConatants;
    private JTable table;
    private Object[][] rowData;
    private JScrollPane tp;
    public final String[] COLUMN_NAMES = {"Name","Phone Number","Meesage","Status","Sent With WhatsApp"};

    public AppPanel() {
        //this.setVisible(false);
        this.listOfConatants = new ListOfConatants();
        this.setBounds(0, 0, WIDTH_OF_WINDOW, HEIGHT_OF_WINDOW);
        this.setBackground(Color.GREEN);
        this.setLayout(null);

        JButton addContant = new JButton("Add Contant");
        addContant.addActionListener((event) -> {
            new AddContactWindow(listOfConatants);
        });
        addContant.setBounds(850, 10, 150, 90);
        this.add(addContant);
        JButton sendToList = new JButton("Send To List");
        sendToList.setBounds(850, 100, 150, 90);
        sendToList.addActionListener((event) -> {
            DRIVER.sendToList(listOfConatants);
        });
        JButton updateTable=new JButton("Update Table");
        updateTable.setBounds(850, 190, 150, 90);
        updateTable.addActionListener((event)->{
            updateTable();
        });
        DefaultTableModel model = new DefaultTableModel(COLUMN_NAMES,0);
        table = new JTable(model);
        table.setName("data table");
        table.setBackground(Color.white);
        tp = new JScrollPane(table);
        tp.setSize(850, 500);
        tp.setVisible(true);
        this.add(tp);
        this.add(addContant);
        this.add(updateTable);
        this.add(sendToList);
        JButton setMessage = new JButton("Set Message");
        setMessage.setBounds(updateTable.getX(), updateTable.getY()+ updateTable.getHeight(), updateTable.getWidth(), updateTable.getHeight());
        setMessage.addActionListener((event)->{
            new AddMessageWindow(listOfConatants);
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
        for (int i = 0; i < listOfConatants.size(); i++) {
            PhoneNumberIL temp = listOfConatants.getConants(i);
            Object[] newRow = {temp.getName(), temp.getPhoneNumber(), temp.getMessage(), null, temp.isSent()};
            model.addRow(newRow);
        }
        repaint();
    }
}
