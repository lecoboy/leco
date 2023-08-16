package com.leco.demo.jvm;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * try-with-resources 语法糖
 * @author greg
 * @version 2021/10/13 22:26
 */
public class TwrDemo {

    public static void main(String[] args) {
        try (InputStream is = new FileInputStream("object.txt")) {
            System.out.println(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
