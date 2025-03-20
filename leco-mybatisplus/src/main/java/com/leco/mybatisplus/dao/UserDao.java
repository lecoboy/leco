package com.leco.mybatisplus.dao;

import com.leco.mybatisplus.dto.UserDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author greg
 * @version 2024/8/12
 **/
//@Mapper
public interface UserDao {
    List<UserDTO> queryUsers(@Param("index") Integer index,
                             @Param("count") Integer count,
                             @Param("roleName") String roleName);
}
