package com.example.test;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
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


    /**
     * Hàm doGet không bảo mật về dữ liệu giao nhận nên chỉ dùng để điều hướng trang
     * @param req an {@link HttpServletRequest} object that contains the request the client has made of the servlet
     *
     * @param resp an {@link HttpServletResponse} object that contains the response the servlet sends to the client
     *
     * @throws IOException
     */
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        /*Nếu nhận được thông tin account từ client*/
        Account account = (Account) req.getSession().getAttribute("account");

        /*Nếu nhận được req từ client, kiểm tra theo từng trường hợp để xử lý*/
        String action = req.getParameter("action");

        /*Nếu nhận được selected từ client, xử lý*/
        String selected = req.getParameter("selected");

        /*Nếu nhận được product từ client, xử lý*/
        String productName = req.getParameter("product");

        /*Nếu nhận được search từ client, xử lý*/
        String keyword = req.getParameter("search");

        /*Tạo dữ liệu cho list products*/
        try {
            String connectionURL = "jdbc:mysql://localhost/bakeshop";
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection(connectionURL, "root", "admin");
            Statement statement = connection.createStatement();

            if(!connection.isClosed() && products.size() == 0) {

                String sql = "select * from products order by ProductName asc";
                ResultSet resultSet = statement.executeQuery(sql);

                while (resultSet.next()) {
                    Product product = new Product();
                    product.setProductID(resultSet.getString("ProductID"));
                    product.setProductName(resultSet.getString("ProductName"));
                    product.setProductPrice(Integer.parseInt(resultSet.getString("ProductPrice")));
                    product.setProductDetail(resultSet.getString("ProductDetail"));
                    product.setProductIMGURL(resultSet.getString("ProductIMGURL"));
                    products.add(product);
                }

                session = req.getSession();
                session.setAttribute("products", products);
            }

            connection.close();
        }
        catch (Exception ex) {
            System.out.println("Unable to connect to database" + ex);
        }

        /*Tạo dữ liệu cho list originalProducts*/
        try {
            String connectionURL = "jdbc:mysql://localhost/bakeshop";
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection(connectionURL, "root", "admin");
            Statement statement = connection.createStatement();

            if(!connection.isClosed() && originalProducts.size() == 0) {
                String sql = "select * from products order by ProductName asc";
                ResultSet resultSet = statement.executeQuery(sql);

                while (resultSet.next()) {
                    Product product = new Product();
                    product.setProductID(resultSet.getString("ProductID"));
                    product.setProductName(resultSet.getString("ProductName"));
                    product.setProductPrice(Integer.parseInt(resultSet.getString("ProductPrice")));
                    product.setProductDetail(resultSet.getString("ProductDetail"));
                    product.setProductIMGURL(resultSet.getString("ProductIMGURL"));
                    originalProducts.add(product);
                }

                session = req.getSession();
                session.setAttribute("originalProducts", originalProducts);
            }

            else {
                /*System.out.println("Cannot connect to MySQL server!");*/
            }

            connection.close();
        }
        catch (Exception ex) {
            System.out.println("Unable to connect to database" + ex);
        }

        /*Trường hợp điều hướng, nếu nhấn vào button log in, điều hướng đến trang product.jsp*/
        if (action != null && action.matches("productPage")) {
            resp.sendRedirect("product");
        }

        /*Trường hợp điều hướng, nếu nhấn vào button log in, điều hướng đến trang login.jsp*/
        if (action != null && action.matches("loginPage")) {

            try {
                String connectionURL = "jdbc:mysql://localhost/bakeshop";
                Class.forName("com.mysql.jdbc.Driver");
                Connection connection = DriverManager.getConnection(connectionURL, "root", "admin");
                Statement statement = connection.createStatement();
                String sql = "select * from accounts";
                ResultSet resultSet = statement.executeQuery(sql);

                if(!connection.isClosed() && accounts.size() == 0) {

                    while (resultSet.next()) {
                        Account acc = new Account();
                        acc.setUsername(resultSet.getString("Username"));
                        acc.setPassword(resultSet.getString("Password"));
                        acc.setDisplayName(resultSet.getString("DisplayName"));
                        acc.setAddress(resultSet.getString("Address"));
                        acc.setEmail(resultSet.getString("Email"));
                        acc.setPhone(resultSet.getString("Phone"));
                        accounts.add(acc);
                    }

                    session = req.getSession();
                    session.setAttribute("accounts", accounts);
                    resp.sendRedirect("login");
                }

                else if (!connection.isClosed() && accounts.size() != 0) {
                    resp.sendRedirect("login");
                }

                else {
                    /*System.out.println("Cannot connect to MySQL server!");*/
                }

                connection.close();
            }

            catch (Exception ex) {
                System.out.println("Unable to connect to database" + ex);
            }
        }

        /*Trường hợp người dùng muốn log out*/
        if (action != null && action.matches("delete")) {
            Account acc = (Account) req.getSession().getAttribute("account");
            acc.setUsername(null);
            acc.setPassword(null);
            acc.setLogged(false);
            acc.setTime(0);

            for (Product i: originalProducts) {
                i.setProductQuantity(0);
            }

            resp.sendRedirect("./");
        }

        /*Trường hợp productName không null thì bắt đầu xử lý*/
        if (productName != null) {

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
        }

        /*Trường hợp selected khác null, xử lý*/
        if (selected != null && account.getUsername() != null){
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

        else if (selected != null && account.getUsername() == null) {
            resp.sendRedirect("controller?action=loginPage");
        }

        /*Client yêu cầu order, dữ liệu sẽ cập nhật vào data*/
        if (action != null && action.matches("order")) {

            /*Get các thông tin cần thiết để tạo đơn hàng*/
            Account acc = (Account) req.getSession().getAttribute("account");
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDateTime now = LocalDateTime.now();
            String date = dtf.format(now);
            String OrderID = null;

            /*Tạo đơn hàng*/
            try {

                /*Tạo truy cập với database*/
                String connectionURL = "jdbc:mysql://localhost/bakeshop";
                Class.forName("com.mysql.jdbc.Driver");
                Connection connection = DriverManager.getConnection(connectionURL, "root", "admin");
                Statement statement = connection.createStatement();

                /*Insert thông tin vào database*/
                String sql1 = "INSERT INTO `bakeshop`.`orders` (`Username`, `Date`) VALUES (\'" + acc.getUsername() + "\',\'" + date + "\')";
                statement.executeUpdate(sql1);

                /*Ngắt kết nối database*/
                connection.close();
            }
            catch (Exception ex) {
                System.out.println("Unable to connect to database" + ex);
            }

            /*Get OrderID*/
            try {

                /*Tạo truy cập với database*/
                String connectionURL = "jdbc:mysql://localhost/bakeshop";
                Class.forName("com.mysql.jdbc.Driver");
                Connection connection = DriverManager.getConnection(connectionURL, "root", "admin");
                Statement statement = connection.createStatement();

                /*Query thông tin twf database*/
                String sql2 = "select OrderID from orders order by OrderID desc limit 1";
                ResultSet kq2 = statement.executeQuery(sql2);

                /*Kết nối không gián đoạn thì get OrderID*/
                if (!connection.isClosed()) {
                    while (kq2.next()) {
                        OrderID = kq2.getString(1);
                    }

                    /*Ngắt kết nối database*/
                    connection.close();
                }
            }
            catch (Exception ex) {
                System.out.println("Unable to connect to database" + ex);
            }

            /*Nhập các sản phẩm có trong giỏ vào đơn hàng*/
            /*Sử dụng foreach để duyệt sản phẩm*/
            for (Product pr: originalProducts) {

                /*Nếu sản phẩm nào >0 tương đương đã được đặt mua*/
                if (pr.getProductQuantity() > 0) {

                    /*Insert sản phẩm vào database*/
                    try {

                        /*Tạo kết nối*/
                        String connectionURL = "jdbc:mysql://localhost/bakeshop";
                        Class.forName("com.mysql.jdbc.Driver");
                        Connection connection = DriverManager.getConnection(connectionURL, "root", "admin");
                        Statement statement = connection.createStatement();

                        /*Insert dữ liệu vào database*/
                        String sql3 = "INSERT INTO `bakeshop`.`order_item` (`OrderID`, `ProductID`, `Quantity`) VALUES ('" + OrderID + "', '" + pr.getProductID() + "', '" + pr.getProductQuantity() + "')";
                        int kq3 = statement.executeUpdate(sql3);

                        /*Trường hợp kết nối không gián đoạn*/
                        if (!connection.isClosed()) {

                            /*Insert thành công, gán lại giá trị = 0*/
                            if (kq3 == 1) {
                               pr.setProductQuantity(0);
                            }
                        }

                        /*Ngược lại báo lỗi ra console*/
                        else {
                            System.out.println("Insert into failed!");
                        }

                        /*Ngắt kết nối databse*/
                        connection.close();
                    }
                    catch (Exception ex) {
                        System.out.println("Unable to connect to database" + ex);
                    }
                }
            }

            /*Sau khi hoàn tất nhập sản phẩm - Quay về trang chủ*/
            resp.sendRedirect("./");
        }

        /*Khi client cung cấp từ khóa ta trả về như sau*/
        if (keyword != null) {

            /*Sử dụng từ khóa để query*/
            try {

                /*Tạo kết nối database*/
                String connectionURL = "jdbc:mysql://localhost/bakeshop";
                Class.forName("com.mysql.jdbc.Driver");
                Connection connection = DriverManager.getConnection(connectionURL, "root", "admin");
                Statement statement = connection.createStatement();

                /*Query sản phẩm với từ khóa*/
                String sql = "select * from products where ProductName LIKE '%" + keyword +"%'";
                products = new ArrayList<>();
                ResultSet resultSet = statement.executeQuery(sql);

                /*Nếu kết nối không gián đoạn và list products bằng 0*/
                if(!connection.isClosed() && products.size() == 0) {

                    /*Duyệt tất cả trường*/
                    while (resultSet.next()) {

                        /*Gán thông tin vào obj product*/
                        Product product = new Product();
                        product.setProductID(resultSet.getString("ProductID"));
                        product.setProductName(resultSet.getString("ProductName"));
                        product.setProductPrice(Integer.parseInt(resultSet.getString("ProductPrice")));
                        product.setProductDetail(resultSet.getString("ProductDetail"));
                        product.setProductIMGURL(resultSet.getString("ProductIMGURL"));

                        /*Add vào list products*/
                        products.add(product);
                    }

                    /*Sử dụng session*/
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
                }

                /*Trường hợp ngược lại*/
                else if (!connection.isClosed() && products.size() != 0) {
                    resp.sendRedirect("product");
                }

                /*Ngắt kết nối database*/
                connection.close();
            }
            catch (Exception ex) {
                System.out.println("Unable to connect to database" + ex);
            }
        }
    }
}