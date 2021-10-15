package com.greg.leco.demo.jvm;

/**
 * 类加载 - 双亲委派模式
 * @author greg
 * @version 2021/10/15
 */
public class ClassLoaderDemo {

    public static void main(String[] args) throws ClassNotFoundException {
        ClassLoader classLoader = ClassLoaderDemo.class.getClassLoader();
        Class<?> c = classLoader.loadClass("com.greg.leco.demo.jvm.IncDemo");
        System.out.println(c);
    }
}
