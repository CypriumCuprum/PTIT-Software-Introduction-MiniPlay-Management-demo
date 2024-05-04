package Form;

import Entity.Good32;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.util.ArrayList;

public class SearchGoodbyNameFrm32 extends JFrame {
    public static final int W_FRAME = 1080;
    public static final int H_FRAME = 720;
    private JPanel panel1;
    private JTextField searchField;
    private JButton searchButton;
    private JTable tablegood;
    private JScrollPane scrollPane;
    private JTextField textField;
    private DefaultTableModel tableModel;
    private ArrayList<Good32> goodstable;

    public SearchGoodbyNameFrm32() {
        super("Search Good by Name");
        setLayout(null);
        setSize(W_FRAME, H_FRAME);
        setLocationRelativeTo(null);
        setLocation(100, 100);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
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


        tableModel = new DefaultTableModel(new Object[]{"ID", "Name"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tablegood = new JTable(tableModel);
        scrollPane = new JScrollPane(tablegood);
        scrollPane.setBounds(50, 100, 800, 500);
        panel1.add(scrollPane);
    }

    public void addSearchListener(ActionListener listener) {
        searchButton.addActionListener(listener);
    }

    public void addTableListener(MouseAdapter listener) {
        tablegood.addMouseListener(listener);
    }

    public JTextField getSearchField() {
        return searchField;
    }

    public JTable getTableclient() {
        return tablegood;
    }

    public void setGoodTable(ArrayList<Good32> goods) {
        goodstable = goods;
        tableModel.setRowCount(0);
        for (Good32 good : goodstable) {
            tableModel.addRow(new Object[]{good.getId(), good.getName()});
        }
    }

    public JButton getSearchButton() {
        return searchButton;
    }

    public JTable getTablegood() {
        return tablegood;
    }

    public ArrayList<Good32> getGoodstable() {
        return goodstable;
    }
}
