package com.raymondmuzzi.my.shop.web.admin.dao.impl;

import com.raymondmuzzi.my.shop.domain.User;
import com.raymondmuzzi.my.shop.web.admin.dao.UserDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

/**
 * @author raymondmuzzi
 * @date 2020-03-13 22:17:59
 */
@Repository("userDao")
public class UserDaoImpl implements UserDao {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserDaoImpl.class);

    @Override
    public User getUser(String email, String password) {

        LOGGER.debug("Invoke UserDaoImpl getUser(), parameters email:{} password:{}", email, password);

        User user = null;
        if ("admin@gmail.com".equals(email) && "admin".equals(password)) {
            user = new User();
            user.setEmail(email);
            user.setUsername("admin");

            LOGGER.info("Get user {} info succeed", user.getUsername());
        } else {
            LOGGER.warn("Get user {} info failed", email);
        }
        return user;
    }
}
