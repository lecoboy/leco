package com.leco.mybatisplus.service.impl;

import com.leco.mybatisplus.service.AccountService;
import org.junit.jupiter.api.Test;
//import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.FileNotFoundException;
import java.math.BigDecimal;

/**
 * @author greg
 * @version 2024/6/24
 **/
//@RunWith(SpringRunner.class)
//@SpringBootTest
public class AccountServiceTest {

    @Autowired
    private AccountService accountService;

    //@Test
    public void testTransfer() throws FileNotFoundException {
        accountService.transfer(1, 2, new BigDecimal("100"));
    }

    //@Test
    public void testResetBalance() {
        accountService.resetBalance();
    }

}
