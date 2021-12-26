package com.greg.leco.rocketmq.filter;

import com.greg.leco.rocketmq.utils.ConsumerHelper;
import org.apache.rocketmq.client.consumer.MessageSelector;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;

/**
 * @author greg
 * @version 2021/12/26 21:46
 */
public class SqlFilterConsumerTest {
    private final static ConsumerHelper consumerHelper = new ConsumerHelper("192.168.0.104:9876;192.168.0.102:9876");

    public static void main(String[] args) {
        consumerHelper.startHelloPushConsumer("SqlFilterMsg", consumer -> {
            try {
                // 如果要使用SQL过滤的方式，broker配置文件中要加上 enablePropertyFilter=true
                consumer.subscribe("hello", MessageSelector.bySql("i>2"));
            } catch (MQClientException e) {
                e.printStackTrace();
            }
            consumer.registerMessageListener((MessageListenerConcurrently) (msgs, context) -> {
                msgs.forEach(msg -> System.out.println("收到消息: " + new String(msg.getBody())));
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            });
        });
        System.out.println("消费者启动");
    }
}
