package com.leco.juc.demo;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author greg
 * @version 2021/10/22
 */
@Slf4j
public class FutureTaskDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<Integer> task = new FutureTask<>(() -> {
            log.debug("run");
            Thread.sleep(1000);
            return 200;
        });
        Thread t1 = new Thread(task, "t1");
        t1.start();
        log.debug("{}", task.get());
    }

}