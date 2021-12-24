package com.greg.leco.juc.test;

import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author greg
 * @version 2021/11/21 17:00
 * 保护性暂停应用：居民楼下的信箱
 * 5个居民对应5个信箱，5个邮递员分别给5个信箱送信
 */
public class MailboxesTest {
    public static void main(String[] args) {
        for (int i=0;i<5;i++) {
            new People().start();
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (int id : MailBoxes.getIds()) {
            new Postman(id, "内容"+id).start();
        }
    }
}

@Slf4j
class People extends Thread {
    @Override
    public void run() {
        GuardedObject guardedObject = MailBoxes.createGuardedObject();
        log.debug("收信开始，id={}", guardedObject.getId());
        Object mail = guardedObject.get(5000);
        log.debug("收信结束，id={}, 内容={}", guardedObject.getId(), mail);
    }
}

@Slf4j
class Postman extends Thread {
    private int id;
    private String mail;

    public Postman(int id, String mail) {
        this.id = id;
        this.mail = mail;
    }


    @Override
    public void run() {
        GuardedObject guardedObject = MailBoxes.getGuardedObject(this.id);
        log.debug("送信，id={},内容={}", this.id, this.mail);
        guardedObject.complete(this.mail);
    }
}

class MailBoxes {
    private static Map<Integer, GuardedObject> boxes = new ConcurrentHashMap<>();
    private static int id = 1;

    private synchronized static int generateId() {
        return id++;
    }

    public static GuardedObject createGuardedObject() {
        GuardedObject guardedObject = new GuardedObject(generateId());
        boxes.put(guardedObject.getId(), guardedObject);
        return guardedObject;
    }

    public static GuardedObject getGuardedObject(int id) {
        return boxes.remove(id);
    }

    public static Set<Integer> getIds() {
        return boxes.keySet();
    }
}

class GuardedObject {
    private int id;
    private Object response;

    public GuardedObject(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

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

