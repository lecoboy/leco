package com.leco.web.controller;

import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author greg
 * @version 2023/8/16
 * 说明：研究分布式环境下，扣库存时如何防止超卖问题
 **/
@Controller
@RequestMapping("/stock")
public class StockController {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private Redisson redisson;

    @GetMapping("/deduct")
    @ResponseBody
    public String deduct() {
        return deduct_redisson();
    }

    private String deduct_redisson() {
        String lockKey = "lockKey";
        RLock lock = redisson.getLock(lockKey);
        try {
            lock.lock();
            deductProcess();
        } finally {
            lock.unlock();
        }
        return "success";
    }

    /**
     * 这种方式存在一个问题，就是当线程运行时间超过30秒，另一个线程会进去，也可能导致超卖问题
     * @return
     */
    private String deduct_1() {
        String lockKey = "lockKey";
        String clientId = UUID.randomUUID().toString();
        try {
            // 过期时间可以应对服务器宕机，锁无法被删除的场景
            Boolean lock = stringRedisTemplate.opsForValue().setIfAbsent(lockKey, clientId, 30, TimeUnit.SECONDS);
            if (!lock) {
                return "error";
            }
            deductProcess();
        } finally {
            // 防止删掉其他线程的lockKey
            if (clientId.equals(stringRedisTemplate.opsForValue().get(lockKey))) {
                stringRedisTemplate.delete(lockKey);
            }
        }
        return "success";
    }

    private void deductProcess() {
        int stock = Integer.parseInt(stringRedisTemplate.opsForValue().get("stock"));
        if (stock > 0) {
            int realStock = stock - 1;
            stringRedisTemplate.opsForValue().set("stock", realStock + "");
            System.out.println("扣减成功，剩余库存：" + realStock);
        } else {
            System.out.println("库存不足，扣减失败！");
        }
    }
}
