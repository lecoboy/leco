package com.leco.juc.utils;

/**
 * @author greg
 * @version 2021/12/14
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
