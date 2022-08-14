package com.example.test.dao;

import com.example.test.model.Account;
import com.example.test.model.Product;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class OrderDAO {

    private String jdbcURL = "jdbc:mysql://localhost/bakeshop";
    private String jdbcUsername = "root";
    private String jdbcPassword = "admin";
    private String orderID;

    public void insertOrder(Account account, List<Product> products) throws ClassNotFoundException, SQLException {

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime now = LocalDateTime.now();
        String date = dtf.format(now);

        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        Statement statement = connection.createStatement();
        String insertOrder =  "insert into `bakeshop`.`orders` (`Username`, `Date`) values (\'" + account.getUsername() + "\',\'" + date + "\')";
        statement.executeUpdate(insertOrder);

        String getOrderID = "select OrderID from orders order by OrderID desc limit 1";
        ResultSet resultSet = statement.executeQuery(getOrderID);

        while (resultSet.next()) {
            orderID = resultSet.getString(1);
        }

        for (Product pr: products) {

            if (pr.getProductQuantity() > 0) {
                String insertFromCart = "INSERT INTO `bakeshop`.`order_item` (`OrderID`, `ProductID`, `Quantity`) VALUES ('" + orderID + "', '" + pr.getProductID() + "', '" + pr.getProductQuantity() + "')";
                int result = statement.executeUpdate(insertFromCart);
                if (result == 1) {
                    pr.setProductQuantity(0);
                }
            }
        }

        connection.close();
    }
}