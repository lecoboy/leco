package com.leco.rocketmq.transaction;

import com.leco.rocketmq.utils.ConsumerHelper;

/**
 * @author greg
 * @version 2021/12/26 22:51
 */
public class TxMqConsumerTest {
    private final static ConsumerHelper consumerHelper = new ConsumerHelper("192.168.0.104:9876;192.168.0.102:9876");

    public static void main(String[] args) {
        consumerHelper.startConPushConsumer("TxMsgTopic", "txMsg", msgs ->
            msgs.forEach(msg -> System.out.println("收到消息: " + new String(msg.getBody())))
        );
        System.out.println("消费者启动");
    }
}
