package com.greg.leco.mybatisplus;

import com.greg.leco.mybatisplus.dao.DemoDao;
import com.greg.leco.mybatisplus.entity.Demo;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.Random;

@SpringBootTest
class DemoDaoTest {

    @Resource
    private DemoDao demoDao;

    @Test
    void testSelectById() {
        Demo demo = demoDao.selectById(1);
        System.out.println(demo);
    }

    @Test
    void testInsert() {
        Random random = new Random();
        Demo demo = new Demo();
        for (int i = 0; i < 1000000; i++) {
            demo.setId(null);
            demo.setA(random.nextInt(10000));
            demo.setB(random.nextInt(10000));
            demoDao.insert(demo);
        }
    }

}
