package com.example.test.dao;

import com.example.test.model.Account;

import java.sql.*;

public class AccountDAO {

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

        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        Statement statement = connection.createStatement();
        String checkAccount =  "select * from accounts where Username = \'" + account.getUsername() + "\' and password = \'" + account.getPassword() + "\';" ;
        ResultSet resultSet = statement.executeQuery(checkAccount);

        if (resultSet.next()) {
            connection.close();
            return true;
        }
        else {
            connection.close();
            return false;
        }
    }
}