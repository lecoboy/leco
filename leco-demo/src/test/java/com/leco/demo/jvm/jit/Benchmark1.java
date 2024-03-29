package com.leco.demo.jvm.jit;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * 通过基准测试了解方法内联做的字段优化
 * @author greg
 * @version 2021/10/17 17:10
 */
@Warmup(iterations = 2, time = 1)
@Measurement(iterations = 5, time = 1)
@State(Scope.Benchmark)
public class Benchmark1 {

    int[] elements = randomInts(1_000);
    private static int[] randomInts(int size) {
        Random random = ThreadLocalRandom.current();
        int[] values = new int[size];
        for (int i = 0; i < size; i++) {
            values[i] = random.nextInt();
        }
        return values;
    }
    @Benchmark
    public void test1() {
        // 方法内联字段优化，会用一个局部变量存储成员字段
        for (int i = 0; i < elements.length; i++) {
            doSum(elements[i]);
        }
    }
    @Benchmark
    public void test2() {
        int[] local = this.elements;
        for (int i = 0; i < local.length; i++) {
            doSum(local[i]);
        }
    }
    @Benchmark
    public void test3() {
        for (int element : elements) {
            doSum(element);
        }
    }
    static int sum = 0;
    @CompilerControl(CompilerControl.Mode.DONT_INLINE)
    static void doSum(int x) {
        sum += x;
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(Benchmark1.class.getSimpleName())
                .forks(1)
                .build();
        new Runner(opt).run();
    }
}
