package com.leco.rocketmq.base;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.protocol.heartbeat.MessageModel;

/**
 * @author greg
 * @version 2021/12/25 19:04
 */
public class BaseConsumerTest {
    public static void main(String[] args) throws Exception {
        // 1.创建消费者Consumer，制定消费者组名
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("defaultGroup");
        // 2.指定Nameserver地址
        consumer.setNamesrvAddr("192.168.0.104:9876;192.168.0.102:9876");
        // 3.订阅主题Topic和Tag
        consumer.subscribe("hello", "syncMsg");
        // 设置消费模式：负载均衡(默认)|广播模式
        consumer.setMessageModel(MessageModel.BROADCASTING);
        // 4.设置回调函数，处理消息
        consumer.registerMessageListener((MessageListenerConcurrently) (msgs, context) -> {
                    msgs.forEach(msg -> System.out.println(new String(msg.getBody())));
                    return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
                }
        );
        // 5.启动消费者consumer
        consumer.start();
    }
}
