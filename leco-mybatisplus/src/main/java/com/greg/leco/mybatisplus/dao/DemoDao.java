package com.greg.leco.mybatisplus.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.greg.leco.mybatisplus.entity.Demo;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author greg
 * @version 2021/12/27
 */
@Mapper
public interface DemoDao extends BaseMapper<Demo> {
}
