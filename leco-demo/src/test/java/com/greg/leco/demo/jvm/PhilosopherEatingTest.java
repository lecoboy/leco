package com.greg.leco.demo.jvm;

import lombok.extern.slf4j.Slf4j;

/**
 * @author greg
 * @version 2021/12/3
 */
@Slf4j
public class PhilosopherEatingTest {
    public static void main(String[] args) {
        Chopstick chopstick1 = new Chopstick();
        Chopstick chopstick2 = new Chopstick();
        Chopstick chopstick3 = new Chopstick();
        Chopstick chopstick4 = new Chopstick();
        Chopstick chopstick5 = new Chopstick();
        Philosopher philosopher1 = new Philosopher("苏格拉底", chopstick1, chopstick2);
        Philosopher philosopher2 = new Philosopher("柏拉图", chopstick2, chopstick3);
        Philosopher philosopher3 = new Philosopher("奥古斯丁", chopstick3, chopstick4);
        Philosopher philosopher4 = new Philosopher("赫拉克利特", chopstick4, chopstick5);
        Philosopher philosopher5 = new Philosopher("普罗提诺", chopstick5, chopstick1);
        philosopher1.start();
        philosopher2.start();
        philosopher3.start();
        philosopher4.start();
        philosopher5.start();
    }
}

@Slf4j
class Philosopher extends Thread {
    private final Chopstick left;
    private final Chopstick right;

    Philosopher(String name, Chopstick left, Chopstick right) {
        this.setName(name);
        this.left = left;
        this.right = right;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (left) {
                synchronized (right) {
                    log.debug(this.getName() + " eating...");
                }
            }
        }
    }
}

class Chopstick {

}
