package com.leco.mybatisplus.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author greg
 * @version 2021/12/27
 */
@TableName("t")
@Data
public class Demo {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private Integer a;
    private Integer b;
}
