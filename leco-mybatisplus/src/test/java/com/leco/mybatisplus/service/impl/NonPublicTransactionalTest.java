package com.leco.mybatisplus.service.impl;

import org.junit.jupiter.api.Test;
//import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author greg
 * @version 2024/6/24
 **/
//@RunWith(SpringRunner.class)
//@SpringBootTest
public class NonPublicTransactionalTest {

    @Autowired
    private AccountServiceImpl accountService;

    ////@Test
    public void testNonPublicTransactionalTest() {
        accountService.testNonPublicTransactional();
    }
}
