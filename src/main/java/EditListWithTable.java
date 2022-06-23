import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class EditListWithTable extends MyAddWindow {
    public final String[] COLUMN_NAMES = {"NAME", "PHONE_NUMBER"};
    public final int REDUCTION=38 ,NUM_OF_ITEMS=4,START_X = 0, START_Y = 0, WIDTH = this.getWidth(), HEIGHT =(this.getHeight()-REDUCTION)/NUM_OF_ITEMS;

    private JTable table;
    private JScrollPane tp;

    public EditListWithTable(ListOfConatants list) {
        super();
        JLabel instructions = new JLabel("<html>Edit the name or number by<br> changing the text in the table<br>" +
                "Delete - Delete the entries <br> in the relevant rows<br>By clicking Add to list - <br>all other data will be deleted (messages, etc.)<html>");
        instructions.setBounds(START_X,START_Y,WIDTH,HEIGHT);
        this.add(instructions);
        DefaultTableModel model = new DefaultTableModel(COLUMN_NAMES, 0);
        table = new JTable(model);
        table.setName("data table");
        table.setBackground(Color.white);
        tp = new JScrollPane(table);
        tp.setBounds(START_X,START_Y+HEIGHT,WIDTH, HEIGHT*2);
        tp.setVisible(true);
        this.add(tp);
        this.setVisible(true);
        updateTable(list);
        JButton editName = new JButton("Edit Name");
        editName.setBounds(START_X, tp.getY() + tp.getHeight(), WIDTH, HEIGHT);
        editName.addActionListener((event) -> {
            getAndSetData(list);
        });


        this.add(editName);
        this.setVisible(true);

    }

    private void updateTable(ListOfConatants list) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
        for (int i = 0; i < list.size(); i++) {
            PhoneNumberIL temp = list.getConants(i);
            Object[] newRow = {temp.getName(), temp.getPhoneNumber()};
            model.addRow(newRow);

        }
        repaint();
    }

    private void getAndSetData(ListOfConatants list) {
        list.getList().clear();
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        for (int i = 0; i < model.getRowCount(); i++) {
            String name = model.getValueAt(i, 0).toString();
            if (name == null) {
                name = "";
            }
            String phoneNumber = model.getValueAt(i, 1).toString();
            if (phoneNumber==null){
                phoneNumber="";
            }
            if (name==""&&phoneNumber==""){
                continue;
            }
            try {
                PhoneNumberIL temp = new PhoneNumberIL(phoneNumber, name);
                list.addPhoneNumberIL(temp);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

        }
        dispose();
    }


}
