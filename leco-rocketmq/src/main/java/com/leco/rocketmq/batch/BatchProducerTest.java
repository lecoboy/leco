package com.leco.rocketmq.batch;

import com.google.common.collect.Lists;
import com.leco.rocketmq.utils.ProducerHelper;
import org.apache.rocketmq.client.producer.SendResult;

/**
 * @author greg
 * @version 2021/12/26 16:33
 */
public class BatchProducerTest {
    private final static ProducerHelper producerHelper = new ProducerHelper("192.168.0.104:9876;192.168.0.102:9876");

    public static void main(String[] args) {
        producerHelper.batchStringMsgProducerTest("hello", "BatchMsgs", Lists.newArrayList(
                "Hello World1",
                "Hello World2",
                "Hello World3"
        ), (msgs, producer) -> {
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
