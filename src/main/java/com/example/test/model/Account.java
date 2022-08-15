package com.example.test.model;

public class Account {

    /*Variable of account object*/
    private String username;
    private String password;
    private String displayName;
    private String address;
    private String email;
    private String phone;

    /*Error for notification*/
    private String error;

    /*Logged variable check user logged or not*/
    private boolean logged = false;

    /*First time logged, notification will show notification*/
    private int time = 1;


    /**
     * Construction for bean and account haven't data
     */
    public Account() {}


    /**
     * Construction for create object with data
     * @param username
     * @param password
     */
    public Account(String username, String password) {
        setUsername(username);
        setPassword(password);
        this.logged = true;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isLogged() {
        return logged;
    }

    public void setLogged(boolean logged) {
        this.logged = logged;
    }

    public int getTime() {
        if (time == 1) {
            time = 2;
            return 1;
        }
        return time;
    }

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


    /**
     * Vilidate fuction check username, password and return error
     * @return
     */
    public boolean validate() {

        /*Null case, return false*/
        if (username.length() == 0 || password.length() == 0) {
            error = "Username and password cannot be null";
            return false;
        }

        /*Short case, return false*/
        else if (username.length() < 6 || password.length() < 6) {
            error = "Username and password must be at least 6 characters";
            return false;
        }

        /*Backspace case, return false*/
        else if (username.matches("\\w*\\s+\\w*")) {
            error = "Username and password cannot contain space";
            return false;
        }

        /*Otherwise, return true*/
        else {
            return true;
        }
    }
}