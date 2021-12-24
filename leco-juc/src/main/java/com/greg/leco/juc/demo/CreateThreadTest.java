package com.greg.leco.juc.demo;

import lombok.extern.slf4j.Slf4j;


/**
 * @author greg
 * @version 2021/10/20 23:30
 * 创建线程的几种不同方式
 */
@Slf4j
public class CreateThreadTest {

    public static void main(String[] args) {
        test1();
    }

    private static void test1() {
        Thread t = new Thread() {
            @Override
            public void run() {
                log.debug("t1 run");
            }
        };
        t.setName("t1");
        t.start();
        log.debug("main run");
    }

    private static void test2() {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                log.debug("t1 run");
            }
        };
        Thread t1 = new Thread(runnable, "t1");
        t1.start();
    }

    private static void test3() {
        Runnable runnable = () -> {log.debug("t1 run");};
        Thread t1 = new Thread(runnable, "t1");
        t1.start();
    }
}
