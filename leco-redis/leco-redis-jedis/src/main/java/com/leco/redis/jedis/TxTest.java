package com.leco.redis.jedis;

import com.alibaba.fastjson.JSONObject;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

/**
 * @author greg
 * @version 2021/12/20 22:20
 * 事务测试
 */
public class TxTest {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("127.0.0.1", 6379);

        jedis.flushDB();

        JSONObject user1 = new JSONObject();
        user1.put("name", "zhangsan");
        user1.put("age", 17);

        JSONObject user2 = new JSONObject();
        user2.put("name", "lisi");
        user2.put("age", 19);

        Transaction multi = jedis.multi();

        try {
            multi.set("user1", user1.toJSONString());
            multi.set("user2", user2.toJSONString());
//            int i = 1 / 0;
            multi.exec();
        } catch (Exception e) {
            multi.discard();
            e.printStackTrace();
        } finally {
            System.out.println(jedis.get("user1"));
            System.out.println(jedis.get("user2"));
            multi.close();
        }

    }
}
