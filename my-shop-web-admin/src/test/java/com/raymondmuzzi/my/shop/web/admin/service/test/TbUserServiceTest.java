package com.raymondmuzzi.my.shop.web.admin.service.test;

import com.raymondmuzzi.my.shop.common.dto.BaseResult;
import com.raymondmuzzi.my.shop.domain.TbUser;
import com.raymondmuzzi.my.shop.web.admin.service.TbUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.List;

/**
 * An example for spring test
 *
 * @author raymondmuzzi
 * @date 2020-03-20 22:11:14
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({
        "classpath:spring-context.xml",
        "classpath:spring-context-druid.xml",
        "classpath:spring-context-mybatis.xml"
})
public class TbUserServiceTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(TbUserServiceTest.class);
    @Autowired
    private TbUserService tbUserService;

    @Test
    public void testSelectAll() {
        List<TbUser> tbUsers = tbUserService.selectAll();
        for (TbUser tbUser : tbUsers) {
            LOGGER.info("Result:{}" + tbUser);
        }
    }

    @Test
    public void testInsert() {
        TbUser tbUser = new TbUser();
        tbUser.setUsername("raymond3");
        tbUser.setPassword(DigestUtils.md5DigestAsHex("123456".getBytes()));
        tbUser.setEmail("1232333@gmail.com");
        tbUser.setPhone("123233323");
        BaseResult baseResult = tbUserService.save(tbUser);
        LOGGER.info("Insert status:{}", baseResult.getStatus());
    }

    @Test
    public void testMd5Login() {
        TbUser user = tbUserService.login("raymond", DigestUtils.md5DigestAsHex("muzzi".getBytes()));
        LOGGER.info("The login user is {}", user);
    }

    @Test
    public void testLogin() {
        TbUser login = tbUserService.login("zhangsan", "123456");
        LOGGER.info("Login result:{}", login);
    }

    @Test
    public void testTbUserDelete() {
        int delete = tbUserService.delete(41L);
        LOGGER.info("Deleted {} lines", delete);
    }

    @Test
    public void testTbUserGetById() {
        TbUser user = tbUserService.getById(38L);
        LOGGER.info("The query result is {}", user);
    }

    @Test
    public void testTbUserUpdateById() {
        TbUser tbUser = new TbUser();
        tbUser.setId(39L);
        tbUser.setUsername("raymond");
        tbUser.setPassword(DigestUtils.md5DigestAsHex("123456".getBytes()));
        tbUser.setPhone("updatePhone2");
        tbUser.setEmail("update2@gmail.com");
        tbUser.setCreated(new Date());

        int update = tbUserService.updateById(tbUser);
        LOGGER.info("Updated {} lines", update);
    }

    @Test
    public void testTbUserGetLikeUsername() {
        List<TbUser> list = tbUserService.getLikeUsername("aaaaaaaaaaa");
        for (TbUser tbUser : list) {
            LOGGER.info("Result:{}", tbUser);
        }
    }

    @Test
    public void testTbUserSearch() {
        TbUser tbUser1 = new TbUser();
        tbUser1.setEmail("a");
        tbUser1.setPhone("3");
        List<TbUser> list = tbUserService.search(tbUser1);
        for (TbUser tbUser : list) {
            LOGGER.info("Search result:{}", tbUser);
        }
    }

    @Test
    public void testTbUserDeleteMulti() {
        String ids = "40,47";
        int n = tbUserService.deleteMulti(ids.split(","));
        LOGGER.info("Affected row number is {}", n);
    }
}