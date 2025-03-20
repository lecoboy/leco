package com.leco.mybatis.service.impl;

import com.leco.mybatis.annotation.Master;
import com.leco.mybatis.annotation.Slave;
import com.leco.mybatis.dao.UserDao;
import com.leco.mybatis.entity.User;
import com.leco.mybatis.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

//@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserDao userDao;

    @Slave
    @Override
    public List<User> userList() {
        return userDao.userList();
    }

    @Master
    @Override
    public int insertUser(User user) {
        return userDao.insertUser(user);
    }
}
