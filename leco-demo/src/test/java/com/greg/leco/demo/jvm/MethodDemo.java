package com.greg.leco.demo.jvm;

/**
 * 了解调用方法时的字节码
 * @author greg
 * @version 2021/10/11 0:44
 */
public class MethodDemo {

    public MethodDemo() {}

    private void test1() {}

    private final void test2() {}

    public void test3() {}

    public static void test4() {}

    public static void main(String[] args) {
        MethodDemo demo = new MethodDemo();
        demo.test1();
        demo.test2();
        demo.test3();
        demo.test4();
        MethodDemo.test4();
    }

}
