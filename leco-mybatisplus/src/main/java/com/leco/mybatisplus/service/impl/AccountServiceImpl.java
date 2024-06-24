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
@Service
public class AccountServiceImpl implements AccountService {
    @Resource
    private AccountDao accountDao;

    @Transactional
    @Override
    public void transfer(Integer from, Integer to, BigDecimal amount) {
        try {
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
            accountDao.updateById(accountTo);
        } catch (Exception e) {
            e.printStackTrace();
            // 事务通知只有捕捉到了目标抛出的异常，才能进行回滚处理；如果目标自己处理掉异常，事务通知就不会回滚。
            throw new RuntimeException("系统错误");
        }
    }

    @Override
    public void resetBalance() {
        accountDao.resetBalance();
    }
}
