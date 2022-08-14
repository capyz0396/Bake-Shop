package com.example.test.model;

public class Account {

    /*Các biến lưu giá trị cho bean*/
    private String username;
    private String password;
    private String displayName;
    private String address;
    private String email;
    private String phone;

    private String error;

    /*Sử dụng biến logged để check trạng thái logged của người dùng*/
    private boolean logged = false;

    /*Nếu người dùng mới đăng nhập thành công thì hiện thông báo*/
    private int time = 1;


    /**
     * Hàm khởi tạo mặc định để useBean có thể access vào
     */
    public Account() {}


    /**
     * Hàm khởi tạo dành cho setBean ở LoginController
     * @param username
     * @param password
     */
    public Account(String username, String password) {
        setUsername(username);
        setPassword(password);
        this.logged = true;
    }


    /**
     * Hàm getUsername
     * @return
     */
    public String getUsername() {
        return username;
    }


    /**
     * Hàm setUsername
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }


    /**
     * Hàm getPassword
     * @return
     */
    public String getPassword() {
        return password;
    }


    /**
     * Hàm setPassword
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }


    /**
     * Hàm get trạng thái logged
     * @return
     */
    public boolean isLogged() {
        return logged;
    }


    /**
     * Hàm set trạng thái logged hoặc logout
     * @param logged
     */
    public void setLogged(boolean logged) {
        this.logged = logged;
    }


    /**
     * Hàm get first time logged
     * @return
     */
    public int getTime() {
        if (time == 1) {
            time = 2;
            return 1;
        }
        return time;
    }


    /**
     * Hàm set time cho những lần sau
     * @param time
     */
    public void setTime(int time) {
        this.time = time;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getError() {
        return error;
    }
    public boolean validate() {

        if (username.length() == 0 || password.length() == 0) {
            error = "Username and password cannot be null";
            return false;
        }

        /*Trường hợp client nhập input dưới 6 kí tự*/
        else if (username.length() < 6 || password.length() < 6) {
            error = "Username and password must be at least 6 characters";
            return false;
        }

        /*Trường hợp có kí tự khoảng trống trong username và password*/
        else if (username.matches("\\w*\\s+\\w*")) {
            error = "Username and password cannot contain space";
            return false;
        }
        else {
            return true;
        }
    }
}