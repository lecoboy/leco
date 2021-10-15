package com.greg.leco.demo.jvm;

import org.junit.Test;

/**
 * 了解一下 iinc 指令
 *
 * 反编译 class 文件:
 * javap -v IncDemo.class
 *
 * @author greg
 * @version 2021/10/10 17:00
 */
public class IncDemo {

    public static void main(String[] args) {
        int a = 10;
        int b = a++ + ++a + a--;
        System.out.println(a);
        System.out.println(b);
    }

    @Test
    public void test() {
        int i = 0;
        int x = 0;
        while (i < 10) {
            x = x++;
            i++;
        }
        System.out.println(x);
    }

    @Test
    public void test2() {
        int x = 6;
        x = x++;
        System.out.println(x);
    }

}
