package com.leco.mybatisplus.service;

import java.math.BigDecimal;

/**
 * @author greg
 * @version 2024/6/24
 **/
public interface AccountService {
    /**
     * 转账
     * @param from
     * @param to
     * @param amount
     */
    void transfer(Integer from, Integer to, BigDecimal amount);

    void resetBalance();
}
