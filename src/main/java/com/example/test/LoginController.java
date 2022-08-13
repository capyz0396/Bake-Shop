package com.example.test;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

public class LoginController extends HttpServlet {


    /**
     * Khắc phục nhược điểm lộ dữ liệu ở URL, hàm doPost giao nhận thông tin bảo mật hơn
     * Sử dụng để giao nhận username, password,...
     * @param req an {@link HttpServletRequest} object that contains the request the client has made of the servlet
     *
     * @param resp an {@link HttpServletResponse} object that contains the response the servlet sends to the client
     *
     * @throws IOException
     * @throws ServletException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

        /*Get dữ liệu từ client(trang client get dữ liệu từ input)*/
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String remember = req.getParameter("checkbox");

        /*Get dữ liệu từ web.xml*/
        String uid = getServletContext().getInitParameter("username");
        String pws = getServletContext().getInitParameter("password");
        List<Account> accountList = (List<Account>) req.getSession().getAttribute("accounts");

        /*Trường hợp cả 2 input đều null*/
         if (username.length() == 0 || password.length() == 0) {
            req.setAttribute("error", "Username and password cannot be null");
            req.getRequestDispatcher("login").forward(req, resp);
         }

         /*Trường hợp client nhập input dưới 6 kí tự*/
         else if (username.length() < 6 || password.length() < 6) {
            req.setAttribute("error","Username and password must be at least 6 characters");
            req.getRequestDispatcher("login").forward(req, resp);
        }

         /*Trường hợp có kí tự khoảng trống trong username và password*/
         else if (username.matches("\\w*\\s+\\w*")) {
            req.setAttribute("error", "Username and password cannot contain space");
            req.getRequestDispatcher("login").forward(req, resp);
        }

         /*Ngược lại báo lỗi không có tài khoản*/
         else if (username.matches(uid) && password.matches(pws)) {

             /*Tạo obj để set trong session*/
             Account account = new Account(username, password);

            /*Set session để các trang khác có thể check logged*/
             req.getSession().setAttribute("account", account);

            /*Trường hợp người dùng có click vào "Remember me"
             * Set giá trị cho cookie và gửi đến client*/
             if (remember != null && remember.matches("remember")) {
                 Cookie cookieUsername = new Cookie("username", username);
                 Cookie cookiePassword = new Cookie("password", password);
                 cookieUsername.setMaxAge(1800);
                 cookiePassword.setMaxAge(1800);
                 resp.addCookie(cookieUsername);
                 resp.addCookie(cookiePassword);
                 resp.sendRedirect("dashboard");
             }

            /*Ngược lại cũng có gửi, nhưng các cookie đó có thời hạn 0s
             * Trường hợp có cookie sẵn thì update về 0 để hủy luôn cookie cũ*/
            else {
                 Cookie cookieUsername = new Cookie("username", username);
                 Cookie cookiePassword = new Cookie("password", password);
                 cookieUsername.setMaxAge(0);
                 cookiePassword.setMaxAge(0);
                 resp.addCookie(cookieUsername);
                 resp.addCookie(cookiePassword);
                 resp.sendRedirect("dashboard");
             }
        }

         else {

             boolean logged = false;

             for (Account i : accountList) {

                 if (i.getUsername().matches(username) && i.getPassword().matches(password)) {

                     logged = true;

                     /*Tạo obj để set trong session*/
                     Account account = new Account(username, password);

                     /*Set session để các trang khác có thể check logged*/
                     req.getSession().setAttribute("account", account);

                     /*Trường hợp người dùng có click vào "Remember me"
                      * Set giá trị cho cookie và gửi đến client*/
                     if (remember != null && remember.matches("remember")) {
                         Cookie cookieUsername = new Cookie("username", username);
                         Cookie cookiePassword = new Cookie("password", password);
                         cookieUsername.setMaxAge(1800);
                         cookiePassword.setMaxAge(1800);
                         resp.addCookie(cookieUsername);
                         resp.addCookie(cookiePassword);
                     }

                     /*Ngược lại cũng có gửi, nhưng các cookie đó có thời hạn 0s
                      * Trường hợp có cookie sẵn thì update về 0 để hủy luôn cookie cũ*/
                     else {
                         Cookie cookieUsername = new Cookie("username", username);
                         Cookie cookiePassword = new Cookie("password", password);
                         cookieUsername.setMaxAge(0);
                         cookiePassword.setMaxAge(0);
                         resp.addCookie(cookieUsername);
                         resp.addCookie(cookiePassword);
                     }

                     /*Chuyển từ login.jsp về index.jsp, người dùng sẽ thấy tên username hiển thị (trạng thái logged)*/
                     resp.sendRedirect("dashboard");
                     break;
                 }
             }

             if (!logged) {
                 req.setAttribute("error", "Username and password is unavailable");
                 req.getRequestDispatcher("login").forward(req, resp);
             }
         }
    }
}