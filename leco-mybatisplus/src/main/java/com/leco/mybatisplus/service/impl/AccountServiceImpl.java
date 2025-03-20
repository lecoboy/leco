package com.leco.mybatisplus.service.impl;

import com.leco.mybatisplus.dao.AccountDao;
import com.leco.mybatisplus.entity.Account;
import com.leco.mybatisplus.service.AccountService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;

/**
 * @author greg
 * @version 2024/6/24
 **/
//@Service
public class AccountServiceImpl implements AccountService {
    @Resource
    private AccountDao accountDao;

    @Transactional(rollbackFor = Exception.class) //默认只会回滚非检查异常，这样写可以回滚所有异常
    @Override
    public void transfer(Integer from, Integer to, BigDecimal amount) /*throws FileNotFoundException */{
//        try {
        Account accountFrom = accountDao.selectById(from);
        Account accountTo = accountDao.selectById(to);
        if (amount.compareTo(accountFrom.getBalance()) > 0) {
            throw new RuntimeException("余额不足");
        }
        accountFrom.setBalance(accountFrom.getBalance().subtract(amount));
        accountTo.setBalance(accountTo.getBalance().add(amount));
        accountDao.updateById(accountFrom);
        // 制造一个异常
//            int a = 1/0;//除零异常
//            if (amount.compareTo(BigDecimal.ZERO) > 0) {
//                throw new RuntimeException("制造异常");//自定义异常
//            }
//        new FileInputStream("ddd");//检查异常
        accountDao.updateById(accountTo);
//        } catch (Exception e) {
//            e.printStackTrace();
//            // 事务通知只有捕捉到了目标抛出的异常，才能进行回滚处理；如果目标自己处理掉异常，事务通知就不会回滚。
//            throw new RuntimeException("系统错误");
//        }
    }

    @Override
    public void resetBalance() {
        accountDao.resetBalance();
    }

    @Transactional(rollbackFor = Exception.class)
    void testNonPublicTransactional() {//非public方法会导致事务失效，无法回滚
        BigDecimal amount = new BigDecimal("100");
        Account accountFrom = accountDao.selectById(1);
        Account accountTo = accountDao.selectById(2);
        accountFrom.setBalance(accountFrom.getBalance().subtract(amount));
        accountTo.setBalance(accountTo.getBalance().add(amount));
        accountDao.updateById(accountFrom);
        accountDao.updateById(accountFrom);
        // 制造一个异常
        int a = 1 / 0;//除零异常
        accountDao.updateById(accountTo);
    }
}
