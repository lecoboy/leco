package com.leco.demo.jvm.jmm;

/**
 * 原子性
 * i++,i-- 不是原子性操作
 * @author greg
 * @version 2021/10/18 0:06
 */
public class JMMDemo1 {

    volatile static int i = 0;

    public static void main(String[] args) throws InterruptedException {

        Thread t1 = new Thread(() -> {
            for (int j = 0; j < 5000; j++) {
                i++;
            }
        });
        Thread t2 = new Thread(() -> {
            for (int j = 0; j < 5000; j++) {
                i--;
            }
        });
        t1.start();
        t2.start();

        t1.join();
        t2.join();

        // volatile是保证了可见性，但不保证原子性，所以这里输出结果不一定是0
        System.out.println(i);
    }
}
