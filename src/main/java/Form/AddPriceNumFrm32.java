// AddPriceNumFrm32.java
package Form;

import Entity.Good32;

import javax.swing.*;
import java.awt.event.ActionListener;

public class AddPriceNumFrm32 extends JFrame {
    private static final int W_FRAME = 400;
    private static final int H_FRAME = 600;
    private JTextField priceField;
    private JTextField numField;
    private JButton submitButton;
    private JPanel panel1;
    private JLabel priceLabel;
    private JLabel numLabel;
    private JLabel goodNameLabel;
    private Good32 good32;

    public AddPriceNumFrm32() {
        super("Add Price Number");
        setSize(W_FRAME, H_FRAME);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        setLocation(100, 100);
        GUI();
    }

    private void GUI() {
        panel1 = new JPanel();
        panel1.setLayout(null);
        panel1.setBounds(0, 0, W_FRAME, H_FRAME);
        add(panel1);

        goodNameLabel = new JLabel("Good Name: ");
        goodNameLabel.setBounds(0, 0, 400, 20);

        priceLabel = new JLabel("Price");
        priceLabel.setBounds(0, 40, 400, 20);

        priceField = new JTextField("0");
        priceField.setBounds(0, 60, 400, 20);

        numLabel = new JLabel("Num");
        numLabel.setBounds(0, 80, 400, 20);

        numField = new JTextField("1");
        numField.setBounds(0, 100, 400, 20);

        submitButton = new JButton("Submit");
        submitButton.setBounds(100, 120, 100, 20);

        panel1.add(goodNameLabel);
        panel1.add(priceLabel);
        panel1.add(priceField);
        panel1.add(numLabel);
        panel1.add(numField);
        panel1.add(submitButton);
    }

    public void addSubmitListener(ActionListener listener) {
        submitButton.addActionListener(listener);
    }

    public JButton getSubmitButton() {
        return submitButton;
    }

    public void setGood(Good32 good) {
        this.good32 = good;
        goodNameLabel.setText("Good Name: " + good.getName());
    }

    public JTextField getNumField() {
        return numField;
    }

    public JTextField getPriceField() {
        return priceField;
    }

    public Good32 getGood32() {
        return good32;
    }

    public JLabel getNumLabel() {
        return numLabel;
    }

    public void setNumLabel(String text) {
        numLabel.setText(text);
    }
}