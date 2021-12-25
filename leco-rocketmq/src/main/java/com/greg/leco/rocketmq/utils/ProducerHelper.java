package com.greg.leco.rocketmq.utils;

import com.google.common.collect.Lists;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * @author greg
 * @version 2021/12/25 23:15
 */
public class ProducerHelper {
    private final String namesrvAddr;
    private final static String DEFAULT_GROUP = "defaultGroup";

    public ProducerHelper(String namesrvAddr) {
        this.namesrvAddr = namesrvAddr;
    }

    public void helloProducerTest(String tag, BiConsumer<Message, DefaultMQProducer> producerHandler) {
        List<String> msgBodies = Lists.newArrayList();
        for(int i=0;i<10;i++) {
            msgBodies.add("Hello World" + i);
        }
        stringMsgProducerTest("hello", tag, msgBodies, producerHandler);
    }

    public void stringMsgProducerTest(String topic, String tag, List<String> msgs, BiConsumer<Message, DefaultMQProducer> producerHandler) {
        producerTest(topic, tag, msgs, e -> e, producerHandler);
    }

    /**
     * 内置toString消息转换器的生产者测试，即消息转换器中直接返回消息对象的toString方法
     * @param topic
     * @param tag
     * @param msgs
     * @param producerHandler
     * @param <T>
     */
    public <T> void toStringMsgProducerTest(String topic, String tag, List<T> msgs, BiConsumer<Message, DefaultMQProducer> producerHandler) {
        producerTest(topic, tag, msgs, T::toString, producerHandler);
    }

    public <T> void toStringMsgProducerTest(String topic, String tag, List<T> msgs, Consumer<ProducerContext<T>> producerHandler) {
        producerTest(topic, tag, msgs, T::toString, producerHandler);
    }

    /**
     * 生产者测试
     * @param topic 主题
     * @param tag 标签
     * @param msgs 消息对象列表
     * @param msgConverter 消息转换器(将消息对象转换成消息body)
     * @param producerHandler 生产者处理器(控制生产者如何发送消息)
     * @param <T> 消息对象的类型
     */
    public <T> void producerTest(String topic, String tag, List<T> msgs, Function<T,String> msgConverter, BiConsumer<Message, DefaultMQProducer> producerHandler) {
        producerTest(topic, tag, msgs, msgConverter, context -> producerHandler.accept(context.getMessage(), context.getProducer()));
    }

    public <T> void producerTest(String topic, String tag, List<T> msgs, Function<T,String> msgConverter, Consumer<ProducerContext<T>> producerHandler) {
        producerTest(DEFAULT_GROUP, topic, tag, msgs, msgConverter, producerHandler);
    }

    private <T> void producerTest(String group, String topic, String tag, List<T> msgs, Function<T, String> msgConverter, Consumer<ProducerContext<T>> producerHandler) {
        // 1.创建消息生产者producer，并制定生产者组名
        DefaultMQProducer producer = new DefaultMQProducer(group);
        // 2.指定Nameserver地址
        producer.setNamesrvAddr(namesrvAddr);
        // 3.启动producer
        try {
            producer.start();
        } catch (MQClientException e) {
            e.printStackTrace();
        }
        // 4.创建消息对象，指定主题Topic、Tag和消息体
        msgs.forEach(msg -> {
            String body = msgConverter.apply(msg);
            Message message = new Message(topic, tag, body.getBytes());
            producerHandler.accept(new ProducerContext<>(msg, message, producer));
        });
        // 6.关闭生产者producer
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        producer.shutdown();
    }
}
