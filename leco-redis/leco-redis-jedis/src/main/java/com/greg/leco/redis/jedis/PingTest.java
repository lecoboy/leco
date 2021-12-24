package com.greg.leco.redis.jedis;

import redis.clients.jedis.Jedis;

/**
 * @author greg
 * @version 2021/12/20 22:09
 */
public class PingTest {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        System.out.println(jedis.ping());
    }
}
