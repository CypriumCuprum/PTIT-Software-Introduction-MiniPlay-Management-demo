package DAO;

import Entity.Client32;
import Entity.Good32;
import Entity.OrderForm32;
import Entity.User32;
import Form.EditInOutFrm32;
import Form.SearchClientbyNameFrm32;
import Form.SearchGoodbyNameFrm32;
import Form.VoucherClientFrm32;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class SearchDAO32 {
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/software";
    private static final String DATABASE_USER = "root";
    private static final String DATABASE_PASSWORD = "131103";
    public Connection connection;

    private SearchClientbyNameFrm32 searchClientbyNameFrm32;
    private VoucherClientFrm32 voucherClientFrm32;
    private SearchGoodbyNameFrm32 searchGoodbyNameFrm32;
    private ArrayList<Good32> goods;
    private ArrayList<OrderForm32> orderForms;
    private User32 user32;

    public SearchDAO32(User32 user32) {
        try {
            connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        this.user32 = user32;
        searchClientbyNameFrm32 = new SearchClientbyNameFrm32();
        searchClientbyNameFrm32.addSearchListener(new SearchListener());
        searchClientbyNameFrm32.addTableListener(new TableListener());
    }

    public SearchClientbyNameFrm32 getSearchClientbyNameFrm32() {
        return searchClientbyNameFrm32;
    }

    public SearchGoodbyNameFrm32 callSearchGoodbyNameFrm32() {
        searchGoodbyNameFrm32 = new SearchGoodbyNameFrm32();
        searchGoodbyNameFrm32.addSearchListener(new SearchListener());
        searchGoodbyNameFrm32.setVisible(true);
        return searchGoodbyNameFrm32;
    }

    private void setSearchGoodbyNameFrm32(String name) {
        String sql = "SELECT * FROM good32 WHERE good_name LIKE '%" + name + "%'";
        goods = new ArrayList<>();
        try {
            connection.prepareStatement(sql);
            ResultSet resultSet = connection.createStatement().executeQuery(sql);
            while (resultSet.next()) {
                String good_name = resultSet.getString("good_name");
                int good_id = resultSet.getInt("id");
                System.out.println(good_id + " " + good_name);
                Good32 good = new Good32(good_id, good_name);
                goods.add(good);
                searchGoodbyNameFrm32.setGoodTable(goods);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void searchClientByName(String name) {
        String sql = "SELECT * FROM client32 WHERE client_name LIKE '%" + name + "%'";
        ArrayList<Client32> clients = new ArrayList<>();
        try {
            connection.prepareStatement(sql);
            ResultSet resultSet = connection.createStatement().executeQuery(sql);
            while (resultSet.next()) {
                String client_name = resultSet.getString("client_name");
                String client_address = resultSet.getString("client_address");
                String client_phone = resultSet.getString("phone");
                int client_id = resultSet.getInt("id");
                System.out.println(client_id + " " + client_name + " " + client_address + " " + client_phone);
                Client32 client = new Client32(client_id, client_name, client_address, client_phone);
                clients.add(client);
                searchClientbyNameFrm32.setClientTable(clients);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private void setOrderForm32(int client_ID) {
        String sql = "SELECT * FROM orderform32 WHERE clientid = " + client_ID;
        orderForms = new ArrayList<>();
        try {
            connection.prepareStatement(sql);
            ResultSet resultSet = connection.createStatement().executeQuery(sql);
            while (resultSet.next()) {
                int orderId = resultSet.getInt("id");
                Date beginTime = resultSet.getDate("beginTime");
                Date endTime = resultSet.getDate("endTime");
                float rentPriceOne = resultSet.getFloat("rentPriceOne");
                float fullPredictPrice = resultSet.getFloat("fullPredictPrice");
                float deposit = resultSet.getFloat("deposit");
                Date deposit_date = resultSet.getDate("deposit_date");
                int clientid = resultSet.getInt("clientid");
                int userid = resultSet.getInt("userid");
                Date createTime = resultSet.getDate("createTime");
                System.out.println(orderId + " " + beginTime + " " + endTime + " " + rentPriceOne + " " + fullPredictPrice + " " + deposit + " " + deposit_date + " " + clientid + " " + userid + " " + createTime);
                OrderForm32 orderForm = new OrderForm32(orderId, beginTime, endTime, rentPriceOne, fullPredictPrice, deposit, deposit_date, clientid, userid, createTime);
                orderForms.add(orderForm);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public boolean testConnection() {
        try (Connection conn = DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD)) {
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    class SearchListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == searchClientbyNameFrm32.getSearchButton()) {
                searchClientByName(searchClientbyNameFrm32.getSearchField().getText());
            } else if (e.getSource() == searchGoodbyNameFrm32.getSearchButton()) {
                setSearchGoodbyNameFrm32(searchGoodbyNameFrm32.getSearchField().getText());
            }
        }

    }

    class TableListener extends MouseAdapter {
        private boolean isClicked = false;

        @Override
        public void mouseClicked(MouseEvent e) {
            if (e.getSource() == searchClientbyNameFrm32.getTableclient()) {
                if (!isClicked) {
                    isClicked = true;
                    int row = searchClientbyNameFrm32.getTableclient().getSelectedRow();
                    int client_ID = Integer.parseInt(searchClientbyNameFrm32.getTableclient().getValueAt(row, 0).toString());
                    String client_name = searchClientbyNameFrm32.getTableclient().getValueAt(row, 1).toString();
                    if (row >= 0) {
                        // Do something when the row is clicked
                        setOrderForm32(client_ID);
                        voucherClientFrm32 = new VoucherClientFrm32();
                        voucherClientFrm32.setIdLabel(client_ID);
                        voucherClientFrm32.setNameLabel(client_name);
                        voucherClientFrm32.setOrderForms(orderForms);
                        voucherClientFrm32.addTableListener(new TableVoucherListener());
                        voucherClientFrm32.setVisible(true);
                        searchClientbyNameFrm32.setVisible(false);
                        System.out.println("Row " + row + " clicked");
                    }
                    isClicked = false;
                }
            } else if (e.getSource() == searchGoodbyNameFrm32.getTablegood()) {
                if (!isClicked) {
                    isClicked = true;
                    int row = searchGoodbyNameFrm32.getTablegood().rowAtPoint(e.getPoint());
                    if (row >= 0) {
                        Good32 good = goods.get(row);
                        // Do something when the row is clicked
                        System.out.println("Row " + row + " clicked");
                    }
                    isClicked = false;
                }
            }

        }
    }

    class TableVoucherListener implements TableModelListener {
        @Override
        public void tableChanged(TableModelEvent e) {
            if (e.getColumn() == 0) {
                // Checkbox column has changed
                // Add your effect here

                int row = e.getFirstRow();
                boolean isChecked = (boolean) voucherClientFrm32.getTableModel().getValueAt(row, 0);
                OrderForm32 orderForm32 = orderForms.get(row);

                if (isChecked) {
                    GoodDAO32 goodDAO32 = new GoodDAO32(user32, orderForm32);
                    System.out.println("Checkbox column " + row + " has changed");
                    EditInOutFrm32 editInOutFrm32 = goodDAO32.callEditInOutFrm32(orderForm32);
                    editInOutFrm32.addWindowListener(new WindowAdapter() {
                        @Override
                        public void windowClosing(WindowEvent e) {
                            super.windowClosing(e);
                            voucherClientFrm32.getTableModel().setValueAt(false, row, 0);
                            editInOutFrm32.setVisible(false);
                            System.out.println(editInOutFrm32.isVisible());
                        }
                    });
                }
            }
        }
    }
}
