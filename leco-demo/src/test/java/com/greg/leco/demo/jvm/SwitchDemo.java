package com.greg.leco.demo.jvm;

/**
 * 看看 switch 字符串 生成的字节码长啥样
 * @author greg
 * @version 2021/10/13 0:19
 */
public class SwitchDemo {

    public static void main(String[] args) {
        String str = "hello";
        switch (str) {
            case "hello":
                System.out.println("h");
                break;
            case "world":
                System.out.println("w");
                break;
        }
    }

}
