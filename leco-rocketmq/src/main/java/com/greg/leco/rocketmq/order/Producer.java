package com.greg.leco.rocketmq.order;

import com.greg.leco.rocketmq.utils.ProducerHelper;

import java.util.List;

/**
 * @author greg
 * @version 2021/12/25 20:04
 * 使用订单场景演示 顺序消息
 */
public class Producer {
    private final static ProducerHelper producerHelper = new ProducerHelper("192.168.0.104:9876;192.168.0.102:9876");

    public static void main(String[] args) {
        // 组装消息
        List<OrderStep.Order> orders = OrderStep.buildOrders();
        // 发送顺序消息
        producerHelper.toStringMsgProducerTest("OrderTopic", "Order", orders, context -> {
            try {
                context.getProducer().send(
                        // 发送的消息
                        context.getMessage(),
                        (mqs, msg, arg) ->
                                // 队列选择器：发送到哪个队列
                                mqs.get((Integer) arg % mqs.size()),
                        // 将什么参数交给队列选择器
                        context.getMsgObj().getOrderId());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
