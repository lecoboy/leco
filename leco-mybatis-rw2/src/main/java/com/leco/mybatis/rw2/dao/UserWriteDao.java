package com.leco.mybatis.rw2.dao;

import com.leco.mybatis.rw2.entity.User;

import java.util.List;

public interface UserWriteDao {
    List<User> userList();
}
