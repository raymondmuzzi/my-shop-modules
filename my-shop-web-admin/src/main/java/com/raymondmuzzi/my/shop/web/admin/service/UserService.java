package com.raymondmuzzi.my.shop.web.admin.service;

import com.raymondmuzzi.my.shop.domain.User;

/**
 * UserService interface
 *
 * @author raymondmuzzi
 * @date 2020-03-13 22:21:28
 */
public interface UserService {
    /**
     * Login via email and password
     *
     * @param email    user's email
     * @param password user's password
     * @return user's info from DB
     */
    User login(String email, String password);
}

