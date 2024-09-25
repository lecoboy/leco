package com.leco.mybatis.rw2.dao_read;

import com.leco.mybatis.rw2.entity.User;

import java.util.List;

public interface UserReadDao {
    List<User> userList();
}
