package com.greg.leco.juc.test.utils;

/**
 * @author greg
 * @version 2021/12/12 14:06
 */
public class Sleeper {
    public static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
