package com.raymondmuzzi.my.shop.web.admin.service.impl;

import com.raymondmuzzi.my.shop.domain.User;
import com.raymondmuzzi.my.shop.web.admin.dao.UserDao;
import com.raymondmuzzi.my.shop.web.admin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author raymondmuzzi
 * @date 2020-03-13 22:22:43
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public User login(String email, String password) {
        return userDao.getUser(email, password);
    }
}
