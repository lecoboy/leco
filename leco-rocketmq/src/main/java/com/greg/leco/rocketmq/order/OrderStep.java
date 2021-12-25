package com.greg.leco.rocketmq.order;

import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author greg
 * @version 2021/12/25 19:53
 */
public class OrderStep {

    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    @Data
    public static class Order {
        private Integer orderId;
        private String desc;
    }

    public static List<Order> buildOrders() {
        return Lists.newArrayList(
                Order.builder()
                        .orderId(10001)
                        .desc("创建")
                        .build(),
                Order.builder()
                        .orderId(10001)
                        .desc("付款")
                        .build(),
                Order.builder()
                        .orderId(10001)
                        .desc("推送")
                        .build(),
                Order.builder()
                        .orderId(10001)
                        .desc("完成")
                        .build(),
                Order.builder()
                        .orderId(10002)
                        .desc("创建")
                        .build(),
                Order.builder()
                        .orderId(10002)
                        .desc("付款")
                        .build(),
                Order.builder()
                        .orderId(10002)
                        .desc("推送")
                        .build(),
                Order.builder()
                        .orderId(10002)
                        .desc("完成")
                        .build(),
                Order.builder()
                        .orderId(10003)
                        .desc("创建")
                        .build(),
                Order.builder()
                        .orderId(10003)
                        .desc("付款")
                        .build(),
                Order.builder()
                        .orderId(10003)
                        .desc("推送")
                        .build(),
                Order.builder()
                        .orderId(10003)
                        .desc("完成")
                        .build()
        );
    }
}
