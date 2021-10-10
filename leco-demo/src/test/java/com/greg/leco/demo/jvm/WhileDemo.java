package com.greg.leco.demo.jvm;

/**
 * 了解循环相关指令
 * @author greg
 * @version 2021/10/10 17:40
 */
public class WhileDemo {

    public void testWhile() {
        int a = 0;
        while (a < 10) {
            a++;
        }
    }

    public void testDoWhile() {
        int a = 0;
        do {
            a++;
        } while (a < 10);
    }

    public void testFor() {
        for (int i=0; i < 10; i++) {

        }
    }
}
