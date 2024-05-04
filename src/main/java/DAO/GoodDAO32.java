package DAO;

import Entity.*;
import Form.AddPriceNumFrm32;
import Form.EditInOutFrm32;
import Form.SearchGoodbyNameFrm32;
import Form.VoucherClientFrm32;
import util.DateConvertTimeStamp;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.BigInteger;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class GoodDAO32 {
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/software";
    private static final String DATABASE_USER = "root";
    private static final String DATABASE_PASSWORD = "131103";
    public Connection connection;
    private EditInOutFrm32 editInOutFrm32;
    private Payment1day32 payment1day32;
    private SearchGoodbyNameFrm32 searchGoodbyNameFrm32;
    private VoucherClientFrm32 voucherClientFrm32;
    private AddPriceNumFrm32 addPriceNumFrm32;
    private ArrayList<UsedGood32> allusedGoods;
    private ArrayList<UsedGood32> newestusedGoods;
    private User32 user32;
    private OrderForm32 orderForm32;
    private boolean haspayment1day;

    public GoodDAO32(User32 user32, OrderForm32 orderForm32) {
        try {
            connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        this.user32 = user32;
        this.orderForm32 = orderForm32;
//      editInOutFrm32.setVisible(true);
    }

    public VoucherClientFrm32 callVoucherClientFrm32() {
        voucherClientFrm32 = new VoucherClientFrm32();
        voucherClientFrm32.setVisible(true);
        return voucherClientFrm32;
    }

    public AddPriceNumFrm32 callAddPriceNumFrm32(Good32 good32) {
        addPriceNumFrm32 = new AddPriceNumFrm32();
        addPriceNumFrm32.addSubmitListener(new addActionListener());
        addPriceNumFrm32.setGood(good32);
        addPriceNumFrm32.setVisible(true);
        return addPriceNumFrm32;
    }

    public EditInOutFrm32 callEditInOutFrm32(OrderForm32 orderForm32) {

        allusedGoods = new ArrayList<>();
        newestusedGoods = new ArrayList<>();

        BigInteger idpayment1day = DateConvertTimeStamp.DateToday2timeStamp(orderForm32.getOrderId());
        payment1day32 = new Payment1day32(idpayment1day, user32.getId(), null, null, 0, new Date(System.currentTimeMillis()));
        payment1day32.setPaymentRent(orderForm32.getRentPriceOne());
        loadToday();
        haspayment1day = false;
        editInOutFrm32 = new EditInOutFrm32(payment1day32);
        haspayment1day = findPayment1day(idpayment1day);
        if (haspayment1day) {
            editInOutFrm32.setPayment1day32(payment1day32);
        }
        editInOutFrm32.addSubmitListener(new addActionListener());
        editInOutFrm32.addAddGoodButtonListener(new addActionListener());
        editInOutFrm32.setVisible(true);
        return editInOutFrm32;
    }

    private void loadToday() {
        Date today = new Date(System.currentTimeMillis());
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String date = formatter.format(today);
        System.out.println(date);
        String sql = "SELECT * FROM rentschedule32 WHERE orderformID = " + orderForm32.getOrderId() + " AND rentDate = \"" + date + "\"";
        try {
            connection.prepareStatement(sql);
            ResultSet resultSet = connection.createStatement().executeQuery(sql);
            while (resultSet.next()) {
                Time timeStart = resultSet.getTime("timeStart");
                Time timeEnd = resultSet.getTime("timeEnd");
                System.out.println(timeStart + " " + timeEnd);
                payment1day32.setBeginTime1day(timeStart);
                payment1day32.setEndTime1day(timeEnd);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private boolean findPayment1day(BigInteger id) {
        String sql = "SELECT * FROM payment1day32 WHERE id = " + id.toString();
        System.out.println(sql);
        try {
            connection.prepareStatement(sql);
            ResultSet resultSet = connection.createStatement().executeQuery(sql);
            if (!resultSet.next()) {
                System.out.println("No payment1day found");
                return false;
            } else {
                do {
                    System.out.println("Payment1day found");
                    BigInteger idpayment = resultSet.getBigDecimal("id").toBigInteger();
                    int userid = resultSet.getInt("userid");
                    Time beginTime1day = resultSet.getTime("beginTime1day");
                    Time endTime1day = resultSet.getTime("endTime1day");
//                    int orderformid = resultSet.getInt("orderformid");
                    Date createTime = resultSet.getDate("createTime");
                    payment1day32 = new Payment1day32(idpayment, userid, beginTime1day, endTime1day, 0, createTime);
                    loadUsedGoods();
                } while (resultSet.next());

            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    private void loadUsedGoods() {
        BigInteger payment1dayid = payment1day32.getId();
        String sql = "SELECT * FROM usedgood32 WHERE payment1dayid = " + payment1dayid.toString();
        System.out.println(sql);
        try {
            connection.prepareStatement(sql);
            ResultSet resultSet = connection.createStatement().executeQuery(sql);
            while (resultSet.next()) {
                int goodid = resultSet.getInt("goodid");
                float unitPrice = resultSet.getFloat("unitPrice");
                int num = resultSet.getInt("num");
                Good32 good32 = findGoodbyID(goodid);
                UsedGood32 usedGood32 = new UsedGood32(good32, unitPrice, num);
//                payment1day32.setGoodPayment(usedGood32.getUnitprice() * usedGood32.getNum());
                allusedGoods.add(usedGood32);
                addGoodtoTableEditInOutFrm32(usedGood32);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Good32 findGoodbyID(int goodid) {
        String sql = "SELECT * FROM good32 WHERE id = " + goodid;
        try {
            connection.prepareStatement(sql);
            ResultSet resultSet = connection.createStatement().executeQuery(sql);
            while (resultSet.next()) {
                String good_name = resultSet.getString("good_name");
                Good32 good32 = new Good32(goodid, good_name);
                return good32;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void addGoodtoTableEditInOutFrm32(UsedGood32 usedGood32) {
        ArrayList<UsedGood32> usedGoods = editInOutFrm32.getUsedGoods();
        if (checkusedGood(usedGood32, usedGoods)) {
            JOptionPane.showMessageDialog(null, "Good already added");
            return;
        }
        editInOutFrm32.addGoodToTable(usedGood32);
    }

    private boolean checkusedGood(UsedGood32 usedGood32, ArrayList<UsedGood32> usedGoods) {
        for (UsedGood32 usedGood : usedGoods) {
            if (usedGood.getGood32().getId() == usedGood32.getGood32().getId()) {
                return true;
            }
        }
        return false;
    }

    private boolean savePayment1daytoDB() {
        BigInteger id = payment1day32.getId();
        int userid = user32.getId();
        String beginTime1day = payment1day32.getBeginTime1day().toString();
        String endTime1day = payment1day32.getEndTime1day().toString();
        Date createTime = payment1day32.getCreateTime();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String createTimestr = formatter.format(createTime);
        String sql = "INSERT INTO payment1day32(id,userid,beginTime1day,endTime1day,orderformid,createTime) VALUES (" + id + "," + userid + ",\"" + beginTime1day + "\",\"" + endTime1day + "\"," + orderForm32.getOrderId() + ",\"" + createTimestr + "\")";
        try {
            connection.prepareStatement(sql);
            connection.createStatement().executeUpdate(sql);

            if (!saveUsedGoodtoDB()) {
                JOptionPane.showMessageDialog(null, "Save usedGood failed");
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Save payment1day failed");
            return false;
        }
        return true;
    }

    private boolean saveUsedGoodtoDB() {
        for (UsedGood32 usedGood32 : newestusedGoods) {
            int goodid = usedGood32.getGood32().getId();
            float unitprice = usedGood32.getUnitprice();
            int num = usedGood32.getNum();
            BigInteger payment1dayid = payment1day32.getId();
            String sql = "INSERT INTO usedgood32(goodid,unitPrice,num,payment1dayid) VALUES (" + goodid + "," + unitprice + "," + num + "," + payment1dayid + ")";
            try {
                connection.prepareStatement(sql);
                connection.createStatement().executeUpdate(sql);
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        }
        return true;
    }

    class addActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == editInOutFrm32.getSubmitButton()) {
                boolean isClicked = false;
                if (isClicked) {
                    JOptionPane.showMessageDialog(null, "Please wait for the previous action to complete");
                } else {
                    isClicked = true;
                    if (haspayment1day) {
                        if (saveUsedGoodtoDB()) {
                            isClicked = false;
                            JOptionPane.showMessageDialog(null, "Submit successful");
                        }
                        return;
                    } else if (savePayment1daytoDB()) {
                        isClicked = false;
                        JOptionPane.showMessageDialog(null, "Submit successful");
                    }
                }
                System.out.println("Submit button clicked");
            } else if (e.getSource() == editInOutFrm32.getAddGoodButton()) {
                System.out.println("Add Good button clicked");
                SearchDAO32 searchDAO32 = new SearchDAO32(user32);
                searchGoodbyNameFrm32 = searchDAO32.callSearchGoodbyNameFrm32();
                searchGoodbyNameFrm32.addTableListener(new TableListener());
            } else if (e.getSource() == addPriceNumFrm32.getSubmitButton()) {
                int num = Integer.parseInt(addPriceNumFrm32.getNumField().getText());
                if (num > 0) {
                    UsedGood32 usedGood = new UsedGood32(addPriceNumFrm32.getGood32(), Float.parseFloat(addPriceNumFrm32.getPriceField().getText()), Integer.parseInt(addPriceNumFrm32.getNumField().getText()));
                    newestusedGoods.add(usedGood);
                    addGoodtoTableEditInOutFrm32(usedGood);
                    addPriceNumFrm32.setVisible(false);
                    System.out.println("Submit Price Num button clicked");
                } else {
                    System.out.println("Num must be greater than 0");
                    addPriceNumFrm32.setNumLabel("Num !! Num must be greater than 0!!!!!");
                }
            }
        }
    }

    class TableListener extends MouseAdapter {
        private boolean isClicked = false;

        @Override
        public void mouseClicked(MouseEvent e) {
            if (e.getSource() == searchGoodbyNameFrm32.getTablegood()) {
                if (!isClicked) {
                    isClicked = true;
                    int row = searchGoodbyNameFrm32.getTablegood().getSelectedRow();
                    Good32 good32 = searchGoodbyNameFrm32.getGoodstable().get(row);
                    addPriceNumFrm32 = callAddPriceNumFrm32(good32);
                    searchGoodbyNameFrm32.setVisible(false);
                    isClicked = false;
                }
            }
        }
    }
}
