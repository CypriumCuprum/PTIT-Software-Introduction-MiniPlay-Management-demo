package Form;

import Entity.Client32;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.util.ArrayList;

public class SearchClientbyNameFrm32 extends JFrame {
    public static final int W_FRAME = 1080;
    public static final int H_FRAME = 720;
    private JPanel panel1;
    private JTextField searchField;
    private JButton searchButton;
    private JTable tableclient;
    private JScrollPane scrollPane;
    private JTextField textField;
    private DefaultTableModel tableModel;

    public SearchClientbyNameFrm32() {
        super("Search Client by Name");
        setLayout(null);
        setSize(W_FRAME, H_FRAME);
        setLocationRelativeTo(null);
        setLocation(100, 100);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        GUI();
    }

    private void GUI() {
        panel1 = new JPanel();
        panel1.setLayout(null);
        panel1.setBounds(0, 0, W_FRAME, H_FRAME);
        add(panel1);

        searchField = new JTextField();
        searchField.setBounds(50, 50, 200, 30);
        panel1.add(searchField);

        searchButton = new JButton("Search");
        searchButton.setBounds(300, 50, 100, 30);

        panel1.add(searchButton);


        tableModel = new DefaultTableModel(new Object[]{"ID", "Name", "Address", "Phone"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tableclient = new JTable(tableModel);
        scrollPane = new JScrollPane(tableclient);
        scrollPane.setBounds(50, 100, 800, 500);
        panel1.add(scrollPane);
    }

    public void addSearchListener(ActionListener listener) {
        searchButton.addActionListener(listener);
    }

    public void addTableListener(MouseAdapter listener) {
        tableclient.addMouseListener(listener);
    }

    public JTextField getSearchField() {
        return searchField;
    }


    public void setClientTable(ArrayList<Client32> clients) {
        tableModel.setRowCount(0);
        for (Client32 client : clients) {
            tableModel.addRow(new Object[]{client.getId(), client.getName(), client.getAddress(), client.getPhone()});
        }
    }

    public JTable getTableclient() {
        return tableclient;
    }

    public JButton getSearchButton() {
        return searchButton;
    }
}
