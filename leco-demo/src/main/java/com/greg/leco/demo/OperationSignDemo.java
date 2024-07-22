package com.greg.leco.demo;

/**
 * @author greg
 * @version 2024/7/22
 * 了解各种运算符号
 **/
public class OperationSignDemo {
    public static void main(String[] args) {
        int n = 15;// int n = 16;
        for(int i = 0; i < 100; i++) {
            System.out.print(String.format("%d & %d = %d", i, n, i & n));
            System.out.println(String.format(" -- %d %% %d = %d", i, n, i % n));
        }
    }
}
