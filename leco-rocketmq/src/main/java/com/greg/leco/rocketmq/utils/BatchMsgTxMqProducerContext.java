package com.greg.leco.rocketmq.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.rocketmq.client.producer.TransactionMQProducer;
import org.apache.rocketmq.common.message.Message;

import java.util.List;

/**
 * @author greg
 * @version 2021/12/25 20:51
 */
@AllArgsConstructor
@Data
public class BatchMsgTxMqProducerContext<T> {
    private List<T> msgObjs;
    private List<Message> messages;
    private TransactionMQProducer producer;
}
