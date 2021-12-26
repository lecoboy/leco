package com.greg.leco.rocketmq.filter;

import com.greg.leco.rocketmq.utils.ProducerHelper;
import org.apache.rocketmq.client.producer.SendResult;

/**
 * @author greg
 * @version 2021/12/26 21:38
 */
public class SqlFilterProducerTest {
    private final static ProducerHelper producerHelper = new ProducerHelper("192.168.0.104:9876;192.168.0.102:9876");

    public static void main(String[] args) {
        producerHelper.batchHelloWordsProducerTest("SqlFilterMsg", (msgs, producer) -> {
            for (int i = 0; i < msgs.size(); i++) {
                msgs.get(i).putUserProperty("i", String.valueOf(i));
            }
            SendResult result = null;
            try {
                result = producer.send(msgs);
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("发送结果: " + result);
        });
    }
}
