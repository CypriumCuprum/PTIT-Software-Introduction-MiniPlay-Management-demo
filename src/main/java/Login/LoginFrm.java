package Login;

import Entity.User32;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginFrm extends JFrame {
    public static final int H_FRAME = 500;
    public static final int W_FRAME = 600;
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/software";
    private static final String DATABASE_USER = "root";
    private static final String DATABASE_PASSWORD = "131103";
    private final Insets insets;
    public Connection connection;
    private JTextField txtUserName;
    private JPasswordField txtPassword;
    private JLabel label_icon;
    private JLabel label_errorText;
    private JButton btnLogin;
    private JButton btnCancel;

    public LoginFrm() {
        super("Login");
        setIconImage(Toolkit.getDefaultToolkit().getImage("src\\icon\\Login_user_24.png"));
        //setResizable(false);
        setLayout(null);
        setSize(W_FRAME, H_FRAME);
        setLocationRelativeTo(null);
        setLocation(getX() - 80, getY() - 80);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        try {
            connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        insets = this.getInsets();


        GUI();

    }

    public static void main(String[] args) {
        LoginFrm loginFrm = new LoginFrm();
        loginFrm.setVisible(true);
    }

    private void GUI() {
        JPanel panel1 = new JPanel();
        panel1.setLayout(null);
        panel1.setBounds(insets.left, insets.top, W_FRAME - insets.left - insets.right,
                H_FRAME - insets.bottom - insets.top);
        add(panel1);

        JLabel label_username = new JLabel("Username");
        label_username.setBounds(50, 80, 100, 20);
        panel1.add(label_username);

        JLabel label_password = new JLabel("Password");
        label_password.setBounds(50, 110, 100, 20);
        panel1.add(label_password);

        txtUserName = new JTextField();
        txtUserName.setBounds(150, 80, 150, 20);

        txtUserName.addActionListener(e -> {
            txtPassword.requestFocus();
        });
        panel1.add(txtUserName);

        txtPassword = new JPasswordField();
        txtPassword.setBounds(150, 110, 150, 20);

        txtPassword.addActionListener(e -> {
            btnLogin.doClick();
        });
        panel1.add(txtPassword);

        btnLogin = new JButton("Login");
        btnLogin.setBounds(50, 140, 100, 20);
        panel1.add(btnLogin);
        btnLogin.addActionListener(e -> {
            String userName = txtUserName.getText();
            String password = new String(txtPassword.getPassword());
            String sql = "SELECT * FROM user32 WHERE accname = \"" + userName + "\" AND accpassword = \"" + password + "\"";
            if (userName.equals("admin") && password.equals("admin")) {
                JOptionPane.showMessageDialog(null, "Login successful");
                EventQueue.invokeLater(() -> {
                    LoginFrm.this.dispose();
                    User32 user32 = new User32();
                    new HomePage(user32).setVisible(true);
                });
            } else {
                try {
                    ResultSet resultSet = connection.createStatement().executeQuery(sql);
                    if (resultSet.next()) {
                        JOptionPane.showMessageDialog(null, "Login successful");
                        int id = resultSet.getInt("id");
                        String username = resultSet.getString("user_name");
                        String user_address = resultSet.getString("user_address");
                        String phone = resultSet.getString("phone");
                        String user_level = resultSet.getString("user_level");
                        String accname = resultSet.getString("accname");
                        String accpassword = resultSet.getString("accpassword");
                        User32 user32 = new User32(id, username, user_address, phone, user_level, accname, accpassword);
                        EventQueue.invokeLater(() -> {
                            LoginFrm.this.dispose();
                            new HomePage(user32).setVisible(true);
                        });
                    } else {
                        JOptionPane.showMessageDialog(null, "Login failed");
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });

    }
}
