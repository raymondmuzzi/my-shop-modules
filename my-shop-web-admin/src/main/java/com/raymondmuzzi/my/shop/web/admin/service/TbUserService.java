package com.raymondmuzzi.my.shop.web.admin.service;

import com.raymondmuzzi.my.shop.common.dto.BaseResult;
import com.raymondmuzzi.my.shop.domain.TbUser;

import java.util.List;

/**
 * @author raymondmuzzi
 * @date 2020-03-20 21:50:49
 */
public interface TbUserService {
    /**
     * Select all users' info
     *
     * @return the user list info
     */
    List<TbUser> selectAll();

    /**
     * Login via username and password
     *
     * @param username user's username
     * @param password user's password
     * @return user's info from DB
     */
    TbUser login(String username, String password);

    /**
     * Save the user's info
     * <p>
     * If the user's id is null, add the user.
     * Or update the user
     *
     * @param tbUser
     * @return the base result
     */
    BaseResult save(TbUser tbUser);

    /**
     * Delete user by id
     *
     * @param id
     * @return affected line count
     */
    int delete(Long id);

    /**
     * Get user by id
     *
     * @param id user's id
     * @return user's info
     */
    TbUser getById(Long id);

    /**
     * Update the user by id
     *
     * @param tbUser updated user
     * @return affected line counts
     */
    int updateById(TbUser tbUser);

    /**
     * Get user info by fuzzy username
     *
     * @param username fuzzy username
     * @return user's info
     */
    List<TbUser> getLikeUsername(String username);

    /**
     * Search the TbUser list by the keyword
     *
     * @param keyword search keyword
     * @return the TbUser list
     */
    List<TbUser> search(String keyword);
}
