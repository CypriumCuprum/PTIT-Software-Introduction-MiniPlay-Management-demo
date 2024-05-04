package Login;

import java.awt.*;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        // Press Alt+Enter with your caret at the highlighted text to see how
        // IntelliJ IDEA suggests fixing it.
        System.out.println("Hello, World!");
        EventQueue.invokeLater(() -> {
            new LoginFrm().setVisible(true);

        });
//        SearchDAO32 dao = new SearchDAO32();
//        boolean isConnected = dao.testConnection();
//        System.out.println("Connection successful: " + isConnected);
    }
}