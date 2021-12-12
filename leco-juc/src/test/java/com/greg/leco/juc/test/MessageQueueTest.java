package com.greg.leco.juc.test;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.LinkedList;

/**
 * @author greg
 * @version 2021/11/21 17:46
 * 消息队列：生产者消费者模式
 */
@Slf4j
public class MessageQueueTest {
    public static void main(String[] args) {
        MessageQueue queue = new MessageQueue(2);
        // 3个生产者
        for (int i = 0; i < 3; i++) {
            int id = i;
            new Thread(() -> {
                queue.put(new Message(id, "消息" + id));
            }, "生产者" + i).start();
        }

        // 1个消费者，1s消费一次
        new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Message message = queue.take();
                log.debug("消费者收到消息={}，处理完成", message);
            }

        }, "消费者").start();

    }
}

@Slf4j
class MessageQueue {
    LinkedList<Message> list = new LinkedList<>();
    private int capacity;

    public MessageQueue(int capacity) {
        this.capacity = capacity;
    }

    public synchronized Message take() {
        while (list.isEmpty()) {
            try {
                log.debug("消息队列为空，消费者暂停消费");
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Message message = list.removeFirst();
        log.debug("消费者即将收到消息，消息为：{}", message);
        this.notifyAll();
        return message;
    }

    public synchronized void put(Message message) {
        while (list.size() == capacity) {
            try {
                log.debug("消息队列已满，生产者暂停生产");
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        list.addLast(message);
        log.debug("生产者生产成功，消息为：{}", message);
        this.notifyAll();
    }
}

@Getter
final class Message {
    private int id;
    private Object value;

    public Message(int id, Object value) {
        this.id = id;
        this.value = value;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", value=" + value +
                '}';
    }
}
