package com.leco.mybatisplus.dto;

import com.leco.mybatisplus.entity.UserRole;
import lombok.Data;

import java.util.List;

/**
 * @author greg
 * @version 2024/8/12
 **/
@Data
public class UserDTO {
    private Integer id;
    private String name;
    private List<UserRole> userRoles;
}
