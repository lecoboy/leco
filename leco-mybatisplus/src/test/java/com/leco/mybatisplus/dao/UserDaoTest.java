package com.leco.mybatisplus.dao;

import com.leco.mybatisplus.dto.UserDTO;
import com.leco.mybatisplus.entity.UserRole;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeanUtils;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author greg
 * @version 2024/8/12
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserDaoTest {
    @Resource
    private UserRoleDao userRoleDao;
    @Resource
    private UserDao userDao;

    @Test
    public void testQueryUsers() {
        // sql一对多分页，并且join的表可以用来作为查询条件
        List<UserDTO> userDTOS = userDao.queryUsers(0, 5, "组");
        List<Integer> userIds = userDTOS.stream().map(UserDTO::getId).collect(Collectors.toList());
        List<UserRole> userRoles = userRoleDao.userRolesByUserIds(userIds);
        Map<Integer, List<UserRole>> userRoleMap = userRoles.stream().collect(Collectors.groupingBy(UserRole::getUserId, Collectors.toList()));
        userDTOS.forEach(u -> {
            u.setUserRoles(userRoleMap.get(u.getId()));
        });
        System.out.println(userDTOS);
    }

    @Test
    public void test() {
        UserRole userRole = userRoleDao.selectById(1);
        System.out.println(userRole);
    }
}
