package com.leco.juc.demo;

import com.leco.juc.utils.Sleeper;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author greg
 * @version 2021/12/13
 * 尝试自己实现一个简单的线程池
 */
@Slf4j
public class MyThreadPoolTest {

    public static void main(String[] args) {
        MyThreadPool myThreadPool = new MyThreadPool(2, 1);
        myThreadPool.execute(() -> {
            log.debug("1");
            Sleeper.sleep(2000);
        });
        myThreadPool.execute(() -> {
            log.debug("2");
            Sleeper.sleep(1000);
        });
        myThreadPool.execute(() -> {
            log.debug("3");
            Sleeper.sleep(500);
        });
        myThreadPool.execute(() -> {
            log.debug("4");
        });
    }

    static class MyThreadPool {
        private int coreSize;//核心线程数
        private int maxQueueSize;//队列最大长度
        private BlockingQueue<Runnable> blockingQueue;//阻塞队列
        private int[] states;//核心线程状态
        private Worker[] workers;//核心线程
        private static int poolCount;
        private int workerCount;

        MyThreadPool(int coreSize, int maxQueueSize) {
            this.coreSize = coreSize;
            this.maxQueueSize = maxQueueSize;
            blockingQueue = new BlockingQueue<>(maxQueueSize);
            states = new int[coreSize];
            workers = new Worker[coreSize];
            poolCount++;
        }

        public void execute(Runnable task) {
            for (int i = 0; i < states.length; i++) {
                if (states[i] == 0 && workers[i] == null) {
                    workers[i] = new Worker(task, blockingQueue, poolCount, ++workerCount);
                    workers[i].start();
                    states[i] = 1;
                    return;
                }
            }
            blockingQueue.put(task);
        }
    }

    static class Worker extends Thread {
        private Runnable task;
        private BlockingQueue<Runnable> blockingQueue;//阻塞队列

        Worker(Runnable task, BlockingQueue<Runnable> blockingQueue, int poolCount, int workerCount) {
            this.task = task;
            this.blockingQueue = blockingQueue;
            this.setName("pool-" + poolCount + "-thread-" + workerCount);
        }

        @Override
        public void run() {
            while (task != null || (task = blockingQueue.take()) != null) {
                task.run();
                task = null;
            }
        }
    }

    static class BlockingQueue<T> {
        private int size;
        private Deque<T> blockingQueue = new ArrayDeque<>(size);
        private ReentrantLock lock = new ReentrantLock();
        private Condition fullCondition = lock.newCondition();
        private Condition emptyCondition = lock.newCondition();

        BlockingQueue(int size) {
            this.size = size;
        }

        public T take() {
            lock.lock();

            try {
                while (blockingQueue.size() == 0) {
                    try {
                        emptyCondition.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                T task = blockingQueue.removeFirst();
                fullCondition.signal();
                return task;
            } finally {
                lock.unlock();
            }
        }

        public void put(T task) {
            lock.lock();

            try {
                while (blockingQueue.size() == size) {
                    try {
                        fullCondition.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                blockingQueue.addLast(task);
                emptyCondition.signal();
            } finally {
                lock.unlock();
            }
        }
    }
}
