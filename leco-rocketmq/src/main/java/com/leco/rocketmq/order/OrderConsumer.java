package com.leco.rocketmq.order;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerOrderly;

/**
 * @author greg
 * @version 2021/12/26 14:31
 */
public class OrderConsumer {
    public static void main(String[] args) throws Exception {
        // 1.创建消费者Consumer，制定消费者组名
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("defaultGroup");
        // 2.指定Nameserver地址
        consumer.setNamesrvAddr("192.168.0.104:9876;192.168.0.102:9876");
        // 3.订阅主题Topic和Tag
        consumer.subscribe("OrderTopic", "Order");
        // 设置消费模式：负载均衡(默认)|广播模式
//        consumer.setMessageModel(MessageModel.BROADCASTING);
        // 4.设置回调函数，处理消息
        consumer.registerMessageListener((MessageListenerOrderly) (msgs, context) -> {
                    msgs.forEach(msg -> System.out.println(Thread.currentThread().getName() + "----" + new String(msg.getBody())));
                    return ConsumeOrderlyStatus.SUCCESS;
                }
        );
        // 5.启动消费者consumer
        consumer.start();
        System.out.println("消费者启动");
    }
}
