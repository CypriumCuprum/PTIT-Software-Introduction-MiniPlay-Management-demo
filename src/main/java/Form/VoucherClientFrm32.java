package Form;

import Entity.OrderForm32;

import javax.swing.*;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class VoucherClientFrm32 extends JFrame {
    private static final int W_FRAME = 1080;
    private static final int H_FRAME = 720;

    private JLabel nameLabel;
    private JLabel IdLabel;
    private JTable orderFormTable;
    private DefaultTableModel tableModel;

    private JScrollPane scrollPane;

    public VoucherClientFrm32() {
        super("Voucher Client");
        setLayout(new BorderLayout());
        setSize(W_FRAME, H_FRAME);
        setLocationRelativeTo(null);
        setLocation(100, 100);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        GUI();
    }

    private void GUI() {
        nameLabel = new JLabel("Client Name: ");
        nameLabel.setBounds(0, 0, 500, 20);
        IdLabel = new JLabel("Client ID: ");
        IdLabel.setBounds(500, 0, 100, 20);

        JPanel panel1 = new JPanel();
        panel1.setLayout(null);
        panel1.setBounds(0, 0, W_FRAME, H_FRAME);
        panel1.add(IdLabel);
        panel1.add(nameLabel);

        add(panel1);

        tableModel = new DefaultTableModel(new Object[]{"Select", "Order ID", "Begin Time", "End Time"}, 0) {
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                if (columnIndex == 0) {
                    return Boolean.class; // The class of the column in the model
                }
                return super.getColumnClass(columnIndex);
            }

            @Override
            public boolean isCellEditable(int row, int column) {
                Boolean isChecked = (Boolean) getValueAt(row, 0);
                if (isChecked != null && isChecked) {
                    return false;
                }
                return column == 0;
            }
        };
//        tableModel.addColumn("Select");
//        tableModel.addColumn("Order ID");
//        tableModel.addColumn("Begin Time");
//        tableModel.addColumn("End Time");

        orderFormTable = new JTable(tableModel);
//        orderFormTable.setBounds(0, 20, W_FRAME, H_FRAME - 20);
        scrollPane = new JScrollPane(orderFormTable);
        scrollPane.setBounds(0, 50, W_FRAME, H_FRAME - 20);
        panel1.add(scrollPane);
    }

    public void setOrderForms(ArrayList<OrderForm32> orderForms) {
        tableModel.setRowCount(0);
        for (OrderForm32 orderForm : orderForms) {
            tableModel.addRow(new Object[]{false, orderForm.getOrderId(), orderForm.getBeginTime(), orderForm.getEndTime()});
        }
    }

    public void setIdLabel(int id) {
        this.IdLabel.setText("Client ID: " + id);
    }

    public void setNameLabel(String nameLabel) {
        this.nameLabel.setText("Client name: " + nameLabel);
    }

    public void addTableListener(TableModelListener tableModelListener) {
        tableModel.addTableModelListener(tableModelListener);
    }


    public JTable getorderFormTable() {
        return orderFormTable;
    }

    public DefaultTableModel getTableModel() {
        return tableModel;
    }
}