package com.raymondmuzzi.my.shop.web.admin.service.impl;

import com.raymondmuzzi.my.shop.common.constant.StatusEnum;
import com.raymondmuzzi.my.shop.common.dto.BaseResult;
import com.raymondmuzzi.my.shop.common.utils.RegexpUtils;
import com.raymondmuzzi.my.shop.domain.TbUser;
import com.raymondmuzzi.my.shop.web.admin.dao.TbUserDao;
import com.raymondmuzzi.my.shop.web.admin.service.TbUserService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * TbUserService's implementation class
 *
 * @author raymondmuzzi
 * @date 2020-03-20 21:51:28
 */
@Service
public class TbUserServiceImpl implements TbUserService {
    private static final Logger LOGGER = LoggerFactory.getLogger(TbUserServiceImpl.class);
    @Autowired
    private TbUserDao tbUserDao;

    @Override
    public List<TbUser> selectAll() {
        return tbUserDao.selectAll();
    }

    @Override
    public TbUser login(String username, String password) {
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
            throw new RuntimeException("User's username or password can not be empty");
        }
        TbUser tbUser = tbUserDao.selectUserByUsername(username);
        if (tbUser != null) {
            // encrypt password and compare with db's password
            String md5Password = DigestUtils.md5DigestAsHex(password.getBytes());
            if (md5Password.equals(tbUser.getPassword())) {
                return tbUser;
            }
        }
        return null;
    }

    @Override
    public BaseResult save(TbUser tbUser) {
        if (tbUser == null) {
            throw new RuntimeException("TbUser cannot be null");
        } else {
            // check the form data
            BaseResult baseResult = checkTbUser(tbUser);

            if (StatusEnum.getByValue(StatusEnum.SUCCESS) == baseResult.getStatus()) {
                // add user
                if (null == tbUser.getId()) {
                    // encrypt the password
                    tbUser.setPassword(DigestUtils.md5DigestAsHex(tbUser.getPassword().getBytes()));
                    try {
                        if (tbUserDao.insert(tbUser) > 0) {
                            baseResult.setMessage("Add user success");
                            LOGGER.info("Add user {} success", tbUser.getUsername());
                        } else {
                            baseResult.setStatus(StatusEnum.getByValue(StatusEnum.SERVER_ERROR));
                            baseResult.setMessage("No data insert into the db");
                            LOGGER.warn("Add user {} failed, no data insert into the db", tbUser.getUsername());
                        }
                    } catch (Exception e) {
                        if (e instanceof DuplicateKeyException) {
                            baseResult.setStatus(StatusEnum.getByValue(StatusEnum.SERVER_ERROR));
                            baseResult.setMessage("Add user failed, username or email or phone already exists");
                            LOGGER.warn("Add user failed, username:{} or email:{} or phone:{} already exists",
                                    tbUser.getUsername(),
                                    tbUser.getEmail(),
                                    tbUser.getPhone());
                        } else {
                            baseResult.setStatus(StatusEnum.getByValue(StatusEnum.SERVER_ERROR));
                            LOGGER.warn("Add user failed, exception message is {}", e.getMessage());
                        }
                    }
                }
                // update user
                else {
                    // TODO update the password or not, should be re-judged ###
                    if (StringUtils.isNoneEmpty(tbUser.getPassword())) {
                        tbUser.setPassword(DigestUtils.md5DigestAsHex(tbUser.getPassword().getBytes()));
                    }
                    tbUserDao.updateById(tbUser);
                    baseResult.setMessage("Add user success");
                }
            }

            return baseResult;
        }
    }

    @Override
    public int delete(Long id) {
        if (id <= 0) {
            throw new RuntimeException(String.format("Illegal user's id: %d", id));
        }
        return tbUserDao.delete(id);
    }

    @Override
    public TbUser getById(Long id) {
        if (id <= 0) {
            throw new RuntimeException(String.format("Illegal user's id: %d", id));
        }
        return tbUserDao.getById(id);
    }

    @Override
    public int updateById(TbUser tbUser) {
        if (tbUser == null) {
            throw new RuntimeException("The updated user cannot be null");
        }
        return tbUserDao.updateById(tbUser);
    }

    @Override
    public List<TbUser> getLikeUsername(String username) {
        if (StringUtils.isEmpty(username)) {
            throw new RuntimeException("The username cannot be empty");
        }
        List<TbUser> userList = tbUserDao.getByUsernameLike(username);
        if (userList == null) {
            userList = new ArrayList<>();
        }
        return userList;
    }

    /**
     * Check the user's save info is legal or not
     *
     * @param tbUser
     * @return the base result
     */
    private BaseResult checkTbUser(TbUser tbUser) {
        // default result is success
        BaseResult baseResult = BaseResult.success();
        if (tbUser == null) {
            baseResult = BaseResult.fail("TbUser cannot be null");
        }

        else if (StringUtils.isBlank(tbUser.getUsername())) {
            baseResult = BaseResult.fail("The username cannot be empty");
        }

        else if (StringUtils.isBlank(tbUser.getPassword())) {
            baseResult = BaseResult.fail("The password cannot be empty");
        }

        else if (StringUtils.isBlank(tbUser.getPhone())) {
            baseResult = BaseResult.fail("The phone cannot be empty");
        }

        else if (!RegexpUtils.checkPhone(tbUser.getPhone())) {
            baseResult = BaseResult.fail("The phone number is illegal");
        }

        else if (StringUtils.isBlank(tbUser.getEmail())) {
            baseResult = BaseResult.fail("The email cannot be empty");
        }

        else if (!RegexpUtils.checkEmail(tbUser.getEmail())) {
            baseResult = BaseResult.fail("The email is illegal");
        }

        return baseResult;
    }
}
