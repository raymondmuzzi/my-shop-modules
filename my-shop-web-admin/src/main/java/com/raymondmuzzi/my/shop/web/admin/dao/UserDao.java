package com.raymondmuzzi.my.shop.web.admin.dao;

import com.raymondmuzzi.my.shop.domain.User;

/**
 * UserDao interface
 *
 * @author raymondmuzzi
 * @date 2020-03-13 22:09:42
 */
public interface UserDao {

    /**
     * Get the user from DB by email and password
     *
     * @param email    user's email
     * @param password user's password
     * @return the user entity from DB
     */
    User getUser(String email, String password);
}
