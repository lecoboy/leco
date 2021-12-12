package com.greg.leco.juc.test;

import lombok.extern.slf4j.Slf4j;

/**
 * @author greg
 * @version 2021/11/20 21:35
 * 保护性暂停
 */
@Slf4j
public class GuardedObjectTest {
    public static void main(String[] args) {
        GuardedObject guardedObject = new GuardedObject();
        new Thread(() -> {
            log.debug("begin");
            Object response = guardedObject.get(2000);
            log.debug("response: " + response);
        }, "t1").start();

        new Thread(() -> {
            log.debug("begin");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            guardedObject.complete(new Object());
        }, "t2").start();
    }

    static class GuardedObject {
        private Object response;

        public synchronized Object get(long timeout) {
            long start = System.currentTimeMillis();
            long passedTime = 0;
            while (response == null) {
                long waitTime = timeout - passedTime;
                if (waitTime <= 0) {
                    break;
                }
                try {
                    this.wait(waitTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                passedTime = System.currentTimeMillis() - start;
            }
            return response;
        }

        public synchronized void complete(Object response) {
            this.response = response;
            this.notifyAll();
        }
    }
}
