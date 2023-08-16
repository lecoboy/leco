package com.leco.demo.jvm.jit;

/**
 * 逃逸分析
 * -XX:-DoEscapeAnalysis
 *
 * @author greg
 * @version 2021/10/17 16:45
 */
public class JITDemo {

    public static void main(String[] args) {
        for (int i = 0; i < 200; i++) {
            long start = System.nanoTime();
            for (int j = 0; j < 1000; j++) {
                new Object();
            }
            long end = System.nanoTime();
            System.out.printf("%d\t%d\n", i, (end - start));
        }
    }
}
