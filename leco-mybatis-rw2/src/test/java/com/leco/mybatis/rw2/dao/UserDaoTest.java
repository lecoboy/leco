package com.leco.mybatis.rw2.dao;

import com.leco.mybatis.rw2.dao_read.UserReadDao;
import com.leco.mybatis.rw2.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserDaoTest {
    @Resource
    private UserDao userDao;
    @Resource
    private UserWriteDao userWriteDao;
    @Resource
    private UserReadDao userReadDao;

    @Test
    public void userDaoTest() {
        List<User> users = userDao.userList();
        System.out.println(users);
    }

    @Test
    public void userWriteDaoTest() {
        List<User> users = userWriteDao.userList();
        System.out.println(users);
    }

    @Test
    public void userReadDaoTest() {
        List<User> users = userReadDao.userList();
        System.out.println(users);
    }
}
