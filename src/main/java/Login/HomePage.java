package Login;

import DAO.SearchDAO32;
import Entity.User32;

import javax.swing.*;
import java.awt.*;

public class HomePage extends JFrame {
    public static final int W_FRAME = 1080;
    public static final int H_FRAME = 720;
    private JPanel panel1;
    private JMenuBar menuBar;
    private JMenu displayMenu;
    private JMenuItem searchClientbyname;
    private JMenuItem statistics;
    private JMenuItem payment;
    private Insets insets;
    private User32 user32;

    public HomePage(User32 user32) {
        super("Home Page");
        setLayout(null);
        setSize(W_FRAME, H_FRAME);
        setLocationRelativeTo(null);
        setLocation(100, 100);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        this.user32 = user32;
        insets = this.getInsets();
        GUI();
    }

    private void GUI() {
        createMenus();
    }

    private void createMenus() {
        menuBar = new JMenuBar();
        displayMenu = new JMenu("Menu");
//        clientMenu = new JMenu("Client");
//        statisticsMenu = new JMenu("Statistics");
//        paymentMenu = new JMenu("Payment");

        searchClientbyname = new JMenuItem("Search Client by Name");
        searchClientbyname.addActionListener(e -> {
            HomePage.this.dispose();
            SearchDAO32 searchDAO32 = new SearchDAO32(user32);
            searchDAO32.getSearchClientbyNameFrm32().setVisible(true);
        });

        statistics = new JMenuItem("Statistics");
        payment = new JMenuItem("Payment");

        displayMenu.add(searchClientbyname);
        displayMenu.add(statistics);
        displayMenu.add(payment);

        menuBar.add(displayMenu);

        this.setJMenuBar(menuBar);

    }
}
