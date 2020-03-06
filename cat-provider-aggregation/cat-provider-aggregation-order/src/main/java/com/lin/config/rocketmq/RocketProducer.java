package com.lin.config.rocketmq;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * 生产者
 * @author lzr
 * @date 2020-02-11 22:29:00
 */
@Component
@Slf4j
public class RocketProducer {
    private String namesrvAddr = "127.0.0.1:9876";

    private String producerGroup = "cat-producer";

    private final DefaultMQProducer producer = new DefaultMQProducer(producerGroup);

    /**
     * 初始化
     */
    @PostConstruct
    public void start(){
        try {
            log.info("MQ: 启动生产者");
            producer.setNamesrvAddr(namesrvAddr);
            log.info(namesrvAddr);
            producer.start();
        } catch (MQClientException e) {
            log.error("MQ: 启动生产者失效：{}-{}", e.getResponseCode(), e.getErrorMessage());
            throw new RuntimeException(e.getErrorMessage(), e);
        }
    }

    /**
     * 发送消息
     * @param content 消息内容
     * @param topic 主题
     * @param tags 标签
     * @param keys 唯一主键
     */
    public void sendMessage(String content, String topic, String tags, String keys){
        try {
            byte[] messageBody = content.getBytes(RemotingHelper.DEFAULT_CHARSET);

            Message mqMsg = new Message(topic, tags, keys, messageBody);

            producer.send(mqMsg, new SendCallback() {
                @Override
                public void onSuccess(SendResult sendResult) {
                    log.info("MQ：生产者发送消息 {}",sendResult);
                }

                @Override
                public void onException(Throwable e) {
                    log.error(e.getMessage(), e);
                }
            });

        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    @PreDestroy
    public void stop(){
        producer.shutdown();
        log.info("MQ: 关闭生产者");
    }
}
