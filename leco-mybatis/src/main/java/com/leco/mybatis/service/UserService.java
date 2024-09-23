package com.leco.mybatis.service;

import com.leco.mybatis.entity.User;

import java.util.List;

public interface UserService {
    List<User> userList();
    int insertUser(User user);
}
