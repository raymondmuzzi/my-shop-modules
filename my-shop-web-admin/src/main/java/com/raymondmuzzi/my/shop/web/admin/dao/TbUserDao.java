package com.raymondmuzzi.my.shop.web.admin.dao;

import com.raymondmuzzi.my.shop.domain.TbUser;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author raymondmuzzi
 * @date 2020-03-20 21:41:17
 */
@Repository
public interface TbUserDao {

    /**
     * Select all user's info in db
     *
     * @return the user list result
     */
    List<TbUser> selectAll();

    /**
     * Get tbUser by username and password
     *
     * @param username
     * @return the user's info via username and password
     */
    TbUser selectUserByUsername(@Param("username") String username);

    /**
     * Add user
     *
     * @param tbUser added user's info
     * @return affected line counts
     */
    int insert(TbUser tbUser);

    /**
     * Delete user
     *
     * @param id user's id
     * @return affected line counts
     */
    int delete(@Param("id") Long id);

    /**
     * Get user by id
     *
     * @param id user's id
     * @return user's info
     */
    TbUser getById(@Param("id") Long id);

    /**
     * Update the user by id
     *
     * @param tbUser the updated user
     * @return affected line counts
     */
    int updateById(TbUser tbUser);

    /**
     * Get user info by fuzzy username
     *
     * @param username fuzzy username
     * @return user's info list
     */
    List<TbUser> getByUsernameLike(@Param("username") String username);

    /**
     * Search the List<TbUser> where matched the input param
     *
     * @param tbUser the query param encapsulated in the TbUser object
     * @return the query result
     */
    List<TbUser> search(TbUser tbUser);


    /**
     * Delete multi user data in db by id array
     *
     * @param ids id array
     * @return affected row number
     */
    int deleteMulti(@Param("ids") String[] ids);
}
