package com.lin.config.rocketmq;

import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;

/**
 * @author lzr
 * @date 2020-01-26 20:53:10
 */
public class RocketConsumer {
    public void send(String topic, String group, String message) throws MQClientException {
        DefaultMQProducer producer = new DefaultMQProducer("cat-book");
        producer.start();
    }
}
