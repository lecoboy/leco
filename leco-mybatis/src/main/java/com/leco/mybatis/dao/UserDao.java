package com.leco.mybatis.dao;

import com.leco.mybatis.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserDao {
    @Select("select u.* from user u")
    List<User> userList();
    
    @Select("select u.* from user u where u.id = #{id}")
    User getUserById(@Param("id") Integer id);

    @Insert("insert into user(`name`) values(#{user.name})")
    int insertUser(@Param("user") User user);
}
