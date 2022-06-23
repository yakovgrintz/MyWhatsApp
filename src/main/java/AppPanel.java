import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.io.FileOutputStream;
import java.util.concurrent.atomic.AtomicBoolean;

public class AppPanel extends JPanel implements MyApp {
    private ListOfConatants listOfConatants;
    private JTable table;
    private Object[][] rowData;
    private JScrollPane tp;
    public final int NUMBER_0F_BUTTONS = 7, START_X = 0, START_Y = 0, REDUCTION = 38;
    public final int WIDTH_OF_BUTTON = 150, X_VAL_OF_BUTTON = 850, HEIGHT_OF_BUTTON = (HEIGHT_OF_WINDOW - REDUCTION) / NUMBER_0F_BUTTONS;
    public final String[] COLUMN_NAMES = {"Name", "Phone Number", "Meesage", "Status", "Sent With WhatsApp", "Answer"};

    public AppPanel() {
        this.listOfConatants = new ListOfConatants();
        this.setBounds(START_X, START_Y, WIDTH_OF_WINDOW, HEIGHT_OF_WINDOW);
        this.setLayout(null);
        AtomicBoolean checkData = new AtomicBoolean(false);


        JButton addContant = new JButton("Add Contant");
        addContant.addActionListener((event) -> {
            new AddContactWindow(this.listOfConatants);
        });
        addContant.setBounds(X_VAL_OF_BUTTON, START_Y, WIDTH_OF_BUTTON, HEIGHT_OF_BUTTON);
        this.add(addContant);

        JButton sendToList = new JButton("Send To List");
        sendToList.setBounds(X_VAL_OF_BUTTON, addContant.getY() + HEIGHT_OF_BUTTON, WIDTH_OF_BUTTON, HEIGHT_OF_BUTTON);
        sendToList.addActionListener((event) -> {
            DRIVER.sendToList(listOfConatants);
            checkData.set(true);
        });
        JButton updateTable = new JButton("Update Table");
        updateTable.setBounds(X_VAL_OF_BUTTON, sendToList.getY() + HEIGHT_OF_BUTTON, WIDTH_OF_BUTTON, HEIGHT_OF_BUTTON);
        updateTable.addActionListener((event) -> {
            updateTable(checkData);
        });

        DefaultTableModel model = new DefaultTableModel(COLUMN_NAMES, 0);
        table = new JTable(model);
        table.setName("data table");
        table.setBackground(Color.white);
        tp = new JScrollPane(table);
        tp.setSize(WIDTH_OF_WINDOW - WIDTH_OF_BUTTON, HEIGHT_OF_WINDOW - REDUCTION);
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

        JButton exportReport = new JButton("Export Report");
        exportReport.setBounds(X_VAL_OF_BUTTON, setMessage.getY() + HEIGHT_OF_BUTTON, WIDTH_OF_BUTTON, HEIGHT_OF_BUTTON);
        exportReport.addActionListener((event) -> {
            try {
                exportToExcel(table);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        JButton addListContant = new JButton("Add List Of Contants");
        addListContant.setBounds(X_VAL_OF_BUTTON, exportReport.getY() + HEIGHT_OF_BUTTON, WIDTH_OF_BUTTON, HEIGHT_OF_BUTTON);
        addListContant.addActionListener((event) -> {
            new AddListOfContact(listOfConatants);
        });

        JButton editList = new JButton("Edit List");
        editList.setBounds(X_VAL_OF_BUTTON, addListContant.getY() + HEIGHT_OF_BUTTON, WIDTH_OF_BUTTON, HEIGHT_OF_BUTTON);
        editList.addActionListener((event) -> {
            try {
                new EditListWithTable(listOfConatants);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        });
        this.add(editList);
        this.add(exportReport);
        this.add(addListContant);
        this.setVisible(true);

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

    }


    public void updateTable(AtomicBoolean updateData) {
        new Thread(()->{
            while (true){
                try {
                    DRIVER.checkAnswerAndStatus(this.listOfConatants, updateData);

                    DefaultTableModel model = (DefaultTableModel) table.getModel();
                    model.setRowCount(0);
                    for (int i = 0; i < this.listOfConatants.size(); i++) {
                        PhoneNumberIL temp = this.listOfConatants.getConants(i);
                        Object[] newRow = {temp.getName(), temp.getPhoneNumber(), temp.getMessage(), temp.getStatus(), temp.isSent(), temp.getAnswer()};
                        model.addRow(newRow);
                    }
                    repaint();
                    Thread.sleep(30*MS);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

        }).start();

    }

    private void exportToExcel(JTable table) throws Exception {
        TableModel model = table.getModel();
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet();
        Row row;
        Cell cell;

        // write the column headers
        row = sheet.createRow(0);
        for (int c = 0; c < model.getColumnCount(); c++) {
            cell = row.createCell(c);
            cell.setCellValue(model.getColumnName(c));
        }

        for (int r = 0; r < model.getRowCount(); r++) {
            row = sheet.createRow(r + 1);
            for (int c = 0; c < model.getColumnCount(); c++) {
                cell = row.createCell(c);
                Object value = model.getValueAt(r, c);
                if (value instanceof String) {
                    cell.setCellValue((String) value);
                } else if (value instanceof Double) {
                    cell.setCellValue((Double) value);
                }
            }
        }
        String filePath = "./Report.xlsx";
        FileOutputStream out = new FileOutputStream(filePath);
        workbook.write(out);
        out.close();
        workbook.close();

    }
}
