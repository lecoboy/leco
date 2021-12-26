package com.greg.leco.rocketmq.delay;

import com.greg.leco.rocketmq.utils.ProducerHelper;
import org.apache.rocketmq.client.producer.SendResult;

/**
 * @author greg
 * @version 2021/12/26 15:29
 */
public class DelayProducerTest {
    private final static ProducerHelper producerHelper = new ProducerHelper("192.168.0.104:9876;192.168.0.102:9876");

    public static void main(String[] args) {
        producerHelper.helloProducerTest("DelayMsg", (msg, producer) -> {
            msg.setDelayTimeLevel(2);
            try {
                SendResult result = producer.send(msg);
                System.out.println("发送结果: " + result);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
