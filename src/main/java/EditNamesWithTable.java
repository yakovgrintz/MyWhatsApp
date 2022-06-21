import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class EditNamesWithTable extends MyAddWindow{
    public final String[] COLUMN_NAMES = {"NAME", "PHONE_NUMBER"};
    private JTable table;
    private JScrollPane tp;

    public EditNamesWithTable(ListOfConatants list){
        super();
        DefaultTableModel model = new DefaultTableModel(COLUMN_NAMES, 0);
        table = new JTable(model);
        table.setName("data table");
        table.setBackground(Color.white);
        tp = new JScrollPane(table);
        tp.setSize(this.getWidth(), HEIGHT_OF_ADD_WINDOW/4*3);
        tp.setVisible(true);
        this.add(tp);
        this.setVisible(true);
        updateTable(list);
        JButton editName = new JButton("Edit Name");
        editName.setBounds(tp.getX(),tp.getY()+tp.getHeight(), tp.getWidth(), (HEIGHT_OF_ADD_WINDOW-tp.getHeight())-10);
        editName.addActionListener((event)->{
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
    private void getAndSetData(ListOfConatants list){
        list.getList().clear();
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        for (int i = 0; i < model.getRowCount(); i++) {
            String name = model.getValueAt(i,0).toString();
            if (name==null){
                name="";
            }
            String phoneNumber = model.getValueAt(i,1).toString();
            try {
                PhoneNumberIL temp = new PhoneNumberIL(phoneNumber,name);
                list.addPhoneNumberIL(temp);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

        }
    }

    public static void main(String[] args) {
        ListOfConatants l = new ListOfConatants();
        l.addPhoneNumberIL(new PhoneNumberIL("0505151524", "yakov"));
        l.addPhoneNumberIL(new PhoneNumberIL("0586010301", ""));
        l.addPhoneNumberIL(new PhoneNumberIL("0586010301",""));
        EditNamesWithTable e = new EditNamesWithTable(l);
    }
}
