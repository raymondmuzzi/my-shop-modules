package com.raymondmuzzi.my.shop.domain;

import java.io.Serializable;

/**
 * @author raymondmuzzi
 * @date 2020-03-13 22:09:18
 */
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    private String username;
    private String password;
    private String email;

    /** Remember me button in home page */
    private boolean isRemembered;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isRemembered() {
        return isRemembered;
    }

    public void setRemembered(boolean remembered) {
        isRemembered = remembered;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
