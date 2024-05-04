package util;

import Entity.OrderForm32;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;

public class OrderFormPanel extends JPanel {
    private JLabel orderIdLabel;
    private JLabel beginTimeLabel;
    private JLabel endTimeLabel;
    private JCheckBox checkBox;

    public OrderFormPanel(OrderForm32 orderForm) {
        setLayout(new BorderLayout());

        checkBox = new JCheckBox();
        add(checkBox, BorderLayout.WEST);

        JPanel labelPanel = new JPanel();
        checkBox.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                // Checkbox has been selected
                // Do something here
            } else {
                // Checkbox has been deselected
                // Do something here
            }
        });
        labelPanel.setLayout(new BoxLayout(labelPanel, BoxLayout.Y_AXIS));
        orderIdLabel = new JLabel("Order ID: " + orderForm.getOrderId());
        beginTimeLabel = new JLabel("Begin Time: " + orderForm.getBeginTime());
        endTimeLabel = new JLabel("End Time: " + orderForm.getEndTime());
        labelPanel.add(orderIdLabel);
        labelPanel.add(beginTimeLabel);
        labelPanel.add(endTimeLabel);

        add(labelPanel, BorderLayout.CENTER);
        // Add a border to the panel
        setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.GRAY));
    }
}