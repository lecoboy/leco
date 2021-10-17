package com.greg.leco.demo.jvm.jmm;

/**
 * 可见性
 * @author greg
 * @version 2021/10/17 23:38
 */
public class JMMDemo2 {

    static boolean run = true;
    static long i = 0;

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            // run的值true从工作内存中的高速缓存中取，不知道主存中的run值已改变
            while (run) {
                // println中用了synchronize，强制从主存中取值
//                System.out.println(++i);
            }
            System.out.println("t1 done");

        });
        t1.start();

        Thread.sleep(1000);
        run = false;
        System.out.println("main done");
    }
}
