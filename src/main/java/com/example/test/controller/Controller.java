package com.example.test.controller;

import com.example.test.dao.AccountDAO;
import com.example.test.dao.OrderDAO;
import com.example.test.dao.ProductDAO;
import com.example.test.model.Account;
import com.example.test.model.Product;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Controller extends HttpServlet {

    /*List products chứa products được tìm kiếm hoặc toàn bộ products khi show ở trang product*/
    List<Product> products = new ArrayList<>();

    /*List originalProducts chứa các sản phẩm được đặt vào giỏ hàng*/
    List<Product> originalProducts = new ArrayList<>();

    /*Chứa toàn bộ thông tin account với List*/
    List<Account> accounts = new ArrayList<>();

    /*Chứa mọi thông tin client cần ở session*/
    HttpSession session;

    Account account;


    /**
     *
     * @param req an {@link HttpServletRequest} object that contains the request the client has made of the servlet
     *
     * @param resp an {@link HttpServletResponse} object that contains the response the servlet sends to the client
     *
     * @throws IOException
     */
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        try {
            products = new ProductDAO().getProducts();
            if (originalProducts.size() == 0) {
                originalProducts = products;
            }
        }
        catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }

        /*Nếu nhận được req từ client, kiểm tra theo từng trường hợp để xử lý*/
        String action = req.getParameter("action");

        switch (action) {

            case "productPage":
                session = req.getSession();
                session.setAttribute("products", products);
                resp.sendRedirect("product");
                break;

            case "loginPage":
                resp.sendRedirect("login");
                break;

            case "login":
                System.out.println("Right here");
                break;

            case "search":
                String keyword = req.getParameter("keyword");
                try {
                    products = new ProductDAO().getProducts(keyword);
                }
                catch (ClassNotFoundException | SQLException e) {
                    throw new RuntimeException(e);
                }

                session = req.getSession();

                /*Nếu list products vẫn có độ dài = 0 thì trả về tất cả sản phẩm*/
                if (products.size() == 0) {
                    products = originalProducts;
                    session.setAttribute("notfound", true);
                }

                /*Ngược lại trả về thông báo false*/
                else {
                    session.setAttribute("notfound", false);
                }

                /*Gán từ khóa và list products vào session*/
                session.setAttribute("keyword", keyword);
                session.setAttribute("products", products);

                /*Điều hướng đến trang product để cập nhật lại sản phẩm được tìm kiếm*/
                resp.sendRedirect("product");
                break;

            case "delete":
                account = (Account) req.getSession().getAttribute("account");
                account.setUsername(null);
                account.setPassword(null);
                account.setLogged(false);
                account.setTime(0);

                for (Product i: originalProducts) {
                    i.setProductQuantity(0);
                }

                resp.sendRedirect("./");
                break;

            case "detail":

                /*Nếu nhận được product từ client, xử lý*/
                String productName = req.getParameter("product");

                /*Tạo 1 biến chứa toàn bộ thông tin product được yêu cầu*/
                Product product = null;

                /*Tìm ra thông tin product trong list đã lưu từ trước*/
                for (Product i:products) {
                    if (productName.matches(i.getProductID())) {
                        product = i;
                    }
                }

                session.setAttribute("productDetail", product);
                resp.sendRedirect("detail");
                break;

            case "cart":

                /*Nếu nhận được selected từ client, xử lý*/
                String selected = req.getParameter("selected");
                account = (Account) req.getSession().getAttribute("account");

                if (account.isLogged()) {

                    int quantity = Integer.parseInt(req.getParameter("droplist"));

                    for (Product i: originalProducts) {
                        if (i.getProductID().matches(selected)) {
                            i.setProductQuantity(i.getProductQuantity() + quantity);
                        }
                    }

                    products = new ArrayList<>();
                    session.setAttribute("originalProducts", originalProducts);
                    resp.sendRedirect("controller?action=productPage");
                }
                else {
                    resp.sendRedirect("controller?action=loginPage");
                }
                break;

            case "order":
                try {
                    new OrderDAO().insertOrder(account, originalProducts);
                } catch (ClassNotFoundException | SQLException e) {
                    throw new RuntimeException(e);
                }
                resp.sendRedirect("./");
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String action = req.getParameter("action");

        if (action.matches("login")) {
            Account account = new Account(req.getParameter("username"), req.getParameter("password"));
            if (account.validate()) {
                try {
                    if (new AccountDAO().checkAccount(account)) {

                        req.getSession().setAttribute("account", account);
                        String remember = req.getParameter("checkbox");
                        Cookie cookieUsername = new Cookie("username", account.getUsername());
                        Cookie cookiePassword = new Cookie("password", account.getPassword());

                        if (remember != null && remember.matches("remember")) {

                            cookieUsername.setMaxAge(1800);
                            cookiePassword.setMaxAge(1800);
                        }
                        else {
                            cookieUsername.setMaxAge(0);
                            cookiePassword.setMaxAge(0);
                        }

                        resp.addCookie(cookieUsername);
                        resp.addCookie(cookiePassword);
                        resp.sendRedirect("dashboard");
                    }
                    else {
                        req.setAttribute("error", "Username and password is unavailable");
                        req.getRequestDispatcher("login").forward(req, resp);
                    }
                } catch (ClassNotFoundException | SQLException e) {
                    throw new RuntimeException(e);
                }
            } else {
                req.setAttribute("error", account.getError());
                req.getRequestDispatcher("login").forward(req, resp);
            }
        }
    }
}