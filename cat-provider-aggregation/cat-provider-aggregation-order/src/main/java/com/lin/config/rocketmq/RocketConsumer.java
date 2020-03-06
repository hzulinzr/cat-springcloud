package com.lin.config.rocketmq;


import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.common.protocol.heartbeat.MessageModel;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.List;

/**
 * 消费者
 *
 * @author lzr
 * @date 2020-01-26 20:53:10
 */
@Component
@Slf4j
public class RocketConsumer implements MessageListenerConcurrently {

    private String namesrvAddr = "127.0.0.1:9876";

    private String consumerGroup = "cat-consumer";

    private final DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(consumerGroup);

    /**
     * 初始化
     */
    @PostConstruct
    public void start() {
        try {
            log.info("MQ: 启动消费者");
            consumer.setNamesrvAddr(namesrvAddr);
            //从消息队列头开始消费
            consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
            //集群消费模式
            consumer.setMessageModel(MessageModel.CLUSTERING);
            //订阅主题
            consumer.subscribe("cat", "*");
            //注册消息监听器
            consumer.registerMessageListener(this);
            //启动消费端
            consumer.start();
        } catch (MQClientException e) {
            log.error("MQ:启动消费者失败：{}-{}", e.getResponseCode(), e.getErrorMessage());
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    /**
     * 消费消息
     * @param msgs
     * @param context
     * @return
     */
    @Override
    public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
        int index = 0;
        try {
            for (; index < msgs.size(); index++) {
                MessageExt msg = msgs.get(index);

                String messageBody = new String(msg.getBody(), RemotingHelper.DEFAULT_CHARSET);
                log.info("MQ: 消费者接受新消息： {} {} {} {} {}", msg.getMsgId(), msg.getTopic(), msg.getTags(), msg.getKeys(), messageBody);
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }finally {
            if( index < msgs.size()){
                context.setAckIndex(index + 1);
            }
        }
        return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
    }

    @PreDestroy
    public void stop(){
        consumer.shutdown();
        log.info("MQ: 关闭消费者");
    }
}
