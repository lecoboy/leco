package com.leco.mybatisplus.dao;

import com.leco.mybatisplus.entity.Account;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
//import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author greg
 * @version 2024/8/11
 **/
//@RunWith(SpringRunner.class)
//@SpringBootTest
public class AccountDaoTest {
    @Resource
    private AccountDao accountDao;

    //@Test
    public void testAccountsByStatuses() {
        ArrayList<String> strList = Lists.newArrayList();
        strList.add("apple");
        List<Account> accounts = accountDao.accountsByStatuses(Lists.newArrayList((byte) 1));
        System.out.println(accounts);
    }
}
