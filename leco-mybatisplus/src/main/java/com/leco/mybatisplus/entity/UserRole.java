package com.leco.mybatisplus.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * @author greg
 * @version 2024/8/12
 **/
@Data
public class UserRole {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String name;
    private Integer userId;
}
