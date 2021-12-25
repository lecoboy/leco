package com.greg.leco.rocketmq.order;

import com.greg.leco.rocketmq.base.producer.BaseProducer;

import java.util.List;

/**
 * @author greg
 * @version 2021/12/25 20:04
 * 使用订单场景演示 顺序消息
 */
public class Producer {
    public static void main(String[] args) {
        // 组装消息
        List<OrderStep.Order> orders = OrderStep.buildOrders();
        // 发送顺序消息
        BaseProducer.toStringMsgProducerTest("OrderTopic", "Order", orders, context -> {
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
