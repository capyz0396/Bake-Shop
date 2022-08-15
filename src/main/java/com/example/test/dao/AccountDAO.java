package com.example.test.dao;

import com.example.test.model.Account;

import java.sql.*;

public class AccountDAO {

    /*Variable set up information for connect database job*/
    private String jdbcURL = "jdbc:mysql://localhost/bakeshop";
    private String jdbcUsername = "root";
    private String jdbcPassword = "admin";


    /**
     * Check account with database from MySQL
     * @param account
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public boolean checkAccount(Account account) throws ClassNotFoundException, SQLException {

        /*Create connection with database*/
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        Statement statement = connection.createStatement();

        /*Set query code*/
        String checkAccount =  "select * from accounts where Username = \'" + account.getUsername() + "\' and password = \'" + account.getPassword() + "\';" ;

        /*Get result*/
        ResultSet resultSet = statement.executeQuery(checkAccount);

        /*If found return true*/
        if (resultSet.next()) {
            connection.close();
            return true;
        }

        /*Else return false*/
        else {
            connection.close();
            return false;
        }
    }
}