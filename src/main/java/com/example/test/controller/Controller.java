package com.example.test.controller;

import com.example.test.dao.AccountDAO;
import com.example.test.dao.OrderDAO;
import com.example.test.dao.ProductDAO;
import com.example.test.model.Account;
import com.example.test.model.Product;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Controller extends HttpServlet {

    /*List products for show product on product page*/
    List<Product> products = new ArrayList<>();

    /*List originalProducts hold quantity customer add to cart*/
    List<Product> originalProducts = new ArrayList<>();

    /*Session will be storage save data*/
    HttpSession session;

    /*When logged, use this account get information user*/
    Account account;


    /**
     *
     * @param req
     * @param resp
     * @throws IOException
     */
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        try {
            /*When controller running, alway prepare list products size and list original for show job*/
            if (products.size() == 0) {
                products = new ProductDAO().getProducts();
                if (originalProducts.size() == 0) {
                    originalProducts = products;
                }
            }
        }
        catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }

        /*Controller will set value for action variable*/
        String action = req.getParameter("action");

        /*Find value of action variable and work by case*/
        switch (action) {

            /*Set attribute by products list and redirect to product.jsp*/
            case "productPage":
                session = req.getSession();
                session.setAttribute("products", products);
                resp.sendRedirect("product");
                break;

            /*Redirect to login page*/
            case "loginPage":
                resp.sendRedirect("login");
                break;

            /*Update data in products list*/
            case "search":

                /*Get keyword from request*/
                String keyword = req.getParameter("keyword");

                /*Query product name by keyword*/
                try {
                    products = new ProductDAO().getProducts(keyword);
                }
                catch (ClassNotFoundException | SQLException e) {
                    throw new RuntimeException(e);
                }

                /*Get session prepare set new attribute (new products list)*/
                session = req.getSession();

                /*Query by keyword but not found*/
                if (products.size() == 0) {

                    /*Refill products list by originalProducts list*/
                    products = originalProducts;

                    /*Set attribute not found, this job make notification in product page*/
                    session.setAttribute("notfound", true);
                }

                /*Otherwise, set attribute not found with false*/
                else {
                    session.setAttribute("notfound", false);
                }

                /*Set attribue keyword and products to session*/
                session.setAttribute("keyword", keyword);
                session.setAttribute("products", products);

                /*Redirect to product page*/
                resp.sendRedirect("product");
                break;

            /*Renew account object*/
            case "delete":

                /*Get account from session and renew it*/
                account = (Account) req.getSession().getAttribute("account");
                account = new Account();

                /*Set quantity of original product list to 0*/
                for (Product i: originalProducts) {
                    i.setProductQuantity(0);
                }

                /*Redirect to index page*/
                resp.sendRedirect("./");
                break;

            /*Set information product and redirect to detail page*/
            case "detail":

                /*Get product name from clicked action of user*/
                String productName = req.getParameter("product");

                /*Find that product and set attribute it*/
                for (Product i:originalProducts) {
                    if (productName.matches(i.getProductID())) {
                        session.setAttribute("productDetail", i);
                    }
                }

                /*Redirect to detail page*/
                resp.sendRedirect("detail");
                break;

            case "cart":

                /*In session, get product and account*/
                String selected = req.getParameter("selected");
                account = (Account) req.getSession().getAttribute("account");

                /*When user is logged*/
                if (account.isLogged()) {

                    /*Get quantity customer add to cart*/
                    int quantity = Integer.parseInt(req.getParameter("droplist"));

                    /*Add quantity to object in original product list*/
                    for (Product i: originalProducts) {

                        /*Find object by Product ID and set new quantity*/
                        if (i.getProductID().matches(selected)) {
                            i.setProductQuantity(i.getProductQuantity() + quantity);
                        }
                    }

                    /*Renew product list*/
                    products = new ArrayList<>();

                    /*Set attribute original product list*/
                    session.setAttribute("originalProducts", originalProducts);

                    /*Redirect to self for refill products */
                    resp.sendRedirect("controller?action=productPage");
                }

                /*User not logged, redirect to login page*/
                else {
                    resp.sendRedirect("login");
                }
                break;

            /*Insert new order to database, add all product in cart to this new order*/
            case "order":
                try {
                    new OrderDAO().insertOrder(account, originalProducts);
                } catch (ClassNotFoundException | SQLException e) {
                    throw new RuntimeException(e);
                }

                /*Redirect to index page*/
                resp.sendRedirect("./");
                break;
        }
    }


    /**
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        /*Get value action from parameter*/
        String action = req.getParameter("action");

        /*Validate object and if it pass, will check it with database*/
        if (action.matches("login")) {

            /*Set account object by username, password from form login page*/
            account = new Account(req.getParameter("username"), req.getParameter("password"));

            /*Validate it, if pass*/
            if (account.validate()) {

                /*Check it with database*/
                try {

                    /*If passed, set cookies when remember is checked*/
                    if (new AccountDAO().checkAccount(account)) {

                        req.getSession().setAttribute("account", account);
                        String remember = req.getParameter("checkbox");
                        Cookie cookieUsername = new Cookie("username", account.getUsername());
                        Cookie cookiePassword = new Cookie("password", account.getPassword());

                        if (remember != null && remember.matches("remember")) {

                            cookieUsername.setMaxAge(1800);
                            cookiePassword.setMaxAge(1800);
                        }

                        /*Otherwise, delete all cookies*/
                        else {
                            cookieUsername.setMaxAge(0);
                            cookiePassword.setMaxAge(0);
                        }

                        /*Add new value of cookies to response*/
                        resp.addCookie(cookieUsername);
                        resp.addCookie(cookiePassword);

                        /*Redirect to dashboard*/
                        resp.sendRedirect("dashboard");
                    }

                    /*Not found data in database*/
                    else {

                        /*Set error attribute in forward to login page*/
                        req.setAttribute("error", "Username and password is unavailable");
                        req.getRequestDispatcher("login").forward(req, resp);
                    }
                } catch (ClassNotFoundException | SQLException e) {
                    throw new RuntimeException(e);
                }
            }

            /*Validate not pass*/
            else {

                /*Get error from inside object, set attribute*/
                req.setAttribute("error", account.getError());

                /*Redirect to login page*/
                req.getRequestDispatcher("login").forward(req, resp);
            }
        }
    }
}