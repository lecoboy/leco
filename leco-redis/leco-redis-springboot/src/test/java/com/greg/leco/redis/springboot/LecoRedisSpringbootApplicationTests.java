package com.greg.leco.redis.springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
class LecoRedisSpringbootApplicationTests {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

//    @Test
    void contextLoads() {
        redisTemplate.opsForValue().set("k1", "中文");
        System.out.println(redisTemplate.opsForValue().get("k1"));
    }

}
