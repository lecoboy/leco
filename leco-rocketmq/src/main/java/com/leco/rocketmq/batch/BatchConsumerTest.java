package com.leco.rocketmq.batch;

import com.leco.rocketmq.utils.ConsumerHelper;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;

/**
 * @author greg
 * @version 2021/12/26 16:39
 */
public class BatchConsumerTest {
    private final static ConsumerHelper consumerHelper = new ConsumerHelper("192.168.0.104:9876;192.168.0.102:9876");

    public static void main(String[] args) {
        consumerHelper.startHelloPushConsumer("BatchMsgs", consumer -> {
            consumer.registerMessageListener((MessageListenerConcurrently) (msgs, context) -> {
                msgs.forEach(msg -> System.out.println("收到消息: " + new String(msg.getBody())));
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            });
        });
        System.out.println("消费者启动");
    }
}
