package com.leco.rocketmq.base;

import com.leco.rocketmq.utils.ProducerHelper;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;

/**
 * @author greg
 * @version 2021/12/25 16:51
 */
public class BaseProducerTest {
    private final static ProducerHelper producerHelper = new ProducerHelper("192.168.0.104:9876;192.168.0.102:9876");

    public static void main(String[] args) {
        sendSyncMsg();
    }

    /**
     * 发送单向消息
     */
    public static void sendOneWayMsg() {
        producerHelper.helloProducerTest("oneWayMsg", (msg, producer) -> {
            try {
                producer.sendOneway(msg);
                System.out.println("发送单向消息");
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * 发送异步消息
     */
    public static void sendAsyncMsg() {
        producerHelper.helloProducerTest("asyncMsg", (msg, producer) -> {
            try {
                producer.send(msg, new SendCallback() {
                    @Override
                    public void onSuccess(SendResult sendResult) {
                        System.out.println("发送结果: " + sendResult);
                    }

                    @Override
                    public void onException(Throwable e) {
                        e.printStackTrace();
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * 发送同步消息
     */
    public static void sendSyncMsg() {
        producerHelper.helloProducerTest("syncMsg", (msg, producer) -> {
            try {
                SendResult result = producer.send(msg);
                System.out.println("发送结果: " + result);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
