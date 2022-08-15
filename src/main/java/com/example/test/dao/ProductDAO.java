package com.example.test.dao;

import com.example.test.model.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {

    /*Variable set up information for connect database job*/
    private String jdbcURL = "jdbc:mysql://localhost/bakeshop";
    private String jdbcUsername = "root";
    private String jdbcPassword = "admin";

    /*Create product list*/
    private List<Product> products = new ArrayList<>();


    /**
     * Query all data
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public List<Product> getProducts() throws ClassNotFoundException, SQLException {

        /*Create connection to database*/
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        Statement statement = connection.createStatement();

        /*Prepare query code and execute*/
        String selectAllProduct =  "select * from products order by ProductName asc";
        ResultSet resultSet = statement.executeQuery(selectAllProduct);

        /*Read line data*/
        while (resultSet.next()) {

            /*Set this data to product object*/
            Product product = new Product();
            product.setProductID(resultSet.getString("ProductID"));
            product.setProductName(resultSet.getString("ProductName"));
            product.setProductPrice(Integer.parseInt(resultSet.getString("ProductPrice")));
            product.setProductDetail(resultSet.getString("ProductDetail"));
            product.setProductIMGURL(resultSet.getString("ProductIMGURL"));

            /*Add object to product list*/
            products.add(product);
        }

        /*Close connection and return product list*/
        connection.close();
        return products;
    }


    /**
     * Query data by keyword
     * @param keyword
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public List<Product> getProducts(String keyword) throws ClassNotFoundException, SQLException {

        /*Create connection to database*/
        Connection connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        Class.forName("com.mysql.jdbc.Driver");
        Statement statement = connection.createStatement();

        /*Prepare query code and execute*/
        String selectProductByKeyword = "select * from products where ProductName LIKE '%" + keyword +"%'";
        ResultSet resultSet = statement.executeQuery(selectProductByKeyword);

        /*Read line data*/
        while (resultSet.next()) {

            /*Set data to product object*/
            Product product = new Product();
            product.setProductID(resultSet.getString("ProductID"));
            product.setProductName(resultSet.getString("ProductName"));
            product.setProductPrice(Integer.parseInt(resultSet.getString("ProductPrice")));
            product.setProductDetail(resultSet.getString("ProductDetail"));
            product.setProductIMGURL(resultSet.getString("ProductIMGURL"));

            /*Add object to product list*/
            products.add(product);
        }

        /*Close connection and return product list*/
        connection.close();
        return products;
    }
}