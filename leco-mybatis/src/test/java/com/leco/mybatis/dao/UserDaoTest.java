package com.leco.mybatis.dao;

import com.leco.mybatis.context.DBContextHolder;
import com.leco.mybatis.entity.User;
import org.junit.jupiter.api.Test;
//import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

//@RunWith(SpringRunner.class)
@SpringBootTest
public class UserDaoTest {
    @Resource
    private UserDao userDao;

    //@Test
    public void testGetUserById() {
        User user = userDao.getUserById(0);
        System.out.println(user);
    }
    
    //@Test
    public void testUserList() {
        DBContextHolder.master();
        List<User> users = userDao.userList();
        System.out.println(users);
    }

    //@Test
    public void testSlaveReadMasterWrite() {
        // slave read
        DBContextHolder.slave();
        List<User> users = userDao.userList();
        System.out.println(users);
        // master write
        DBContextHolder.master();
        User user = new User();
        user.setName("master insert test 001");
        userDao.insertUser(user);
    }
}
