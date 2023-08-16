package com.leco.rocketmq.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;

/**
 * @author greg
 * @version 2021/12/25 20:51
 */
@AllArgsConstructor
@Data
public class ProducerContext<T> {
    private T msgObj;
    private Message message;
    private DefaultMQProducer producer;
}
