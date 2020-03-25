package com.raymondmuzzi.my.shop.web.admin.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({
        "classpath:spring-context.xml",
        "classpath:spring-context-druid.xml",
        "classpath:spring-context-mybatis.xml"
})
public class TbUserDaoTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(TbUserDaoTest.class);
    @Autowired
    private TbUserDao tbUserDao;

    @Test
    public void testUserDaoDeleteMulti() {
        String[] ids = {"54", "48"};
        int number = tbUserDao.deleteMulti(ids);
        LOGGER.info("Affected row number is {}", number);
    }
}