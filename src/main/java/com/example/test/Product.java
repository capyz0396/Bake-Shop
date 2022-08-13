package com.example.test;

public class Product {

    private String ProductID;
    private String ProductName;
    private int ProductPrice;
    private String ProductDetail;
    private String ProductIMGURL;
    private int ProductQuantity;


    public String getProductID() {
        return ProductID;
    }

    public void setProductID(String productID) {
        ProductID = productID;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String productName) {
        ProductName = productName;
    }

    public int getProductPrice() {
        return ProductPrice;
    }

    public void setProductPrice(int productPrice) {
        ProductPrice = productPrice;
    }

    public String getProductDetail() {
        return ProductDetail;
    }

    public void setProductDetail(String productDetail) {
        ProductDetail = productDetail;
    }

    public String getProductIMGURL() {
        return ProductIMGURL;
    }

    public void setProductIMGURL(String productIMGURL) {
        ProductIMGURL = productIMGURL;
    }

    public int getProductQuantity() {
        return ProductQuantity;
    }

    public void setProductQuantity(int productQuantity) {
        ProductQuantity = productQuantity;
    }
}