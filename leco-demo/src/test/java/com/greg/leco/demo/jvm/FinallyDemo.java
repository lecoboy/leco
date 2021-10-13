package com.greg.leco.demo.jvm;

import org.junit.Test;

/**
 * 看看 finally 生成的字节码, 并探索一下finally的用法
 *
 * 异常表会检测 catch 块中的代码，有异常(有两种情况，一是代码中有异常，二是主动抛出异常)则跳转到 finally 代码块，并尝试使用 athrow 将该异常抛出。
 * catch 块中无异常时，会直接执行 finally 块中的代码,不会使用 athrow 。
 * catch块中字节码后面一般会紧跟finally块中的字节码，但当catch块中通过throw语句显式的抛出异常时有个优化，
 * - 就是不会有这块字节码，只有try块字节码后面的finally块字节码和异常表跳转的finally字节码。
 *
 * @author greg
 * @version 2021/10/13 22:44
 */
public class FinallyDemo {

    /**
     * 解决finally块的异常会吞掉catch块的异常 的问题
     * @param args
     */
    public static void main(String[] args) {
        int i = 10;
        Exception v = null;
        try {
            i = 20;
            int[] a = new int[1];
            a[1] = 3;
            System.out.println("try");
        } catch (Exception e) {
            v = e;
            System.out.println("catch");
            // 如果finally中出了异常且没被捕获，这里抛出去的异常就会被吞掉
            throw e;
        } finally {
            // 为了上面抛出的异常不被吞掉，finally可以加一层try-catch;
            // 又因为上面没抛出异常时，这里有异常直接抛出就行了，没必要try-catch消耗性能，所以先判断v是不是空
            if (v != null) {
                try {
                    finallyHandle(i);
                } catch (Exception e) {
                    v.addSuppressed(e);
                }
            } else {
                finallyHandle(i);
            }

        }

        System.out.println("done");
    }

    public static void finallyHandle(int i) {
        i = 10 / 0;
        System.out.println("finally");
    }

    /**
     * 这个例子告诉我们两件事：
     * 1.一个异常被catch了，你可以主动选择是否抛出这个异常(catch后不处理，吞掉了try代码块中的异常)
     * 2.一个异常没被catch，它也有可能不被抛出(这个例子里catch中的异常就被finally的异常给吞掉了)
     */
    @Test
    public void testEatException() {
        try {
            int[] a = new int[1];
            a[2] = 3;
            System.out.println("try");
        } catch (Exception e) {
            // catch后不处理，吃掉了try代码块中的异常
            int i = 10 / 0;
            System.out.println("catch");
        } finally {
            // finally中的异常吃掉了catch中的异常
            Integer t = null;
            System.out.println(t.intValue());
            System.out.println("finally");
        }
    }

}
