package com.leco.demo;


import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class StringAndByteDemo {
    
    //@Test
    public void testBytesToStringToBytes() {
        byte[] bytes = new byte[] { 50, 0, -1, 28, -24 };
        System.out.println(Arrays.toString(bytes));
        String string = new String(bytes);
        byte[] ret = string.getBytes();
        System.out.println(ret.length);
        System.out.println(string);
        System.out.println(new String(ret));
    }
}
