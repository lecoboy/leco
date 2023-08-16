package com.leco.demo.jvm.jit;

/**
 * 方法内联
 * 打印内联信息：
 * -XX:+UnlockDiagnosticVMOptions -XX:+PrintInlining
 * 禁用内联:
 * -XX:CompileCommand=dontinline,*JITDemo2.square
 *
 * @author greg
 * @version 2021/10/17 16:55
 */
public class JITDemo2 {

    public static void main(String[] args) {
        int x = 0;
        for (int i = 0; i < 500; i++) {
            long start = System.nanoTime();
            for (int j = 0; j < 1000; j++) {
                x = square(i);
            }
            long end = System.nanoTime();
            System.out.printf("%d\t%d\t%d\n", i, x, end - start);
        }
    }

    private static int square(final int i) {
        return i * i;
    }
}
