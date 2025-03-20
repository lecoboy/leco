package com.greg.leco.demo;

public class WhileDemo {
    public static void main(String[] args) {
        int i = 0;
        while (i < 100) {
            // 第一次输出为10而不是0，所以是先执行+=，再执行printLn
            System.out.println(i+=10);
        }
    }
}
