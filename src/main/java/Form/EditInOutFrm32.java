// EditInOutFrm32.java
package Form;

import Entity.Payment1day32;
import Entity.UsedGood32;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class EditInOutFrm32 extends JFrame {
    private static final int W_FRAME = 800; // updated width
    private static final int H_FRAME = 400; // updated height
    private JLabel formatField, checkinLabel, checkoutLabel, rentpaymentLabel, goodpaymentLabel;
    private JTextField checkInField;
    private JTextField checkOutField;
    private JTextField rentPaymentField;
    private JButton addGoodButton;
    private JButton submitButton;
    private JPanel panel1;

    private DefaultTableModel tableModel;
    private JTable goodtable;
    private JScrollPane goodScrollPane;
    private ArrayList<UsedGood32> usedGoods;
    private Payment1day32 payment1day32;

    public EditInOutFrm32(Payment1day32 payment1day) {
        super("Edit In Out");
        setSize(W_FRAME, H_FRAME);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        setLocation(100, 100);
        usedGoods = new ArrayList<>();
        this.payment1day32 = payment1day;
        GUI();

    }

    private void GUI() {
        panel1 = new JPanel();
        panel1.setLayout(null);
        panel1.setBounds(0, 0, W_FRAME, H_FRAME);
        add(panel1);
        // In Out Time
        formatField = new JLabel("Format: YYYY-MM-DD");
        formatField.setBounds(0, 0, W_FRAME, 20);

        checkinLabel = new JLabel("Check In");
        checkinLabel.setBounds(0, 20, 100, 20);

        checkoutLabel = new JLabel("Check Out");
        checkoutLabel.setBounds(0, 40, 100, 20);

        checkInField = new JTextField(payment1day32.getBeginTime1day().toString());
        checkInField.setBounds(100, 20, 100, 20);

        checkOutField = new JTextField(payment1day32.getEndTime1day().toString());
        checkOutField.setBounds(100, 40, 100, 20);

        rentpaymentLabel = new JLabel("Rent Payment");
        rentpaymentLabel.setBounds(0, 60, 100, 20);

        rentPaymentField = new JTextField(payment1day32.getRentPayment() + "");
        rentPaymentField.setBounds(100, 60, 100, 20);

        addGoodButton = new JButton("Add Good");
        addGoodButton.setBounds(100, 80, 100, 20);

        tableModel = new DefaultTableModel(new Object[]{"Name", "Price", "Num"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        goodtable = new JTable(tableModel);
        goodScrollPane = new JScrollPane(goodtable);
        goodScrollPane.setBounds(100, 100, 600, 200);

        goodpaymentLabel = new JLabel("Good Payment" + payment1day32.getGoodPayment());
        goodpaymentLabel.setBounds(0, 300, 200, 20);

        submitButton = new JButton("Submit");
        submitButton.setBounds(100, 320, 100, 20);


        panel1.add(formatField);
        panel1.add(checkinLabel);
        panel1.add(checkoutLabel);
        panel1.add(checkInField);
        panel1.add(checkOutField);
        panel1.add(rentpaymentLabel);
        panel1.add(addGoodButton);
        panel1.add(goodpaymentLabel);
        panel1.add(submitButton);
        panel1.add(goodScrollPane);
        panel1.add(rentPaymentField);
    }

    public void addSubmitListener(ActionListener listener) {
        submitButton.addActionListener(listener);
    }

    public void addAddGoodButtonListener(ActionListener listener) {
        addGoodButton.addActionListener(listener);
    }

    public void addGoodToTable(UsedGood32 usedGood32) {
        this.usedGoods.add(usedGood32);
        tableModel.addRow(new Object[]{usedGood32.getGood32().getName(), usedGood32.getUnitprice(), usedGood32.getNum()});
        setGoodPayment(usedGood32.getUnitprice() * usedGood32.getNum());
    }

    private void setRentPayment(float rentPayment) {
        rentPaymentField.setText(rentPayment + "");
    }

    private void setGoodPayment(float goodPayment) {
        payment1day32.setGoodPayment(goodPayment);
        goodpaymentLabel.setText("Good Payment: " + payment1day32.getGoodPayment());
    }

    public void setPayment1day32(Payment1day32 payment1day32) {
        this.payment1day32 = payment1day32;
        checkInField.setText(payment1day32.getBeginTime1day().toString());
        checkOutField.setText(payment1day32.getEndTime1day().toString());
        setRentPayment(payment1day32.getRentPayment());
    }

    public JButton getSubmitButton() {
        return submitButton;
    }

    public JButton getAddGoodButton() {
        return addGoodButton;
    }

    public ArrayList<UsedGood32> getUsedGoods() {
        return usedGoods;
    }
}