package com.leco.mybatisplus.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.leco.mybatisplus.entity.UserRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author greg
 * @version 2024/8/12
 **/
@Mapper
public interface UserRoleDao extends BaseMapper<UserRole> {
    List<UserRole> userRolesByUserIds(@Param("userIds") List<Integer> userIds);
}
