package com.leco.demo.jvm;

/**
 * @author greg
 * @version 2021/10/10 17:56
 */
public class CInitDemo {
    static int i = 10;

    static {
        i = 20;
    }

    static {
        i = 30;
    }

}
