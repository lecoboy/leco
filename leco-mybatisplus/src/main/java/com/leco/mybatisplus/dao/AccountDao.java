package com.leco.mybatisplus.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.leco.mybatisplus.entity.Account;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

/**
 * @author greg
 * @version 2024/6/24
 **/
@Mapper
public interface AccountDao extends BaseMapper<Account> {
    @Update("update `account` set balance=1000 where id>0 and id<3")
    int resetBalance();
}
