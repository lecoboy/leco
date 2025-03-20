package com.leco.mybatisplus;

import com.leco.mybatisplus.dao.DemoDao;
import com.leco.mybatisplus.entity.Demo;
import org.junit.jupiter.api.Test;
//import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Random;

//import org.junit.jupiter.api.Test;
//@RunWith(SpringRunner.class)
//@SpringBootTest
public class DemoDaoTest {

    @Resource
    private DemoDao demoDao;

//    @DynamicTest
    void testSelectById() {
        Demo demo = demoDao.selectById(1);
        System.out.println(demo);
    }

    //@org.junit.jupiter.api.Test
    public void testInsert() {
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
