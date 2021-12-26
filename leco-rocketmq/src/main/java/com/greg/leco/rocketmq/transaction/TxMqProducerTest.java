package com.greg.leco.rocketmq.transaction;

import com.greg.leco.rocketmq.utils.MessageConstants;
import com.greg.leco.rocketmq.utils.ProducerHelper;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.LocalTransactionState;
import org.apache.rocketmq.client.producer.TransactionListener;
import org.apache.rocketmq.client.producer.TransactionMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.List;

/**
 * @author greg
 * @version 2021/12/26 22:30
 */
public class TxMqProducerTest {
    private final static ProducerHelper producerHelper = new ProducerHelper("192.168.0.104:9876;192.168.0.102:9876");

    public static void main(String[] args) {

        producerHelper.batchMsgTxMqProducerTest(MessageConstants.DEFAULT_GROUP, "TxMsgTopic", "txMsg",
                producerHelper.generateHelloWorldList(3), e -> e, context -> {
                    TransactionMQProducer producer = context.getProducer();
                    List<Message> messages = context.getMessages();
                    producer.setTransactionListener(new TransactionListener() {
                        @Override
                        public LocalTransactionState executeLocalTransaction(Message msg, Object arg) {
                            String body = new String(msg.getBody());
                            try {
                                if (body.contains("0")) {
                                    System.out.println("本地事务执行完毕，提交消息: " + body);
                                    return LocalTransactionState.COMMIT_MESSAGE;
                                } else if (body.contains("1")) {
                                    int i = 1 / 0;
                                    System.out.println(i);
                                    return LocalTransactionState.COMMIT_MESSAGE;
                                } else {
                                    System.out.println("发现未知消息: " + body + ", 等待检查");
                                    return LocalTransactionState.UNKNOW;
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                                System.out.println("发生异常，回滚消息: " + body);
                                return LocalTransactionState.ROLLBACK_MESSAGE;
                            }
                        }

                        @Override
                        public LocalTransactionState checkLocalTransaction(MessageExt msg) {
                            System.out.println("检查本地事务消息并回滚: " + new String(msg.getBody()));
                            return LocalTransactionState.ROLLBACK_MESSAGE;
                        }
                    });

                    try {
                        producer.start();
                    } catch (MQClientException e) {
                        e.printStackTrace();
                    }
                    messages.forEach(message -> {
                        try {
                            producer.sendMessageInTransaction(message, null);
                        } catch (MQClientException e) {
                            e.printStackTrace();
                        }
                    });
                });
    }
}
