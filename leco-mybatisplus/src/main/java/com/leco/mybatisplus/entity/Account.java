package com.leco.mybatisplus.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author greg
 * @version 2024/6/24
 **/
@Data
public class Account {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String name;
    private BigDecimal balance;
}
