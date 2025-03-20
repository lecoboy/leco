package com.greg.leco.demo;

import java.util.Base64;

public class EncodeDemo {
    public static void main(String[] args) {
        String str = Base64.getEncoder().encodeToString("abcd".getBytes());
        System.out.println(str);
    }
}
