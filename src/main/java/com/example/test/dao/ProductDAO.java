package com.example.test.dao;

import com.example.test.model.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {

    private String jdbcURL = "jdbc:mysql://localhost/bakeshop";
    private String jdbcUsername = "root";
    private String jdbcPassword = "admin";

    private List<Product> products = new ArrayList<>();

    public List<Product> getProducts() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        Statement statement = connection.createStatement();
        String selectAllProduct =  "select * from products order by ProductName asc";
        ResultSet resultSet = statement.executeQuery(selectAllProduct);

        while (resultSet.next()) {
            Product product = new Product();
            product.setProductID(resultSet.getString("ProductID"));
            product.setProductName(resultSet.getString("ProductName"));
            product.setProductPrice(Integer.parseInt(resultSet.getString("ProductPrice")));
            product.setProductDetail(resultSet.getString("ProductDetail"));
            product.setProductIMGURL(resultSet.getString("ProductIMGURL"));
            products.add(product);
        }

        connection.close();
        return products;
    }

    public List<Product> getProducts(String keyword) throws ClassNotFoundException, SQLException {

        Connection connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        Class.forName("com.mysql.jdbc.Driver");
        Statement statement = connection.createStatement();
        String selectProductByKeyword = "select * from products where ProductName LIKE '%" + keyword +"%'";
        ResultSet resultSet = statement.executeQuery(selectProductByKeyword);

        while (resultSet.next()) {
            Product product = new Product();
            product.setProductID(resultSet.getString("ProductID"));
            product.setProductName(resultSet.getString("ProductName"));
            product.setProductPrice(Integer.parseInt(resultSet.getString("ProductPrice")));
            product.setProductDetail(resultSet.getString("ProductDetail"));
            product.setProductIMGURL(resultSet.getString("ProductIMGURL"));
            products.add(product);
        }

        connection.close();
        return products;
    }
}