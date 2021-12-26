package com.greg.leco.rocketmq.utils;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.List;
import java.util.function.Consumer;

/**
 * @author greg
 * @version 2021/12/26 15:32
 */
public class ConsumerHelper {
    private final String namesrvAddr;
    private final static String DEFAULT_GROUP = "defaultGroup";

    public ConsumerHelper(String namesrvAddr) {
        this.namesrvAddr = namesrvAddr;
    }

    public void startHelloPushConsumer(String tag, Consumer<DefaultMQPushConsumer> consumerHandler) {
        startPushConsumer("hello", tag, consumerHandler);
    }

    public void startPushConsumer(String topic, String tag, Consumer<DefaultMQPushConsumer> consumerHandler) {
        // 1.创建消费者Consumer，制定消费者组名
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(DEFAULT_GROUP);
        // 2.指定Nameserver地址
        consumer.setNamesrvAddr(namesrvAddr);
        // 3.订阅主题Topic和Tag
        try {
            consumer.subscribe(topic, tag);
            // 设置消费模式：负载均衡(默认)|广播模式
//            consumer.setMessageModel(MessageModel.BROADCASTING);
            // 4.设置回调函数，处理消息
            consumerHandler.accept(consumer);
            // 5.启动消费者consumer
            consumer.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void startConPushConsumer(String topic, String tag, Consumer<List<MessageExt>> listenerHandler) {
        // 1.创建消费者Consumer，制定消费者组名
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(DEFAULT_GROUP);
        // 2.指定Nameserver地址
        consumer.setNamesrvAddr(namesrvAddr);
        // 3.订阅主题Topic和Tag
        try {
            consumer.subscribe(topic, tag);
            // 设置消费模式：负载均衡(默认)|广播模式
//            consumer.setMessageModel(MessageModel.BROADCASTING);
            // 4.设置回调函数，处理消息
            consumer.registerMessageListener((MessageListenerConcurrently) (msgs, context) -> {
                listenerHandler.accept(msgs);
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            });
            // 5.启动消费者consumer
            consumer.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
