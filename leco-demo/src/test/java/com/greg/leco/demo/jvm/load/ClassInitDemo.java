package com.greg.leco.demo.jvm.load;

/**
 * 类初始化
 * @author greg
 * @version 2021/10/15
 */
public class ClassInitDemo {

    public static void main(String[] args) {
        System.out.println(B.class);
    }

    static class A {
        static final int i = 10;
        static final String s = "hello";
        static final B b = null;

        static {
            System.out.println("A init");
        }
    }
    static class B {
        static {
            System.out.println("B init");
        }
    }
}
