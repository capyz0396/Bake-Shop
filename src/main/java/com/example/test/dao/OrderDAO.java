package com.example.test.dao;

import com.example.test.model.Account;
import com.example.test.model.Product;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class OrderDAO {

    /*Variable set up information for connect database job*/
    private String jdbcURL = "jdbc:mysql://localhost/bakeshop";
    private String jdbcUsername = "root";
    private String jdbcPassword = "admin";
    private String orderID;

    public void insertOrder(Account account, List<Product> products) throws ClassNotFoundException, SQLException {

        /*Get currently time and format it*/
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime now = LocalDateTime.now();
        String date = dtf.format(now);


        /*Create connection to database*/
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        Statement statement = connection.createStatement();

        /*Prepare insert code query and execute*/
        String insertOrder =  "insert into `bakeshop`.`orders` (`Username`, `Date`) values (\'" + account.getUsername() + "\',\'" + date + "\')";
        statement.executeUpdate(insertOrder);

        /*Prepare query Order ID code and execute*/
        String getOrderID = "select OrderID from orders order by OrderID desc limit 1";
        ResultSet resultSet = statement.executeQuery(getOrderID);

        /*Get result and set that data to orderID variable*/
        while (resultSet.next()) {
            orderID = resultSet.getString(1);
        }

        /*Foreach all original product list*/
        for (Product pr: products) {

            /*If quantity not matches 0*/
            if (pr.getProductQuantity() > 0) {

                /*Insert it to database*/
                String insertFromCart = "INSERT INTO `bakeshop`.`order_item` (`OrderID`, `ProductID`, `Quantity`) VALUES ('" + orderID + "', '" + pr.getProductID() + "', '" + pr.getProductQuantity() + "')";
                int result = statement.executeUpdate(insertFromCart);

                /*If insert successfully, set quantity to 0*/
                if (result == 1) {
                    pr.setProductQuantity(0);
                }
            }
        }

        /*Close connection*/
        connection.close();
    }
}